package pl.piotr.skoczylas.graphsearching.model;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class Graph {
    private List<Vertex> vertexList;
    private Set<Vertex> vertexSet;
    private Boolean directed;

    public Graph(int vertexNumber, boolean directed) {
        this.vertexList = new ArrayList<>();
        this.vertexSet = new HashSet<>();
        this.directed = directed;
        createVertexes(vertexNumber);
    }

    public Graph(List<Vertex> vertexList, boolean directed) {
        this.vertexList = vertexList;
        this.directed = directed;
    }

    private void createVertexes(int vertexNumber) {
        for (int i = 1; i <= vertexNumber; i++) {
            this.addVertex();
        }
        vertexSet.addAll(vertexList);
    }

    public void addVertex(Vertex vertex) {
        if (!vertexSet.contains(vertex)) {
            vertex.setNumber(vertexList.size() + 1);
            vertexList.add(vertex);
        }
    }

    public Vertex addVertex() {
        Vertex vertex = new Vertex(vertexList.size() + 1, null);
        vertexList.add(vertex);
        vertexSet.add(vertex);
        return vertex;
    }

    public void addVertexList(List<Vertex> vertexList) {
        vertexList.forEach(this::addVertex);
    }

    public Vertex getVertex(int number) {
        return vertexList.get(number - 1);
    }

    public int addEdge(Vertex v1, Vertex v2) {
        if (!v1.createConnection(v2)) {
            return -1;
        }

        if (!getDirected()) {
            v2.createConnection(v1);
        }

        return 1;
    }

    public int addEdge(int v1, int v2) {
        if (!checkInputData(v1, v2)) {
            return 0;
        }
        return addEdge(vertexList.get(v1 - 1), vertexList.get(v2 - 1));
    }

    public int removeEdge(Vertex v1, Vertex v2) {
        if (!v1.removeConnection(v2)) {
            return -1;
        }

        if (!getDirected()) {
            v2.removeConnection(v1);
        }

        return 1;
    }

    public int removeEdge(int v1, int v2) {
        if (!checkInputData(v1, v2)) {
            return 0;
        }
        return removeEdge(vertexList.get(v1 - 1), vertexList.get(v2 - 1));
    }

    private boolean checkInputData(int v1, int v2) {
        if (v1 > vertexList.size() || v2 > vertexList.size() || v1 < 1 || v2 < 1) {
            return false;
        }

        return true;
    }

    public boolean checkVertexCorrectness(int v) {
        if (v > vertexList.size() || v < 1) {
            return false;
        }

        return true;
    }

    public void printGraph() {
        for (Vertex vertex: this.vertexList) {
            System.out.print(vertex.getNumber() + ": ");
            vertex.printEdges();
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return vertexList.stream().map(Vertex::toString).collect(Collectors.joining("\n"));
    }
}
