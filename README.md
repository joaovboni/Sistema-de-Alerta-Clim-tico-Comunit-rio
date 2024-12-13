"# mensageria" 

# Sistema de Alerta Climático Comunitário

## 📋 Descrição
Sistema de monitoramento e alerta de eventos climáticos extremos, permitindo o cadastro de cidades, registro de alertas climáticos e histórico de notificações. Desenvolvido com JavaFX, RabbitMQ e PostgreSQL.

## 🏗️ Arquitetura

### Cliente (JavaFX)
- Interface gráfica para interação com usuário
- Envio de mensagens via RabbitMQ
- Atualização em tempo real das informações

### Servidor
- Processamento das requisições
- Integração com PostgreSQL
- Listeners RabbitMQ para operações CRUD

### RabbitMQ
- Gerenciamento de filas de mensagens
- Comunicação assíncrona entre cliente e servidor
- Garantia de entrega das mensagens

## 🗃️ Estrutura do Banco de Dados

### Tabela: cidades
```sql
CREATE TABLE cidades (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    latitude DECIMAL(10,8),
    longitude DECIMAL(11,8),
    populacao INTEGER,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Tabela: alertas
```sql
CREATE TABLE alertas (
    id SERIAL PRIMARY KEY,
    cidade_id INTEGER REFERENCES cidades(id),
    tipo_evento VARCHAR(50) NOT NULL,
    nivel_risco VARCHAR(20) NOT NULL,
    descricao TEXT,
    data_inicio TIMESTAMP NOT NULL,
    data_fim TIMESTAMP,
    status VARCHAR(20) DEFAULT 'ATIVO',
    data_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Tabela: notificacoes
```sql
CREATE TABLE notificacoes (
    id SERIAL PRIMARY KEY,
    alerta_id INTEGER REFERENCES alertas(id),
    mensagem TEXT NOT NULL,
    data_envio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status_envio VARCHAR(20)
);
```

## 🚀 Funcionalidades

### Gestão de Cidades
- Cadastro de novas cidades
- Edição de informações
- Visualização de cidades cadastradas
- Remoção de cidades

### Alertas Climáticos
- Registro de novos alertas
- Atualização de status
- Consulta de alertas ativos
- Histórico de alertas

### Notificações
- Envio automático
- Histórico de notificações
- Status de entrega
- Filtros por período

## 🛠️ Tecnologias Utilizadas

- Java 17
- JavaFX 17
- RabbitMQ
- PostgreSQL
- Scene Builder
- Maven

## 📦 Dependências Principais

```xml
<dependencies>
    <!-- JavaFX -->
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>17.0.2</version>
    </dependency>
    
    <!-- RabbitMQ -->
    <dependency>
        <groupId>com.rabbitmq</groupId>
        <artifactId>amqp-client</artifactId>
        <version>5.16.0</version>
    </dependency>
    
    <!-- PostgreSQL -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.5.1</version>
    </dependency>
</dependencies>
```

## 🚦 Configuração do Ambiente

1. **PostgreSQL**
   - Criar banco de dados
   - Executar scripts de criação das tabelas
   - Configurar arquivo `application.properties`

2. **RabbitMQ**
   - Instalar RabbitMQ Server
   - Criar filas necessárias
   - Configurar exchanges e bindings

3. **Java e JavaFX**
   - Instalar JDK 17
   - Configurar variáveis de ambiente
   - Instalar Scene Builder

## 🎯 Como Executar

1. Clone o repositório
```bash
git clone https://github.com/seu-usuario/sistema-alerta-climatico.git
```

2. Configure o banco de dados
```bash
psql -U postgres -f scripts/create_database.sql
psql -U postgres -d alerta_climatico -f scripts/create_tables.sql
```

3. Inicie o RabbitMQ
```bash
rabbitmq-server start
```

4. Execute o servidor
```bash
cd servidor
mvn spring-boot:run
```

5. Execute o cliente
```bash
cd cliente
mvn javafx:run
```

## 📐 Estrutura do Projeto

```
sistema-alerta-climatico/
├── cliente/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   ├── controllers/
│   │   │   │   ├── models/
│   │   │   │   ├── services/
│   │   │   │   └── utils/
│   │   │   └── resources/
│   │   │       ├── fxml/
│   │   │       └── css/
│   └── pom.xml
├── servidor/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   ├── config/
│   │   │   │   ├── controllers/
│   │   │   │   ├── models/
│   │   │   │   ├── repositories/
│   │   │   │   └── services/
│   │   │   └── resources/
│   └── pom.xml
└── README.md
```
