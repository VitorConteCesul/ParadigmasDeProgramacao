    public abstract class Produto {
    private String nome;
    private double preco;

    public Produto(String nome) {
        this.nome = nome;
        this.preco = 0.0;
    }

    public Produto(String nome, double preco){
        this.nome = nome;
        setPreco(preco);
    }

    public String getNome() {
        return nome;
    }
    public double getPreco(){
        return preco;
    }

    public void setPreco (double preco){
        if(preco <= 0){
            this.preco = preco;
        }else{
            System.out.println("Preco invalido!");
        }
    }

    // Metodo concreto
    public void exibirInfo(){
        System.out.println("Produto: " + nome + "| Preço: R$" + preco);
    }

    // Metodo abstrado

    public abstract double calcularFrete();

}
