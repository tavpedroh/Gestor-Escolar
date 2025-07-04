import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import dao.CursoDao;
import model.Curso.Curso;

public class TesteCursoDao {
    
    @Test
    public void inserirBuscarAlterarExcluir() throws SQLException{
        //Inserir
        Curso curso = new Curso();
        curso.setNome("Engenharia de Software");
        curso.setDuracaoSemestres(8);
        CursoDao curDao = new CursoDao();
        int id = curDao.inserir2(curso);
        curso.setId(id);
        assertTrue(curso.getId() > -1);
    
        //Buscar
        curDao = new CursoDao();
        Curso curso2 = new Curso();
        curso2 = curDao.listarUm(curso.getId());
        assertEquals(curso, curso2);
    
        //Alterar
        curDao = new CursoDao();
        curso2.setNome("Engenharia de Software Alterado");
        curDao.alterar(curso2);

        Curso curso3 = new Curso();
        curDao = new CursoDao();
        curso3 = curDao.listarUm(curso2.getId());
        assertEquals(curso2, curso3);

        //Exluir
        curDao = new CursoDao();
        curDao.excluir(curso3.getId());
        
        curDao = new CursoDao();
        Curso cursoExcluido = curDao.listarUm(curso3.getId());

        assertNull(cursoExcluido);
    }
    
}
