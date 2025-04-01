package br.gov.mt.seplag.seletivo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.mt.seplag.seletivo.service.impl.AuthenticationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api")
public class AuthenticateController{
    private final AuthenticationServiceImpl authenticationService;
    public AuthenticateController(
        AuthenticationServiceImpl authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping("/public/authenticate")
    @Operation(summary = "Rota de autenticação. Autentica o usuário via autenticação básica e retorna um token JWT.")
    public ResponseEntity<?> authenticate(
        Authentication authentication) {
      String token = authenticationService.authenticate(authentication);      
      return ResponseEntity.ok().body(token);
    }

    @PostMapping("/refresh-token")
    @Operation(summary = "Rota de atualização do token. Retorna um novo token JWT a partir de um token válido.")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String authHeader) {
        return authenticationService.refreshToken(authHeader);
    }
}
