package br.com.loto.service.impl;

import br.com.loto.api.dto.requests.LoginRequest;
import br.com.loto.api.dto.responses.AccountResponse;
import br.com.loto.api.dto.responses.LoginResponse;
import br.com.loto.api.mappers.AccountMapper;
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
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountAuthServiceImpl implements IAccountAuthService {

    private final IAccountService accountService;

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final AccountMapper accountMapper;

    @Override
    public LoginResponse login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return LoginResponse.builder()
                .token("Bearer ".concat(jwtUtil.generateToken(userDetails)))
                .content(accountMapper.convertEntityToResponse(accountService.findByCpfWithThrow(userDetails.getUsername())))
                .build();
    }

    @Override
    public AccountResponse getAccount() {
        return accountMapper.convertEntityToResponse(accountService.findUserCurrent());
    }
}
