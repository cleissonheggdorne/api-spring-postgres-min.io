# PROJETO PRÁTICO IMPLEMENTAÇÃO BACK-END PSS 02/2025/SEPLAG (Analista de TI - Perfil Junior, Pleno e Sênior)

API Spring Boot com PostgreSQL e MinIO (Dockerizada)
📋 Visão Geral
API REST desenvolvida em Spring Boot com:

PostgreSQL (banco de dados)

MinIO (armazenamento de objetos S3-compatível)

Docker Compose (orquestração)

🚀 Instalação Rápida
Pré-requisitos
Docker -> https://www.docker.com/
MAVEN -> https://maven.apache.org/download.cgi?.
Docker Compose (v2+)

Git (opcional) -> https://git-scm.com/downloads

1- git clone https://github.com/cleissonheggdorne/api-spring-postgres-min.io.git ou Baixe o projeto como um arquivo zip.
2- cd seu-projeto

# Buildando o projeto
3- mvn clean package -DskipTests

# Subindo o projeto
4- docker-compose up --build (Fica na janela do terminal e permite vê os logs em tempo real)
  ou  docker-compose up -d --build (Subindo em background)

O que você deve ver no terminal?

 ✔ api                      Built                                                                                              
 ✔ Container postgres-db    Healthy                                                                                             
 ✔ Container minio-storage  Started                                                                                            
 ✔ Container spring-api     Started 

# Testando a API
SWAGGER UI -> http://localhost:8080/swagger-ui/index.html#/

Dados de Login de teste
-> 12345678912
-> 123456

📊 Serviços em Execução
Serviço	URL	Credenciais
API	            http://localhost:8080	12345678912/123456
MinIO Console	http://localhost:9001	minioadmin/minioadmin
PostgreSQL   	porta 5432

⚙️ Comandos Úteis
# Verificar status dos containers
docker-compose ps

# Parar todos os serviços
docker-compose down

# Parar e remover volumes
docker-compose down -v

📌 Informações Adicionais
Autor: [Cleisson da Rosa Heggdorne] Tecnólogo em Sistemas de Computação pela Universidade Federal Fluminense (UFF).

Versão: 1.0.0

* Nota para avaliadores: Todos os serviços podem ser iniciados com um único comando (docker compose up). A API está pronta para testes imediatos após a inicialização.

🔒 Observação sobre Arquivos Sensíveis no Repositório
⚠️ Atenção: Arquivos Sensíveis Versionados
Este repositório contém arquivos de configuração sensíveis (como chaves privadas e arquivos .yaml com credenciais) apenas para fins de avaliação técnica