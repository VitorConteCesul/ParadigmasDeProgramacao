package org.vito.model;

// Serve para representar um dado fixo que é o jogador
// Com suas variáveis
//public class Player {
//    private  String id;
//    private String nome;
//    private int pontos;
//    private int vitorias;
//
//    Player(){
//
//    }
//}


// ... A partir do Java 16, geramos tudo automaticamente:

public record Player(
        String id, // id gerado pelo Mongo
        String nome, // nome digitado pelo usuário
        int pontos, //pontos atuais (0-20)
        int vitorias //vitorias acumuladas
) {}