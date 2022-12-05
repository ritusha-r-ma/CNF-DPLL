package validation;

/**
 * Argument handling and validating class
 */
public class ArgValidation {
    static Boolean isVerboseEnable;
    static int noOfColors;
    static String filePath;

    public ArgValidation(String[] arguments) {
        isVerboseEnable = false;
        filePath = "";
        noOfColors = 0;
        readArgument(arguments);
    }

    private void readArgument(String[] args) {
        for (String arg : args) {
            if (arg.equals("-v")) {
                isVerboseEnable = true;
            } else if (arg.contains(".txt")) {
                filePath = arg;
            } else {
                noOfColors = Integer.parseInt(arg);
            }
        }
    }

    public static Boolean getIsVerboseEnable() {
        return isVerboseEnable;
    }

    public static String getFilePath() {
        return filePath;
    }

    public static int getNoOfColors() {
        return noOfColors;
    }
}
