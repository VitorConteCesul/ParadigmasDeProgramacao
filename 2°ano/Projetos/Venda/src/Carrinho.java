import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<Produto> itens;

    public Carrinho(){
        itens = new ArrayList<>();
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
}
