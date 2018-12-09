/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mips64bm.rtd;

import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import mips64bm.gui.*;

/**
 *
 * @author jonats
 */
public class RuntimeData {

    public BitOps bto = new BitOps();
    LinkedList<String[]> labelTable; // each get(i) [0]label [1]hexaddres
    LinkedList<String[]> progBytes; //each get(i) [0]byte [1]hexaddress
    LinkedList<String[]> instrxnTable; // each get(i) [0]instruction string [1]hexaddress
    String[][] regContents = {
        {"r0", "00000", "0000000000000000"},
        {"r1", "00001", "0000000000000000"},
        {"r2", "00010", "0000000000000000"},
        {"r3", "00011", "0000000000000000"},
        {"r4", "00100", "0000000000000000"},
        {"r5", "00101", "0000000000000000"},
        {"r6", "00110", "0000000000000000"},
        {"r7", "00111", "0000000000000000"},
        {"r8", "01000", "0000000000000000"},
        {"r9", "01001", "0000000000000000"},
        {"r10", "01010", "0000000000000000"},
        {"r11", "01011", "0000000000000000"},
        {"r12", "01100", "0000000000000000"},
        {"r13", "01101", "0000000000000000"},
        {"r14", "01110", "0000000000000000"},
        {"r15", "01111", "0000000000000000"},
        {"r16", "10000", "0000000000000000"},
        {"r17", "10001", "0000000000000000"},
        {"r18", "10010", "0000000000000000"},
        {"r19", "10011", "0000000000000000"},
        {"r20", "10100", "0000000000000000"},
        {"r21", "10101", "0000000000000000"},
        {"r22", "10110", "0000000000000000"},
        {"r23", "10111", "0000000000000000"},
        {"r24", "11000", "0000000000000000"},
        {"r25", "11001", "0000000000000000"},
        {"r26", "11010", "0000000000000000"},
        {"r27", "11011", "0000000000000000"},
        {"r28", "11100", "0000000000000000"},
        {"r29", "11101", "0000000000000000"},
        {"r30", "11110", "0000000000000000"},
        {"r31", "11111", "0000000000000000"},};
    //IF
    public String pc = "";
    public String ifIdIr = "";
    public String ifIdNpc = "";
    //ID
    public String idExA = "";
    public String idExB = "";
    public String idExNpc = "";
    public String idExIr = "";
    public String idExImm = "";
    //EX
    public String exMemIr = "";
    public String exMemAluOut = "";
    public String exMemCond = "";
    public String exMemB = "";
    //MEM
    public String memWdIr = "";
    public String memWbLmd = "";
    public String memExMemAluOut = "";
    public String memWbAluOut = "";
    //WB
    public String regsMemWbIr = "";
    // previous
    //IF
    public String PrevPc = "";
    public String PrevIfIdIr = "";
    public String PrevIfIdNpc = "";
    //ID
    public String PrevIdExA = "";
    public String PrevIdExB = "";
    public String PrevIdExNpc = "";
    public String PrevIdExIr = "";
    public String PrevIdExImm = "";
    //EX
    public String PrevExMemIr = "";
    public String PrevExMemAluOut = "";
    public String PrevExMemCond = "";
    public String PrevExMemB = "";
    //MEM
    public String PrevMemWdIr = "";
    public String PrevMemWbLmd = "";
    public String PrevMemExMemAluOut = "";
    public String PrevMemWbAluOut = "";
    //WB
    public String PrevRegsMemWbIr = "";

