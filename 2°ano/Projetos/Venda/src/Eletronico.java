public class Eletronico extends Produto implements Promocional{
    private String marca;

    public Eletronico(String nome, double preco, String marca){
        super(nome, preco);
        this.marca = marca;
    }

    public Eletronico(String nome, String marca){
        super(nome);
        this.marca = marca;
    }

    @Override
    public double calcularFrete(){
        // 20 reais + 5% do preco
        return 20 + (0.05 + getPreco());
    }

    @Override
    public boolean aplicarDesconto(double porcentagem){
        // O eletronico tem desconto maximo de 10%
        if (porcentagem > 0.1){
            System.out.println("Desconto não aplicado");
            return false;
        }

        setPreco((1 - porcentagem) * getPreco());
        System.out.println("Desconto de: " + (porcentagem * 100) + "%");
        return true;
    }

    @Override
    public void exibirInfo(){
        System.out.println("Eletronico: " + getNome());
    }
}
