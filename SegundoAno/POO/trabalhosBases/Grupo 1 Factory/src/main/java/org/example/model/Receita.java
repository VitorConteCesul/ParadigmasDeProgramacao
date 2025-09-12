package org.example.model;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@BsonDiscriminator
public class Receita extends Lancamento {
    public Receita() {}
    public Receita(String descricao, double valor, java.time.LocalDate data) {
        super(descricao, valor, data, "RECEITA");
    }
}
