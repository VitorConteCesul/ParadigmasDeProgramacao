#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    char musica[100];
    struct Node* next;
    struct Node* prev;
} Node;

void adicionarInicio(Node** head, char* novaMusica){
    Node* novoNode = (Node*)malloc(sizeof(Node));
    strcpy(novoNode->musica, novaMusica);

    novoNode->next = *head;
    novoNode->prev = NULL;

    if(*head != NULL){
        (*head)->prev = novoNode;
    }else{
        printf("A lista est� vazia!");
        return;
    }

    *head = novoNode;
}

void adicionarFim(Node** head, char novaMusica){
    Node* novoNode = (Node*)malloc(sizeof(Node));
    // Cria um ponteiro para recorrer a lista at� o ultimo n�.
    Node* ultimo = *head;

    // Copia o nome da musica para o n�.
    strcpy(novoNode->musica, novaMusica);

    // Como este n� ser� o ultimo, o penteiro next � NULL.
    novoNode->next = NULL;

    // Se a lista est� vazia, o novo n� serpa o primeiro e unico n�.
    if(*head == NULL){
    // N�o ha n� anterior, pois este ser� o unico n�.
        novoNode->prev = NULL;
    // Atualizar o ponteiro da HEAD para o novo n�.
        *head = novoNode;
    // Sai da fun��o porque a inser��o esta completa.
        return;
    }

    // Percorre a lista at� encontrar o ultimo n�.
    while(ultimo->next != NULL){
        ultimo = ultimo->next;
    }

    // Atualiza os pnteiros para adicionar o novo n� no final da lista.
    // O antigo ultimo n[o agora aponta para o novo n� como proximo.
    ultimo->next = novoNode;
    novoNode->prev = ultimo;
}

void removerMusica(Node** head, char* musicaRemover){
    // 1: Criar m ponteiro tempor�rio para percorrer a lista.
    Node* temporario = *head;

    // 2: Percorrer a lista em busca do n� que contem a musica a ser removida.
    while(temporario != NULL && strcmp(temporario->musica, musicaRemover) != 0){
        temporario = temporario->next;
    }
    // 3: Se a musica n�o foi encontrada, sai da fun��o.
    if(temporario == NULL) return;

    // 4: Se o n� a ser removido for o primeiro, atualizar o HEAD.
    if(*head == temporario){
        *head = temporario->next;
    }
    // 5: Se o n� n�o for ultimo, ajusta o prev do proximo n�.
    if(temporario->next != NULL){
        // O proximo n� aponta para o n� anterior ao n� removido
        temporario->next->prev = temporario->prev;
    }
    // 6: Se o n� a ser removido n�o � o primeior, ajusta o ponteiro next do n� anteriro.
    if(temporario->prev != NULL){
        // O n� anterior aponta para o proximo
        temporario->prev->next = temporario->next;
    }
    // 7: Libera mem�ria alocada.
    free(temporario);
}

int main()
{
    printf("Hello world!\n");
    return 0;
}
