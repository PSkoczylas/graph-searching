package pl.piotr.skoczylas.graphsearching.service;

import pl.piotr.skoczylas.graphsearching.model.Graph;
import pl.piotr.skoczylas.graphsearching.model.Vertex;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class TopologicalSort extends Dfs {
    private LinkedList<Vertex> outTimes = new LinkedList<>();

    public LinkedList<Vertex> getTopologicalList(Graph g) {
        if(!g.getDirected()) {
            return null;
        }
        super.searchGraph(g);
        if (hasCycle) {
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

    public String toString() {
        return "Wierzchołki posortowane topologicznie:\n" +
                outTimes.stream().map(v -> v.getNumber().toString()).collect(Collectors.joining(" -> "))
                + "\n" + "\nPrzeszukwany graf:\n" + graph.toString();
    }
}
