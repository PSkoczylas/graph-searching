package pl.piotr.skoczylas.graphsearching.service;

import pl.piotr.skoczylas.graphsearching.model.Graph;
import pl.piotr.skoczylas.graphsearching.model.Vertex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WriteToFile {
    public File saveGraphToFile(File file, Graph graph) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(changeGraphToFileFormat(graph));
            fileWriter.flush();
            fileWriter.close();
        } catch(IOException iOException) {
            return null;
        }
        return file;
    }

    private String changeGraphToFileFormat(Graph graph) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(graph.getVertexList().size() + " ");
        if (graph.getDirected()) {
            stringBuilder.append(1 + "\n");
        } else {
            stringBuilder.append(0 + "\n");
        }

        for (Vertex vertex: graph.getVertexList()) {
            if (!vertex.getEdgeList().isEmpty()) {
                stringBuilder.append(vertex.getNumber() + " ");
                vertex.getEdgeList().forEach(vertex1 -> stringBuilder.append(vertex1.getNumber() + " "));
                stringBuilder.append("\n");
            }
        }

        return new String(stringBuilder);
    }
}
