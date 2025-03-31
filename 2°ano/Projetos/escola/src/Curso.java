public class Curso {
    public String codigo;
    public String nome;
    public int cargaHoraria;

    // Construtor padrão

    public Curso(){
        this.codigo = "1111";
        this.nome = "Desconhecido";
        this.cargaHoraria = 0;
    }

    // Contrutor parametrizado

    public Curso(String codigo, String nome, int cargaHoraria){
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }

    // Getters

    public String getCodigo() {return codigo;}
    public String getNome() {return nome;}
    public int getCargaHoraria() {return cargaHoraria;}

    // Setters

    public void setCodigo(String codigo) {this.codigo = codigo;}
    public void setNome(String nome){this.nome = nome;}
    public void setCargaHoraria(int cargaHoraria){this.cargaHoraria = cargaHoraria;}


    // ExibirInfo

    public void exibirInfo(){
        System.out.println("Codigo: " + codigo + "| Nome: " + nome + "| CargaHoraria: " + cargaHoraria);
    }
}
