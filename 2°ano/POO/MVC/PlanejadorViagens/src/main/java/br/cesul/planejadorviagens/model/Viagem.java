package br.cesul.planejadorviagens.model;
import org.bson.types.ObjectId;
import java.time.LocalDate;

// Espelhar os dados do banco através de uma entidade
// Esta classe vai representar UMA VIAGEM

// O que tem que ter:
// - Construtor
// - Atributos com getters/setters



public class Viagem {
    private ObjectId id; //Armazenar _id gerado pelo mongo
    private String partida;
    private String destino;
    private double custo;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    // POJO Codec
    // POJO Significa Plain Old Java Object
    public Viagem(){}

    public Viagem(ObjectId id, String partida, String destino, double custo, LocalDate dataInicio, LocalDate dataFim) {
        this.id = id;
        this.partida = partida;
        this.destino = destino;
        this.custo = custo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public String getPartida(){
        return partida;
    }

    public void setPartida(String partida) {this.partida = partida;}

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}
