package br.gov.mt.seplag.seletivo.service.impl;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
  private static final ZoneId ZONE_ID = ZoneId.systemDefault();

  @Value("${jwt.token_expiration_minutes}")
  private long TOKEN_EXPIRATION_MINUTES;
  private final JwtEncoder encoder;

  public JwtService(JwtEncoder encoder) {
    this.encoder = encoder;
  }

  public String generateToken(Authentication authentication) {
    Instant now = Instant.now();
    Instant expiresAt = now.plus(TOKEN_EXPIRATION_MINUTES, ChronoUnit.MINUTES);
    
    ZonedDateTime localExpires = ZonedDateTime.ofInstant(expiresAt, ZONE_ID);

    String scope = authentication
        .getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors
            .joining(" "));

    JwtClaimsSet claims = JwtClaimsSet.builder()
        .issuer("spring-security-jwt")
        .issuedAt(now)
        .expiresAt(localExpires.toInstant())
        .subject(authentication.getName())
        .claim("scope", scope)
        .build();

    return encoder.encode(
        JwtEncoderParameters.from(claims))
        .getTokenValue();
  }

  public String refreshToken(String username, String scope) {
      Instant now = Instant.now();
      Instant expiresAt = now.plus(TOKEN_EXPIRATION_MINUTES, ChronoUnit.MINUTES);
      
      ZonedDateTime localExpires = ZonedDateTime.ofInstant(expiresAt, ZONE_ID);
    
      JwtClaimsSet claims = JwtClaimsSet.builder()
              .issuer("self")
              .issuedAt(now)
              .expiresAt(localExpires.toInstant())
              .subject(username)
              .claim("scope", scope)
              .build();

      return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }

}
