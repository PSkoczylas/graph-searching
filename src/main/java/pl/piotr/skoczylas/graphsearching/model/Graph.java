package pl.piotr.skoczylas.graphsearching.model;

import java.util.Collections;
import java.util.List;

public class Graph {
    private List<Vertex> vertexList;
    private boolean directed;


    Graph(int vertexNumber) {
        createVertexes(vertexNumber);
    }

    Graph(List<Vertex> vertexList) {
        this.vertexList = vertexList;
    }

    private void createVertexes(int vertexNumber) {
        Collections.nCopies(1, vertexNumber)
                .forEach(i -> new Vertex(i, null, null));
    }
}
