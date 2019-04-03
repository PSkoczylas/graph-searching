package pl.piotr.skoczylas.graphsearching.service;

import pl.piotr.skoczylas.graphsearching.model.Graph;
import pl.piotr.skoczylas.graphsearching.model.Vertex;

import java.util.LinkedList;
import java.util.Queue;

public class Bfs {
    private Queue<Vertex> queue = new LinkedList<>();

    public Graph searchGraph(Graph graph, Vertex beginVertex) {
        Graph bfsTree = new Graph(graph.getVertexList().size(), graph.getDirected());
        graph.getVertexList().forEach(v -> v.setColor('w'));
        graph.getVertexList().forEach(v -> v.setDistance(Integer.MAX_VALUE));
        visitVertex(beginVertex,  0);
        queue.add(beginVertex);
        while (!queue.isEmpty()) {
            Vertex currentVertex = queue.poll();
            for (Vertex neighborhood : currentVertex.getEdgeList()) {
                if (neighborhood.getColor() == 'w') {
                    neighborhood.setColor('g');
                    // nad tym sie zastanowic
                    visitVertex(neighborhood,  currentVertex.getDistance() + 1);
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
    }
}
