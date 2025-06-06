package br.cesul.rpschallenge.model;

// Guardar as estatísticas do jogador durante UMA unica partida
// Não mostra nada em tela, nem acessa banco
// sua função é guardar e atualizar os dados sobre a partida
// e calcular informações derivadas (total de vitórias, % vitorias)

public class PlayerStatis {
    private int wins;
    private int draws;
    private int losses;

    // Metodo que atualiza os contadores com base no resultado da rodada
    public void register(int result){
        switch (result){
            case 1 -> wins++;
            case 0 -> draws++;
            case -1 -> losses++;
        }
    }

    public int getWins() {
        return wins;
    }

    public int getDraws() {
        return draws;
    }

    public int getLosses() {
        return losses;
    }

    // método que retorne o total de rodadas já jogadas
    public int totalRounds(){
        return wins + losses + draws;
    }

    // metodo calcula o percentual de vitorias de 0 a 100

    public double winRate(){
        return totalRounds() == 0 ? 0.0 : (double) (wins * 100 / totalRounds());
    }

    // Apresentar os dados do jogo em log


    @Override
    public String toString() {
        return "V: " + wins +
                "E" + draws +
                "D" + losses;
    }
}
