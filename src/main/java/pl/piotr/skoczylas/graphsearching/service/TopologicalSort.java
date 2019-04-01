package pl.piotr.skoczylas.graphsearching.service;

import pl.piotr.skoczylas.graphsearching.model.Graph;
import pl.piotr.skoczylas.graphsearching.model.Vertex;

import java.util.LinkedList;

public class TopologicalSort extends Dfs {
    LinkedList<Vertex> outTimes = new LinkedList<>();

    public LinkedList<Vertex> getTopologicalList(Graph g) {
        if(!g.getDirected()) {
            return null;
        }
        super.searchGraph(g);
        if (hasCycle == true) {
            return null;
        }
        return outTimes;
    }

    @Override
    public void outFromVertex(Vertex vertex) {
        vertex.setColor('b');
        outTimes.addFirst(vertex);
        vertex.setTimeOut(time);
    }
}
