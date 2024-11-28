package src.edu.curso;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class FornecedorControl {
    private IntegerProperty idForn = new SimpleIntegerProperty(01);
    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty CNPJ = new SimpleStringProperty("");
    private StringProperty email = new SimpleStringProperty("");
    private StringProperty endereco = new SimpleStringProperty("");
    private StringProperty telefone = new SimpleStringProperty("");

    private ObservableList<Fornecedor> lista = FXCollections.observableArrayList();

    private FornecedorDAO fornecedorDAO;
    private int contador =0;

    public FornecedorControl() throws MedicamentoException{
        fornecedorDAO = new FornecedorDAOImpl();
    }

    public void excluir( Fornecedor f ) throws MedicamentoException{
        fornecedorDAO.remover( f );
        pesquisarTodos();
    }

    public void gravar() throws MedicamentoException{
        Fornecedor fornecedor = entidadeParaTela();
        if(idForn.get() == 0){
            fornecedor.setIdForn(++contador);
            fornecedorDAO.inserir(fornecedor);
        }else{
            fornecedorDAO.atualizar(fornecedor);
        }
        limparTudo();
        pesquisarTodos();
    }

    public void pesquisar() throws MedicamentoException{
        List<Fornecedor> listaTemp = fornecedorDAO.pesquisarPorNome(nome.get());
        lista.clear();
        lista.addAll(listaTemp);
    }

    public void limparTudo(){
        idForn.set(01);
        nome.set("");
        CNPJ.set("");
        email.set("");
        endereco.set("");
        telefone.set("");
    }

    public void fornecedorParaTela(Fornecedor f){
        idForn.set(f.getIdForn());
        nome.set(f.getNome());
        CNPJ.set(f.getCNPJ());
        email.set(f.getEmail());
        endereco.set(f.getEndereco());
        telefone.set(f.getTelefone());
    }

    public Fornecedor telaParaEntidade(){
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setIdForn(idForn.get());
        fornecedor.setNome(nome.get());
        fornecedor.setCNPJ(CNPJ.get());
        fornecedor.setEmail(email.get());
        fornecedor.setEndereco(endereco.get());
        fornecedor.setTelefone(telefone.get());

        return fornecedor;
    }

    public ObservableList<Fornecedor> getLista(){
        return this.lista;
    }
    public IntegerProperty idProperty(){
        return this.idForn;
    }
    public StringProperty nomeProperty(){
        return this.nome;
    }
    public StringProperty CNPJProperty(){
        return this.CNPJ;
    }
    public StringProperty EmailProperty(){
        return this.email;
    }
    public StringProperty EnderecoProperty(){
        return this.endereco;
    }
    public StringProperty TelefoneProperty(){
        return this.telefone;
    }
}
