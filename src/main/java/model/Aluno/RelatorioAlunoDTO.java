package model.Aluno;

public class RelatorioAlunoDTO {
    
    private String nomeAluno;
    private String nomeCurso;
    private int id;

    public RelatorioAlunoDTO(int id, String nomeAluno, String nomeCurso){
        this.id = id; 
        this.nomeAluno = nomeAluno;
        this.nomeCurso = nomeCurso;
    }

    public int getId() {
        return id;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }
 
}
