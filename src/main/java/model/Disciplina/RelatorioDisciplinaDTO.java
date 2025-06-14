package model.Disciplina;

public class RelatorioDisciplinaDTO {
    private int idDisciplina;
    private String nomeDisciplina;
    private String nomeCurso;

    public RelatorioDisciplinaDTO(int idDisciplina, String nomeDisciplina, String nomeCurso) {
        this.idDisciplina = idDisciplina;
        this.nomeDisciplina = nomeDisciplina;
        this.nomeCurso = nomeCurso;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    
    public String getNomeCurso() {
        return nomeCurso;
    }
}
