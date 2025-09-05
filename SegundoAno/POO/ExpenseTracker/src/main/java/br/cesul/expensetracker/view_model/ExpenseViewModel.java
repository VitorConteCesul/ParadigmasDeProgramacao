package br.cesul.expensetracker.view_model;

import br.cesul.expensetracker.model.Expense;
import br.cesul.expensetracker.repository.ExpenseRepository;
import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.bson.types.ObjectId;

import java.time.LocalDate;

// Está é a camada VIEWMODEL
// Não conhece as classes de UI (TextField, Botões...)
// Ponte entre a lógica de domínio e a interface
// Mantemos só as "Propriedades" -> a view fará binding nelas
// 0 menções de controles JavaFX
// Poderá ser testado em JUnit sem testes UI
public class ExpenseViewModel {
    // 1º Setar as propriedades que a view vai 'amarrar' nos componentes de entrada


    // TextField de descrição
    private final StringProperty description = new SimpleStringProperty();

    // DoubleProperty fornece eventos de get e set()
    private final DoubleProperty amount = new SimpleDoubleProperty();
    private final ObjectProperty<LocalDate> date = new SimpleObjectProperty<>(LocalDate.now());

    // Coleção observável que abastecerá a tableView
    private final ObservableList<Expense> expenses = FXCollections.observableArrayList();

    // Porque não precisamos de Properties para colunas da tabela.
    // Porque as colunas são campos obtidos através de valores da tabela
    // Ou seja, o que "Atualiza


    // Propriedade CALCULADA
    private final DoubleBinding total = new DoubleBinding() {
        { bind(expenses); }
        @Override
        protected double computeValue() {
            return expenses.stream().mapToDouble(Expense::getAmount).sum();
        }
    };

    private final ExpenseRepository repo = new ExpenseRepository();

    // Construtor - Carregar os dados e configurar os observadores
    public ExpenseViewModel(){
        // Puxar os itens existentes e colocar na lista observável
        expenses.addAll(repo.findAll());

        expenses.addListener((ListChangeListener<? super Expense>) change ->{
            while(change.next()){
                if (change.wasAdded()){
                    change.getAddedSubList().forEach(repo::insert);
                }
                if (change.wasRemoved()){
                    change.getRemoved().forEach(repo::delete);
                }
            }
        });
    }

    public StringProperty descriptionProperty() {return description;}
    public DoubleProperty amountProperty() {return amount;}
    public ObjectProperty<LocalDate>dateProperty(){return date;}
    public ObservableList<Expense> getExpenses() {return expenses;}
    public DoubleBinding totalProperty() {return total;}

    // Criar uma expense e adicionar na lista

    public void addExpense(){
        // Regras de validação
        if (description.get() == null || description.get().isBlank()) return;

        Expense exp = new Expense(description.get(), amount.get(), date.get());
        expenses.add(0,exp); // Indice 0, linha do topo da tabela
        clearInputs();
    }
    private void clearInputs(){
        // Platform.runLater: garante a execução na thread JavaFX
        Platform.runLater(() -> {
            description.set("");
            amount.set(0);
            date.set(LocalDate.now());
        });
    }

    public void deleteSelect(Expense e){
        if(e != null) expenses.remove(e);
    }
}





























