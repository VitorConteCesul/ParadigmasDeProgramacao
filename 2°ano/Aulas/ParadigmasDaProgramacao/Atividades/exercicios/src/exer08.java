import java.util.Scanner;

public class exer08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite um número: ");
        int num = scanner.nextInt();
        boolean primo = true;

        if(num <= 1){
            primo = false;
        }else{
            for(int i = 2; i <= Math.sqrt(num); i++){
                if(num % i == 0){
                    primo = false;
                    break;
                }
            }
            if(primo){
                System.out.println("é primo");
            }else{
                System.out.println("não é primo");
            }
        }
        scanner.close();
    }
}
