import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Escola sistema = new Escola();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n Sistema de gerenciamento escolar \n");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Cadastrar Curso");
            System.out.println("3 - Matricular aluno em curso");
            System.out.println("4 - Remover matricula de aluno");
            System.out.println("5 - Listar alunos por curso");
            System.out.println("6 - Atribuir nota e matricula");
            System.out.println("7 - Buscar aluno por nome");
            System.out.println("9 - Listar cursos com média acima de X");
            System.out.println("10 - Exibir ranking de alunos");
            System.out.println("11 - Relatório geral");
            System.out.println("0 - Cadastrar aluno");
            System.out.println("1 - Sair");
            System.out.println("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do aluno");
                    String nomeAluno = scanner.nextLine();
                    System.out.println("Digite a matricula do aluno");
                    String matriculaAluno = scanner.nextLine();

                    Aluno aluno = new Aluno(nomeAluno, matriculaAluno);
                    sistema.cadastrarAluno(aluno);
                    break;

                case 2:
                    System.out.println("Digite o código do curso: ");
                    String codigoCurso = scanner.nextLine();
                    System.out.println("Digite o nome do curso: ");
                    String nomeCurso = scanner.nextLine();
                    System.out.println("Digite a carga horária do curso: ");
                    int cargaHoraria = scanner.nextInt();
                    scanner.nextLine();

                    Curso curso = new Curso(codigoCurso, nomeCurso, cargaHoraria);
                    sistema.cadastrarCursos(curso);
                    break;

                case 3:
                    if(sistema.alunos.isEmpty() || sistema.cursos.isEmpty()){
                        System.out.println("É necessário ter alunos e cursos");
                    }else {
                        System.out.println("Alunos disponiveis: ");
                        for (int i = 0; i < sistema.alunos.size(); i++) {
                            System.out.println(i + " -");
                            sistema.alunos.get(i).exibirInfo();
                        }
                        System.out.println("Escolha o índice do aluno: ");
                        int indiceAluno = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Cursos disponiveis: ");
                        for (int i = 0; i < sistema.cursos.size(); i++) {
                            System.out.println(i + " -");
                            sistema.cursos.get(i).exibirInfo();
                        }
                        System.out.println("Escolha o índice do curso: ");
                        int indiceCurso = scanner.nextInt();
                        scanner.nextLine();

                        Matricula matricula = new Matricula(sistema.alunos.get(indiceAluno), sistema.cursos.get(indiceCurso));

                        sistema.matricularAluno(matricula);
                    }

                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 0:
            }

        } while (opcao != 0);
    }
}