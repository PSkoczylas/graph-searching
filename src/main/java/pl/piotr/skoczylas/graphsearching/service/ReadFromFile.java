package pl.piotr.skoczylas.graphsearching.service;

import pl.piotr.skoczylas.graphsearching.model.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFromFile {
    public Graph getGraphFromFile()  {
        try {
            Scanner scanner = new Scanner(new File("graph.txt"));
            if (scanner.hasNextInt()) {
                int vertexNumber = scanner.nextInt();
                if (scanner.hasNextInt()) {
                    boolean directed;
                    int checkDirected = scanner.nextInt();
                    if (checkDirected == 0) {
                        directed = false;
                    } else {
                        directed = true;
                    }

                    Graph graph = new Graph(vertexNumber, directed);

                    while (scanner.hasNextInt()) {
                        graph.addEdge(scanner.nextInt(), scanner.nextInt());
                    }

                    return graph;
                }
            }
        } catch (FileNotFoundException f) {

        }
        return null;
    }
}
