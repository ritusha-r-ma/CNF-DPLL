package main;

import cnf.CNF;
import dpll.DPLLInterState;
import dpll.DPLL;
import utilities.CommonUtil;
import validation.ArgValidation;
import validation.FileValidation;

import java.util.List;

public class start {
    public static void main (String[] args) {
        new ArgValidation(args);
        FileValidation fileValidation = new FileValidation(ArgValidation.getFilePath());

        // Generate CNF Statements
        CNF cnf = new CNF(fileValidation.graph.getNodeAdjMap(), fileValidation.graph.getAllNodes(), ArgValidation.getNoOfColors());

        // Add CNF to DPLL Intermediate State to begin DPLL
        DPLLInterState cnfState = new DPLLInterState(cnf.getCnfStatements());

        // Print CNF Clauses, If Verbose is applied
        CommonUtil.printCNFClauses(cnf.getCnfStatements(), ArgValidation.getIsVerboseEnable());

        // Apply DPLL
        List<List<String>> result = new DPLL().applyDPLLAlg(cnfState);

        // Print DPLL Result
        CommonUtil.printDPLLResult(result);
    }
}
