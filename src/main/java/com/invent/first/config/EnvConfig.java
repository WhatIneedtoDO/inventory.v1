package com.invent.first.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;

@Configuration
@EnableConfigurationProperties
public class EnvConfig {

    @Autowired
    private Dotenv dotenv;
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
        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(contextSource());
    }

    @Bean
    public ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
        return new ActiveDirectoryLdapAuthenticationProvider(dotenv.get("AD_DOMAIN"), dotenv.get("AD_URL"));
    }
}
