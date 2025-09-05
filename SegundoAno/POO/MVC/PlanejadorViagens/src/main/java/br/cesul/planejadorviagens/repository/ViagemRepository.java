package br.cesul.planejadorviagens.repository;

//Camada de persistencia. Interage com o MongoDB
// e devolve/recebe objetos do modelo
// Não ha nenhuma manipulação/import de JFX/Interface visual

import br.cesul.planejadorviagens.config.MongoConfig;
import br.cesul.planejadorviagens.model.Viagem;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Sorts.ascending;
// Será responsável por consultar e alterar
// os dados do mongo
// E só trabalha com objetos do tipo Viagem
public class ViagemRepository {
    // Conectar com a coleção de viagens do mongo
    // Levando em consideração o CODEC POJO
    private final MongoCollection<Viagem> col =
            MongoConfig.db.getCollection("tripplanner", Viagem.class);

    // CreateReadUpdateDelet's
    public void salvar(Viagem v){
        // Objeto convertido automatimente em BSON
        col.insertOne(v);
    }

    public void atualizar(Viagem v){
        col.replaceOne(Filters.eq("_id", v.getId()), v);
    }

    public boolean conflitaExcluindoId(ObjectId ignorarId, LocalDate ini, LocalDate fim){
        long qtd = col.countDocuments(
                Filters.and(
                        Filters.lte("dataInicio", fim),
                        Filters.gte("dataFim", ini),
                        Filters.ne("_id", ignorarId)
                )
        );
        return qtd > 0;
    }

    public List<Viagem> listarTodas(){
        return col.find()
                // Ordena por data de inicio
                .sort(ascending("dataInicio"))
                // Converte o resultado em uma Lista java
                .into(new ArrayList<>()); // Buscar em todos os documentos da COL
    }

    public double somaCustos(){

        // 1 - Pegar as viagens da coleção
        // 2 - Somar o campo 'custo' em memória
        return col.find()
                .into(new ArrayList<>()) // Converte em lista
                .stream() // usa a Stream API do java (for)
                .mapToDouble(Viagem::getCusto) // Extrai o custo mapeando a variável
                .sum(); // Soma tudo
    }

    // Verifica se o inicio/fim se sobrepoe a alguma viagem existente
    public boolean conflita(LocalDate ini, LocalDate fim){
        long qtd = col.countDocuments(
                Filters.and(
                        // a viagem começa antes ou durante o intervalo
                        // "CAMPO" é menor ou igual ao VALOR"
                        Filters.lte("dataInicio", ini),
                        // a viagem termina depois ou durante o inicio
                        // "CAMPO é maior ou igual ao VALOR
                        Filters.gte("dataFim", fim)
                )
        );
        return qtd > 0;
    }
}
