#include <stdio.h>
#include <stdlib.h>

//Estrutura para uma pilha

typedef struct Node{
    int data;
    struct Node* next;
}Node;


//função para empinhar um elemento(push)->adicionar
void push(Node** top, int valor){
    Node* novoNode = (Node*)malloc(sizeof(Node));
    novoNode->data = valor;

    novoNode->next = *top;
    *top = novoNode;
}

//função para desempilhar um elemento(pop)->remover
int pop(Node** top){
    if(*top == NULL){
        printf("Pilha vazia \n");
        return -1;
    }

    Node* temp = *top;

    int valor = temp->data;

    *top = (*top)->next;

    free(temp);
    return valor;
}

// função para ver o elemento do topo(peek);
int peek(Node* top){
    if(top = NULL){
        printf("Pilha vazia \n");
        return -1;
    }
    return top->data;
}

//funçao para exibir todos os elementos da pilha
void exibirPilha(Node* top){
    Node* temp = top;
    printf("Estado atual da pilha \n");

    while(temp != NULL){
        printf("%d \n", temp->data);
        temp = temp->next;
    }

    printf("NULL\n");
}

int main(){
    Node* pilha = NULL;
    //empilhando 10
    push(&pilha, 10);
    return 0;
     //empilhando 20
    push(&pilha, 20);
    return 0;
     //empilhando 30
    push(&pilha, 30);

    printf("Topo da pilha: %d\n", peek(pilha));

    exibirPilha(pilha);

    printf("Desempilhando: %d\n", pop(&pilha));

    printf("Topo da pilha após desempilhar: %d\n", peek(pilha));
    exibirPilha(pilha);
    return 0;
}
