package br.com.loto.security;

import br.com.loto.entity.Account;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    
    private String chaveSecreta = "chaveSecretaParaGerarToken";
    private int validadeToken=900000;
    private static final Logger logger= LoggerFactory.getLogger(JwtUtil.class);

    public String gerarTokenUsername(Account account){
        return Jwts.builder().setSubject(account.getUsername()).
        setIssuedAt(new Date()).
        setExpiration(new Date(new Date().getTime()+validadeToken)).
        signWith(SignatureAlgorithm.HS512, chaveSecreta).compact();
    }

    public String getEmailToken(String token){
       return Jwts.parser().setSigningKey(chaveSecreta).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validarToken(String token, HttpServletRequest request){
        try {
            Jwts.parser().setSigningKey(chaveSecreta).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Assinatura Inválida", e.getMessage());
        }catch (ExpiredJwtException e){
            logger.error("Token expirado", e.getMessage());
            request.setAttribute("validacaoToken", "Token expirado");
        }catch (UnsupportedJwtException e){
            logger.error("Token não suportado", e.getMessage());

        }
        return false;
    }
}
