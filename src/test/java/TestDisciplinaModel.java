import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import model.Disciplina.Disciplina;

public class TestDisciplinaModel {
    
    @Test
    public void testeDescontoCargaHoraria() {
        Disciplina disciplina = new Disciplina();
        disciplina.setCargaHoraria(100);
        
        // Teste com percentual válido
        double resultado = disciplina.descontoCargaHoraria(20);
        assertEquals(80.0,resultado,0.0001);
        
        // Teste com percentual inválido (0%)   
        resultado = disciplina.descontoCargaHoraria(0);
        assertEquals(0.0, resultado, 0.0001);
        
        // Teste com percentual inválido (>100%)
        resultado = disciplina.descontoCargaHoraria(120);
        assertEquals(0.0, resultado, 0.0001);
    }
}
