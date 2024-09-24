package br.com.loto.config.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permitir todas as rotas
                .allowedOrigins("http://messejana.conectalot.com.br:19880") // Permitir o frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
                .allowedHeaders("Content-Type", "Authorization") // Cabeçalhos permitidos
                .allowCredentials(true); // Permitir cookies ou credenciais
    }
}