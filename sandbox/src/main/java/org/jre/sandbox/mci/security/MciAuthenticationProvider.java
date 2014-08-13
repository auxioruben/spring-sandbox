package org.jre.sandbox.mci.security;


import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class MciAuthenticationProvider implements AuthenticationProvider{

	private String secretpassword = "supersecret";
	
	@Override
	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {
	        String name = auth.getName();
	        String password = auth.getCredentials().toString();
	        if (password.equals(secretpassword)) {
	            List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
	            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
	            if(name.equals("admin")) 
	            		grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	            return new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
	        } else {
	            throw new BadCredentialsException("You do not know the secret password");
	        }
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}

}
