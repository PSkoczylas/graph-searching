package pl.piotr.skoczylas.graphsearching.service;

import pl.piotr.skoczylas.graphsearching.model.Graph;
import pl.piotr.skoczylas.graphsearching.model.Vertex;

public class Dfs {
    private Graph dfsTree;
    protected int time;
    public boolean hasCycle;

    public Graph searchGraph(Graph graph) {
        hasCycle = false;
        dfsTree = new Graph(graph.getVertexList().size(), graph.getDirected());
        graph.getVertexList().forEach(v -> v.setColor('w'));
        time = 0;
        for (Vertex v: graph.getVertexList()) {
            if (v.getColor() == 'w') {
                this.searchVertex(v);
            }
        }

        return dfsTree;
    }

    private void searchVertex(Vertex vertex) {
        time++;
        vertex.setTimeIn(time);
        vertex.setColor('g');
        System.out.println("Odwiedzam " + vertex.getNumber());
        for (Vertex v: vertex.getEdgeList()) {
            if (v.getColor() == 'w') {
                dfsTree.addEdge(vertex.getNumber(), v.getNumber());
                searchVertex(v);
            } else {
                if (v.getColor() == 'g') {
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
    }
}
