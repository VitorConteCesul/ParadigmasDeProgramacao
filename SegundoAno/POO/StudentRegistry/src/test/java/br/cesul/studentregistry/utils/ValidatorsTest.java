package br.cesul.studentregistry.utils;

// Testar se as validações de UI funcionam corretamente
// 1 - O que a viewModel vai precisar decidir?
//   - Texto não pode ser vazio -> notBlank
//   - Email tem que ser um formato plausivel
//   - A nota tem que estar entre 0 e 10

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

// Ao inves de reescrever 10 vezes o mesmo teste com valores diferentes, vamos usar o Teste Parametrizado
// É um teste que roda varias vezes, uma para cada conjunto (entrada - saída)
// Este teste recebe 'Parametros' que vem de alguma fonte de dados
// Costuma-se utilizar a @CsvSourse
// Cada linha do csv Armazena valores de teste separados por virgula
// e o último deles representa se este conjunto de dados deve passar ou não
public class ValidatorsTest {
    // notBlank(string) -> só é true se NÃO for null e tem algo
    // além de espaços

    // teste executado várias vezes (uma por linha do csv)
    @ParameterizedTest
    @DisplayName("NotBlanck deve detectar strings válidas/inválidas")
    @CsvSource(
            value = {
                    // Formato de cada linha: "Entrada, resultado esperado"
                    // Casos válidos (esperado = true)
                    "'abc', true", // texto normal
                    "' x', true", // tem espaço, mas também tem conteúdo
                    // Casos Inválidos (esperado = false)
                    "'   ', false",
                    "'', false",
                    "null, false"
            },
            nullValues = {"null"}
    )
    void notBlank_tests(String input, boolean expected){
        // Arrange
        // Act
        boolean resultado = Validators.notBlank(input);

        // Assert
        assertEquals(expected, resultado, "Notblank errado");

        // COMO PROGRAMADOR eu pensei no seguinte:
        // 1 - Listei os casos que me interessam
        // (Validos, invalidos, bordas)
        // 2 - Transformei os dados em linhas CSV
        // 3 - Escrevi um único teste que compra um dado input com um dado expected
        //

    }

    // Validação de email faremos simples, regras:
    // - Tem que ter "@"
    // - Tem que ter '.' depois do "@"
    // - O '.' não é o último caractere
    @ParameterizedTest
    @DisplayName("Email simples deve validar formato bascio")
    @CsvSource(
            value = {
                     // Validos
                    "conte@gmail.com, true",
                    "bito@exemplo.org, true",
                    // Inválidos
                    "sem-arroba.com, false",
                    "arroba@semponto, false",
                    "@vazio.com, false",
                    "null, false"
            },
            nullValues = {"null"}
    )
    void email_tests(String input, boolean expected){
        // Act
        boolean resultado = Validators.email(input);

        // Assert
        assertEquals(expected, resultado, "Notblank errado");
    }
}
