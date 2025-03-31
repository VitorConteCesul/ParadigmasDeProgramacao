public class Roupa extends Produto implements Promocional{
    private String tamanho;

    public Roupa(String nome, double preco, String tamanho) {
        super(nome, preco);
        this.tamanho = tamanho;
    }

    @Override
    public double calcularFrete(){
        return 10.0;
    }

    @Override
    public boolean aplicarDesconto(double porcentagem) {
        // Roupas aceitam até 30% de desconto
        if (porcentagem > 0.3) {
            System.out.println("Desconto excedeu o máximo");
            return false;
        }
        setPreco((1 - porcentagem) * getPreco());
        System.out.println("Desconto de: " + (porcentagem * 100) + "%");

        return true;
    }
    @Override
    public void exibirInfo(){
        super.exibirInfo();
        System.out.println("Roupa: " + getNome() + "| Tamanho" + tamanho + "| Preco: " + getPreco()) ;
    }
}
