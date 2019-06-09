package pl.piotr.skoczylas.graphsearching.model;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class Vertex {
    private List<Vertex> edgeList;
    private Set<Vertex> edgeSet;
    private Integer distance;
    private Integer number;
    private int timeIn;
    private int timeOut;
    private char color;

    public Vertex(int number, List<Vertex> edgeList) {
        this.number = number;
        this.edgeList = createEdgeList(edgeList);
        this.edgeSet = createEdgeSet();
        this.edgeSet = new HashSet<>();
        this.timeIn = this.timeOut = -1;
        this.color = 'w';
        this.distance = Integer.MAX_VALUE;
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

    boolean createConnection(Vertex neighbourhood) {
        if (edgeSet.contains(neighbourhood)) {
            return false;
        }

        edgeList.add(neighbourhood);
        edgeSet.add(neighbourhood);
        return true;
    }

    boolean removeConnection(Vertex neighbourhood) {
        if (edgeSet.contains(neighbourhood)) {
            edgeSet.remove(neighbourhood);
            edgeList.remove(neighbourhood);
            return true;
        }

        return false;
    }

    void printEdges() {
        edgeList.forEach(e -> System.out.print(e.getNumber() + ", "));
    }

    @Override
    public String toString() {
        return this.number + ": " + edgeList.stream()
                .map(v -> v.getNumber().toString())
                .collect(Collectors.joining(", "));
    }
}
