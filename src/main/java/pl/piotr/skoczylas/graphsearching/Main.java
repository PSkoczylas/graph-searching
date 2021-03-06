package pl.piotr.skoczylas.graphsearching;

import pl.piotr.skoczylas.graphsearching.model.Graph;
import pl.piotr.skoczylas.graphsearching.model.Vertex;
import pl.piotr.skoczylas.graphsearching.service.Bfs;
import pl.piotr.skoczylas.graphsearching.service.TopologicalSort;
import pl.piotr.skoczylas.graphsearching.view.MainView;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        MainView mainPage = new MainView();
        mainPage.startApplication();
    }

    public static void checkGraph() {
        Graph g = new Graph(10, true);
        /*g.addEdge(1, 8);
        g.addEdge(8, 10);
        g.addEdge(8, 3);
        g.addEdge(10, 9);
        g.addEdge(9, 2);
        //g.addEdge(9, 4);
        g.addEdge(9, 5);
        g.addEdge(5, 6);
        g.addEdge(2, 6);
        //g.addEdge(10, 4);
        g.addEdge(4, 1);*/
        //Bfs bfs = new Bfs();
        //Graph tree = bfs.searchGraph(g, g.getVertex(1));
        TopologicalSort topologicalSort = new TopologicalSort();
        LinkedList<Vertex> topological = topologicalSort.getTopologicalList(g);
        System.out.println(g);
        System.out.println(topological);
    }

    public static void dfs() {
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
        //Bfs bfs = new Bfs();
        //Graph tree = bfs.searchGraph(g, g.getVertex(1));
        Bfs bfs = new Bfs();
        System.out.println(g);
        Graph bfsTree = bfs.searchGraph(g, g.getVertex(1));
        //g.getVertexList().forEach(v -> System.out.println(v.getDistance()));
        //System.out.println(bfsTree);
    }
}
