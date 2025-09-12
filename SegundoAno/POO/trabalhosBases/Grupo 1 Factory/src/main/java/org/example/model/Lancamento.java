package org.example.model;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@BsonDiscriminator // permite polimorfismo Receita/Despesa no POJO codec
public abstract class Lancamento {
    @BsonId
    private ObjectId id;
    private String descricao;
    private double valor;
    private LocalDate data;
    private String tipo; // "RECEITA" ou "DESPESA"

    public Lancamento() {}
    public Lancamento(String descricao, double valor, LocalDate data, String tipo) {
        this.descricao = descricao; this.valor = valor; this.data = data; this.tipo = tipo;
    }

    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
