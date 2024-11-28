package src.edu.curso;

public class Fornecedor {

    private int idForn= 0;
    private String nome = "";
    private String CNPJ = "";
    private String email = " ";
    private String endereco = "";
    private String telefone = "";

    public int getIdForn() {
        return idForn;
    }
    public void setIdForn(int idForn) {
        this.idForn = idForn;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCNPJ() {
        return CNPJ;
    }
    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.telefone = endereco;

    } public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
}
