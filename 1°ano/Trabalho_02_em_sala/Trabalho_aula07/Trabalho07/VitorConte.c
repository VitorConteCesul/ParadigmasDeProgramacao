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

void push(HistNode** top, char* valor){
    HistNode* newNode = (HistNode*)malloc(sizeof(HistNode));
    strcpy(newNode->pagina, valor);

    newNode->next = *top;
    *top = newNode;
    printf("Empilhando: %s", valor);
}

//popHist: remove a pagina do historio e volta para a anterior(desempilhar)

int pop(HistNode** top){
    if(*top == NULL){
        printf("Historico vazio\n");
        return -1;
    }

    HistNode* temp = *top;

    char* valor = temp->pagina;

    *top = (*top)->next;
    printf("desempilhando: %s", temp->pagina);

    free(temp);
    return valor;
}
// função para criar uma fila de downloads.

Queue* criarFilaDownloads(){
    Queue* fila = (Queue*)malloc(sizeof(Queue));
    fila -> front = NULL;
    fila -> rear = NULL;
    return fila;
}

//enqueueDownload: adiciona um novo download no final da fila

void enqueue(Queue* fila, char* valor){
    DownNode* novoNode = (DownNode*)malloc(sizeof(Queue));

    strcpy(novoNode->arquivo, valor);
    novoNode->next = NULL;

    if(fila->rear == NULL){
        fila->front = fila->rear = novoNode;
        return;
    }

    fila->rear->next = novoNode;
    fila->rear = novoNode;

    printf("Enfileirando %s", valor);
}

// dequere: remove o primeiro download da fila

void dequere(Queue* fila){
    if(fila->front == NULL){
        printf("Fila vazia");
        return -1;
    }
     DownNode* temp = fila->front;
    int valor = temp->arquivo;

    fila->front = fila->front->next;

    if(fila->front == NULL){
        fila->rear = NULL;
    }

    free(temp);
    return valor;
}



int main() {

HistNode* pilha = NULL;

push(&pilha, "TESTE1\n");
push(&pilha, "TESTE2\n");
push(&pilha, "TESTE3\n\n");

pop(&pilha);


Queue* fila = criarFilaDownloads();

enqueue(&fila, "download1");
enqueue(&fila, "download2");
enqueue(&fila, "download3");




}


