package pl.piotr.skoczylas.graphsearching.model;

import java.util.*;

public class Vertex {
    private List<Vertex> edgeList;
    private Set<Vertex> edgeSet;
    private int number;

    public Vertex(int number, List<Vertex> edgeList) {
        this.number = number;
        this.edgeList = createEdgeList(edgeList);
        this.edgeSet = createEdgeSet();
        this.edgeSet = edgeSet;
    }

    private List<Vertex> createEdgeList(List<Vertex> edgeList) {
        if (edgeList != null) {
            return edgeList;
        }
        return new ArrayList<>();
    }

    private Set<Vertex> createEdgeSet() {
        Set<Vertex> edgeSet = new HashSet<>();
        if (edgeList == null) {
            return edgeSet;
        }
        edgeSet.addAll(edgeList);
        return edgeSet;
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

    public void setNumber(int number) {
        this.number = number;
    }
}
