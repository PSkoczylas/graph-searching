package pl.piotr.skoczylas.graphsearching.service;

import pl.piotr.skoczylas.graphsearching.model.Graph;
import pl.piotr.skoczylas.graphsearching.model.Vertex;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Dfs {
    private Graph dfsTree;
    protected int time;
    public boolean hasCycle;
    private List<Vertex> timeInList = new LinkedList<>();
    private List<Vertex> timeOutList = new LinkedList<>();
    protected Graph graph;

    public Graph searchGraph(Graph graph) {
        this.graph = graph;
        hasCycle = false;
        dfsTree = new Graph(graph.getVertexList().size(), graph.getDirected());
        graph.getVertexList().forEach(v -> v.setColor('w'));
        time = 0;
        for (Vertex v: graph.getVertexList()) {
            if (v.getColor() == 'w') {
                this.searchVertex(v, null);
            }
        }

        return dfsTree;
    }

    private void searchVertex(Vertex vertex, Vertex parent) {
        time++;
        vertex.setTimeIn(time);
        vertex.setColor('g');
        timeInList.add(vertex);
        for (Vertex v: vertex.getEdgeList()) {
            if (v.getColor() == 'w') {
                dfsTree.addEdge(vertex.getNumber(), v.getNumber());
                searchVertex(v, vertex);
            } else {
                if (v.getColor() == 'g' && (graph.getDirected() || v != parent)) {
                    hasCycle = true;
                }
            }
        }
        outFromVertex(vertex);
    }

    protected void outFromVertex(Vertex vertex) {
        vertex.setColor('b');
        time++;
        vertex.setTimeOut(time);
        timeOutList.add(vertex);
    }

    public String toString() {
        time = 0;
        return "Przebieg przeszukiwania w głąb.\n\nKolejność wierzchołków według czasu wejścia:\n" +
                printVertexInformation(timeInList) + "\n\nKolejność wierzchołków według czasu wyjścia\n" +
                printVertexInformation(timeOutList) + "\n\n" + printCycleInformation() +
                "\n\nLas przeszukiwania w głąb:\n" + dfsTree.toString()
                + "\n\nPrzeszukwany graf:\n" + graph.toString();
    }

    private String printVertexInformation(List<Vertex> vertexList) {
        return vertexList.stream().map(v -> v.getNumber().toString()).collect(Collectors.joining(" -> "));
    }

    private String printCycleInformation() {
        if (hasCycle) {
            return "Graf posiada cykl";
        }

        return "Graf nie posiada cyklu";
    }
}
