package br.com.loto.service.impl;

import br.com.loto.api.dto.requests.LoginRequest;
import br.com.loto.api.dto.responses.LoginResponse;
import br.com.loto.config.security.JwtUtil;
import br.com.loto.domain.entity.Account;
import br.com.loto.service.IAccountAuthService;
import br.com.loto.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountAuthServiceImpl implements IAccountAuthService {

    private final IAccountService accountService;

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails user = (UserDetails) authentication.getPrincipal();
        Account account = accountService.findByUsernameWithThrow(user.getUsername());
        String token = jwtUtil.generateTokenUsername(account);

        return LoginResponse.builder()
                .token("Bearer ".concat(token))
                .permissions(user.getAuthorities().stream()
                        .map(a -> a.getAuthority())
                        .toList())
                .build();
    }
}
