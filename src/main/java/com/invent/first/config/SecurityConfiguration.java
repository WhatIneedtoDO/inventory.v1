package com.invent.first.config;

import com.invent.first.Entity.Enum.Permission;
import com.invent.first.Entity.Enum.Role;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpHeaders;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.naming.directory.SearchControls;
import java.util.List;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final UserDetailsService userDetailsService;
  private final LogoutHandler logoutHandler;

  @Autowired
  private LdapTemplate ldapTemplate;
  @Autowired
  private Dotenv dotenv;

  private static final Logger logger = LoggerFactory.getLogger(EnvConfig.class);

  @Bean
  public WebMvcConfigurer CorsConfig(){
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET","POST","HEAD","PUT","DELETE","OPTIONS");
      }
    };
  }
  @Bean
  public CorsFilter corsFilter(){
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOriginPattern("*");
    config.addAllowedHeader("*");
    config.addExposedHeader(HttpHeaders.AUTHORIZATION);
    config.addAllowedMethod("*");
    source.registerCorsConfiguration("/**",config);
    return new CorsFilter(source);
  }
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .cors()
            .and()
            .csrf()
            .disable()
            .authorizeHttpRequests()
            .requestMatchers(
                    "/api/v1/auth/**",
                    "/",
                    "/static/**",
                    "/static/public/scripts/**",
                    "/v2/api-docs",
                    "/v3/api-docs",
                    "/v3/api-docs/**",
                    "/swagger-resources",
                    "/swagger-resources/**",
                    "/configuration/ui",
                    "/configuration/security",
                    "/swagger-ui/**",
                    "/webjars/**",
                    "/swagger-ui.html"
            )
            .permitAll()


            .requestMatchers("/api/v1/management/**").hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name())


            .requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(Permission.ADMIN_READ.name(), Permission.MANAGER_READ.name())
            .requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(Permission.ADMIN_CREATE.name(), Permission.MANAGER_CREATE.name())
            .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(Permission.ADMIN_UPDATE.name(), Permission.MANAGER_UPDATE.name())
            .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(Permission.ADMIN_DELETE.name(), Permission.MANAGER_DELETE.name())


            /* .requestMatchers("/api/v1/admin/**").hasRole(ADMIN.name())

             .requestMatchers(GET, "/api/v1/admin/**").hasAuthority(ADMIN_READ.name())
             .requestMatchers(POST, "/api/v1/admin/**").hasAuthority(ADMIN_CREATE.name())
             .requestMatchers(PUT, "/api/v1/admin/**").hasAuthority(ADMIN_UPDATE.name())
             .requestMatchers(DELETE, "/api/v1/admin/**").hasAuthority(ADMIN_DELETE.name())*/


            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .logout()
            .logoutUrl("/api/v1/auth/logout")
            .addLogoutHandler(logoutHandler)
            .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
    ;

    return http.build();
  }
  @Autowired
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
            .userDetailsService(userDetailsService)
            .and()
            .ldapAuthentication()
            .userSearchBase(dotenv.get("LDAP_USER_SEARCH_BASE"))
            .userSearchFilter("(sAMAccountName={0})")
            .groupSearchBase(dotenv.get("LDAP_GROUP_SEARCH_BASE"))
            .contextSource((BaseLdapPathContextSource) ldapTemplate.getContextSource());
  }

  @EventListener
  public void logLdapUsers(ContextRefreshedEvent event) {
    try {
      logger.debug("Searching for LDAP users with base DN: {}", dotenv.get("LDAP_BASE"));
      SearchControls searchControls = new SearchControls();
      searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
      searchControls.setReturningObjFlag(true); // Установка флага для ContextMapper
      // Create SearchControls with custom time limit
      searchControls.setTimeLimit(120000); // 30 seconds

      List<String> users = ldapTemplate.search(
              dotenv.get("LDAP_BASE"),                   // Base DN
              "(objectClass=person)",                    // Фильтр                   // Фильтр
              SearchControls.SUBTREE_SCOPE,              // Область поиска
              new String[]{"sAMAccountName"},                       // Атрибуты для получения
              (AttributesMapper<String>) attributes -> attributes.get("sAMAccountName").get().toString() // Маппер
      );
      users.forEach(user -> logger.info("Found LDAP user: {}", user));
    } catch (Exception e) {
      logger.error("Error searching for LDAP users", e + "\n EventContext : " + event.getApplicationContext() + "\n ContextSource : " + ldapTemplate.getContextSource() + "\n LdapTemplate : " + ldapTemplate);
    }
  }
}