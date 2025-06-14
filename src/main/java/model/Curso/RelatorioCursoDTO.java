package model.Curso;

public class RelatorioCursoDTO {
    private int idCurso;
    private String nomeCurso;
    private int quantidadeAluno;
    private int quantidadeDisciplina;

    public RelatorioCursoDTO(int id, String nomeCurso, int quantidadeAluno, int quantidadeDisciplina) {
        this.idCurso = id;
        this.nomeCurso = nomeCurso;
        this.quantidadeAluno = quantidadeAluno;
        this.quantidadeDisciplina = quantidadeDisciplina;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public int getQuantidadeAluno() {
        return quantidadeAluno;
    }

    public int getQuantidadeDisciplina() {
        return quantidadeDisciplina;
    }
}
