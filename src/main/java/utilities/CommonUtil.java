package utilities;

import java.util.List;
import java.util.Objects;

public class CommonUtil {

    public static void printCNFClauses(List<List<String>> cnfClauses, boolean isVerboseApplied) {
        if (!isVerboseApplied) {
            return;
        }

        System.out.println("***** CNF Clauses for given problem *****");
        for (List<String> cnf : cnfClauses) {
            for (String clause : cnf) {
                System.out.print(clause + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    public static void printDPLLResult(List<List<String>> answer) {
        if (Objects.isNull(answer)) {
            ErrorHandlingUtil.errorOccurred("DPLL not applicable for the given problem.");
        }

        System.out.println("***** DPLL Assignment Values *****");
        for (List<String> a : answer) {
            if (a.get(1).equals("true")) {
                String[] result = a.get(0).split("_");
                System.out.println(result[0] + " = " + new ColorPanel().getCompleteColorName(result[1]));
            }
        }
    }
}
