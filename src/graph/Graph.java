package graph;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    HashMap<Integer,Node> nodes;
    HashSet<Edge> edges;

    public Graph(){
        nodes = new HashMap<Integer, Node>();
        edges = new HashSet<Edge>();
    }



}
