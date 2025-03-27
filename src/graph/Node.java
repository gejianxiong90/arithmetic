package graph;

import java.util.ArrayList;
import java.util.List;

// 点结构的描述  A 0
public class Node {
    int value;
    int in;
    int out;
    List<Node> nexts;
    List<Edge> edges;

    public Node(int value){
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
    }
}
