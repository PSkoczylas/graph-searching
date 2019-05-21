package pl.piotr.skoczylas.graphsearching.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import pl.piotr.skoczylas.graphsearching.model.Graph;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pl.piotr.skoczylas.graphsearching.service.Bfs;
import pl.piotr.skoczylas.graphsearching.service.Dfs;
import pl.piotr.skoczylas.graphsearching.service.TopologicalSort;

public class MainController {
    private Graph graph;
    private Integer vertexNumber;

    @FXML
    private TextField vertexNumberGetter;

    @FXML
    private Text showGraphText;

    @FXML
    private CheckBox isDirected;

    @FXML
    private Button addEdgeButton;

    @FXML
    private TextField vertexForEdge1;

    @FXML
    private TextField vertexForEdge2;

    @FXML
    private TextField bfsBeginVertex;

    @FXML
    void addEdge(ActionEvent event) {
        String showText;
        try {
            graph.addEdge(Integer.parseInt(vertexForEdge1.getText()),
                    Integer.parseInt(vertexForEdge2.getText()));
            showText = "Poprawnie wprowadzono krawędź.\nStan grafu:\n";
        } catch(Exception e) {
            showText = "Wprowadzono niepoprawne dane\n";
        }

        showGraphText.setText(showText + graph.toString());

    }

    @FXML
    void getVertexesNumber(ActionEvent event) {
        String showText;
        try {
            vertexNumber = Integer.parseInt(vertexNumberGetter.getText());
            graph = new Graph(vertexNumber, isDirected.isSelected());
            showText = "Poprawnie wprowadzono ilość wierzchołków.\nStan grafu:\n";
            showGraphText.setText(showText + graph.toString());
        } catch(Exception e) {
            showText = "Wprowadzono niepoprawne dane\n";
            showGraphText.setText(showText);
        }
    }

    @FXML
    void runDfs(ActionEvent event) {
        try {
            Dfs dfs = new Dfs();
            dfs.searchGraph(graph);
            showGraphText.setText(dfs.toString());
        } catch (Exception e) {
            showGraphText.setText("Najpierw wprowadź graf\n");
        }
    }

    @FXML
    void runBfs(ActionEvent event) {
        try {
            Bfs bfs = new Bfs();
            bfs.searchGraph(graph, graph.getVertex(Integer.parseInt(bfsBeginVertex.getText())));
            showGraphText.setText(bfs.toString());
        } catch (Exception e) {
            showGraphText.setText("Najpierw wprowadź graf i poprawny wierzchołek startowy\n");
        }
    }

    @FXML
    void runTopSort(ActionEvent event) {
        try {
            TopologicalSort topologicalSort = new TopologicalSort();
            if (topologicalSort.getTopologicalList(graph) != null) {
                showGraphText.setText(topologicalSort.toString());
            } else {
                showGraphText.setText("Graf nie może być posortowany topologicznie." +
                        "Musi być skierowanym grafem acyklicznym");
            }
        } catch (Exception e) {
            showGraphText.setText("Najpierw wprowadź graf\n");
        }
    }

}
