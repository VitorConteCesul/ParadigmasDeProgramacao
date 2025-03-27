#include <stdio.h>
#include <stdlib.h>
#include <string.h>


typedef struct HistNode{
    char pagina[100];
    struct HistNode* next;
}HistNode;

typedef struct DownNode{
    char arquivo[100];
    struct DownNode* next;
}DownNode;

typedef struct Queue{
    DownNode *front;
    DownNode *rear;
}Queue;

// pushHist: adicionar ao historico uma nova pagina(empilhar)

void push(HistNode** top, char* novaPagina){
    HistNode* newNode = (HistNode*)malloc(sizeof(HistNode));
    strcpy(newNode->pagina, novaPagina);

    newNode->next = *top;
    *top = newNode;
    printf("Empilhando: %s", novaPagina);
}

//popHist: remove a pagina do historio e volta para a anterior(desempilhar)

char pop(HistNode** top){
    if(*top == NULL){
        printf("A pilha está vazia\n");
        return NULL;
    }

    HistNode* temp = *top;

    char* pagina = strdup(temp->pagina);

    *top = (*top)->next;
    printf("desempilhando: %s", temp->pagina);

    free(temp);

    return pagina;
}
// função para criar uma fila de downloads.

Queue* criarFilaDownloads(){
    Queue* fila = (Queue*)malloc(sizeof(Queue));
    fila->front = NULL;
    fila->rear = NULL;
    return fila;
}

//enqueueDownload: adiciona um novo download no final da fila

void enqueue(Queue* fila, char* arquivo){
    DownNode* novoNode = (DownNode*)malloc(sizeof(Queue));

    strcpy(novoNode->arquivo, arquivo);
    novoNode->next = NULL;

    if(fila->rear == NULL){
        fila->front = fila->rear = novoNode;
        return;
    }

    fila->rear->next = novoNode;
    fila->rear = novoNode;

    printf("Enfileirando %s", arquivo);
}

// dequere: remove o primeiro download da fila

char* dequere(Queue* fila){
    if(fila->front == NULL){
        printf("Fila vazia\n");
        return NULL;
    }
    DownNode* temp = fila->front;
    char* arquivo = strdup(temp->arquivo);

    fila->front = fila->front->next;

    if(fila->front == NULL){
        fila->rear = NULL;
    }

    free(temp);
    return arquivo;
}



int main() {

HistNode* historico = NULL;

push(&historico, "TESTE1\n");
push(&historico, "TESTE2\n");
push(&historico, "TESTE3\n\n");

pop(&historico);


Queue* downloads = criarFilaDownloads();

enqueue(&downloads, "download1");
enqueue(&downloads, "download2");
enqueue(&downloads, "download3");




}


