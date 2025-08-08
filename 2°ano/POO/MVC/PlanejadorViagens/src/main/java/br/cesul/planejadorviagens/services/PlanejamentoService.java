package br.cesul.planejadorviagens.services;

// Contem validações e cálculos, ISOLANDO a lógica do contrller
// 0 referencias de Mongo
// o referencias de tela

// Serve pra organizar a lógica de negócio da aplicação
// Separar responsabilidades
// Validar dados  EX: Impedir datas invertidas , Custo negativo..)

// Se não tivesse o service o controller teria que fazer
// todas as validações por si
// A tela teria que chamar o banco direto (violação do MVC)
// Regras ficariam espalhadas..

import br.cesul.planejadorviagens.model.Viagem;
import br.cesul.planejadorviagens.repository.ViagemRepository;

import java.time.LocalDate;
import java.util.List;

public class PlanejamentoService {
    private final ViagemRepository repo = new ViagemRepository();

    // Receber os dados da viagem (como se fosse da tela)
    // Adicionar uma viagem se as regras estiverem OK
    public void adicionar(String destino, LocalDate ini, LocalDate fim, double custo) throws IllegalAccessException {
        // Se algo estiver errado, lança exceção
        // que será capturado pelo controller ou pela interface
        if (destino == null || destino.isBlank())
            throw new IllegalAccessException("Destino vazio");
        if (ini == null || fim == null)
            throw new IllegalAccessException("Datas são obrigatórias");
        if (ini.isAfter(fim))
            throw new IllegalAccessException("Inicio posterior ao fim");
        if (custo < 0)
            throw new IllegalAccessException("Custo negativo");
        if (repo.conflita(ini, fim))
            throw new IllegalAccessException("Conflito com outra viagem");


        repo.salvar(new Viagem(null,destino, custo, ini, fim));
    }

    // Métodos de consulta
    // No memento apenas encapsula o acesso ao Repositório
    // Listar
    public List<Viagem> listar(){
        return repo.listarTodas();
    }

    // Somar custos
    public double totalGasto(){
        return repo.somaCustos();
    }
}

