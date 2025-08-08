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

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TripController {
    // Elementos do FXML (fx:id)

    @FXML private DatePicker dataInicioPicker;
    @FXML private DatePicker dataFimPicker;
    @FXML private TextField destinoField;
    @FXML private TextField orcamentoField;
    @FXML private Button btnAdicionar;
    @FXML private TableView viagensTable;
    @FXML private TableColumn colCidade;
    @FXML private TableColumn ColIni;
    @FXML private TableColumn ColFim;
    @FXML private TableColumn colCusto;
    @FXML private Label lbtTotal;
}
