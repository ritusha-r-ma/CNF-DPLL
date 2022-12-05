package dpll;

import dpll.cases.EasyCase;
import dpll.cases.HardCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DPLL {
    private EasyCase easyCase;
    private HardCase hardCase;

    public DPLL() {
        easyCase = new EasyCase();
        hardCase = new HardCase();
    }

    /**
     * Apply DPLL, first apply easy case then go for hard case
     * @param cnfState
     * @return
     */
    public List<List<String>> applyDPLLAlg(DPLLInterState cnfState){
        if (easyCase.isSingletonCase(cnfState.getCompleteCNF())) {
            easyCase.applySingletonCase(cnfState);
        }

        if (easyCase.isPureLiteralCase(cnfState.getCompleteCNF())) {
            easyCase.applyPureLiteralCase(cnfState);
        }

        if (hardCase.isHardCase(cnfState.getCompleteCNF())) {
            hardCase.applyHardCase(cnfState);
        }

        return hardCase.doBacktracking(cnfState);
    }

    /**
     * Apply the value of the atom to the previous CNF and generated a new CNF
     * @param cnf
     * @param atom
     * @param guess
     * @return
     */
    public static List<List<String>> perform(List<List<String>> cnf, String atom, Boolean guess) {
        if (Objects.isNull(cnf) || cnf.size() == 0) {
            return null;
        }

        List<List<String>> newCNF = new ArrayList<>();

        for (List<String> s : cnf) {
            if (!(guess && s.contains(atom) || !guess && s.contains("!" + atom))) {
                List<String> newSentence = new ArrayList<>();
                for (String expression : s) {
                    if (!expression.equals(atom) && !expression.equals("!" + atom)){
                        newSentence.add(expression);
                    }
                }

                if (newSentence.size() == 0) {
                    return null;
                }
                newCNF.add(newSentence);
            }
        }

        return newCNF;
    }
}
