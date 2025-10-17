package br.cesul.studentregistry.model;

// Aqui ficam todos os testes do StudentModel
// O passo a passo é o seguinte:

// 1 - Pense primeiro no Contrato (o que a entidade deve garantir)
//     Ao 'nascer', o Student tem um id gerado, strings vazias,
//     nota 0.0, ativo = 'true'

// 2 - Escrever o primeiro teste (RED)
//     Expressar esse contrato no teste
//     "defaultConstructor_initilizesFields"

//3 -  Fazer o mínimo (opcional) para fazer o (green)
//     Implementar o construtor padrão com getters e setters
//     inicializando os atributos

//4 -  Refatorar (Sem mudar comportamento)
//     Melhorar o código, deixando-o mais profundo,
//     porém, mantendo os testes passando

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    @Test
    @DisplayName("Construtor padrão inicia id, strings vazias," +
            "nota 0.0 e ativo=true")
    void defultConstructor_initializesFields(){
        // Arrange
        // Act - Ação ÚNICA e clara: Criar o Studant pelo
        // construtor padrão
        Student s = new Student();

        // ASSERT - Validar cada parte do contrato inicial
        assertNotNull(s.getId(), "ID deve ser gerado, nunca nulo");
        assertEquals("", s.getName(), "Nome inicia como string vazia");
        assertEquals("", s.getEmail(), "Email inicia como string vazia");
        assertEquals(0.0, s.getGrade(),0.0001, "Nota inicia com 0.0");
        assertTrue(s.isActive(), "Aluno vem como ativo");

    }

    @Test
    @DisplayName("Construtor completo preenche todos os campos(exceto" + "id que continua gerado)")
    void fullConstructor_setsFields(){
        // Arrange
        String name = "Conte";
        String email = "conte.vitoo@gmail.com";
        double grade = 8.5;
        boolean active = false;

        //Act
        Student s = new Student(name, email, grade, active);

        //Assert
        assertNotNull(s.getId(), "Id deve ser gerado no construtor");
        assertEquals(name, s.getName(), "Nome deve refletir o 'name'");
        assertEquals(email, s.getEmail(), "Nome deve refletir o 'email'");
        assertEquals(grade, s.getGrade(), 0.0001, "Grade deve refletir" + "o 'grade'");
        assertEquals(active, s.isActive(),"Active deve refletiro 'active'");
    }
}
