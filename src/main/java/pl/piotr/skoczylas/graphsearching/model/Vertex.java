package pl.piotr.skoczylas.graphsearching.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Vertex {
    private List<Vertex> edgeList;
    private Set<Vertex> edgeSet;
    private int number;

    Vertex(int number, List<Vertex> edgeList, Set<Vertex> edgeSet) {
        this.number = number;
        this.edgeList = createEdgeList(edgeList);
        this.edgeSet = createEdgeSet(edgeSet);
        this.edgeSet = edgeSet;
    }

    private List<Vertex> createEdgeList(List<Vertex> edgeList) {
        if (edgeList != null) {
            return edgeList;
        }
        return new ArrayList<>();
    }

    private Set<Vertex> createEdgeSet(Set<Vertex> edgeSet) {
        if (edgeSet != null) {
            return edgeSet;
        }
        return new HashSet<>();
    }

    public boolean createConnection(Vertex neighbourhood) {
        if (edgeSet.contains(neighbourhood)) {
            return false;
        }

        edgeList.add(neighbourhood);
        edgeSet.add(neighbourhood);
        return true;
    }

    public int getNumber() {
        return number;
    }
}
