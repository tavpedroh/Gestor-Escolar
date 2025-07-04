package model.Curso;

import java.util.Objects;

public class Curso {

    private int id;
    private String nome;
    private int duracaoSemestres;

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

    public int getDuracaoSemestres() {
        return duracaoSemestres;
    }
    public void setDuracaoSemestres(int duracaoSemestres) {
        this.duracaoSemestres = duracaoSemestres;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Curso)) return false;
        Curso curso = (Curso) obj;
        return id == curso.id &&
               duracaoSemestres == curso.duracaoSemestres &&
               nome.equals(curso.nome);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, nome, duracaoSemestres);
    }
}
