import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Carrinho carrinho = new Carrinho();
        int opcao = -1;

        while(opcao != 0){
            System.out.println("-- Loja --");
            System.out.println("1 - Adicionar Produto");
            System.out.println("2 - Remover Produto");
            System.out.println("3 - Listar itens do Carrinho");
            System.out.println("4 - Aplicar desconto a produtos prom.");
            System.out.println("5 - Calcular Total (preço + frete)");
            System.out.println("0 - Sair");
            System.out.println("Escolha uma opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao){
                case 1:
                    adicionarProdutoMenu(sc, carrinho);
                    break;
                case 2:
                    removerProdutoMenu(sc, carrinho);
                    break;

                case 3:
                    carrinho.listarItens();
                    break;

                case 4:
                    aplicarDescontoMenu(sc, carrinho);

                case 5:
                    double total = carrinho.calcularTotal();
                    System.out.println("Valor total do carrinho: R$ " + total);
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");

                default:
                    System.out.println("Opção Inválida. Digite outra opção: ");

            }
        }
    }

    private static void adicionarProdutoMenu(Scanner sc, Carrinho carrinho){
        System.out.println("\n -- Adicionar produto --");
        System.out.println("Qual é o tipo do produto que deseja adicionar");
        System.out.println("1 - Eletronico");
        System.out.println("2 - Roupa");
        System.out.println("3 - Livro");
        System.out.println("Digite a opção:");
        int tipo = Integer.parseInt(sc.nextLine());

        System.out.println("Digite o nome do produto: ");
        String nome = sc.nextLine();

        System.out.println("Digite o preço do produto: ");
        double preco = Double.parseDouble(sc.nextLine());

        switch(tipo){
            case 1:
                System.out.println("Digite a marca do eletronico: ");
                String marca = sc.nextLine();
                Eletronico e = new Eletronico(nome, preco, marca);
                carrinho.adicionarProduto(e);
                break;
            case 2:
                System.out.println("Digite o tamanho da roupa: ");
                String tamanho = sc.nextLine();
                Roupa r = new Roupa(nome, preco, tamanho);
                carrinho.adicionarProduto(r);
                break;
            case 3:
                System.out.println("Digite o autor do livro: ");
                String autor = sc.nextLine();
                Livro l = new Livro(nome, preco, autor);
                carrinho.adicionarProduto(l);
                break;
            default:
                System.out.println("Tipo invalido, produto nao adicionado");
        }
    }

    private static void removerProdutoMenu(Scanner sc, Carrinho carrinho){
        System.out.println("\n -- Remover produto --");
        System.out.println("Qual o nome do produto que deseja remover");
        String nomeRemover = (sc.nextLine());

        Produto produtoEncontrado = null;

        for (Produto p : carrinho.getItens()){
            if (p.getNome().equals(nomeRemover)){
                carrinho.removerProduto(p);
                produtoEncontrado = p;
            }
        }

        if (produtoEncontrado != null){
            carrinho.removerProduto(produtoEncontrado);
        }
        System.out.println("Produto não encontrado");
    }

    private static void aplicarDescontoMenu(Scanner sc, Carrinho carrinho){
        System.out.println("\n ---Aplicar Desconto --");
        System.out.println("Digite a porcentagem de desconto ");
        double porcentagem = Double.parseDouble(sc.nextLine());

        carrinho.aplicarDescontoEmPromocionais(porcentagem / 100);
    }
}