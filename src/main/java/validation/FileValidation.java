package validation;

import utilities.ErrorHandlingUtil;
import cnf.graph.Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileValidation {

    public Graph graph;

    public FileValidation(String path) {
        graph = new Graph();
        readInputFile(path);
    }

    private void readInputFile(String ioPath) {
        try {
            int lineNumber = 1;
            String line;
            BufferedReader br = new BufferedReader(new FileReader(ioPath));
            while ((line = br.readLine()) != null) {
                massageInputFile(line.replace(" ", ""), lineNumber);
                lineNumber++;
            }
        } catch (Exception e) {
            ErrorHandlingUtil.errorOccurred(e.getMessage());
        }
    }

    /**
     * Read the file and process into graph
     * @param line
     * @param lineNumber
     */
    private void massageInputFile(String line, int lineNumber) {
        if (line.contains("#") || line.isEmpty()) {
            return;
        }

        if (line.contains(":")) {
            String[] text = line.split(":");

            if (text.length != 2 || !text[1].contains("[") || !text[1].contains("]")) {
                ErrorHandlingUtil.errorOccurred(lineNumber + "of the text file contains single parameter only");
            }

            String[] neighbour = text[1].replace("[", "").replace("]", "").split(",");
            List<String> neighbors = (neighbour.length == 1 && neighbour[0].isEmpty()) ? new ArrayList<>() : Arrays.stream(neighbour).collect(Collectors.toList());

            this.graph.addAllNodes(text[0], neighbors);
        } else {
            ErrorHandlingUtil.errorOccurred(lineNumber + " Bad Format");
        }
    }
}
