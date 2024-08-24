package com.application.inventApp.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
  @Value("${security.jwt.key.private}")
  private String privateKey;
  @Value(("${security.jwt.user.generator}"))
  private String userGenetator;

  public String createToken(Authentication authentication) {
    Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
    String username = authentication.getPrincipal().toString();
    String roles = authentication.getAuthorities().stream()
        .map(grantedAuthority -> grantedAuthority.getAuthority())
        .collect(Collectors.joining(","));
    String jwtToken = JWT.create()
        .withIssuer(this.userGenetator)
        .withSubject(username)
        .withClaim("roles", roles)
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis() + 1800000))
        .withJWTId(UUID.randomUUID().toString())
        .withNotBefore(new Date(System.currentTimeMillis()))
        .sign(algorithm);

    return jwtToken;
  }

  public DecodedJWT validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
      JWTVerifier verifier = JWT.require(algorithm)
          .withIssuer(this.userGenetator)
          .build();
      DecodedJWT decodedJWT = verifier.verify(token);
      return decodedJWT;

    } catch (JWTVerificationException e) {
      throw new JWTVerificationException("Token invalido");
    }
  }

  public String extractUsername(DecodedJWT decodedJWT){
    return decodedJWT.getSubject().toString();
  }

  public Claim getClaim(DecodedJWT decodedJWT, String  claimName){
    return decodedJWT.getClaim(claimName);
  }

  public Map<String, Claim> getAllClaims(DecodedJWT decodedJWT){
    return decodedJWT.getClaims();
  }


}
