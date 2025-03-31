public class Main {
    public static void main(String[] args) {
        variaveis();
        demonstrarConversoes();
    }

    public static void variaveis(){
        System.out.println("------ Variaveis------");

        int integer = 10;
        double decimal = 10.5;
        char caractere = 'A';
        boolean logico = true;

        System.out.println("Inteiro: " + integer);
        System.out.println("Double" + decimal);
        System.out.println("Char" + caractere);
        System.out.println("boolean" + logico);
    }

    public static void demonstrarConversoes(){
        System.out.println("\n -- conversoes -- ");

        int inteiro = 100;
        double decimal = 99.99;

        double conversaoImplicita = inteiro;
        System.out.println("Conversão implicita de int para double: " + conversaoImplicita);

        int conversaoExplicita = (int) decimal;

        System.out.println("Conversão explicita de double para int: " + conversaoExplicita);

        String numeroComoTexto = "123";
        // Try-Catch ---- toda vez que for fazer uma conversão/atribuição
        // que pode resultar em erro. Use o Try-Catch

        try {
            // vai tentar executar tudo o que estiver aqui dentro
            // parseInt: converter para int
            int numeroConvertido = Integer.parseInt(numeroComoTexto);
            System.out.println("Conversão: " + numeroConvertido);
        }catch(NumberFormatException e){
            System.out.println("Erro na conversão: " + e.getMessage());
        }


    }

    public static void demonstrarAtribuicoes(){
        System.out.println("\n Atribuições em java");

        int a = 5;
        int b;
        b = a;

        double d = a;
    }
}