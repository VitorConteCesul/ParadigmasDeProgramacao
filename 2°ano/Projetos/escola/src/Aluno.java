// testando commit

public class Aluno {
    public String nome;
    public String matricula;

    //Construtor padrão

    public Aluno(){
        this.nome = "Nome padrão";
        this.matricula = "0000";
    }

    // Construtor com parametros obrigatorios
    // Aluno aluno = new Aluno("vitor", "0000)

    public Aluno(String nome, String matricula){
        this.nome = nome;
        this.matricula = matricula;
    }

    //Getters
    public String getNome() {return nome;}
    public String getMatricula() {return matricula;}

    //Setters
    public void setNome(String nome){this.nome = nome;}
    public void setMatricula(String matricula){this.matricula = matricula;}


    //Exibir Info

    public void exibirInfo(){
        System.out.println("Nome: " + nome + " | Matricula: " + matricula);
    }

    // Aluno aluno
    // aluno.exibirInfo()
}
