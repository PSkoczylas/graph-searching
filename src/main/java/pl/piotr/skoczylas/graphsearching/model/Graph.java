package pl.piotr.skoczylas.graphsearching.model;

import java.util.Collections;
import java.util.List;

public class Graph {
    private List<Vertex> vertexList;
    private boolean directed;

    public Graph(int vertexNumber, boolean directed) {
        createVertexes(vertexNumber);
        this.directed = directed;
    }

    public Graph(List<Vertex> vertexList, boolean directed) {
        this.vertexList = vertexList;
        this.directed = directed;
    }

    private void createVertexes(int vertexNumber) {
        Collections.nCopies(1, vertexNumber)
                .forEach(i -> new Vertex(i, null));
    }

    public void addVertex(Vertex vertex) {
        vertex.setNumber(vertexList.size() + 1);
        vertexList.add(vertex);
    }

    private Vertex addVertex() {
        Vertex vertex = new Vertex(vertexList.size() + 1, null);
        vertexList.add(vertex);
        return vertex;
    }

    public Vertex getVertex(int number) {
        return vertexList.get(number - 1);
    }
}
