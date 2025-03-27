import java.util.Scanner;

public class exer01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a temperatura em Celsius: ");
        double celsius = scanner.nextDouble();

        double fahrenheit = (celsius * 9 / 5) + 32;
        System.out.println(celsius + "°C equivalem a " + fahrenheit  );

        scanner.close();
    }

}