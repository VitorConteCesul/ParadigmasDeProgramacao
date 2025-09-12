package org.example.repository;

import org.example.config.MongoConfig;
import org.example.model.Lancamento;
import com.mongodb.client.MongoCollection;

import java.util.ArrayList;
import java.util.List;

public class LancamentoRepository {

    private final MongoCollection<Lancamento> col;

    public LancamentoRepository() {
        this.col = MongoConfig.db.getCollection("lancamentos", Lancamento.class);
    }

    public void inserir(Lancamento l) {
        col.insertOne(l);
    }

    public List<Lancamento> listarTodos() {
        return col.find().into(new ArrayList<>());
    }
}
