#include <stdio.h>
#include <stdlib.h>
#include <locale.h>

typedef struct {
    char titulo[100];
    char autor[100];
    int ano;
    char isbn[20];
    int quantidade;
} Livro;

typedef struct{
    Livro *livros;
    int tamanho;
    int capacidade;
} Catalogo;

void inicializarLista(Catalogo *catalogo, int capacidadeInicial){
    catalogo->livros = (Livro *)malloc(capacidadeInicial * sizeof(Livro));
    if(catalogo->livros == NULL){
        printf("Erro ao alocar!");
        exit(1);
    }

    catalogo->tamanho = 0;
    catalogo->capacidade = capacidadeInicial;
}

void aumentarCapacidade(Catalogo *catalogo){
    catalogo->capacidade *= 2;
    catalogo->livros = (Livro *)realloc(catalogo->livros, catalogo->capacidade * sizeof(Livro));
    if(catalogo->livros == NULL){
        printf("Erro ao alocar!");
        exit(1);
    }
}

void adicionarLivro(Catalogo *catalogo, char *titulo, char *autor, int ano, char *isbn, int quantidade){
    if(catalogo->tamanho == catalogo->capacidade){
        aumentarCapacidade(catalogo);
    }

    strcpy(catalogo->livros[catalogo->tamanho].titulo, titulo);
    strcpy(catalogo->livros[catalogo->tamanho].autor, autor);
    catalogo->livros[catalogo->tamanho].ano = ano;
    strcpy(catalogo->livros[catalogo->tamanho].isbn, isbn);
    catalogo->livros[catalogo->tamanho].quantidade = quantidade;

    catalogo->tamanho++;
    printf("Livro adicionado com sucesso!");
}

void removerLivro(Catalogo *catalogo, char *isbn){
    int encontrado = 0;
    for(int i = 0; i < catalogo->tamanho; i++){
        if(strcmp(catalogo->livros[i], isbn) == 0){
            encontrado = 1;
            for(int j = i; catalogo->tamanho - 1; j++){
                catalogo->livros[j] = catalogo->livros[j + 1];
            }
            catalogo->tamanho--;
            printf("Livro removido \n");
            break;
        }
    }

    if(!encontrado){
        printf("Livro nao encontrado \n");
    }
}

void buscarLivro(Catalogo *catalogo, char *termo, int tipo){
    for(int i = 0; i < catalogo->tamanho; i++){
        int encontrado = 0;

        switch (tipo){
            case 1:
                if(strcmp(catalogo->livros[i].titulo, termo) == 0){
                    encontrado = 1;
                }
                break;
            case 2:
                if(strcmp(catalogo->livros[i].autor, termo) == 0){
                    encontrado = 1;
                }
                break;
            case 3:
                if(strcmp(catalogo->livros[i].isbn, termo) == 0){
                    encontrado = 1;
                }
                break;
            default:
                printf("Tipo invalido");
                return;
        }

        if(encontrado){
            printf("Livro encontrado: Titulo %s, Autor: %s, Ano: %d, Quantidade %d \n",
                   catalogo->livros[i].titulo, catalogo->livros[i].autor, catalogo->livros[i].ano, catalogo->livros[i].quantidade
                    );
            return;
        }
    }
    printf("Livro nao encontrado\n");
}

void listarLivros(Catalogo *catalogo){
    if(catalogo->tamanho == 0){
        printf("Lista vazia \n");
    }else{
        for(int i = 0; i < catalogo->tamanho; i++){
        printf("Livro: Titulo %s, Autor: %s, Ano: %d, Quantidade %d \n",
                   catalogo->livros[i].titulo, catalogo->livros[i].autor, catalogo->livros[i].ano, catalogo->livros[i].quantidade
                    );
        }
    }
}

void liberarMemoria(Catalogo *catalogo){
    free(catalogo->livros);
    catalogo->livros = NULL;
}

void atualizarCopias(Catalogo *catalogo, char *isbn, int novaQuantidade){
    for(int i = 0; i < catalogo->tamanho; i++){
        if(strcmp(catalogo->livros[i].isbn, isbn) == 0){
            catalogo->livros[i].quantidade = novaQuantidade;
            printf("Quantidade alterada");
            return;
        }
    }
    printf("Elemento nao encontrado");
}

int main()
{
    setlocale(LC_ALL, "portuguese");
    Catalogo catalogo;
    inicializarLista(&catalogo, 2);

    int opcao;
    char titulo[100];
    char autor[100];
    int ano;
    char isbn[20];
    int quantidade;

    do {
        printf("\n ---- Sistema de gerenciamento de biblioteca ---- \n");
        printf("1. Cadastrar Livro \n");
        printf("2. Remover Livro \n");
        printf("3. Buscar Livro \n");
        printf("4. Listar todos os livros \n");
        printf("5. Atualizar Livro \n");
        printf("6. Encerrar \n");
        printf("Digite uma opção: ");
        scanf("%d", &opcao);

        switch (opcao){
            case 1:
                printf("Digite o titulo do livro: ");
                scanf("%s", titulo);
                getchar();
                printf("Digite o autor do livro: ");
                scanf("%s", autor);
                getchar();
                printf("Digite o ano do livro: ");
                scanf("%d", &ano);
                getchar();
                printf("Digite o ISBN do livro: ");
                scanf("%s", isbn);
                getchar();
                printf("Digite a quantidade do livro: ");
                scanf("%d", &quantidade);

                adicionarLivro(&catalogo, titulo, autor, ano, isbn, quantidade);
                break;
            case 2:
                printf("Digite o ISBN do livro a ser removido: ");
                scanf("%s", isbn);
                removerLivro(&catalogo, isbn);
                break;
            case 3:
                printf("Buscar por: 1. Titulo | 2. Autor | 3. ISBN \n");
                int tipo;
                scanf("%d", &tipo);
                getchar();
                printf("Digite o termo da busca: ");
                scanf("%s", titulo);
                getchar();
                buscarLivro(&catalogo, titulo, tipo);
                break;
            case 4:
                listarLivros(&catalogo);
                break;
            case 5:
                printf("Digite o ISBN do livro que quer atualizar: ");
                scanf("%s", isbn);
                getchar();
                printf("Digite a nova quantidade: ");
                scanf("%d", &quantidade);
                getchar();
                atualizarCopias(&catalogo, isbn, quantidade);
                break;
            case 6:
                printf("Encerrando o programa... \n");
                liberarMemoria(&catalogo);
                break;
        }
    }while(opcao != 6);


    return 0;
}
