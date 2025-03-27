#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int data;
    struct Node* next;
}Node;

typedef struct Queue{
    Node *front; // frente
    Node *rear; // tras - TRASEIRA
} Queue;

// função pra criar uma fila vazia
Queue* criarFila(){
    Queue* fila = (Queue*)malloc(sizeof(Queue));
    fila->front = fila->rear = NULL;
    return fila;
}

//funçao para adicionar um elemento no final da fila(enqueue)

void enqueue(Queue* fila, int valor){
    Node* novoNode = (Node*)malloc(sizeof(Node));
    novoNode->data = valor;
    novoNode->next = NULL;

    if(fila->rear == NULL){
        fila->front = fila->rear = novoNode;
        return;
    }

    fila->rear->next = novoNode;
    fila->rear = novoNode;
}

//função para remover um elemento da frente da fila(dequeue)

int dequeue(Queue* fila){
    if(fila->front == NULL){
        printf("Fila Vazia\n");
        return -1;
    }

    Node* temp = fila->front;
    int valor = temp->data;

    fila->front = fila->front->next;

    if(fila->front == NULL){
        fila->rear = NULL;
    }

    free(temp);
    return valor;
}

int front(Queue* fila){
    if(fila->front == NULL){
        printf("Fila vazia\n ");
        return -1;
    }

    return fila->front->data;
}

void exibirFila(Queue* fila){
    Node* temp = fila->front;
    printf("Estado atual da fila\n");

    while(temp != NULL){
        printf("%d -> ", temp->data);
        temp = temp->next;
    }
    printf("NULL\n");
}




int main()
{
    Queue* fila = criarFila();

    //adiciona 10 a fila
    enqueue(fila, 10);
     //adiciona 20 a fila
    enqueue(fila, 20);
     //adiciona 30 a fila
    enqueue(fila, 30);

    printf("elemento da frente: %d\n", front(fila));

    exibirFila(fila);

    printf("Desenfileirando: %d\n", dequeue(fila));

    printf("elemento da frente apos desenfileirar: %d\n", front(fila));

    exibirFila(fila);

    return 0;
}
