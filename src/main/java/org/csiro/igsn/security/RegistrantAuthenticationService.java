package org.csiro.igsn.security;

import org.apache.log4j.Logger;
import org.csiro.igsn.entity.postgres.Registrant;
import org.csiro.igsn.entity.service.RegistrantEntityService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;

public class RegistrantAuthenticationService implements UserDetailsService {

    final Logger log = Logger.getLogger( RegistrantAuthenticationService.class);
    private RegistrantEntityService registrantEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            log.info("Registrant UserName" + username);
            this.registrantEntityService = new RegistrantEntityService(null, null);
            Registrant r = registrantEntityService.searchRegistrant(username);
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_REGISTRANT");
            UserDetails userDetails = new User(r.getUsername(),
                    r.getPassword(), Arrays.asList(authority));
            return userDetails;

    }
}