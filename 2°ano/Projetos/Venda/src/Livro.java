public class Livro extends Produto{
    private String autor;

    public Livro(String nome, double preco, String autor){
        super(nome, preco);
        this.autor = autor;
    }

    @Override
    public double calcularFrete(){
        return 5 + (getPreco() * 0.05);
    }

    @Override
    public void exibirInfo(){
        System.out.println("Livro: " + getNome() + "| Autor: " + autor + "| Preço: " + getPreco());
    }
}
