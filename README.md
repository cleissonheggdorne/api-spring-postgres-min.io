# 📌 Projeto Prático - Implementação Back-End PSS 02/2025/SEPLAG

API REST desenvolvida com **Spring Boot**, **PostgreSQL** e **MinIO**, totalmente dockerizada para facilitar a implantação e execução.

---

## 📋 Tecnologias Utilizadas

- **Spring Boot** (Back-end)
- **PostgreSQL** (Banco de dados relacional)
- **MinIO** (Armazenamento de objetos compatível com S3)
- **Docker Compose** (Orquestração dos containers)

---

## 🚀 Instalação Rápida

### 📌 Requisitos

- [Docker](https://www.docker.com/)
- [Maven](https://maven.apache.org/download.cgi)
- [Git (opcional)](https://git-scm.com/downloads)

### 📌 Clonando o Repositório

```bash
git clone https://github.com/cleissonheggdorne/api-spring-postgres-min.io.git
cd api-spring-postgres-min.io
```
Ou baixe o projeto como um arquivo ZIP e extraia.

### 📌 Build do Projeto

```bash
mvn clean package -DskipTests
```

### 📌 Subindo os Containers

```bash
docker-compose up --build   # Exibe logs em tempo real
docker-compose up -d --build   # Executa em background
```

---

## ✅ O que Você Deve Ver no Terminal

✔ `api` Built  
✔ `postgres-db` Healthy  
✔ `minio-storage` Started  
✔ `spring-api` Started  

---

## 📊 Testando a API

- **Swagger UI**: [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

### 📌 Credenciais de Teste

| Serviço  | URL | Usuário | Senha |
|-----------|----------------------------|-------------|--------|
| **API** | [http://localhost:8080](http://localhost:8080) | `12345678912` | `123456` |
| **MinIO Console** | [http://localhost:9001](http://localhost:9001) | `minioadmin` | `minioadmin` |
| **PostgreSQL** | Porta `5432` | - | - |

---

## ⚙️ Comandos Úteis

### 📌 Verificar status dos containers
```bash
docker-compose ps
```

### 📌 Parar todos os serviços
```bash
docker-compose down
```

### 📌 Parar e remover volumes
```bash
docker-compose down -v
```

---

## 📌 Observação sobre Arquivos Sensíveis

⚠ **Atenção:** Este repositório contém arquivos de configuração sensíveis (como chaves privadas e arquivos `.yaml` com credenciais) apenas para fins de avaliação técnica.

---

## 📌 Informações Adicionais

- **Autor**: Cleisson da Rosa Heggdorne  
- **Formação**: Tecnólogo em Sistemas de Computação pela Universidade Federal Fluminense (UFF)
- **Versão**: `1.0.0`

---

## 📌 Nota para Avaliadores

Todos os serviços podem ser iniciados com um único comando: `docker-compose up`. A API estará pronta para testes imediatos após a inicialização.

