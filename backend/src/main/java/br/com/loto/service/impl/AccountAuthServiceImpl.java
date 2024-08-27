package br.com.loto.service.impl;

import br.com.loto.api.dto.requests.LoginRequest;
import br.com.loto.api.dto.responses.LoginResponse;
import br.com.loto.config.security.JwtUtil;
import br.com.loto.service.IAccountAuthService;
import br.com.loto.service.IAccountService;
import br.com.loto.service.impl.userDetails.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountAuthServiceImpl implements IAccountAuthService {

    private final IAccountService accountService;

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;

    @Override
    public LoginResponse login(LoginRequest request) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

        //System.out.printf(encoder.encode("123456"));

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);

        return LoginResponse.builder()
                .token("Bearer ".concat(token))
                .permissions(userDetails.getAuthorities().stream()
                        .map(a -> a.getAuthority())
                        .toList())
                .build();
    }
}
