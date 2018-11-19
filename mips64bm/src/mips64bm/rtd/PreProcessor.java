/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mips64bm.rtd;
import java.io.*;
import java.util.*;

/**
 *
 * @author jonats
 */
public class PreProcessor {

    LinkedList<String> readContents(String filename) {
        LinkedList<String> ret = new LinkedList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String currentLine = null;
            while ((currentLine = br.readLine()) != null) {
                ret.add(currentLine);
            }
        } catch (Exception e) {
            System.out.println("ERROR ON FILE READ");
            System.exit(0);
        }
        return ret;
    }

    void writeContents(String content, String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (Exception e) {
            System.out.println("ERROR ON FILEWRITE");
        }
    }

    String removeParanthesis(String arg) {
        String ret = arg;
        ret = ret.replaceAll("\\(", " ");

        ret = ret.replaceAll("\\)", " ");
        return ret;
    }

    String spaceUpLabel(String arg) {
        return arg.replaceAll(":", ": ");
    }

    String removeComma(String arg) {
        return arg.replaceAll(",", " ");
    }

    String cleanWhiteSpaces(String arg) {
        String ret = "";
        ret = ret + arg.replaceAll("\\s+", " ");
        if (!(arg.equals("") || arg.equals(" "))) {
            ret = arg.replaceAll("\\s+", " ");
            if (ret.charAt(0) == ' ') {
                ret = ret.substring(1, ret.length());
            }
        }
        return ret;
    }

    String processComments(String arg) {
        String ret = arg;
        int commentStart = 0;
        for (int i = 0; i < arg.length(); i++) {
            if (arg.charAt(i) == ';') {
                commentStart = i;
                break;
            }
        }
        if (commentStart != 0) {
            ret = arg.substring(commentStart, arg.length()) + "\n" + arg.substring(0, commentStart);
        }
        return ret;
    }

    String standardizeRegisters(String arg) {
        String[] reg = {"r0", "r1", "r2", "r3", "r4", "r5", "r6", "r7", "r8", "r9", "r10", "r11", "r12", "r13", "r14", "r15", "r16", "r17", "r18", "r19", "r20", "r21", "r22", "r23", "r24", "r25", "r26", "r27", "r28", "r29", "r30", "r31"};
        String[] alias = {"zero", "at", "v0", "v1", "a0", "a1", "a2", "a3", "t0", "t1", "t2", "t3", "t4", "t5", "t6", "t7", "s0", "s1", "s2", "s3", "s4", "s5", "s6", "s7", "t8", "t9", "k0", "k1", "gp", "sp", "fp", "ra"};
        String[] argArr = arg.split(" ");
        String ret = "";
        if (argArr.length > 0) {
            ret = ret + argArr[0];
        }
        for (int i = 1; i < 4 && i < argArr.length; i++) {
            String register = argArr[i];
            for (int j = 0; j < reg.length; j++) {
                if (alias[j].equals(argArr[i])) {
                    register = reg[j];
                }
            }
            ret = ret + " " + register;
        }
        for (int i = 4; i < argArr.length; i++) {
            ret = ret + " " + argArr[i];
        }
        return ret;
    }

    String handleLabeledLine(String arg) {
        String ret = "";
        String[] argArr = arg.split(" ");
        String firstPass = arg;
        if (argArr[0].charAt(argArr[0].length() - 1) == ':') {
            firstPass = arg.substring(0, argArr[0].length()) + "\n" + arg.substring(argArr[0].length(), arg.length());
        }
        ret = firstPass;
        String[] firstPassArr = firstPass.split("\n");
        if (firstPassArr.length == 2) {
            if (firstPassArr[1].charAt(0) == ' ') {
                ret = firstPassArr[0] + "\n" + firstPassArr[1].substring(1, firstPassArr[1].length());
            }
        }
        return ret;
    }

    String handleLabelsOnePass(String arg) {
        String ret = "";
        String firstPass = "";
        String secondPass = "";
        String[] argArr = arg.split("\n");
        int csegStart = 0;
        for (int i = 0; i < argArr.length; i++) {
            if (argArr[i].equals(".code")) {
                csegStart = i;
            }
        }
        for (int i = 0; i < argArr.length; i++) {
            if (i > csegStart) {
                firstPass = firstPass + handleLabeledLine(argArr[i]) + "\n";
            } else {
                firstPass = firstPass + argArr[i] + "\n";
            }
        }
        String[] firstPassArr = firstPass.split("\n");
        for (int i = 0; i < firstPassArr.length; i++) {
            if (!(firstPassArr[i].equals(" ") || firstPassArr[i].equals(""))) {
                secondPass = secondPass + firstPassArr[i] + "\n";
            }
        }
        ret = secondPass;
        return ret;
    }

    String handleLabels(String arg) {
        String ret = handleLabelsOnePass(arg);
        boolean unresolved = true;
        while (unresolved) {
            String ret2 = handleLabelsOnePass(ret);
            int oldLength = ret.split("\n").length;
            int newLength = ret2.split("\n").length;
            ret = ret2;
            if (oldLength == newLength) {
                unresolved = false;
            }
        }
        return ret;
    }

    String resolveIntermFilename(String arg) {
        String ret = "";
        for (int i = 0; i < arg.length(); i++) {
            if (arg.charAt(i) == '.') {
                break;
            }
            ret = ret + "" + arg.charAt(i);
        }
        ret = ret + "-intermediate.mti";
        return ret;
    }

    String removeNumSign(String arg) {
        return arg.replaceAll("#", "");
    }

    int[] dsegBounds(String[] fileContents) {
        int[] ret = {0, 0};
        int[] errorRet = {0, 0}; // for if no valid dseg found
        for (int i = 0; i < fileContents.length; i++) {
            if (fileContents[i].equalsIgnoreCase(".data")) {
                ret[0] = i;
            } else if (fileContents[i].equalsIgnoreCase(".code")) {
                ret[1] = i;
            }
        }
        if (ret[0] >= ret[1]) {
            return errorRet;
        } else {
            return ret;
        }
    }

    public String getPreProcessed(String filename){
        LinkedList<String> fileContents = readContents(filename);
        String firstPass = "";
        String secondPass = "";
        String thirdPass = "";
        String fourthPass = "";
        for (int i = 0; i < fileContents.size(); i++) {
            String temp = fileContents.get(i);
            temp = removeParanthesis(temp);
            //temp = removeComma(temp);
            temp = spaceUpLabel(temp);
            temp = cleanWhiteSpaces(temp);
            temp = standardizeRegisters(temp);
            temp = processComments(temp);
            temp = temp.toLowerCase();
            temp = removeNumSign(temp);
            temp = temp.replaceAll(".text", ".code");
            if (!(temp.equals(" ") || temp.equals(""))) {
                firstPass = firstPass + temp + "\n";
            }
        }
        secondPass = handleLabels(firstPass);
        String[] spassArr = secondPass.split("\n");
        int[] dsb = dsegBounds(spassArr);
        for (int i = 0; i < spassArr.length; i++) {
            if (i >= dsb[1]) {
                thirdPass = thirdPass + removeComma(spassArr[i]) + "\n";
            } else {
                thirdPass = thirdPass + spassArr[i] + "\n";
            }
        }
        String[] tpassArr = thirdPass.split("\n");
        for (int i = 0; i < tpassArr.length; i++) {
            if (i < tpassArr.length - 1) {
                fourthPass = fourthPass + cleanWhiteSpaces(tpassArr[i]) + "\n";
            } else {
                fourthPass = fourthPass + cleanWhiteSpaces(tpassArr[i]);
            }
        }
        String intermFilename = resolveIntermFilename(filename);
        //writeContents(fourthPass, intermFilename);        
        return fourthPass;
    }
    
    /**
    void preprocess(String filename) {
        LinkedList<String> fileContents = readContents(filename);
        String firstPass = "";
        String secondPass = "";
        String thirdPass = "";
        String fourthPass = "";
        for (int i = 0; i < fileContents.size(); i++) {
            String temp = fileContents.get(i);
            temp = removeParanthesis(temp);
            //temp = removeComma(temp);
            temp = spaceUpLabel(temp);
            temp = cleanWhiteSpaces(temp);
            temp = standardizeRegisters(temp);
            temp = processComments(temp);
            temp = temp.toLowerCase();
            temp = removeNumSign(temp);
            temp = temp.replaceAll(".text", ".code");
            if (!(temp.equals(" ") || temp.equals(""))) {
                firstPass = firstPass + temp + "\n";
            }
        }
        secondPass = handleLabels(firstPass);
        String[] spassArr = secondPass.split("\n");
        int[] dsb = dsegBounds(spassArr);
        for (int i = 0; i < spassArr.length; i++) {
            if (i >= dsb[1]) {
                thirdPass = thirdPass + removeComma(spassArr[i]) + "\n";
            } else {
                thirdPass = thirdPass + spassArr[i] + "\n";
            }
        }
        String[] tpassArr = thirdPass.split("\n");
        for (int i = 0; i < tpassArr.length; i++) {
            if (i < tpassArr.length - 1) {
                fourthPass = fourthPass + cleanWhiteSpaces(tpassArr[i]) + "\n";
            } else {
                fourthPass = fourthPass + cleanWhiteSpaces(tpassArr[i]);
            }
        }
        String intermFilename = resolveIntermFilename(filename);
        writeContents(fourthPass, intermFilename);
    }
    **/
}
