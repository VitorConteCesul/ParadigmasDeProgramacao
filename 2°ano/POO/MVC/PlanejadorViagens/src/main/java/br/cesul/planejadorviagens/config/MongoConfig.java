package br.cesul.planejadorviagens.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.pojo.PojoCodecProvider;

// Classe utilitária (Singleton) que expõe um DB
// Mantem uma unica conexão durante o processo

// Faremos suporte ao mapeamento dos POJO's
public class MongoConfig {
    public static final String URI = "mongo://localhost:27017";
    // Cliente que abre sessão com o banco
    public static final MongoClient client;
    // Representa a entidade ex: "tripPlanner"
    public static final MongoDatabase db;

    //Bloco estático de inicialização (executa só uma vez)

    static {
        // 1 - Criar um "Codec provider" que converte POJO's
        // Para documentos BSON automaticamente (e ao contrário também)

        PojoCodecProvider pojoCodec = PojoCodecProvider.builder().automatic(true).build();

        // 2 - Criar um codec registry que é uma lista de codecs que
        // o mongo vai ser
        // - Primeiro: Inclui o codec padrão do mongo
        // - Depois: Inclui o codec para POJO's que criamos acima

        MongoClientSettings settins = MongoClientSettings.builder().codecRegistry(
                CodecRegistries.fromRegistries(
                        MongoClientSettings.getDefaultCodecRegistry(),
                        CodecRegistries.fromProviders(pojoCodec)
                )
        ).applyConnectionString(new com.mongodb.ConnectionString(URI)).build();

        // Criar o cliente mongo com as configurações personalizadas
        client = MongoClients.create(settins);

        // Obter a referencia do banco de dados que sera usado em toda a aplicação

        db = client.getDatabase("tripplanner");
    }

    // Construtor não instanciavel (private)
    // Impede que a classe seja instanciada
    private MongoConfig(){}
}

// Como você pode obter os dados do DB:
// MongoConfig.db.getCollection("tripplenner", Viagem.class);
// Alem disso... para manipular não precisa passar por serialização
// viagens.insertOne(new Viagem());

// ASSIM PEGA TODOS: PojoCodecProvider pojoCodec = PojoCodecProvider.builder().automatic(true).build();
// Se você quiser limitar o mapeamento de atributos a um pacote especifico
// PojoCodecProvider pojoCodec = PojoCodecProvider.builder().register("br.cesul.planejadorviagem.model").build()