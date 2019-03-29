package pl.piotr.skoczylas.graphsearching;

import pl.piotr.skoczylas.graphsearching.model.Graph;
import pl.piotr.skoczylas.graphsearching.model.Vertex;
import pl.piotr.skoczylas.graphsearching.service.Dfs;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*
        ArrayList<Vertex> v = new ArrayList<>();
        v.add(new Vertex(1, null));
        v.add(new Vertex(2, null));
        ArrayList<Vertex> v2 = new ArrayList<>();
        v2.add(new Vertex(5, null));
        v2.add(new Vertex(4, null));
        v.add(new Vertex(3, v2));
        //v.get(2).createConnection(v2);
        Graph g = new Graph(v, false);
        g.printGraph();
        Dfs dfs = new Dfs();
        dfs.searchGraph(g);*/
        //Graph g = new Graph();
        Graph g = new Graph(10, false);
        g.addEdge(1, 8);
        g.addEdge(8, 10);
        g.addEdge(8, 3);
        g.addEdge(10, 9);
        g.addEdge(9, 2);
        g.addEdge(9, 4);
        g.addEdge(9, 5);
        g.addEdge(5, 6);
        g.addEdge(2, 6);
        g.addEdge(10, 4);
        g.addEdge(4, 1);
        Dfs dfs = new Dfs();
        Graph tree = dfs.searchGraph(g);
        System.out.println(g);
        System.out.println(tree);
    }
}
