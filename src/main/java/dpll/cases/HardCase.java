package dpll.cases;

import dpll.DPLLInterState;
import dpll.DPLL;
import validation.ArgValidation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * This class is responsible for applying hard case i.e. guessing an atom and apply to CNF
 */
public class HardCase {
    private boolean isVerboseApplied;

    public HardCase() {
        isVerboseApplied = ArgValidation.getIsVerboseEnable();
    }

    /**
     * return true, if hard case applicable else false
     * @param cnf
     * @return
     */
    public boolean isHardCase(List<List<String>> cnf) {
        return Objects.nonNull(cnf) && cnf.size() != 0;
    }

    // Apply Hard case
    public void applyHardCase(DPLLInterState cnfState){
        String atom = getSmallestAlphaAtom(cnfState.getCompleteCNF());

        if (Objects.nonNull(atom)) {
            List<List<String>> updatedCNF = new ArrayList<>(cnfState.getCompleteCNF());
            Boolean guess = !cnfState.getDoBackTrack();

            updatedCNF = applyAtomToCNF(cnfState, atom, updatedCNF, guess);

            if (isVerboseApplied && !cnfState.getDoBackTrack()) {
                System.out.println("HARD CASE : Guess " + atom + " to be true");
            }

            cnfState.setCount(cnfState.getCount() + 1);
            cnfState.setCompleteCNF(updatedCNF);

            if (Objects.nonNull(updatedCNF)){
                cnfState.setLastGuess(atom);
                cnfState.setLastGuessValue(guess);
                cnfState.setLastCnf(updatedCNF);
                cnfState.setDoBackTrack(false);
            }
        }
    }

    /**
     * This will update our CNF, for whatever the value we pick and return the updated CNF
     * @param cnfState
     * @param atom
     * @param updatedCNF
     * @param guess
     * @return
     */
    private static List<List<String>> applyAtomToCNF(DPLLInterState cnfState, String atom, List<List<String>> updatedCNF, Boolean guess) {
        if (atom.contains("!")) {
            updatedCNF = DPLL.perform(updatedCNF, atom.substring(1), !guess);
            cnfState.getAnswer().add(new ArrayList<>(Arrays.asList(atom.substring(1),String.valueOf(!guess))));
        } else {
            updatedCNF = DPLL.perform(updatedCNF, atom, guess);
            cnfState.getAnswer().add(new ArrayList<>(Arrays.asList(atom,String.valueOf(guess))));
        }

        return updatedCNF;
    }


    public List<List<String>> doBacktracking(DPLLInterState cnfState) {
        if (Objects.isNull(cnfState.getCompleteCNF())) {

            if (cnfState.getDoBackTrack() || Objects.isNull(cnfState.getLastGuess())) {
                return null;
            } else {

                if (isVerboseApplied) {
                    System.out.println("Backtrack : Guess " + cnfState.getLastGuess() + " to be " + !cnfState.getLastGuessValue());
                }

                cnfState.setCount(0);
                cnfState.setLastGuessIndex(cnfState.getLastGuessIndex() + cnfState.getCount());

                List<List<String>> backtrackAnswer =  cnfState.getAnswer().subList(0, cnfState.getAnswer().size() - cnfState.getLastGuessIndex());
                backtrackAnswer.get(backtrackAnswer.size()-1).set(1, "false");

                cnfState.setCompleteCNF(cnfState.getLastCnf());
                cnfState.setAnswer(backtrackAnswer);
                cnfState.setDoBackTrack(true);

                return new DPLL().applyDPLLAlg(cnfState);
            }
        } else if (cnfState.getCompleteCNF().isEmpty()) {
            return cnfState.getAnswer();
        }

        return new DPLL().applyDPLLAlg(cnfState);
    }

    /**
     * To make sure we get same result, pick the smallest lexicographical atom
     * @param cnfs
     * @return
     */
    public String getSmallestAlphaAtom(List<List<String>> cnfs) {
        if (Objects.isNull(cnfs) || cnfs.size() == 0) {
            return null;
        }

        String smallestAtom = cnfs.get(0).get(0);
        String atomWithoutNegation = !smallestAtom.contains("!") ? smallestAtom : smallestAtom.substring(1);

        for (List<String> cnf : cnfs) {
            for (String sen : cnf) {
                if (sen.contains("!")) {
                    if (sen.substring(1).compareTo(atomWithoutNegation) < 0) {
                        smallestAtom = sen;
                        atomWithoutNegation = sen.substring(1);
                    }
                } else {
                    if (sen.compareTo(atomWithoutNegation) < 0) {
                        smallestAtom = sen;
                        atomWithoutNegation = sen;
                    }
                }
            }
        }

        return smallestAtom;
    }
}
