package com.application.inventApp.Security.Filter;

import com.application.inventApp.Utils.JwtUtils;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@AllArgsConstructor
public class JwtTokenValidator extends OncePerRequestFilter {

  private JwtUtils jwtUtils;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException, JWTVerificationException {
    String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (jwtToken != null){
      jwtToken = jwtToken.substring(7);
      System.out.println(jwtToken);
      DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);

      String username = jwtUtils.extractUsername(decodedJWT);
      String roles = jwtUtils.getClaim(decodedJWT, "roles").asString();
      
      Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
      SecurityContext context = SecurityContextHolder.createEmptyContext();
      Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

      context.setAuthentication(authentication);
      SecurityContextHolder.setContext(context);
    }
      filterChain.doFilter(request, response);
  }
}
