package cnf;

import utilities.ColorPanel;

import java.util.*;

/**
 * This class is responsible for generating CNF from graph
 */
public class CNF {
    ColorPanel colorPanel;
    List<List<String>> cnfStatements;

    public CNF(Map<String, List<String>> allNodesMap, Set<String> allNodesSet, int noOfColorsAllowed) {
        colorPanel = new ColorPanel();
        cnfStatements = new ArrayList<>();
        stateAllCNFNodes(allNodesMap, allNodesSet, noOfColorsAllowed);
    }

    /**
     * This method will generate childCNF and parentCNF
     * @param nodes
     * @param allNodesSet
     * @param noOfColorsAllowed
     */
    public void stateAllCNFNodes(Map<String, List<String>> nodes, Set<String> allNodesSet, int noOfColorsAllowed) {
        for (Map.Entry<String, List<String>> node : nodes.entrySet()) {
            for (String childNode : node.getValue()) {
                generateChildCNF(node.getKey(), childNode, noOfColorsAllowed);
            }
        }

        generateParentCNF(allNodesSet, noOfColorsAllowed);
    }

    /**
     * To generate negate parent CNF and all colors parent CNF
     * @param allNodesSet
     * @param noOfColorsAllowed
     */
    public void generateParentCNF(Set<String> allNodesSet, int noOfColorsAllowed) {
        for (String n : allNodesSet) {
            generateNegateParentCNF(n, noOfColorsAllowed);
            generateParentCNF(n, noOfColorsAllowed);
        }
    }

    public void generateNegateParentCNF(String node, int col) {
        List<String> cnf = new ArrayList<>();
        int color = 1;
        while (color <= col) {
            String colorValue = colorPanel.getEncodedColor(String.valueOf(color));
            cnf.add(("!" + node + "_" + colorValue));
            color++;
        }

        for (int i = 0; i < cnf.size(); i++) {
            for (int j = i + 1; j< cnf.size(); j++) {
                 List<String> separatedCNF = new ArrayList<>();
                 separatedCNF.add(cnf.get(i));
                 separatedCNF.add(cnf.get(j));
                 cnfStatements.add(separatedCNF);
            }
        }
    }

    public void generateParentCNF(String parentNode, int col) {
        List<String> cnf = new ArrayList<>();

        int color = 1;
        while (color <= col) {
            String colorValue = colorPanel.getEncodedColor(String.valueOf(color));
            cnf.add((parentNode + "_" + colorValue));
            color++;
        }
        cnfStatements.add(cnf);
    }

    /**
     * Generate child node CNF with a given parentNode
     * @param parentNode
     * @param child
     * @param col
     */
    public void generateChildCNF(String parentNode, String child, int col) {
        int color = 1;
        while (color <= col) {
            List<String> cnf = new ArrayList<>();
            String colorValue = colorPanel.getEncodedColor(String.valueOf(color));
            cnf.add("!" + parentNode + "_" + colorValue);
            cnf.add("!" + child + "_" + colorValue);

            cnfStatements.add(cnf);
            color++;
        }
    }


    public List<List<String>> getCnfStatements() {
        return cnfStatements;
    }
}
