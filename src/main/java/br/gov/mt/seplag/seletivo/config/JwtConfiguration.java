// package br.gov.mt.seplag.seletivo.config;

// import java.security.interfaces.RSAPrivateKey;
// import java.security.interfaces.RSAPublicKey;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import com.nimbusds.jose.jwk.JWK;
// import com.nimbusds.jose.jwk.JWKSet;
// import com.nimbusds.jose.jwk.RSAKey;
// import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
// import com.nimbusds.jose.jwk.source.JWKSource;
// import com.nimbusds.jose.proc.SecurityContext;

// import org.springframework.security.oauth2.jwt.JwtDecoder;
// import org.springframework.security.oauth2.jwt.JwtEncoder;

// import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
// import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

// @Configuration
// public class JwtConfiguration {
//     @Value("${jwt.public.key}")
//     private RSAPublicKey key;
//     @Value("${jwt.private.key}")
//     private RSAPrivateKey priv;
//     @Bean
//     JwtEncoder jwtEncoder() {
//         JWK jwk = new RSAKey.Builder(this.key).privateKey(this.priv).build();
//         JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
//         return new NimbusJwtEncoder(jwks);
//     }

//     @Bean
//     JwtDecoder jwtDecoder() {
//         return NimbusJwtDecoder.withPublicKey(this.key).build();
//     }
// }
