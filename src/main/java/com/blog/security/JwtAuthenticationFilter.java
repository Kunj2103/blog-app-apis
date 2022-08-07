package com.blog.security;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//get token
		
	String requestToken = request.getHeader("Authorization");
	
	// Bearer 4546743424546....
	
	System.out.println(requestToken);
	
	String username = null;
	String token = null;
	
	if(requestToken != null && requestToken.startsWith("Bearer")) {
		token = requestToken.substring(7);
		
		try {
			username = this.jwtTokenHelper.getUsernameFromToken(token);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}else {
		System.out.println("jwt token does not begin with Bearer");
	}
	
	if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) 
	{

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
		if(this.jwtTokenHelper.validateToken(token, userDetails)) {
			
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
		else {
			System.out.println("Invalid jwt token");
		}
	}else {
		System.out.println("username is null or context is not null");
	}
	
	filterChain.doFilter(request, response);
		
	}

}
