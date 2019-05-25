package pl.piotr.skoczylas.graphsearching.service;

import pl.piotr.skoczylas.graphsearching.model.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFromFile {
    public Graph getGraphFromFile(File file)  {
        try {
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextInt()) {
                int vertexNumber = scanner.nextInt();
                if (scanner.hasNextInt()) {
                    int checkDirected = scanner.nextInt();
                    boolean directed = checkDirected != 0;
                    Graph graph = new Graph(vertexNumber, directed);

                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        String[] vertexes = line.split(" ");
                        for (int i = 1; i < vertexes.length; i++) {
                            graph.addEdge(Integer.parseInt(vertexes[0]), Integer.parseInt(vertexes[i]));
                        }
                    }

                    return graph;
                }
            }
        } catch (Exception e) {}
        return null;
    }
}
