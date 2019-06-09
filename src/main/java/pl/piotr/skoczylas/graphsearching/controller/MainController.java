package pl.piotr.skoczylas.graphsearching.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.piotr.skoczylas.graphsearching.model.Graph;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pl.piotr.skoczylas.graphsearching.service.*;
import pl.piotr.skoczylas.graphsearching.view.MainView;

import java.io.File;

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

    private File currentFile;

    @FXML
    void addEdge(ActionEvent event) {
        String showText;
        try {
            int result = graph.addEdge(Integer.parseInt(vertexForEdge1.getText()),
                    Integer.parseInt(vertexForEdge2.getText()));
            if (result == 1) {
                showText = "Poprawnie wprowadzono krawędź\n\nStan grafu:\n";
            } else {
                if (result == -1) {
                    showText = "Taka krawędź już istnieje.\nStan grafu:\n";
                } else {
                    showText = "Nie można utworzyć krawędzi, ponieważ podany wierzchołek nie istnieje\nStan grafu:\n";
                }
            }
        } catch(Exception e) {
            showText = "Wprowadzono niepoprawne dane\n";
        }

        if (graph != null) {
            showGraphText.setText(showText + graph.toString());
        } else {
            showGraphText.setText("Najpierw wprowadź graf\nWczytaj graf z pliku lub z klawiatury.\n");
        }
    }

    @FXML
    void removeEdge(ActionEvent event) {
        String showText;
        try {
            int result = graph.removeEdge(Integer.parseInt(vertexForEdge1.getText()),
                    Integer.parseInt(vertexForEdge2.getText()));
            if (result == 1) {
                showText = "Poprawnie usunięto krawędź\n\nStan grafu:\n";
            } else {
                if (result == -1) {
                    showText = "Podana krawędź nie istnieje.\nStan grafu:\n";
                } else {
                    showText = "Podana krawędź nie istnieje. Podano niepoprawny numer wierzchołka.\nStan grafu\n";
                }
            }
        } catch(Exception e) {
            showText = "Wprowadzono niepoprawne dane\n";
        }

        if (graph != null) {
            showGraphText.setText(showText + graph.toString());
        } else {
            showGraphText.setText("Najpierw wprowadź graf\nWczytaj graf z pliku lub z klawiatury.\n\n ");
        }
    }

    @FXML
    void getVertexesNumber(ActionEvent event) {
        String showText;
        try {
            vertexNumber = Integer.parseInt(vertexNumberGetter.getText());
            graph = new Graph(vertexNumber, isDirected.isSelected());
            currentFile = null;
            showText = "Poprawnie wprowadzono ilość wierzchołków.\n\nStan grafu:\n";
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
            String result = dfs.toString();
            showGraphText.setText(result);
            //showGraphText.setText(dfs.toString());
            System.out.println(result);
        } catch (Exception e) {
            showGraphText.setText("Najpierw wprowadź graf\n");
        }
    }

    @FXML
    void runBfs(ActionEvent event) {
        try {
            Bfs bfs = new Bfs();
            int beginVertexNumber = Integer.parseInt(bfsBeginVertex.getText());
            if (!graph.checkVertexCorrectness(beginVertexNumber)) {
                showGraphText.setText("Wprowadzono niepoprawny numer wierzchołka startowego\n\nStan grafu:\n" +
                        graph.toString());
            } else {
                bfs.searchGraph(graph, graph.getVertex(beginVertexNumber));
                //showGraphText.setText(bfs.toString());
                String result = bfs.toString();
                showGraphText.setText(result);
                System.out.println(result);
            }
        } catch (Exception e) {
            if (graph == null) {
                showGraphText.setText("Najpierw wprowadź graf\n");
            } else {
                showGraphText.setText("Najpierw wprowadź graf i poprawny wierzchołek startowy\n");
            }
        }
    }

    @FXML
    void runTopSort(ActionEvent event) {
        try {
            TopologicalSort topologicalSort = new TopologicalSort();
            if (topologicalSort.getTopologicalList(graph) != null) {
                String result = topologicalSort.toString();
                showGraphText.setText(result);
                System.out.println(result);
            } else {
                showGraphText.setText("Graf nie może być posortowany topologicznie. " +
                        "Musi być skierowanym grafem acyklicznym\n\nStan grafu:\n" + graph.toString());
            }
        } catch (Exception e) {
            showGraphText.setText("Najpierw wprowadź graf\n");
        }
    }

    @FXML
    private AnchorPane mainWindow;

    @FXML
    void loadGraphFromFile(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Wybierz plik z grafem");
            Stage stage = (Stage) mainWindow.getScene().getWindow();
            ReadFromFile readFromFile = new ReadFromFile();
            currentFile = fileChooser.showOpenDialog(stage);
            graph = readFromFile.getGraphFromFile(currentFile);
            showGraphText.setText("Poprawnie wczytano graf z pliku\nGraf został utworzony\n\nStan grafu:\n" + graph.toString());
        } catch(Exception e) {
            showGraphText.setText("Nie wczytano pliku lub format jest niepoprawny\n");
        }
    }

    @FXML
    void saveGraphToFile(ActionEvent event) {
        Graph copy = graph;
        try {
            File file = currentFile;
            if (currentFile == null) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Wybierz plik do zapisania grafu");
                Stage stage = (Stage) mainWindow.getScene().getWindow();
                file = fileChooser.showOpenDialog(stage);
            }
            WriteToFile writeToFile = new WriteToFile();
            writeToFile.saveGraphToFile(file, graph);
            currentFile = file;
            showGraphText.setText("Poprawnie zapisano graf do pliku\n\nStan grafu:\n" + graph.toString());
        } catch(Exception e) {
            if (graph == null) {
                showGraphText.setText("Nie wprowadzono grafu\n");
            } else {
                showGraphText.setText("Nie zapisano pliku lub format jest niepoprawny\n");
            }
        }
    }

}
