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

- **Status:** Em andamento
- **Integrantes:** Esthevan Pereira, Lucas Ramon, Maria Eduarda Maia, Maurício Krziminski
- **Link:** [Link Repositório](https://github.com/DudaWendelMaia/T2-Projarc.git)

---

## 💡 Visão Geral

Este projeto é um **Aplicativo de Gerenciamento de Tarefas** com arquitetura de **microsserviços**. O sistema permite que usuários:

- **Gerenciem suas tarefas** de forma organizada e segura
- **Recebam lembretes automáticos** para tarefas pendentes
- **Autentiquem-se facilmente**, garantindo segurança e personalização

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

## 🧩 Funcionalidades

O sistema oferece várias funcionalidades voltadas para a **organização pessoal**, incluindo:

1. **Cadastro e Autenticação de Usuários**
   - Registro e login de usuários com controle de acesso seguro.

2. **Gerenciamento de Tarefas**
   - Criação, edição e exclusão de tarefas com opções de prioridade e status.

3. **Notificações e Lembretes Automáticos**
   - Receba lembretes de tarefas próximas ao vencimento para facilitar o planejamento.

4. **Relatórios e Análises de Tarefas**
   - Geração de relatórios sobre tarefas concluídas, pendentes e de alta prioridade.

5. **Integração com Sistema de Lembretes Externos**
   - Envio de notificações para dispositivos móveis e e-mails.

---

## 🏗️ Arquitetura

A arquitetura do sistema é baseada em **microsserviços**, cada um com uma responsabilidade específica, interligados por um **Gateway de API** para controle de acesso e roteamento.

### Componentes da Arquitetura:

- **Gateway de API**: Centraliza as requisições de autenticação e balanceia o tráfego entre os microsserviços.
- **Microsserviços Independentes**: Cada serviço é isolado e gerencia uma funcionalidade:
  - **Autenticação de Usuários**: Gerencia o cadastro e login de usuários, aplicando autenticação segura.
  - **Gerenciamento de Tarefas**: Cria e gerencia as tarefas de cada usuário, com opções de prioridade e status.
  - **Notificações e Lembretes**: Envia notificações sobre tarefas pendentes e vencidas.
- **Banco de Dados em Desenvolvimento**: Utilizamos o **H2 Database** em ambiente de desenvolvimento para simplificar a configuração e execução.

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
   git clone https://github.com/DudaWendelMaia/T2-Projarc.git
   cd gerenciador-tarefas-microsservicos
   ```

2. **Instalação das Dependências**:
   - Execute o Maven para instalar as dependências:
   ```bash
   mvn clean install
   ```

3. **Executando a Aplicação**
   - Inicie cada serviço individualmente usando:
   ```bash
   mvn spring-boot:run
   ```

4. **Acessando a Aplicação**
   - A aplicação estará disponível em `http://localhost:8080`.

5. **Documentação da API**
   - Acesse a documentação da API gerada automaticamente pelo OpenAPI em `http://localhost:8080/swagger-ui.html`.

---

## 📊 Benefícios para o Usuário

- **Escalabilidade e Flexibilidade**: A arquitetura modular permite que o sistema cresça conforme a demanda aumenta.
- **Organização e Produtividade**: Ferramentas de gerenciamento de tarefas e lembretes automáticos tornam a organização mais eficiente.
- **Segurança e Controle**: Autenticação robusta e proteção de dados para cada usuário.
- **Acessibilidade e Facilidade de Uso**: Interface amigável e intuitiva, com documentação da API acessível para desenvolvedores.

---

## 📌 Conclusão

Este aplicativo de gerenciamento de tarefas em microsserviços oferece uma solução prática e escalável para organizar compromissos e garantir o cumprimento de prazos. É uma opção eficaz para usuários que buscam uma forma segura e confiável de gerenciar seu tempo e produtividade.
