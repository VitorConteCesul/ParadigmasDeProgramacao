import java.util.Scanner;

public class exer02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite um número inteiro: ");
        int numero = scanner.nextInt();

        if (numero % 2 == 0) {
            System.out.println("O num é Par");
        } else {
            System.out.println("O num é Ímpar");
        }
    }
}