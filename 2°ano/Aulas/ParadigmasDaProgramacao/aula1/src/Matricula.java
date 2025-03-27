// Esta classe representa a matrícula de um aluno
// em um curso;
// Todas as operações relacionadas á matrícula
// são feitas aqui dentro.

public class Matricula {
    public Aluno aluno;
    public Curso curso;

    //Classe wrapper Double
    // public Double nota; nota == null
    public double nota; // -1 indica "nota não atribuida"

    //Construtor padrão

    public Matricula(){
        this.aluno = null;
        this.curso = null;
        this.nota = -1;
    }

    //Construtor parametrizado

    public Matricula(Aluno aluno, Curso curso){
        this.aluno = aluno;
        this.curso = curso;
        this.nota = -1;

    }

    public Aluno getAluno(){return aluno;}
    public Curso getCurso(){return curso;}
    public double getNota(){return nota;}
}
