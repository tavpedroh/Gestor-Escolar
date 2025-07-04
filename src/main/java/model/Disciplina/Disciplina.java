package model.Disciplina;

import model.Curso.Curso;

public class Disciplina {
    private int id;
    private String nome;
    private int cargaHoraria;
    private Curso curso;
    
    public Disciplina() {
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

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public double descontoCargaHoraria(int percentual){
        if (percentual <= 0 || percentual > 100) {
            return 0;
        } else {
            return this.cargaHoraria - this.cargaHoraria * (percentual / 100.0);
        }
    }
}   
