package src.edu.curso;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PrincipalBoundary extends Application{
    private Map<String, Tela> telas = new HashMap<>();

    public void start(Stage stage) throws Exception{
        @Override
        telas.put("medicamento", new MedicamentoBoundary());
        telas.put("fornecedor", new FornecedorBoundary());
        BorderPane panePrincipal = new BorderPane();
        MenuBar menuBar = new MenuBar();
        Menu mnuCadastro = new Menu("Cadastro");
        Menu mnuAjuda = new Menu("Ajuda");

        MenuItem mnuItemContato = new MenuItem("Medicamento");
        mnuItemContato.setOnAction ( e -> 
            panePrincipal.setCenter( telas.get("medicamento").render() )
        );

        MenuItem mnuItemEndereco = new MenuItem("Fornecedor");
        mnuItemEndereco.setOnAction( e-> 
            panePrincipal.setCenter( telas.get("fornecedor").render() )
        );

        MenuItem mnuItemCreditos = new MenuItem("Creditos");
        mnuCadastro.getItems().addAll( mnuItemMedicamento );
        mnuCadastro.getItems().addAll( mnuItemFornecedor );
        mnuAjuda.getItems().add( mnuItemCreditos );
        menuBar.getMenus().addAll( mnuCadastro, mnuAjuda);
        panePrincipal.setTop( menuBar );
        Scene scn = new Scene(panePrincipal, 800, 600);
        stage.setScene(scn);
        stage.setTitle("Fornecedor e Medicametos");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(PrincipalBoundary.class, args);
    }
}
