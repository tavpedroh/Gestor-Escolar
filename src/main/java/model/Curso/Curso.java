package model.Curso;

public class Curso {

    private int id;
    private String nome;
    private int duracaoSemetre;

    public Curso() {
    }
    public Curso(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDuracaoSemetre() {
        return duracaoSemetre;
    }
    public void setDuracaoSemetre(int duracaoSemetre) {
        this.duracaoSemetre = duracaoSemetre;
    }
    
}
