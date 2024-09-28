package br.com.loto.config.security;

import br.com.loto.domain.entity.Account;
import br.com.loto.exceptions.TokenException;
import br.com.loto.service.account.IAccountConsultService;
import br.com.loto.service.account.IAccountService;
import br.com.loto.service.userDetails.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class AuthFilterToken extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final IAccountConsultService accountConsultService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Verifica se o endpoint requer autenticação antes de processar a requisição
        if (checkIfEndpointIsNotPublic(request)) {

            // Recupera o token do cabeçalho Authorization da requisição
            String token = recoveryToken(request);

            if (token == null)
                throw new TokenException("O token está ausente.");

            String subject = jwtUtil.getUsername(token);
            Account account = accountConsultService.findByCpfWithThrow(subject);
            UserDetailsImpl userDetails = new UserDetailsImpl(account);

            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response); // Continua o processamento da requisição
    }

    // Recupera o token do cabeçalho Authorization da requisição
    private String recoveryToken(HttpServletRequest request) {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null)
            return authorizationHeader.replace("Bearer ", "");

        return null;
    }

    // Verifica se o endpoint requer autenticação antes de processar a requisição
    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {

        boolean isNotPublic = true;
        String uriCurrent = request.getRequestURI();

        for (String uri : Arrays.stream(WebSecurityConfig.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).toList()) {

            String uriC = removeEnd(uri);

            if (uriCurrent.startsWith(uriC)) {
                isNotPublic = false;
                break;
            }
        }

        return isNotPublic;
    }

    private String removeEnd(String uri) {

        if (uri.endsWith("/**"))
            return uri.substring(0, uri.length() - 2);
        return uri;
    }
}
