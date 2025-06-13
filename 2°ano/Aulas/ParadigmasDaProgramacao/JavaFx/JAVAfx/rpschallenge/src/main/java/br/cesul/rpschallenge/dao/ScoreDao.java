package br.cesul.rpschallenge.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Data Acess Object
//Responsável por gravar e ler a coleção "scores" do banco
public class ScoreDao {
    // Metodo save(score) vai salvar o score no banco
    // Metodo list() vai devolver List<String> prontas para exibir num listview
    // O metodo List poderia retornar uma lista de scores. Mas é uma simples
    // pra UI ja receber a lista


    private static final String FILE_PATH = "scores.txt";

    public void save(String score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(score);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> list() {
        List<String> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }
}
