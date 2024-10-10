package br.com.loto.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final AuthFilterToken authFilterToken;

    private final AuthEntryPointJwt unauthorizedHandler;

    public static final String[] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/api/v1/public/**",
            "/swagger",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/webjars/**",
            "/api-docs/**",
            "/v1/api-docs/**",
            "/v1/api-docs",
            "/v2/api-docs/**",
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/v3/api-docs",
    };

    // Endpoints que requerem autenticação para serem acessados
    public static final String[] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
            "/api/v1/accounts/**",
            "/api/v1/games",
            "/api/v1/games/**",
            "/api/v1/pools",
            "/api/v1/pools/**",
            "/api/v1/lotteries",
            "/api/v1/lotteries/**",
            "/api/v1/bets",
            "/api/v1/bets/**",
            "/api/v1/orders",
            "/api/v1/orders/**"
    };

    // Endpoints que só podem ser acessador por usuários com permissão de cliente
    public static final String[] ENDPOINTS_CUSTOMER = {
            "/users/test/customer"
    };

    // Endpoints que só podem ser acessador por usuários com permissão de administrador
    public static final String[] ENDPOINTS_ADMIN = {
            "/api/v1/accounts/**",
            "/api/v1/games",
            "/api/v1/games/**",
            "/api/v1/pools",
            "/api/v1/pools/**",
            "/api/v1/contests",
            "/api/v1/contests/**",
            "/api/v1/bets",
            "/api/v1/bets/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests() // Habilita a autorização para as requisições HTTP
                .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()
//                .requestMatchers(ENDPOINTS_ADMIN).hasRole("ADMINISTRATOR") // Repare que não é necessário colocar "ROLE" antes do nome, como fizemos na definição das roles
//                .requestMatchers(ENDPOINTS_CUSTOMER).hasRole("CUSTOMER")
                .anyRequest()
                .denyAll()
                .and()
                .cors()
                .and()
                .csrf().disable()
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint(unauthorizedHandler) // Configura o ponto de entrada para autenticação não autorizada
                )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Configura a política de criação de sessão como stateless
                // Adiciona o filtro de autenticação de usuário que criamos, antes do filtro de segurança padrão do Spring Security
                .and().addFilterBefore(authFilterToken, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://messejana.conectalot.com.br:19880/")); // Adicione suas origens permitidas
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // Métodos permitidos
//        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Cabeçalhos permitidos
//        configuration.setAllowCredentials(true); // Permite credenciais (cookies, headers de autorização)
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration); // Aplica a configuração CORS para todas as rotas
//        return source;
//    }
}
