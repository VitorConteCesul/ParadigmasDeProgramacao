import java.util.Scanner;

public class exer03 {
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o primeiro numero: ");
        double num1 = scanner.nextInt();

        System.out.println("Digite o primeiro numero: ");
        double num2 = scanner.nextInt();

        System.out.println("Digite o primeiro numero: ");
        double num3 = scanner.nextInt();

        double media = (num1 + num2 + num3) / 3;

        System.out.println("A média é: " + media);
    }
}
