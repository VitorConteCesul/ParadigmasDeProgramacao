package br.cesul.expensetracker.config;

// Classe Singleton que expõe um link para a DataBase
// com mapeamento automático de pojo's

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.pojo.PojoCodecProvider;

public class MongoConfig {

    // Declaração de variáveis clientes
    private static final String URI = "mongodb://localhost:27017";
    private static final MongoClient client;
    public static final MongoDatabase db;

    // Codec de conversão dos pojo's
    // com mapeamento automático de pojo's

    static {
        var pojoCodec = PojoCodecProvider.builder().automatic(true).build();

        var settings = MongoClientSettings.builder().codecRegistry(CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(pojoCodec)
        )).applyConnectionString(new ConnectionString(URI)).build();

        client = MongoClients.create(settings);
        db = client.getDatabase("expensetracker");
    }

    //Construtor vazio
    private MongoConfig(){}

}
