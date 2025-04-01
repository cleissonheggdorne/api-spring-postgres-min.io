package br.gov.mt.seplag.seletivo.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl {
  private JwtService jwtService;
  private JwtDecoder jwtDecoder;

  public AuthenticationServiceImpl(JwtService jwtService, JwtDecoder jwtDecoder) {
    this.jwtService = jwtService;
    this.jwtDecoder = jwtDecoder;
  }

  public String authenticate(Authentication authentication) {
    return jwtService.generateToken(authentication);
  }

  public ResponseEntity<?> refreshToken(String authHeader) {
    try {
          if (authHeader != null && authHeader.startsWith("Bearer ")) {
              String token = authHeader.substring(7);
              var jwt = jwtDecoder.decode(token);
              
              String username = jwt.getSubject();
              String scope = (String) jwt.getClaims().get("scope");
              
              String newToken = jwtService.refreshToken(username, scope);
              
              return ResponseEntity.ok(newToken);
          }
          return ResponseEntity.badRequest().body("Token inválido");
        } catch (JwtException e) {
            return ResponseEntity.badRequest().body("Token expirado ou inválido");
        }
  }
}
