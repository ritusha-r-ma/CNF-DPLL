package cnf.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * This class is responsible for making graph and keeping count of all vertices
 */
public class Graph {
    private HashMap<String, List<String>> nodeAdjMap = new HashMap<>();
    private HashSet<String> allNodes = new HashSet<>();

    public void addAllNodes(String node, List<String> adjNode) {
        allNodes.add(node);
        allNodes.addAll(adjNode);
        nodeAdjMap.put(node, adjNode);
    }

    public HashMap<String, List<String>> getNodeAdjMap() {
        return nodeAdjMap;
    }

    public HashSet<String> getAllNodes() {
        return allNodes;
    }
}
