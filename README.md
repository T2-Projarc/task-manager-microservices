# 📝 Aplicativo de Gerenciamento de Tarefas com Microsserviços

<sub>*Uma solução simples e escalável para organizar tarefas, com autenticação de usuários e lembretes automáticos.*</sub>

<div align="center">
 <img height=250 width=250 src="https://media.tenor.com/Q9rfrj2lA6kAAAAi/smolverse-smol.gif">
</div>

---

## 📜 Índice

- [ℹ️ Informações](#-informações)
- [💡 Visão Geral](#-visão-geral)
- [🎯 Proposta do Projeto](#-proposta-do-projeto)
- [📈 Contexto e Justificativa](#-contexto-e-justificativa)
- [🛠️ Arquitetura do Sistema](#-arquitetura-do-sistema)
- [💻 Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [🧩 Funcionalidades](#-funcionalidades)
- [🏗️ Arquitetura](#%EF%B8%8F-arquitetura)
- [📥 Instalação e Execução](#-instalação-e-execução)
- [📊 Benefícios para o Usuário](#-benefícios-para-o-usuário)
- [📌 Conclusão](#-conclusão)

---

## ℹ️ Informações

- **Status:** Concuído
- **Integrantes:** Esthevan Pereira, Lucas Ramon, Maria Eduarda Maia, Maurício Krziminski, Mauricio Gaspary
- **Link:** [Link Repositório](https://github.com/T2-Projarc/task-manager-microservices)

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
   - A aplicação estará disponível em `http://localhost:3000/#`.

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
