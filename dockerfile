# Use a imagem do JDK como base
FROM openjdk:17-jdk-alpine

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo JAR gerado para o contêiner
COPY target/*.jar loto.jar

# Define a variável de ambiente para evitar problemas de acessibilidade
ENV JAVA_OPTS=""

# Expor a porta padrão do Spring Boot (8080)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar loto.jar"]
