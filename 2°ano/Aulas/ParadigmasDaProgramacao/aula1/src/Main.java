import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        demonstrarVariaveis();
    }

    public static void domonstrarVariaveis(){
        System.out.println("\n -- Declaração de variaveis");

        // Tipos primitivos (int, double, char, boolean...)

        int integer = 10;
        double decimal = 10.5;
        char caractere = 'A';
        boolean logic = true;

        // Type String que não é primitivo, é uma class

        String texto = "Ola Mundo";

        System.out.println("Inteiro: " + integer);
        System.out.println("Decimal: " + decimal);
        System.out.println("Caractere: " + caractere);
        System.out.println("Logic: " + logic);
        System.out.println("Texto: " + texto);
    }

}