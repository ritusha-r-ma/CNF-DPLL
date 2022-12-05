package dpll.cases;

import dpll.DPLLInterState;
import dpll.DPLL;
import validation.ArgValidation;

import java.util.*;
import java.util.stream.Collectors;

/**
 * To apply pure literal and easy case to given CNF
 */
public class EasyCase {
    private boolean isVerboseApplied;

    public EasyCase() {
        isVerboseApplied = ArgValidation.getIsVerboseEnable();
    }

    /**
     * return true, if singleton case applies else return false
     * @param cnfs
     * @return
     */
    public Boolean isSingletonCase(List<List<String>> cnfs) {
        String singletonAtom = getSingletonAtom(cnfs);
        return !(singletonAtom == null || singletonAtom.isEmpty());
    }

    /**
     * return true, if pure literal case applies else return false
     * @param cnfs
     * @return
     */
    public Boolean isPureLiteralCase(List<List<String>> cnfs) {
        String pureLiteralAtom = getPureLiteralAtom(cnfs);
        return !(pureLiteralAtom == null || pureLiteralAtom.isEmpty());
    }

    // Apply Singleton case
    public void applySingletonCase(DPLLInterState cnfState) {
        while (Objects.nonNull(getSingletonAtom(cnfState.getCompleteCNF()))) {

            List<List<String>> updatedCNF = new ArrayList<>(cnfState.getCompleteCNF());
            String singletonAtom = getSingletonAtom(updatedCNF);

            if (isVerboseApplied) {
                System.out.println("EASY CASE : Unit clause " + singletonAtom);
            }

            doProcessing(cnfState, updatedCNF, singletonAtom);
        }
    }

    // Apply Pure literal case
    public void applyPureLiteralCase(DPLLInterState cnfState) {
        while (Objects.nonNull(getPureLiteralAtom(cnfState.getCompleteCNF()))) {

            List<List<String>> currCnf = new ArrayList<>(cnfState.getCompleteCNF());
            String pureLiteralAtom = getPureLiteralAtom(currCnf);

            if (isVerboseApplied) {
                System.out.println("EASY CASE : pure literal " + pureLiteralAtom + " = true");
            }

            doProcessing(cnfState, currCnf, pureLiteralAtom);
        }
    }

    // Process singleton and easy case
    private void doProcessing(DPLLInterState cnfState, List<List<String>> updatedCNF, String atom) {
        if (atom.contains("!")) {
            updatedCNF = DPLL.perform(updatedCNF, atom.substring(1), false);
            cnfState.getAnswer().add(new ArrayList<>(Arrays.asList(atom.substring(1),String.valueOf(false))));

        } else {
            updatedCNF = DPLL.perform(updatedCNF, atom, true);
            cnfState.getAnswer().add(new ArrayList<>(Arrays.asList(atom,String.valueOf(true))));
        }

        cnfState.setCount(cnfState.getCount() + 1);
        cnfState.setCompleteCNF(updatedCNF);
    }

    /**
     * Return null, if no singleton exist else return singleton atom
     * @param cnf
     * @return
     */
    public String getSingletonAtom(List<List<String>> cnf){
        if (Objects.isNull(cnf) || cnf.size() == 0) {
            return null;
        }

        List<List<String>> singleton = cnf.stream().filter(c -> c.size() == 1).collect(Collectors.toList());
        return singleton.size() == 0 ? null: singleton.get(0).get(0);
    }

    /**
     * Return null, if no pure literal exist else return pure literal atom
     * @param cnf
     * @return
     */
    public String getPureLiteralAtom(List<List<String>> cnf) {
        if (Objects.isNull(cnf) || cnf.size() == 0) {
            return null;
        }

        Set<String> uniqueCNFClause = new HashSet<>();
        for (List<String> sentence : cnf) {
            uniqueCNFClause.addAll(sentence);
        }

        for (List<String> sentence : cnf) {
            for (String expression: sentence) {
                if (expression.contains("!")) {
                    if (!uniqueCNFClause.contains(expression.substring(1))) {
                        return expression;
                    }
                } else {
                    if (!uniqueCNFClause.contains("!" + expression)) {
                        return expression;
                    }
                }
            }
        }

        return null;
    }
}
