package br.com.loto.service.account.impl;

import br.com.loto.api.dto.account.requests.LoginRequest;
import br.com.loto.api.dto.account.responses.AccountResponse;
import br.com.loto.api.dto.account.responses.LoginResponse;
import br.com.loto.api.mappers.AccountMapper;
import br.com.loto.config.security.JwtUtil;
import br.com.loto.service.account.IAccountAuthService;
import br.com.loto.service.account.IAccountService;
import br.com.loto.service.userDetails.UserDetailsImpl;
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
                .token(jwtUtil.generateToken(userDetails))
                .content(accountMapper.convertEntityToResponse(accountService.findByCpfWithThrow(userDetails.getUsername())))
                .build();
    }

    @Override
    public AccountResponse getAccount() {
        return accountMapper.convertEntityToResponse(accountService.findAccountCurrent());
    }
}
