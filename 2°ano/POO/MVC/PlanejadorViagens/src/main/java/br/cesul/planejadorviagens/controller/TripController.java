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
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TripController {
    // Elementos do FXML (fx:id)

    @FXML private DatePicker dataInicioPicker;
    @FXML private DatePicker dataFimPicker;
    @FXML private TextField partidaField;
    @FXML private TextField destinoField;
    @FXML private TextField orcamentoField;
    @FXML private Button btnAdicionar;
    @FXML private Button btnEditar;
    @FXML private TableView<Viagem> viagensTable;
    @FXML private TableColumn<Viagem, String> colCidadePartida;
    @FXML private TableColumn<Viagem, String> colCidade;
    @FXML private TableColumn<Viagem, String> colIni;
    @FXML private TableColumn<Viagem, String> colFim;
    @FXML private TableColumn<Viagem, Number> colCusto;
    @FXML private Label lblTotal;

    private Viagem viagemEmEdicao = null;
    private boolean modoEdicao = false;

    private final PlanejamentoService service = new PlanejamentoService();

   // private final Viagem viagemSelecionada = viagensTable.getSelectionModel().getSelectedItem();

    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    private void initialize(){
        // Define COMO cada coluna extrai informação da entidade

        // Se só remover uma String simples, o JFX não consegue
        // saber se o valor mudou depois. Por isso se devolver uma
        // SimplesStringProperty ele pode observar aquela propriedade
        // e atualizar a interface automaticamente.

        colCidadePartida.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getPartida()));

        colCidade.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getDestino()));

        colIni.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getDataInicio().format(fmt)));

        colFim.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getDataFim().format(fmt)));

        colCusto.setCellValueFactory(c ->
                new SimpleDoubleProperty(c.getValue().getCusto()));

        viagensTable.getSelectionModel().selectedItemProperty().addListener(
                    (obs, oldViagem, novaViagem) ->
                    {
                        if(novaViagem != null){
                            partidaField.setText(novaViagem.getPartida());
                            destinoField.setText(novaViagem.getDestino());
                            dataInicioPicker.setValue(novaViagem.getDataInicio());
                            dataFimPicker.setValue(novaViagem.getDataFim());
                            orcamentoField.setText(String.format(Locale.US, "%.2f", novaViagem.getCusto()));
                        }
                    }
                    );

        // Preencher a tabela com dados já gravados
        // Chamando o service para buscar as viagens
        viagensTable.setItems(FXCollections.observableArrayList(service.listar()));
        atualizarTotal();
    }

    @FXML
    public void editar(){
        Viagem selecionada = viagensTable.getSelectionModel().getSelectedItem();
        if (selecionada == null){
            mostrarErro("Selecione uma viagem na tabela para editar");
            return;
        }
        try {
            double custo = Double.parseDouble(orcamentoField.getText().replace(",", "."));

            service.atualizar(selecionada, partidaField.getText(), destinoField.getText(),
                    dataInicioPicker.getValue(), dataFimPicker.getValue(),
                    custo);

            viagensTable.getItems().setAll(service.listar());
            atualizarTotal();
            limparCampos();
            viagensTable.getSelectionModel().clearSelection();

        }catch (Exception e){
            mostrarErro(e.getMessage());
        }
    }

    @FXML
    public void adicionar(){
        // Handler do botão adicionar viagem

        try{
            // Conversão de dados
            double custo = Double.parseDouble(orcamentoField.getText().replace(",", "."));

            service.adicionar(partidaField.getText(), destinoField.getText(), dataInicioPicker.getValue(), dataFimPicker.getValue(), custo);

            viagensTable.getItems().setAll(service.listar());
            atualizarTotal();



        }catch (Exception ex){
            mostrarErro(ex.getMessage());
        }

    }

    private void limparCampos(){
        partidaField.clear();
        destinoField.clear();
        orcamentoField.clear();
        dataFimPicker.setValue(null);
        dataInicioPicker.setValue(null);
    }

    private void mostrarErro(String msg){
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }

    private void atualizarTotal(){
        lblTotal.setText("Total: R$ " + String.format("%.2f",service.totalGasto()));
    }
}
