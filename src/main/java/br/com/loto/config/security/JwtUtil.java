package br.com.loto.config.security;

import br.com.loto.service.userDetails.UserDetailsImpl;
import br.com.loto.utils.LotoProp;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final LotoProp lotoProp;

    public String generateToken(UserDetailsImpl user) {
        try {
            // Define o algoritmo HMAC SHA256 para criar a assinatura do token passando a chave secreta definida
            Algorithm algorithm = Algorithm.HMAC256(lotoProp.getAuth().getSecretKey());
            return JWT.create()
                    .withIssuer(lotoProp.getAuth().getIssuer()) // Define o emissor do token
                    .withIssuedAt(creationDate()) // Define a data de emissão do token
                    .withExpiresAt(expirationDate()) // Define a data de expiração do token
                    .withSubject(user.getUsername()) // Define o assunto do token (neste caso, cpf da conta)
                    .sign(algorithm); // Assina o token usando o algoritmo especificado
        } catch (JWTCreationException exception){
            throw new JWTCreationException("Erro ao gerar token.", exception);
        }
    }

    public String getUsername(String token) {
        try {
            // Define o algoritmo HMAC SHA256 para verificar a assinatura do token passando a chave secreta definida
            Algorithm algorithm = Algorithm.HMAC256(lotoProp.getAuth().getSecretKey());
            return JWT.require(algorithm)
                    .withIssuer(lotoProp.getAuth().getIssuer()) // Define o emissor do token
                    .build()
                    .verify(token) // Verifica a validade do token
                    .getSubject(); // Obtém o assunto (neste caso, o cpf da conta) do token
        } catch (JWTVerificationException exception){
            throw new JWTVerificationException("Token inválido ou expirado.");
        }
    }

    private Instant creationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Fortaleza")).toInstant();
    }

    private Instant expirationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Fortaleza")).plusMinutes(lotoProp.getAuth().getExpirationInMinutes()).toInstant();
    }
}
