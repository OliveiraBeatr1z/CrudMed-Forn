package src.edu.curso;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

public class FornecedorBoundary implements Tela {
    private Label lblIdForn = new Label("");
    private TextField txtNome = new TextField();
    private TextField txtCNPJ = new TextField();
    private TextField txtEmail = new TextField();
    private TextField txtEndereco = new TextField();
    private TextField txtTelefone = new TextField();

    private FornecedorControl control = null;

    private TableView<Fornecedor> tableview = new TableView<>();

    @Override
    public Pane render() {
        try{
            control = new FornecedorControl();
        }catch (MedicamentoException e ){
            new Alert(AlertType.ERROR, "Erro ao iniciar o sistema", ButtonType.OK).showAndWait();
        }
        BorderPane panePrincipal = new BorderPane();
        GridPane paneForm = new GridPane();

        // Botão de Gravar
        Button btnGravar = new Button("Gravar");
        btnGravar.setOnAction( e -> {
            try {
                control.gravar();
            }catch (MedicamentoException err){
                new Alert(AlertType.ERROR, "Erroao gravar o Fornecedor", ButtonType.OK).showAndWait();
            }
            tableview.refresh();
        });

        // Botão de Pesquisar.
        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction( e-> {
            try{
                control.pesquisar();
            } catch(MedicamentoException err){
                new Alert(AlertType.ERROR, "Erro ao pesquisar fornecedor",ButtonType.OK).showAndWait();
            }
            tableview.refresh();
        });

        // botão de apagar.
        Button btnNovo = new Button("*");
        btnNovo.setOnAction( e-> control.limparTudo());

        paneForm.add(new Label("IdForn: "), 0, 0);
        paneForm.add(lblIdForn, 1, 0);

        paneForm.add(new Label("Nome: "), 1, 1);
        paneForm.add(txtNome, 2, 1);

        paneForm.add(new Label("CNPJ: "), 0, 2);
        paneForm.add(txtCNPJ, 1, 2);

        paneForm.add(new Label("Email: "), 0, 3);
        paneForm.add(txtEmail, 1, 3);

        paneForm.add(new Label("Endereco: "), 0, 4);
        paneForm.add(txtEndereco, 1, 4);

        paneForm.add(new Label("Telefone: "), 0, 5);
        paneForm.add(txtTelefone, 1, 5);

        paneForm.add(btnGravar, 0, 6);
        paneForm.add(btnPesquisar, 1, 6 );

        fornecedores();
        gerarColunas();

        panePrincipal.setTop(paneForm);
        panePrincipal.setCenter(tableview);
        
        return panePrincipal;
    }

    public void gerarColunas(){
        TableColumn<Fornecedor, Integer > col1 = new TableColumn<>("IdForn");
        col1.setCellValueFactory( new PropertyValueFactory<Fornecedor, Integer>("idForn") );

        TableColumn<Fornecedor, String > col2 = new TableColumn<>("Nome");
        col2.setCellValueFactory( new PropertyValueFactory<Fornecedor, String>("nome") );

        TableColumn<Fornecedor, String> col3 = new TableColumn<>("CNPJ");
        col3.setCellValueFactory( new PropertyValueFactory<Fornecedor, String>("CNPJ") );

        TableColumn<Fornecedor, String> col4 = new TableColumn<>("Email");
        col4.setCellValueFactory( new PropertyValueFactory<Fornecedor, String>("email") );

        TableColumn<Fornecedor, String> col5 = new TableColumn<>("Endereco");
        col5.setCellValueFactory( new PropertyValueFactory<Fornecedor, String>("endereco") );

        TableColumn<Fornecedor, String> col6 = new TableColumn<>("Telefone");
        col6.setCellValueFactory( new PropertyValueFactory<Fornecedor, String>("telefone") );

        tableview.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
            if(novo != null){
                System.out.println("Fornecedor: " + novo.getNome());
                control.entidadeParaTela(novo);
            }
        });

        Callback<TableColumn<Fornecedor, Void>, TableCell<Fornecedor, Void>> cb = 
            new Callback<>() {
                @Override
                public TableCell<Fornecedor, Void> call(
                    TableColumn<Fornecedor, Void> param) {
                    TableCell<Fornecedor, Void> celula = new TableCell<>() { 
                        final Button btnApagar = new Button("Apagar");

                        {
                            btnApagar.setOnAction( e -> {
                                Fornecedor fornecedor = tableView.getItems().get( getIndex() );
                                try { 
                                    control.excluir( fornecedor  ); 
                                } catch (MedicamentoException err) { 
                                    new Alert(AlertType.ERROR, "Erro ao excluir o fornecedor", ButtonType.OK).showAndWait();
                                }
                            });
                        }

                        @Override
                        public void updateItem( Void item, boolean empty) {                             
                            if (!empty) { 
                                setGraphic(btnApagar);
                            } else { 
                                setGraphic(null);
                            }
                        }
                        
                    };
                    return celula;            
                } 
            };

        TableColumn<Fornecedor, Void> col7 = new TableColumn<>("Ação");
        col7.setCellFactory(cb);

        tableView.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7);
        tableView.setItems( control.getLista() );
    }

    public void fornecedores(){
        control.idFornProperty().addListener((obs, antigo, novo) -> {
            lblIdForn.setText(String.valueOf(novo));
        });

        IntegerStringConverter integerConverter = new IntegerStringConverter();
        Bindings.bindBidirectional(control.nomeProperty(), txtNome.textProperty());
        Bindings.bindBidirectional(control.CNPJProperty(), txtCNPJ.textProperty());
        Bindings.bindBidirectional(control.emailProperty(), txtEmail.textProperty() );
        Bindings.bindBidirectional(control.enderecoProperty(), txtEndereco.textProperty() );
        Bindings.bindBidirectional(control.telefoneProperty(), txtTelefone.textProperty() );

    }
    
}
