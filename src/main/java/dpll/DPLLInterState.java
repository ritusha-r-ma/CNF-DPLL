package dpll;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible to store DPLL intermediate states, it will useful in case of backtracking
 */
public class DPLLInterState {

    private List<List<String>> completeCNF;
    private List<List<String>> lastCnf;
    private int lastGuessIndex;
    private int count;
    private List<List<String>> answer;
    private Boolean doBackTrack;
    private String lastGuess;
    private Boolean lastGuessValue;

    public DPLLInterState(List<List<String>> currCnf) {
        this.lastGuessIndex = 0;
        this.count = 0;
        this.answer = new ArrayList<>();
        this.doBackTrack = false;
        this.lastGuess = null;
        this.lastGuessValue = null;
        this.completeCNF = currCnf;
        this.lastCnf = new ArrayList<>(currCnf);
    }

    /**
     * Getter snd setters for all the variables
     */

    public String getLastGuess() {
        return lastGuess;
    }

    public void setLastGuess(String lastGuess) {
        this.lastGuess = lastGuess;
    }

    public Boolean getLastGuessValue() {
        return lastGuessValue;
    }

    public void setLastGuessValue(Boolean lastGuessValue) {
        this.lastGuessValue = lastGuessValue;
    }

    public List<List<String>> getCompleteCNF() {
        return completeCNF;
    }

    public void setCompleteCNF(List<List<String>> completeCNF) {
        this.completeCNF = completeCNF;
    }

    public List<List<String>> getLastCnf() {
        return lastCnf;
    }

    public void setLastCnf(List<List<String>> lastCnf) {
        this.lastCnf = lastCnf;
    }

    public int getLastGuessIndex() {
        return lastGuessIndex;
    }

    public void setLastGuessIndex(int lastGuessIndex) {
        this.lastGuessIndex = lastGuessIndex;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<List<String>> getAnswer() {
        return answer;
    }

    public void setAnswer(List<List<String>> answer) {
        this.answer = answer;
    }

    public Boolean getDoBackTrack() {
        return doBackTrack;
    }

    public void setDoBackTrack(Boolean doBackTrack) {
        this.doBackTrack = doBackTrack;
    }
}

