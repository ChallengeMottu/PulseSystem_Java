# 🚀 PulseSystem - Sistema de Gestão Operacional

O PulseSystem é uma aplicação web desenvolvida em Java criada para realizar o controle operacional
do sistema Pulse.

Sua finalidade é controlar as responsabilidades dos colaboradores do Pátio quanto ao registro e visualização
das motos, cadastro de beacons, atualização de status operacional e associação dos dispositivos IoT para a identificação
das motos no espaço.

---

## 💻 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Security**
- **Thymeleaf**
- **Flyway**
- **Oracle**
- **Maven**

---

## 🛠 Funcionalidades

- **Gerenciamento de usuários** com perfis distintos:
  - **GESTOR**: acesso completo e gerenciamento geral.
  - **OPERADOR**: acesso restrito a operações específicas.
  - **MECÂNICO**: acesso a funcionalidades de manutenção e relatórios.
- **Autenticação e autorização** com Spring Security.
- **Criação automática do banco de dados** com Flyway.
- **Interface web responsiva** com Thymeleaf.

---

## 🔗 Integrações com Outras Disciplinas

O PulseSystem não é apenas um sistema isolado: ele foi desenvolvido de forma integrada com projetos e tecnologias construídos nas demais disciplinas do curso, formando um ecossistema completo e colaborativo. Cada área contribuiu diretamente para ampliar a robustez e a coerência da solução.

### Banco de Dados  
A aplicação Java utiliza dados previamente cadastrados no banco de dados, incluindo instâncias da tabela **Parkings** (pátios) e usuários gestores pré-configurados. Essa integração permitiu iniciar o desenvolvimento com uma base sólida e estruturada para controle operacional.

### .NET  
Com a API .NET criada para o cadastro de pátios, os gestores podem registrar suas filiais e configurar a **planta baixa** do espaço, definindo zonas e estrutura física. A partir disso, o PulseSystem consegue consumir essas informações e apresentar o **mapeamento estrutural** completo das unidades, integrando backends de tecnologias diferentes.

### Compliance  
A disciplina de compliance influenciou diretamente o planejamento e organização do projeto. O PulseSystem seguiu um **backlog estruturado no Azure DevOps**, garantindo rastreabilidade, governança e controle de entregas durante todo o desenvolvimento da aplicação Java.

### Mobile  
A aplicação mobile do ecossistema utiliza diretamente a API Java para autenticação e operações essenciais realizadas pelos colaboradores das filiais. Assim, a disciplina de mobile integra-se ao PulseSystem consumindo seus serviços e utilizando sua lógica de negócio.

### IoT  
A integração com IoT aparece no uso de dispositivos físicos (beacons) que permitem o **mapeamento e identificação em tempo real** nos pátios. O PulseSystem faz essa associação entre motocicletas e beacons, refletindo a interação entre hardware e software aprendida na disciplina.

### DevOps  
A disciplina de DevOps contribuiu com práticas essenciais utilizadas no projeto, como o uso de **Docker para containerização**, além de estratégias de deploy em nuvem que garantem portabilidade, escalabilidade e facilidade na distribuição da aplicação.

--- 

## 💡 Associação de Beacons

Além de um CRUD integrado para controle das entidades, temos uma funcionalidade
exclusiva do sistema e muito importante para o seu funcionamento: a associação entre Beacons e Motos.

Essa simples associação permite a identificação da moto por meio do sinal Bluetooth do dispositivo.

---

### 📝 Migrations
Ao total o projeto possui 6 migrations, que envolvem criação das tabelas no banco H2 e inserção de dados iniciais, como
por exemplo, inserção de 3 usuários, cada um de um perfil diferente, para facilitar entrada no sistema.

| Migration | Descrição |
|-----------|-----------|
| `V1__create_parkings_table.sql` | Criação da tabela de estacionamentos (parkings) com informações básicas, como nome, endereço e capacidade. |
| `V2__create_motorcycles_table.sql` | Criação da tabela de motocicletas (motorcycles) contendo dados como placa, modelo, ano e associação ao estacionamento. |
| `V3__create_beacons_table.sql` | Criação da tabela de beacons, incluindo informações de identificação e vinculação a motocicletas e estacionamentos. |
| `V4__create_employee_table.sql` | Criação da tabela de funcionários (employees), com campos como nome, login, senha e perfil (GESTOR, OPERADOR, MECÂNICO). |
| `V5__insert_parking_and_users.sql` | Inserção de dados iniciais na tabela de estacionamentos e usuários pré-cadastrados para teste do sistema. |
| `V6__insert_motorcycles_and_beacons.sql` | Inserção de dados iniciais na tabela de motocicletas e beacons, vinculando-os aos estacionamentos correspondentes. |

--- 

## 🧾 Usuários pré-cadastrados

O sistema já vem com alguns usuários para teste:

| Usuário     | Senha       | Perfil    |
|------------|------------|-----------|
| admin@pulse.com    | gestor123   | GESTOR    |
| amanda.perez@pulse.com  | amanda123   | OPERADOR  |
| marcos.carvalho@pulse.com  | marcos123   | MECÂNICO  |

---

## ⚙️ Configuração de Banco de Dados e Migrations (Flyway & Hibernate)

O PulseSystem utiliza Flyway para controle de versão do banco de dados, porém no ambiente atual de desenvolvimento o Flyway está desativado, e a criação/atualização das tabelas é realizada automaticamente pelo Hibernate, utilizando a configuração:

```bash
spring.jpa.hibernate.ddl-auto=update
```

Caso prefira usar o Flyway para criação do database, basta ativar ele no application.properties:
```bash
spring.flyway.enabled=true
spring.jpa.hibernate.ddl-auto=none
```

---

## ▶️ Como rodar o projeto

**1. Clone o repositório:**
```bash
git clone https://github.com/ChallengeMottu/PulseSystem_Java.git
cd PulseSystem_Java
```

**2. Abra o projeto em alguma IDE**

**3. Execute a aplicação Spring Boot**
```bash
./mvnw spring-boot:run
```
ou via IDE, executando a classe principal com @SpringBootApplication.

**4. Acesse o sistema no navegador, na página de login:**
```bash
http://localhost:8080/login
```

**5. Faça login usando um dos usuários pré-cadastrados.**

---

## 🌐 Deploy da Aplicação na Azure

O deploy do PulseSystem foi realizado na **Microsoft Azure**, utilizando os serviços gerenciados da plataforma para garantir disponibilidade, segurança e escalabilidade do sistema em produção.

### Azure Web App  
A aplicação Java foi publicada em um **Azure Web App**, que oferece um ambiente totalmente gerenciado para execução de aplicações web. Essa abordagem elimina a necessidade de configurações manuais de infraestrutura, permitindo foco total no desenvolvimento e manutenção da aplicação.

### Migração para Banco PaaS – Azure SQL Database  
Para o ambiente em nuvem, o sistema deixou de utilizar o banco Oracle e passou a operar com um banco **PaaS** (Platform as a Service): o **Azure SQL Database**.  

---


👥 Grupo Desenvolvedor

- Gabriela Sousa Reis RM558830
- Laura Amadeu Soares RM556690
- Raphael Kim RM557914



