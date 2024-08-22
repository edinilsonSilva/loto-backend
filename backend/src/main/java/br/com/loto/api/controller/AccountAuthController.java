package br.com.loto.api.controller;

import br.com.loto.api.dto.LoginRequest;
import br.com.loto.config.security.JwtUtil;
import br.com.loto.domain.entity.Account;
import br.com.loto.domain.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountAuthController {

    private final AccountRepository accountRepository;

    private final JwtUtil jwtUtil;
    final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails user = (UserDetails) authentication.getPrincipal();

        Account account = accountRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Conta não encontrada"));

        String token = jwtUtil.gerarTokenUsername(account);
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("permissoes", user.getAuthorities());
        return ResponseEntity.ok(map);
    }

}