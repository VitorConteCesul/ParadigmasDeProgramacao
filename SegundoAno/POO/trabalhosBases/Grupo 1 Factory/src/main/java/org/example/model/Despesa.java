package org.example.model;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@BsonDiscriminator
public class Despesa extends Lancamento {
    public Despesa() {}
    public Despesa(String descricao, double valor, java.time.LocalDate data) {
        super(descricao, valor, data, "DESPESA");
    }
}
