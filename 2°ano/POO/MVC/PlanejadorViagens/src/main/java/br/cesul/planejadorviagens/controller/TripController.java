package br.cesul.planejadorviagens.controller;

// Essa será a única classe de controller do projeto
// A sua função é ligar a interface gráfica (View)
// com a lógica de negócio

// Interage com os elementos do FXML
// Coleta os dados preenchidos pelo usuário
// Converte texto para os tipos corretos
// Chama o service (que valida e salva)
// É o responsável por atualizar a interface
// com os novos dados vindos do service
// Responde a eventos da view

import br.cesul.planejadorviagens.model.Viagem;
import br.cesul.planejadorviagens.services.PlanejamentoService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.format.DateTimeFormatter;

public class TripController {
    // Elementos do FXML (fx:id)

    @FXML private DatePicker dataInicioPicker;
    @FXML private DatePicker dataFimPicker;
    @FXML private TextField destinoField;
    @FXML private TextField orcamentoField;
    @FXML private Button btnAdicionar;
    @FXML private TableView<Viagem> viagensTable;
    @FXML private TableColumn<Viagem, String> colCidade;
    @FXML private TableColumn<Viagem, String> colIni;
    @FXML private TableColumn<Viagem, String> colFim;
    @FXML private TableColumn<Viagem, Number> colCusto;
    @FXML private Label lbtTotal;

    private final PlanejamentoService service = new PlanejamentoService();

    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    private void initialize(){
        // Define COMO cada coluna extrai informação da entidade

        // Se só remover uma String simples, o JFX não consegue
        // saber se o valor mudou depois. Por isso se devolver uma
        // SimplesStringProperty ele pode observar aquela propriedade
        // e atualizar a interface automaticamente.

        colCidade.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getDestino()));

        colIni.setCellFactory(c ->
                new SimpleStringProperty(c.getValue().getDataInicio().format(fmt)));

        colFim.setCellFactory(c ->
                new SimpleStringProperty(c.getValue().getDataFim().format(fmt)));

        colCusto.setCellFactory(c ->
                new SimpleStringProperty(c.getValue().getCusto()));

        // Preencher a tabela com dados já gravados
        // Chamando o service para buscar as viagens
        viagensTable.setItems(FXCollections.observableArrayList(service.listar()));
        atualizarTotal();
    }

    @FXML
    public void adicionar(){
        // Handler do botão adicionar viagem

        try{
            // Conversão de dados
            double custo = Double.parseDouble(orcamentoField.getText().replace(",", "."));
        }catch (Exception ex){
            mostrarErro(ex.getMessage());
        }

    }

    private void mostrarErro(String msg){
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }

    private void atualizarTotal(){
        lbtTotal.setText("Total: R$ " + String.format("%.2f",service.totalGasto()));
    }
}
