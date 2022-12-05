package utilities;

import java.util.HashMap;

public class ColorPanel {
    private static HashMap<String, String> color;
    private static HashMap<String, String> defColor;

    public ColorPanel() {
        color = new HashMap<>();
        color.put("1", "R");
        color.put("2", "G");
        color.put("3", "B");
        color.put("4", "Y");

        defColor = new HashMap<>();
        defColor.put("R", "Red");
        defColor.put("G", "Green");
        defColor.put("B", "Blue");
        defColor.put("Y", "Yellow");
    }

    public String getCompleteColorName(String col) {
        return defColor.get(col);
    }

    public String getEncodedColor(String num) {
        return color.get(num);
    }
}
