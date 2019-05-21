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
    private List<Vertex> vertexTimeList;
    protected Graph graph;

    public Graph searchGraph(Graph graph) {
        this.graph = graph;
        vertexTimeList = new LinkedList<>();
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
        vertexTimeList.add(vertex);
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
        vertexTimeList.add(vertex);
    }

    public String toString() {
        time = 0;
        return "Przebieg przeszukiwania w głąb:\n" +
                vertexTimeList.stream().map(this::printVertexInformation).collect(Collectors.joining("\n"))
                + "\n\n" + printCycleInformation() + "\n\nLas przeszukiwania w głąb: " + dfsTree.toString()
                + "\nPrzeszukwany graf:\n" + graph.toString();
    }

    private String printVertexInformation(Vertex vertex) {
        time++;
        if (vertex.getTimeIn() == time) {
            return "Odwiedzam wierzchołek numer " + vertex.getNumber();
        }

        return "Wychodze z wierzchołka numer " + vertex.getNumber();
    }

    private String printCycleInformation() {
        if (hasCycle) {
            return "Graf posiada cykl";
        }

        return "Graf nie posiada cyklu";
    }
}
