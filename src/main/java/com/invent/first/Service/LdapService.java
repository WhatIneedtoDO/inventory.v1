package com.invent.first.Service;

import com.invent.first.Service.Impl.LdapServiceImpl;
import org.springframework.stereotype.Service;

@Service
public interface LdapService {
    LdapServiceImpl.LdapUser getLdapUserDetails(String username);
}
