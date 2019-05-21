package pl.piotr.skoczylas.graphsearching.service;

import pl.piotr.skoczylas.graphsearching.model.Graph;
import pl.piotr.skoczylas.graphsearching.model.Vertex;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Bfs {
    private Queue<Vertex> queue = new LinkedList<>();
    private List<Vertex> vertexDistanceList = new LinkedList<>();
    private Graph bfsTree, graph;

    public Graph searchGraph(Graph graph, Vertex beginVertex) {
        this.graph = graph;
        bfsTree = new Graph(graph.getVertexList().size(), graph.getDirected());
        graph.getVertexList().forEach(v -> v.setColor('w'));
        graph.getVertexList().forEach(v -> v.setDistance(Integer.MAX_VALUE));
        visitVertex(beginVertex, 0);
        queue.add(beginVertex);
        while (!queue.isEmpty()) {
            Vertex currentVertex = queue.poll();
            for (Vertex neighborhood : currentVertex.getEdgeList()) {
                if (neighborhood.getColor() == 'w') {
                    neighborhood.setColor('g');
                    visitVertex(neighborhood, currentVertex.getDistance() + 1);
                    queue.add(neighborhood);
                    bfsTree.addEdge(currentVertex.getNumber(), neighborhood.getNumber());
                }
            }
            currentVertex.setColor('b');
        }

        return bfsTree;
    }

    private void visitVertex(Vertex vertex, int distance) {
        vertex.setColor('g');
        vertex.setDistance(distance);
        queue.add(vertex);
        vertexDistanceList.add(vertex);
    }

    public String toString() {
        return "Przebieg przeszukiwania wszerz:\n" +
                vertexDistanceList.stream().map(this::printVertexInformation).collect(Collectors.joining("\n"))
                + "\n\nDrzewo przeszukiwania wszerz:\n" + bfsTree.toString()
                + "\nPrzeszukwany graf:\n" + graph.toString();
    }

    private String printVertexInformation(Vertex vertex) {
        return "Odwiedzam wierzchołek numer " + vertex.getNumber() +
                "\nOdległość od źródła: " + vertex.getDistance();
    }
}