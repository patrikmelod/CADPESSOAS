/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cp.data.crud.interfaces;

import com.cp.util.AppLog;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author utfpr
 */
public class EMNames implements Serializable {

    public static final String EMN1 = "default";

    public static Map<String, String> getEMN1Props()  {

        //Em producao Crie uma variavel de ambiente DATABASE_URL. Ex.:
        //DATABASE_URL="jdbc:postgresql://localhost:5432/localdb"
        //Tambem e necessario criar as variaveis DATABASE_USERNAME e DATABASE_PASSWORD
        String BD_producao = System.getenv("DATABASE_URL");
        Map<String, String> properties = new HashMap<>();
        if (BD_producao == null) { //caso nao tenha a variavel de ambiente
            AppLog.getInstance().info("Configurar banco de dados para acesso local");
            properties.put("jakarta.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/localdb");
            properties.put("jakarta.persistence.jdbc.user", "postgres");
            properties.put("jakarta.persistence.jdbc.driver", "org.postgresql.Driver");
            properties.put("jakarta.persistence.jdbc.password", "postgres");
        } else { //se a variavel de ambiente foi criada, indica que o projeto está alocado em producao
            AppLog.getInstance().info("#### Configurar banco de dados em producao #### ");
            String jdbc_database_username = System.getenv("DATABASE_USERNAME");
            String jdbc_database_password = System.getenv("DATABASE_PASSWORD");
            System.out.println("variaveis: " +jdbc_database_username +":"+jdbc_database_password);

            properties.put("jakarta.persistence.jdbc.user", jdbc_database_username);
            properties.put("jakarta.persistence.jdbc.driver", "org.postgresql.Driver");
            properties.put("jakarta.persistence.jdbc.password", jdbc_database_password);
            System.out.println("XXXXXXXXXX ===== fim config");
            properties.put("jakarta.persistence.jdbc.url", BD_producao );
            try {
                System.out.println("Carregar driver");
                Class.forName("org.postgresql.Driver");
            }catch(Exception e){
                System.out.println("ERRO #############################");
            }

           }
        return properties;
    }
}
