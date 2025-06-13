package br.cesul.rpschallenge.util;

import br.cesul.rpschallenge.model.Move;

import java.util.ArrayDeque;
import java.util.Deque;

// Aqui será a "IA" adaptativa
// Funciona assim:
// - Manter as N (window) ultimas jogadas do player
// - Contar qual jogada aparece mais neste intervalo
// - Devolver a jogada que vence esta mais frequente
public class CpuStrategy {
    // Historico "circular" das jogadas do player
    // Usar ArrayDeque -> addLast / removeFirst

    // Variavel int com o tamanho da "janela"
    // Guardar um numero que diz quantas jogadas recentes
    // do player a IA deve analizar

    // Construtor que inicializa a "janela" de jogadores

    private final Deque<Move> historico;
    private final int janela; // tamanho da janela de jogadas a considerar

    public CpuStrategy(int janela) {
        this.janela = janela;
        this.historico = new ArrayDeque<>();
    }

    //Método que vai ser responsável por calcular
    // qual jogada a CPU deve fazer agora

    public Move proximaJogada(Move lastPlayerMove){
        // Atualizar o Deque com a jogada mais recente (addLast)

        // Se passou do limite da janela, descartar o elemento mais
        // antigo (removeFirst)

        //Tratar se ainda é a primeira jogada

        // Contar quantas vezes cada jogada aparece na janela
        // historico.stream().filter(move -> EXPRESSAO).count()
        // Ex: se o mais frequente for papel, você retorna tesoura

        // Atualiza o histórico
        historico.addLast(lastPlayerMove);
        if (historico.size() > janela) {
            historico.removeFirst();
        }

        // Se ainda não tem histórico suficiente, retorna jogada aleatória
        if (historico.size() < janela) {
            return jogadaAleatoria();
        }

        // Conta frequência das jogadas
        long pedras = historico.stream().filter(m -> m == Move.PEDRA).count();
        long papeis = historico.stream().filter(m -> m == Move.PAPEL).count();
        long tesouras = historico.stream().filter(m -> m == Move.TESOURA).count();

        // Descobre qual foi a jogada mais frequente do player
        Move jogadaFrequente;
        if (pedras >= papeis && pedras >= tesouras) {
            jogadaFrequente = Move.PEDRA;
        } else if (papeis >= pedras && papeis >= tesouras) {
            jogadaFrequente = Move.PAPEL;
        } else {
            jogadaFrequente = Move.TESOURA;
        }

        // Retorna a jogada que vence a mais frequente
        return jogadaQueVence(jogadaFrequente);
    }

    private Move jogadaQueVence(Move move) {
        return switch (move) {
            case PEDRA -> Move.PAPEL;
            case PAPEL -> Move.TESOURA;
            case TESOURA -> Move.PEDRA;
        };
    }

    private Move jogadaAleatoria() {
        Move[] moves = Move.values();
        return moves[(int) (Math.random() * moves.length)];
    }
}
