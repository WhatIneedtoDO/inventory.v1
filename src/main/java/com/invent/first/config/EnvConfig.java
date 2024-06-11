package com.invent.first.config;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;

import javax.naming.directory.SearchControls;
import java.util.List;

@Configuration
@EnableConfigurationProperties
public class EnvConfig {

    @Autowired
    private Dotenv dotenv;

    private static final Logger logger = LoggerFactory.getLogger(EnvConfig.class);

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        DataSourceProperties properties = new DataSourceProperties();
        properties.setUsername(dotenv.get("datasource.username"));
        properties.setPassword(dotenv.get("datasource.password"));
        properties.setUrl(dotenv.get("datasource.url"));
        return properties;
    }

    @Bean
    public LdapContextSource contextSource() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl(dotenv.get("LDAP_URL"));
        contextSource.setBase(dotenv.get("LDAP_BASE"));
        contextSource.setUserDn(dotenv.get("LDAP_USER"));
        contextSource.setPassword(dotenv.get("LDAP_PASSWORD"));
        contextSource.setReferral("follow");
        contextSource.setPooled(true);
        contextSource.afterPropertiesSet();  // Обязательно вызовите этот метод
        logger.info("LDAP context source initialized successfully " + contextSource.getBaseLdapName() + " User: " + contextSource.getUserDn() + " Password: " + contextSource.getPassword());
        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate() {
        LdapTemplate ldapTemplate = new LdapTemplate(contextSource());
        ldapTemplate.setIgnorePartialResultException(true); // Игнорировать частичные результаты
        return ldapTemplate;
    }

    @Bean
    public ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
        logger.debug("Initializing Active Directory LDAP Authentication Provider with domain: {} and URL: {}", dotenv.get("AD_DOMAIN"), dotenv.get("AD_URL"));
        return new ActiveDirectoryLdapAuthenticationProvider(dotenv.get("AD_DOMAIN"), dotenv.get("AD_URL"));
    }


}
