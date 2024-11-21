# 📝 Aplicativo de Gerenciamento de Tarefas com Microsserviços

<sub>*Uma solução simples e escalável para organizar tarefas, com autenticação de usuários e lembretes automáticos.*</sub>

<div align="center">
 <img height=250 width=250 src="https://media.tenor.com/Q9rfrj2lA6kAAAAi/smolverse-smol.gif">
</div>

---

## 📜 Índice

- [ℹ️ Informações](#-informações)
- [🎯 Proposta do Projeto](#-proposta-do-projeto)
- [📈 Contexto e Justificativa](#-contexto-e-justificativa)
- [💡 Visão Geral](#-visão-geral)
- [🧩 Funcionalidades](#-funcionalidades)
- [📊 Benefícios para o Usuário](#-benefícios-para-o-usuário)
- [🏗️ Arquitetura](#%EF%B8%8F-arquitetura)
- [💻 Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [↪️ Diagramas UML](#-diagramas-uml)
- [📥 Instalação e Execução](#-instalação-e-execução)
- [📌 Conclusão](#-conclusão)

---

## ℹ️ Informações Gerais

- **Status:** Concuído
- **Integrantes:** Esthevan Pereira, Lucas Ramon, Maria Eduarda Maia, Maurício Krziminski, Mauricio Gaspary
- **Link Repositório:** [Clique Aqui](https://github.com/T2-Projarc/task-manager-microservices)
- **Link Relatório:** [Clique Aqui](https://docs.google.com/document/d/1lvYgolHHPINpKCL97cOvSgLZaYilDP6hm4okh7JmOa0/edit?usp=sharing)

---

## 🎯 Proposta do Projeto

Criar um aplicativo para **gerenciar tarefas e agendamentos**, simplificando o processo de criação, atualização e acompanhamento de compromissos. A estrutura em microsserviços garante uma aplicação modular e escalável, adequada para diferentes volumes de usuários e número de tarefas.

---

## 📈 Contexto e Justificativa

Em um mundo cada vez mais acelerado, a **organização de tarefas pessoais e profissionais** tornou-se essencial. Este sistema:

- **Facilita o gerenciamento de tarefas** com lembretes automáticos
- **Oferece segurança com autenticação** e gestão de usuários
- **Fornece um sistema modular** para atender diferentes demandas de escalabilidade e crescimento

---

## 💡 Visão Geral

Este projeto é um **Aplicativo de Gerenciamento de Tarefas** com arquitetura de **microsserviços**. O sistema permite que usuários:

- Criem, editem e excluam tarefas
- Definam prioridades e status
- Recebam lembretes automáticos baseados no tempo de notificação definido
- Organizem melhor seus compromissos diários e projetos

---

## 🧩 Funcionalidades

O sistema oferece várias funcionalidades voltadas para a **organização pessoal**, incluindo:

- **Criação de Tarefas**: Adicione descrições, defina prioridade e status.
- **Edição Flexível**: Atualize descrições, prioridades ou status individualmente.
- **Lembretes Automáticos**: Receba notificações personalizadas com base no tempo definido.
- **Exclusão de Tarefas**: Remova tarefas desnecessárias com facilidade.
- **Autenticação Segura**: Login e registro de usuários com token JWT.
- **Documentação Automatizada**: Use o Swagger para explorar os endpoints.

---

## 📊 Benefícios para o Usuário

- **Escalabilidade e Flexibilidade**: A arquitetura modular permite que o sistema cresça conforme a demanda aumenta.
- **Organização e Produtividade**: Ferramentas de gerenciamento de tarefas e lembretes automáticos tornam a organização mais eficiente.
- **Segurança e Controle**: Autenticação robusta e proteção de dados para cada usuário.
- **Acessibilidade e Facilidade de Uso**: Interface amigável e intuitiva, com documentação da API acessível para desenvolvedores.

---

## 🏗️ Arquitetura

O sistema é baseado em uma arquitetura de **microsserviços** composta pelos seguintes componentes:

### Componentes da Arquitetura:

1. **Auth-Service**: Gerencia autenticação e autorização com JWT.
2. **Task-Service**: Responsável por criar, editar e gerenciar tarefas.
3. **Notification-Service**: Sistema de notificações para alertar usuários sobre suas tarefas.
4. **Gateway**: Roteia as requisições entre os microsserviços.
5. **Service Discovery (Eureka)**: Permite que os microsserviços se registrem e descubram uns aos outros.

### Fluxo de Comunicação:

- O **Auth-Service** autentica os usuários.
- O **Gateway** direciona as requisições para os serviços apropriados.
- O **Task-Service** processa os dados das tarefas e interage com o banco de dados.
- O **Notification-Service** envia lembretes automáticos via mensagens assíncronas usando RabbitMQ ou Kafka.

---

## 💻 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.4**
- **Spring Data JPA**
- **Spring Web**
- **Spring Security** para autenticação e autorização de usuários
- **H2 Database** para desenvolvimento e testes
- **Spring Cloud Gateway** e **Eureka** para roteamento e descoberta de serviços
- **RabbitMQ ou Kafka** para sistema de notificações e mensagens
- **SpringDoc OpenAPI** para documentação automática da API
- **MapStruct e Lombok** para simplificação e mapeamento de classes

---

## ↪️ Diagramas UML

- Todos os diagramas foram feitos no PlantUML
- Tem diagrama geral de classes e componentes
- Cada microsserviço tem seu diagrama de classe separadamente
- Todos os diagramas estão localizados no relatório do trabalho: [Clique Aqui](https://docs.google.com/document/d/1lvYgolHHPINpKCL97cOvSgLZaYilDP6hm4okh7JmOa0/edit?usp=sharing)

### Diagrama de Classes Geral
- Esse diagrama de classes apresenta as principais entidades do sistema, destacando suas propriedades e métodos, e como eles se relacionam. Abaixo está o link da imagem, o diagrama e seu script:
- Link da imagem: [Clique Aqui](https://www.plantuml.com/plantuml/png/lLN1Rjim3BtxAuIUNCG1jiCkmr0qNLViiBL0ads09j6f44NoABfOCVJV5sPkB5jgdDDqIl8UoST74haE997QdIqQgRPo0-BYTlrm8VuKOl-KbI68jGpR9Vng8T0UOwXxSw4XA3GD6U_4Hx4YD6uJmSwJgOsIZA_D3lQSRrv9Uoi9-7j4RT1uDFISInD8KXk6o4lnKlIQlpZQnwQo8x4pOVIXVfHwThg1sMG32HMuna_lBKYN0T-_-svSIn1keFO89xaVpgS-iBWivOIlizxN16a3e9CxEENv4XeV37ay-cuS0TPImS7x7XzCeJPEtppVTyhlf0smSFf-PFIY9i2ICfPVyWycq2UkSPKa_j4fBvCMHe5fGol09wCWtQ8E6BIX42G1esLkXwh9rQdIqLJZYLGYsmLNZOVLw5SfnnMl1FwxMAOFxZUJG8E5SGAcFNcZ8ze6Y3dXV9EZOFXDA1MzHjwXtwtz5bpPw_nArB3L5gYJUvdRJVK8QhjmUWN3jGbbhpcPxMVl2BsrWEbuU-oj2R_bPIKUYPhbH0SSCxHnOUt1oN8wFBRpFymyEVUHGVFvLNmIHVIPiT7X5qCPHqPn3KxpdyvV)
  
<div align="center">
 <img src="https://www.plantuml.com/plantuml/png/lLN1Rjim3BtxAuIUNCG1jiCkmr0qNLViiBL0ads09j6f44NoABfOCVJV5sPkB5jgdDDqIl8UoST74haE997QdIqQgRPo0-BYTlrm8VuKOl-KbI68jGpR9Vng8T0UOwXxSw4XA3GD6U_4Hx4YD6uJmSwJgOsIZA_D3lQSRrv9Uoi9-7j4RT1uDFISInD8KXk6o4lnKlIQlpZQnwQo8x4pOVIXVfHwThg1sMG32HMuna_lBKYN0T-_-svSIn1keFO89xaVpgS-iBWivOIlizxN16a3e9CxEENv4XeV37ay-cuS0TPImS7x7XzCeJPEtppVTyhlf0smSFf-PFIY9i2ICfPVyWycq2UkSPKa_j4fBvCMHe5fGol09wCWtQ8E6BIX42G1esLkXwh9rQdIqLJZYLGYsmLNZOVLw5SfnnMl1FwxMAOFxZUJG8E5SGAcFNcZ8ze6Y3dXV9EZOFXDA1MzHjwXtwtz5bpPw_nArB3L5gYJUvdRJVK8QhjmUWN3jGbbhpcPxMVl2BsrWEbuU-oj2R_bPIKUYPhbH0SSCxHnOUt1oN8wFBRpFymyEVUHGVFvLNmIHVIPiT7X5qCPHqPn3KxpdyvV" alt="Diagrama de Classes Geral" width="700">
</div>

```PlantUML
@startuml
package "DTO" {
    class TaskRequestDTO {
        - description : String
        - notificationTime : LocalDateTime
        - priority : String
        - status : String
    }
}

package "Entity" {
    class Task {
        + id : Long
        + description : String
        + notificationTime : LocalDateTime
        + notified : boolean
        + notified10MinutesBefore : boolean
        + notified5MinutesBefore : boolean
        + notifiedOnTime : boolean
        + priority : String
        + status : String
        + username : String
    }
}

package "Repository" {
    interface TaskRepository {
        + findByNotifiedFalseAndNotificationTimeAfter(time : LocalDateTime) : List<Task>
        + findByUsername(username : String) : List<Task>
    }
}

package "Service" {
    class TaskService {
        + createTask(description : String, notificationTime : LocalDateTime, priority : String, status : String, username : String) : Task
        + updateTask(id : Long, request : TaskRequestDTO) : Task
        + deleteTask(id : Long) : void
        + getTasksByUsername(username : String) : List<Task>
        + extractUsernameFromToken(request : HttpServletRequest) : String
        + checkAndSendNotifications() : void
    }
}

package "Controller" {
    class TaskController {
        + createTask(request : TaskRequestDTO, httpRequest : HttpServletRequest) : Task
        + getAllTasks(httpRequest : HttpServletRequest) : List<Task>
        + updateTask(id : Long, request : TaskRequestDTO) : Task
        + deleteTask(id : Long) : String
    }
}

TaskController --> TaskService
TaskService --> TaskRepository
TaskRequestDTO --> Task
@enduml
```

### Diagrama de Componentes Geral
- Esse diagrama de componentes apresenta aarquitetura em alto nível, destacando como os microsserviços e outros componentes do sistema interagem. Abaixo está o link da imagem, o diagrama e seu script:
- Link da imagem: [Clique Aqui](https://www.plantuml.com/plantuml/png/VPB1IiD048Rl-nH3xy6x1sbLX29AZTMBU9Wa4rpIxkhER2_YmuW7mRjFa1TpWcsDwsAE__p_bt-6dR9WaBXU6USRWibPy4x8DHDuDg3VNKbO2ynL32tMT6zUpDPwZ7KtTYwHk-AQmiQgxLkQHNb8f4iljhKrYlLkF_g1dg9WXKo_gB1JHzYHfk4_DQ4eZf9AS0YpnHnAUeh4mYQPQY3zcoOM5DYoz1-kjWZQS8c1MiocyxifvCIlerEzV-_VF6mKL69LMRcuEZZ7jiCCBBcTGcvnPPijOkZXzVXi4ji--VyIDnpxrs0zctpd_FLAuaVFWZmWTZajz4bifkIQuHLz0G00)

<div align="center">
 <img src="https://www.plantuml.com/plantuml/png/VPB1IiD048Rl-nH3xy6x1sbLX29AZTMBU9Wa4rpIxkhER2_YmuW7mRjFa1TpWcsDwsAE__p_bt-6dR9WaBXU6USRWibPy4x8DHDuDg3VNKbO2ynL32tMT6zUpDPwZ7KtTYwHk-AQmiQgxLkQHNb8f4iljhKrYlLkF_g1dg9WXKo_gB1JHzYHfk4_DQ4eZf9AS0YpnHnAUeh4mYQPQY3zcoOM5DYoz1-kjWZQS8c1MiocyxifvCIlerEzV-_VF6mKL69LMRcuEZZ7jiCCBBcTGcvnPPijOkZXzVXi4ji--VyIDnpxrs0zctpd_FLAuaVFWZmWTZajz4bifkIQuHLz0G00" alt="Diagrama de Componentes Geral" width="600">
</div>

 ```PlantUML
@startuml
node "Frontend" {
    [React Interface]
}

node "Backend" {
    [Task-Service]
    [Auth-Service]
    [Notification-Service]
}

node "Database" {
    [Task Database]
    [Auth Database]
}

[React Interface] --> [Task-Service] : API Requests
[Task-Service] --> [Task Database] : Persistência de Tarefas
[Task-Service] --> [Notification-Service] : Comunicação via RabbitMQ/Kafka
[Task-Service] --> [Auth-Service] : Validação de Tokens
[Auth-Service] --> [Auth Database] : Persistência de Usuários
[Notification-Service] --> [Task-Service] : Respostas de Notificações
@enduml
```

---

## 📥 Instalação e Execução

### Pré-requisitos

- **Java 17**
- **Maven**

### Instruções de Instalação

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/T2-Projarc/task-manager-microservices
   cd task-manager-microservices
   ```

2. **Instalação das Dependências**:
   - Execute o Maven para instalar as dependências:
   ```bash
   mvn clean install
   ```

3. **Executando a Aplicação - Frontend**
   - Entre na pasta do frontend:
   ```bash
   cd frontend
   ```
   - Execute o seguinte comando para rodar o frontend:
   ```bash
   py -m http.server 3000
   ```
   - A aplicação estará disponível em <http://localhost:3000/frontend/>.

4. **Executando a Aplicação - Backend**
   - Inicie cada serviço individualmente usando:
   ```bash
   mvn spring-boot:run
   ```

5. **Acessando a Documentação (Swagger)**
   - Acesse a documentação da API gerada automaticamente pelo OpenAPI em `http://localhost:porta/swagger-ui/index.html#/`.
   - Substitua `porta` pela porta em que o microsserviço está rodando.

---

## 📌 Conclusão

Este aplicativo de gerenciamento de tarefas em microsserviços oferece uma solução prática e escalável para organizar compromissos e garantir o cumprimento de prazos. É uma opção eficaz para usuários que buscam uma forma segura e confiável de gerenciar seu tempo e produtividade.
