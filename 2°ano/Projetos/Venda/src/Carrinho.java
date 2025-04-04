import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<Produto> itens;

    public Carrinho(){
        itens = new ArrayList<>();
    }

    public List<Produto> getItens(){
        return itens;
    }

    public void adicionarProduto(Produto p){
        itens.add(p);
        System.out.println("Produto adicionado");
    }

    public void removerProduto(Produto p){
        if (itens.remove(p)){
            System.out.println("Produto removido");
        }else {
            System.out.println("Produto não removido");
        }
    }

    public double calcularTotal(){
        double total = 0.0;
        for (Produto p : itens){
            total += p.getPreco() + p.calcularFrete();
        }
        return total;
    }

    // Aplicar desconto somente em itens que implementem Promocional

    public void aplicarDescontoEmPromocionais(double porcentagem){
        //iterar sobre todos os itens da lista
        // verificar quais implementam a interface Promocional

        for(Produto p : itens){
            if(p instanceof Promocional){
                Promocional promo = (Promocional) p;
                promo.aplicarDesconto(porcentagem);
            }else {
                System.out.println("Produto não é promocional");
            }
        }
    }

    public void listarItens(){
        System.out.println("Itens no carrinho");
        for (Produto p : itens){
            p.exibirInfo();
        }
    }
}
