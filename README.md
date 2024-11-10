#Cadastro de Clientes - Pessoas

Para executar este app você deve configurar o banco de dados PostgreSQL. Este pode ser configurado localmente ou por meio de docker.
Caso opte por docker, execute o comando:

####################################################

docker run -it  --rm   --name myPostgresDb    -p 5432:5432     -e POSTGRES_USER=postgres     -e POSTGRES_PASSWORD=postgres     -e POSTGRES_DB=localdb   -d  postgres

####################################################

Para executar o projeto, verifique se o arquivo mvnw é executável, caso não seja, voce deve executar primeiro `chmod +x mvnw` (linux).


############### No linux utilize ################

./mvnw clean package payara-micro:start

#################################################

Para nao executar testes, utilize: ./mvnw clean package -DskipTests=true

############### No windos utilize ################

mvnw.cmd clean package payara-micro:start

#################################################


Talvez seja necessário configurar a variável JAVA_HOME. Para isso, verifique onde sua jdk está instalada e informe a variável utilizando o path resumido (dir /x). Exemplo:

SET  JAVA_HOME="C:\Progra~1\Java\jdk-20"

Após iniciado, voce poderá acessar o projeto em http://localhost:8080.

===========  EM PRODUCAO ======
Para executar em producao voce devera configurar as variaveis de ambiente: DATABASE_URL, DATABASE_USERNAME e DATABASE_PASSWORD

Exemplos:
DATABASE_URL="jdbc:postgresql://127.0.0.1:5432/localdb" 
DATABASE_USERNAME="postgres" -
DATABASE_PASSWORD="postgres"

Há duas maneiras de alocar em producao (1 executar arquivo.jar e 2 gerar container):
1. Executar diretamente o payara-micro ou outro ee server compatível

%> java -jar <payara-micro> Cadastro-Pessoas.war

O arquivo .war encontra-se dentro da pasta target.

------------- -------- -------------- ------------- ------------- ------------- -------------

2. Entregar o projeto via Docker. Para construir a imagem Docker, execute os seguintes comandos no diretório onde este arquivo reside:

#################################################

./mvnw clean package 

docker build -t cadpessoas:v1 .

Para rodar a imagem e configurar as variaveis de ambiente, utilize o comando 

docker run -it --rm -e DATABASE_URL="jdbc:postgresql://<url do banco de dados>" -e DATABASE_USERNAME="<nome do usuario>" -e DATABASE_PASSWORD="<senha do usuario>" -p 8080:8080 cadpessoas:v1

Exemplo de comando completo:
docker run -it --rm -e DATABASE_URL="jdbc:postgresql://192.168.0.110:5432/localdb" -e DATABASE_USERNAME="postgres" -e DATABASE_PASSWORD="postgres" -p 8080:8080 cadpessoas:v1


Assim que a execução começar, você poderá acessar o projeto em http://localhost:8080/Cadastro-Pessoas

Exemplo de configuração para o bd em produção:
docker run -it  --rm   --name ProductPostgresDb    -p 5432:5432     -e POSTGRES_USER=postgres     -e POSTGRES_PASSWORD=sudodb     -e POSTGRES_DB=customerdb   -e PGDATA=/var/lib/postgresql/data/pgdata -v C:/Users/UTFPR/Downloads/dados:/var/lib/postgresql/data -d postgres