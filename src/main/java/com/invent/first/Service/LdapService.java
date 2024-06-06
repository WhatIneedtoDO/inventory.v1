package com.invent.first.Service;

import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

@Service
public class LdapService {
    private final LdapTemplate ldapTemplate;

    public LdapService(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public boolean authenticate(String username, String password) {
        // Add LDAP authentication logic here
        return ldapTemplate.authenticate("", "(uid=" + username + ")", password);
    }
}
