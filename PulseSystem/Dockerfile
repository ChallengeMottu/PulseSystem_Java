# Etapa 1: Build da aplicação
FROM eclipse-temurin:17-jdk-jammy AS build

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia arquivos do Maven Wrapper e pom.xml primeiro (para cache de dependências)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Instala dependências (cache de Maven)
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

# Copia todo o código-fonte
COPY src ./src

# Build do JAR (sem testes para acelerar)
RUN ./mvnw clean package -DskipTests

# Etapa 2: Imagem final
FROM eclipse-temurin:17-jdk-jammy

# Diretório de trabalho da aplicação
WORKDIR /app

# Copia o JAR gerado da etapa de build
COPY --from=build /app/target/pulse-system-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java","-jar","app.jar"]
