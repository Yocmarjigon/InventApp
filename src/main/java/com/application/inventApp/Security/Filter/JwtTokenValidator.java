package com.application.inventApp.Security.Filter;

import com.application.inventApp.Enums.Severity;
import com.application.inventApp.Exception.ExceptionDetails;
import com.application.inventApp.Utils.JwtUtils;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
/* dddd */
  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException, JWTVerificationException, IOException {

    String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (jwtToken != null){
      try{
        jwtToken = jwtToken.substring(7);
        DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);

        String username = jwtUtils.extractUsername(decodedJWT);
        String roles = jwtUtils.getClaim(decodedJWT, "roles").asString();

        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
      }catch (JWTVerificationException e){
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().print(mapper.writeValueAsString(new ExceptionDetails("Autenticación fallida", Severity.ERROR)));
        response.getWriter().flush();

      }

    }
      filterChain.doFilter(request, response);
  }
}
