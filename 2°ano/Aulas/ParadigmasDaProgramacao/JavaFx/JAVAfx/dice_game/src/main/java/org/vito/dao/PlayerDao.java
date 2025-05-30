package org.vito.dao;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.model.Sorts;
import jdk.dynalink.linker.LinkerServices;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.vito.model.Player;
import org.vito.utils.MongoUtil;

import java.util.ArrayList;
import java.util.List;

// DAO = Date Access Object
// Chamará todos os métodos de uma classe intermediaria, sem passar pela main
// Desta forma a main não "fala" direto com o banco
public class PlayerDao {
    // Obtem a collection 'players' (e se não existir, o mongo cria na hora)

    private final MongoCollection<Document> col = MongoUtil.db().getCollection("players");


    // O MongoDB salva em Documents
    // Na nossa aplicação, precisamos do dadoem Player
    // O nome dessa conversão é serialização
    // Neste método: Converter documento do mongo em objeto Player

    private Player toPlayer(Document d){
        return new Player(
                d.getObjectId("_id").toHexString(),
                d.getString("nome"),
                d.getInteger("pontos", 0),
                d.getInteger("vitorias", 0)
        );
    }

    public List<Player> findAll(){
        List<Player> list = new ArrayList<>();

        for(Document d : col.find().sort(Sorts.ascending("nome"))){
            list.add(toPlayer(d));
        }

        return list;
    }

    public void insert(String nome){
        if(nome == null || nome.isBlank()) return;
        col.insertOne(new Document("nome", nome)
                .append("pontos", 0)
                .append("vitorias", 0)
        );
    }

    public void addPoints(String id, int pts){
        Object oid = new ObjectId(id);
        // O update recebe 2 parametros, que é o Documento e o valor
        col.updateOne(eq("_id", oid), inc("pontos", pts));

        //Ler o documento para checar se estourou os 20 pontos
        Document doc = col.find(eq("_id", oid)).first();

        // Verificar se o documento não é nulo e se estourou 20 pontos
        if(doc != null && doc.getInteger("pontos", 0 ) >= 20){
            // 1 - Resetar os "pontos"
            // 2 - Incrementar uma "vitoria"
            col.updateOne(eq("_id", oid),
                        combine(set("pontos", 0), inc("vitorias", 1))
                    );
        }
    }

    public void resertAll(){
        // Filtro vazio {}, deleta todos os campos
        col.deleteMany(new Document());
    }
}
