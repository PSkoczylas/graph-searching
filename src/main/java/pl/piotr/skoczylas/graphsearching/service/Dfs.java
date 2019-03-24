package pl.piotr.skoczylas.graphsearching.service;

import pl.piotr.skoczylas.graphsearching.model.Graph;
import pl.piotr.skoczylas.graphsearching.model.Vertex;

public class Dfs {
    public void searchGraph(Graph graph) {
        graph.getVertexList().forEach(v -> v.setColor('w'));
        for (Vertex v: graph.getVertexList()) {
            if (v.getColor() == 'w') {
                this.searchVertex(v);
            }
        }
    }

    private void searchVertex(Vertex vertex) {
        vertex.setColor('g');
        System.out.println("Odwiedzam " + vertex.getNumber());
        for (Vertex v: vertex.getEdgeList()) {
            if (v.getColor() == 'w') {
                searchVertex(v);
            }
        }
        vertex.setColor('b');
    }
}
