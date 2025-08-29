package br.cesul.expensetracker.view;

import br.cesul.expensetracker.model.Expense;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.NumberStringConverter;

import java.awt.*;

import java.time.LocalDate;

// "Controller" vinculado ao FXML
// Faz APENAS a cola entre componentes da tela e a ViewModel
// Aqui não se implementa regras de negócio
public class ExpenseView {
    @FXML private TextField descriptionField;
    @FXML private TextField amountField;
    @FXML private DatePicker datePicker;
    @FXML private Button addButton;
    @FXML private TableView<Expense> expenseTable;
    @FXML private TableColumn<Expense, LocalDate> dateCol;
    @FXML private TableColumn<Expense, String> descCol;
    @FXML private TableColumn<Expense, Number> amountCol;
    @FXML private Button deleteButton;
    @FXML private Label totalLabel;

    // Método chamado automaticamente pelo FXMLLoader logo que o app sobe
    // Primeiro a ser executado
    @FXML
    private void initialize(){
        // Bindings de entrada
        // Campo de descrição ->

        descriptionField.textProperty().bindBidirectional();
        TextFormatter<Number> amountFormatter =
                new TextFormatter<>(new NumberStringConverter());
        amountField.setTextFormatter(amountFormatter);

        // Fazer um binding bidirecional entre o NumberProperty(VM)
        // e o valueProperty (formatter)

        Bindings.bindBidirectional();

        datePicker.valueProperty().bindBidirectional();

        // Configuração da tabela
        // Dizer qual getter do expense abastece cada coluna
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));

        // Ligar a lista observável do viewModel diretamente na tableview
        // (popular os dados)
        expenseTable.setItems();

        // Mostrar a label formatada com o valor total
        // (atualizar sozinha)
        totalLabel.textProperty.bind();

        addButton.setOnAction(e -> vm.addExpense())
    }
}






















