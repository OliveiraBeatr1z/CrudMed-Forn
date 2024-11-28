package src.edu.curso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mariadb.jdbc.export.Prepare;

public class FornecedorDAOImpl implements FornecedorDAO{

    private final static String DB_CLASS = "org.mariadb.jdbc.Driver";
    private final static String DB_URL = "jdbc:mariadb://localhost:3307/agendadb?allowPublicKeyRetrieval=true";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "alunofatec";

    private Connection con = null;

    public FornecedorDAOImpl() throws MedicamentoException { 
        try { 
            Class.forName(DB_CLASS);
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException | SQLException e) { 
            throw new MedicamentoException( e );
        }
    }
    @Override
    public void inserir(Fornecedor f) throws MedicamentoException {
        try{
            String SQL = """
            INSERT INTO fornecedores (idForn, nome, CNPJ, email, endereco, telefone)
            VALUES (? ? ? ? ? ?)
            """;

            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setInt(1, f.getIdForn());
            stm.setString(2, f.getNome());
            stm.setString(3, f.getCNPJ());
            stm.setString(4, f.getEmail());
            stm.setString(5, f.getEndereco());
            stm.setString(6, f.getTelefone());
            int i = stm.executeUpdate();
        } catch (SQLException e){
            throw new MedicamentoException(e);
        }
    }

    @Override
    public void atualizar(Fornecedor f) throws MedicamentoException {
       try{
        String SQL = """
        UPDATE fornecedores SET nome = ?, CNPJ = ?, email = ?, endereco = ?, telefone = ?
        WHERE IdForn = ?;
        """;

        PreparedStatement stm = con.prepareStatement(SQL);
        stm.setInt(1, f.getIdForn());
            stm.setString(2, f.getNome());
            stm.setString(3, f.getCNPJ());
            stm.setString(4, f.getEmail());
            stm.setString(5, f.getEndereco());
            stm.setString(6, f.getTelefone());
            int i = stm.executeUpdate();
       } catch (SQLException e ){
           throw new MedicamentoException(e);
       }
    }

    @Override
    public void remover(Fornecedor f) throws MedicamentoException {
        try{
            String SQL =  """
                DELETE FROM fornecedores WHERE idForn = ?
            """;
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setInt(1, f.getIdForn());
            int i = stm.executeUpdate();
        } catch (SQLException e){
            throw new MedicamentoException();
        }

    }

    @Override
    public List<Fornecedor> pesquisarPorNome(String nome) throws MedicamentoException {
        List<Fornecedor> lista = new ArrayList<>();
        try{
            String SQL = """
            SELECT * FROM fornecedores WHERE nome LIKE ?
            """;
            PreparedStatement stm = con.prepareStatement(SQL);
            stm.setString(1, "%" + nome + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                Fornecedor f = new Fornecedor();
                f.setIdForn(rs.getInt("idForm"));
                f.setNome(rs.getString("nome"));
                f.setCNPJ(rs.getString("Email"));
                f.setCNPJ(rs.getString("Endereco"));
                f.setCNPJ(rs.getString("Telefone"));
            }
        } catch (SQLException e){
            throw new MedicamentoException();
        }
        return lista;
    }
    
}