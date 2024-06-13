package com.invent.first.Service.Impl;

import com.invent.first.Service.LdapService;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

@Service
public class LdapServiceImpl implements LdapService {
    private final LdapTemplate ldapTemplate;

    public LdapServiceImpl(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public boolean authenticate(String username, String password) {
        // Add LDAP authentication logic here
        return ldapTemplate.authenticate("", "(sAMAccountName=" + username + ")", password);
    }
    @Override
    public LdapUser getLdapUserDetails(String username) {
        return ldapTemplate.search(
                LdapQueryBuilder.query().where("sAMAccountName").is(username),
                new AttributesMapper<LdapUser>() {
                    @Override
                    public LdapUser mapFromAttributes(Attributes attrs) throws NamingException {
                        String givenName = (String) attrs.get("givenName").get();
                        String sn = (String) attrs.get("sn").get();
                        return new LdapUser(givenName, sn);
                    }
                }
        ).stream().findFirst().orElse(null);
    }
    public static class LdapUser {
        private final String givenName;
        private final String sn;

        public LdapUser(String givenName, String sn) {
            this.givenName = givenName;
            this.sn = sn;
        }

        public String getGivenName() {
            return givenName;
        }

        public String getSn() {
            return sn;
        }
    }

}
