# Escolha entre o openliberty e o payara

#configuracao para o openliberty
#FROM open-liberty:24.0.0.7-full-java17-openj9
#COPY --chown=1001:0  target/Cadastro-Pessoas.war /config/dropins/
#COPY --chown=1001:0  server.xml /config

#configuracao para o payara
FROM payara/server-web:6.2024.1-jdk21
COPY target/Cadastro-Pessoas.war $DEPLOY_DIR