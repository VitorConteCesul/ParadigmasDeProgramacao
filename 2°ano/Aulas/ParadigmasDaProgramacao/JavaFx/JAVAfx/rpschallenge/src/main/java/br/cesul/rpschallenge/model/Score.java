package br.cesul.rpschallenge.model;

// Um score representa o resumo final de uma partida
// (após 5 rodadas) que será gravado no mongo
public class Score {
    private String id;
    private String date; //05/05/2025 20:35
    private int wins;
    private int draws;
    private int losses;
    private  double winRate; // percentual de vitorias do usuario
    private int window; // tamanho da janela de otimimização

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }

    public int getWindow() {
        return window;
    }

    public void setWindow(int window) {
        this.window = window;
    }
}