    public void updatePrevs() {
        // place current to prev
        //IF //IF;
        PrevPc = pc;
        PrevIfIdIr = ifIdIr;
        PrevIfIdNpc = ifIdNpc;
        //ID //ID;
        PrevIdExA = idExA;
        PrevIdExB = idExB;
        PrevIdExNpc = idExNpc;
        PrevIdExIr = idExIr;
        PrevIdExImm = idExImm;
        //EX //EX;
        PrevExMemIr = exMemIr;
        PrevExMemAluOut = exMemAluOut;
        PrevExMemCond = exMemCond;
        PrevExMemB = exMemB;
        //MEM //MEM;
        PrevMemWdIr = memWdIr;
        PrevMemWbLmd = memWbLmd;
        PrevMemExMemAluOut = memExMemAluOut;
        PrevMemWbAluOut = memWbAluOut;
        //WB //WB;
        PrevRegsMemWbIr = regsMemWbIr;
        // resets current to blank
        //IF
        //pc = "";
        ifIdIr = "";
        //ifIdNpc = "";
        //ID
        idExA = "";
        idExB = "";
        idExNpc = "";
        idExIr = "";
        idExImm = "";
        //EX
        exMemIr = "";
        exMemAluOut = "";
        exMemCond = "";
        exMemB = "";
        //MEM
        memWdIr = "";
        memWbLmd = "";
        memExMemAluOut = "";
        memWbAluOut = "";
        //WB
        regsMemWbIr = "";
    }

    public void setRegContent(String ref, String cont) {
        for (int i = 0; i < regContents.length; i++) {
            if (ref.equalsIgnoreCase(regContents[i][0]) || ref.equalsIgnoreCase(regContents[i][1])) {
                regContents[i][2] = cont;
            }
        }
    }

    public String getRegContent(String ref) {
        String ret = "";
        for (int i = 0; i < regContents.length; i++) {
            if (ref.equalsIgnoreCase(regContents[i][0]) || ref.equalsIgnoreCase(regContents[i][1])) {
                ret = regContents[i][2];
            }
        }
        return ret;
    }

    public String getMemContent(String address, int byteLength) {
        String ret = "";
        for (int i = 0; i < progBytes.size(); i++) {
            if (address.equalsIgnoreCase(progBytes.get(i)[1])) {
                for (int j = 0; j < byteLength; j++) {
                    ret = ret + progBytes.get(i)[0];
                    i++;
                }
            }
        }
        return ret;
    }

    public void setMemContent(String address, int byteLength, String content) {
        int adr = Integer.parseInt(address, 16);
        address = Integer.toHexString(adr);
        System.out.println(address);
        content = content.substring(content.length() - (byteLength * 2), content.length());
        for (int i = 0; i < progBytes.size(); i++) {
            if (address.equalsIgnoreCase(progBytes.get(i)[1])) {
                for (int j = 0; j < byteLength; j++) {
                    progBytes.get(i)[0] = content.substring(j, j + 2);
                    System.out.println(content.substring(j, j + 2) + "*");
                    System.out.println(progBytes.get(i)[0]);
                    i++;
                }
            }
        }
    }

    public void initMem(LinkedList<String[]> labelTable, LinkedList<String[]> progBytes, LinkedList<String[]> instrxnTable) {
        this.labelTable = labelTable;
        this.instrxnTable = instrxnTable;
        this.progBytes = progBytes;
    }

    public DefaultTableModel updateMemDisplay() {
        String[] columnNames = {"label(s)", "address [hex]", "byte [hex]", ""};
        DefaultTableModel dtm = new DefaultTableModel(columnNames, 0);
        for (int i = 0; i < progBytes.size(); i++) {
            String adr = progBytes.get(i)[1];
            String pbyte = progBytes.get(i)[0];
            String labels = "";
            for (int j = 0; j < labelTable.size(); j++) {
                if (adr.equals(labelTable.get(j)[1])) {
                    labels = labels + labelTable.get(j)[0] + ", ";
                }
            }
            String instrxn = "";
            for (int j = 0; j < instrxnTable.size(); j++) {
                if (adr.equals(instrxnTable.get(j)[1])) {
                    instrxn = instrxnTable.get(j)[0];
                }
            }
            String[] row = {labels, adr, pbyte, instrxn};
            dtm.addRow(row);
        }
        return dtm;
    }

    public DefaultTableModel updateRegsDisplay() {
        String[] columnNames = {"register", "content"};
        DefaultTableModel dtm = new DefaultTableModel(columnNames, 0);
        for (int i = 0; i < 32; i++) {
            String[] row = {"" + regContents[i][0], "" + regContents[i][2]};
            dtm.addRow(row);
        }
        return dtm;
    }

    public RuntimeData() {
        new FrmWindow1(this);
    }
}
