package br.cesul.studentregistry.utils;

public class Validators {
    // Para que o teste notBlank passe precisamos implementar o minímo
    // (dada uma entrada, checar null e espaços)

    // Construtor privado: Este classe não deve ser
    // instanciada (apenas uso estático)
    private Validators(){}

    public static boolean notBlank(String input){
        // 1 - checar null
        // 2 - retirar espaços e ver se está vazio
        // O retorno tem que ser true se 1 e 2 forem falsos

        return input != null && !input.trim().isEmpty();
    }
    public static boolean email(String input){
        // O que espera o teste?
        // - Precisa ter "@"
        // - Tem que ter '.' depois do "@"
        // - o '.' não é o último caractere

        if(!notBlank(input)) return false;

        // Encontrar posições de @ e o ULTIMO ponto
        // Retorna a posição de @ ou -1 se não houver
        int arroba = input.indexOf("@");
        int ponto = input.lastIndexOf('.');

        return arroba > 0 &&
                ponto > arroba + 1 &&
                ponto < input.length() - 1;

    }
}
