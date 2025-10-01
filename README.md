# 🚀 PulseSystem - Sistema de Gestão Operacional

O PulseSystem é uma aplicação web desenvolvida em Java criada para realizar o controle operacional
do sistema Pulse.

Sua finalidade é controlar as responsabilidades dos colaboradores do Pátio quanto ao registro e visualização
das motos, cadastro de beacons, atualização de status operacional e associação dos dispositivos IoT para a identificação
das motos no espaço.

---

## 💻 Tecnologias Utilizadas

- ☕ **Java 17+**
- 🌱 **Spring Boot**
- 🔐 **Spring Security**
- 🖋 **Thymeleaf**
- 🛠 **Flyway**
- 🗄 **H2 Database**
- 📦 **Maven**

---

## 🛠 Funcionalidades

- 👤 **Gerenciamento de usuários** com perfis distintos:
  - 🟢 **GESTOR**: acesso completo e gerenciamento geral.
  - 🔵 **OPERADOR**: acesso restrito a operações específicas.
  - ⚙️ **MECÂNICO**: acesso a funcionalidades de manutenção e relatórios.
- 🔒 **Autenticação e autorização** com Spring Security.
- 🗄 **Criação automática do banco de dados** com Flyway.
- 🌐 **Interface web responsiva** com Thymeleaf.

---

### 🗂 Estrutura do Projeto

src/
├── main/
│   ├── java/
│   │   └── com.pulse/
│   │       ├── controller/      # Controladores web
│   │       ├── model/           # Entidades e modelos
│   │       ├── repository/      # Repositórios JPA
│   │       ├── service/         # Lógica de negócio
│   │       └── security/        # Configurações de Spring Security
│   └── resources/
│       ├── templates/           # Páginas Thymeleaf
│       ├── static/              # CSS, JS, imagens
│       └── db/migration/        # Scripts Flyway

---

### Migrations
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

## ▶️ Como rodar o projeto

1. Clone o repositório:
```bash
git clone https://github.com/ChallengeMottu/PulseSystem_Java.git
cd PulseSystem_Java
```

2. Abra o projeto em alguma IDE

3. Execute a aplicação Spring Boot
```bash
./mvnw spring-boot:run
```
ou via IDE, executando a classe principal com @SpringBootApplication.

**Obs: Ao inicializar a aplicação, as migrations serão executas no banco H2

4. Acesse o sistema no navegador, na página de login:
```bash
http://localhost:8080/login
```

5. Faça login usando um dos usuários pré-cadastrados.

---

👥 Grupo Desenvolvedor

- Gabriela Sousa Reis RM558830
- Laura Amadeu Soares RM556690
- Raphael Kim RM557914



