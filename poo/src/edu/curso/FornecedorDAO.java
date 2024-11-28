package src.edu.curso;

import java.util.List;

public interface FornecedorDAO {
    
    void inserir( Fornecedor f ) throws MedicamentoException;
    void atualizar( Fornecedor f ) throws MedicamentoException;
    void remover( Fornecedor f ) throws MedicamentoException;
    List<Fornecedor> pesquisarPorNome( String nome ) throws MedicamentoException;
    
}