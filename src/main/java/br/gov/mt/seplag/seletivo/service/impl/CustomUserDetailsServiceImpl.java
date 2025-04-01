package br.gov.mt.seplag.seletivo.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.gov.mt.seplag.seletivo.config.UserAuthenticated;
import br.gov.mt.seplag.seletivo.repository.UserRepository;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    
    public CustomUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String document) throws UsernameNotFoundException {
       return userRepository.findByDocument(document)
            .map(UserAuthenticated::new)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
