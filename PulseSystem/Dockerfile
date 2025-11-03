# Etapa 1: Build da aplicação
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app

# Copia apenas o pom.xml para aproveitar cache de dependências
COPY pom.xml .

# Baixa dependências
RUN mvn dependency:go-offline -B

# Copia o código-fonte
COPY src ./src

# Build do jar, sem rodar testes
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final para rodar a aplicação
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copia o jar gerado
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta (ajuste se sua aplicação não usa 8080)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
