package pl.piotr.skoczylas.graphsearching.service;

import pl.piotr.skoczylas.graphsearching.model.Graph;
import pl.piotr.skoczylas.graphsearching.model.Vertex;

public class Dfs {
    private Graph dfsTree;

    public Graph searchGraph(Graph graph) {
        dfsTree = new Graph(graph.getVertexList().size(), graph.getDirected());
        graph.getVertexList().forEach(v -> v.setColor('w'));
        for (Vertex v: graph.getVertexList()) {
            if (v.getColor() == 'w') {
                this.searchVertex(v);
            }
        }

        return dfsTree;
    }

    private void searchVertex(Vertex vertex) {
        vertex.setColor('g');
        System.out.println("Odwiedzam " + vertex.getNumber());
        for (Vertex v: vertex.getEdgeList()) {
            if (v.getColor() == 'w') {
                dfsTree.addEdge(vertex.getNumber(), v.getNumber());
                searchVertex(v);
            }
        }
        vertex.setColor('b');
    }
}
