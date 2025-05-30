package org.vito.utils;

// Essa classe serve para realizarmos a conexão com o banco
// Reaproveitando uma unica conexão

// Então qualquer parte do sistema que utilizar o banco,
// Pode simplismente chamar o 'db' criado por essa classe

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoUtil {
    private static final MongoClient CLIENT = MongoClients.create("mongodb://localhost:27017/");

    // Criação de atributo "DB" que será chamado pelos DAO's
    public static MongoDatabase db(){
        return CLIENT.getDatabase("dice_race");
    }
}
