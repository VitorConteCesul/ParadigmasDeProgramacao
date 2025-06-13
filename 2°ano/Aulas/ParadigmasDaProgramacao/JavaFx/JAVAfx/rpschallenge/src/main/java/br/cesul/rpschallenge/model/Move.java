package br.cesul.rpschallenge.model;

// Esta estrutura representa as tres jogadas possíveis do jogo
public enum Move {
    // Cada linha define um valor único e imútavel
    PEDRA,
    PAPEL,
    TESOURA;

    public static Move getRandomMove() {
        return values()[(int) (Math.random() * 3)];
    }

    // Método que compara  ESTA jogada (this) com outra (parametro recebido)
    // Possíveis retornos das jogadas
    // - 1 > vitoria
    // - 0 > empate
    // - -1 > Derrota
    public int versus(Move other){
        if(this == other) return 0;

        // Operador ternário: CONDIÇÃO ? VERDADEIRO : FALSO
        return(this == PEDRA && other == TESOURA) ||
                (this == PAPEL && other == PEDRA) ||
                (this == TESOURA && other == PAPEL) ? 1 : -1;
    }

    // Sortear uma jogada aleatória para quando a CPU precisar
    // de uma jogada sem estrategia
    public static Move random(){
        return values()[(int) (Math.random() * 3)];
    }
}
