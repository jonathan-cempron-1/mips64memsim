/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mips64bm.rtd;

/**
 *
 * @author jonats
 */
public class BitOps {
     String[][] regs = {
        {"r0","00000"},
        {"r1","00001"},
        {"r2","00010"},
        {"r3","00011"},
        {"r4","00100"},
        {"r5","00101"},
        {"r6","00110"},
        {"r7","00111"},
        {"r8","01000"},
        {"r9","01001"},
        {"r10","01010"},
        {"r11","01011"},
        {"r12","01100"},
        {"r13","01101"},
        {"r14","01110"},
        {"r15","01111"},
        {"r16","10000"},
        {"r17","10001"},
        {"r18","10010"},
        {"r19","10011"},
        {"r20","10100"},
        {"r21","10101"},
        {"r22","10110"},
        {"r23","10111"},
        {"r24","11000"},
        {"r25","11001"},
        {"r26","11010"},
        {"r27","11011"},
        {"r28","11100"},
        {"r29","11101"},
        {"r30","11110"},
        {"r31","11111"},
    };
     
    String[][] isaOpcodeTable = {
        {"DADDU", "r", "000000", "101101"},
        {"DSUBU", "r", "000000", "101111"},
        {"DADDIU", "i", "011001"},
        {"LD", "i", "110111"},
        {"SD", "i", "111111"},
        {"J", "j", "000010"}
    };
    
    public String getOperatorType(String operator){
        String ret = "";
        for(int i = 0; i < isaOpcodeTable.length; i++)
            if(isaOpcodeTable[i][0].equalsIgnoreCase(operator))
                ret = isaOpcodeTable[i][1];
        return ret;
    }

    public String getOpcodeType(String opcode){
        String ret = "";
        for(int i = 0; i < isaOpcodeTable.length; i++)
            if(isaOpcodeTable[i][2].equalsIgnoreCase(opcode))
                ret = isaOpcodeTable[i][1];
        return ret;
    }
    
    public String getFuncOperator(String func){
        String ret = "";
        for(int i = 0; i < isaOpcodeTable.length; i++)
            if(isaOpcodeTable[i].length == 4)
                if(isaOpcodeTable[i][3].equalsIgnoreCase(func))
                    ret = isaOpcodeTable[i][0];
        return ret;
    }
    
    public String getOperatorFunc(String operator){
        String ret = "";
        for(int i = 0; i < isaOpcodeTable.length; i++)
            if(isaOpcodeTable[i].length == 4)
                if(isaOpcodeTable[i][0].equalsIgnoreCase(operator))
                    ret = isaOpcodeTable[i][3];
        return ret;
    }
    
    public String getOperatorOpcode(String operator){
        String ret = "";
        for(int i = 0; i < isaOpcodeTable.length; i++)
            if(isaOpcodeTable[i][0].equalsIgnoreCase(operator))
                ret = isaOpcodeTable[i][2];
        return ret;
    }
    
    public String getOpcodeOperator(String opcode){
        String ret = "";
        for(int i = 0; i < isaOpcodeTable.length; i++)
            if(isaOpcodeTable[i][2].equalsIgnoreCase(opcode))
                ret = isaOpcodeTable[i][0];
        return ret;
    }
    
    public String getRxBin(String reg){
        String ret = "";
        for(int i = 0; i < regs.length; i++)
            if(regs[i][0].equalsIgnoreCase(reg))
                ret = regs[i][1];
        return ret;
    }
     

    public String getRxReg(String bin){
        String ret = "";
        for(int i = 0; i < regs.length; i++)
            if(regs[i][1].equalsIgnoreCase(bin))
                ret = regs[i][0];
        return ret;
    }
    
    public String cvtHexToBin(String hex){
        String ret = "";
        for(int i = 0; i < hex.length(); i++)
            if(hex.substring(i, i+1).equalsIgnoreCase("0"))
                ret = ret + "0000";
            else if(hex.substring(i, i+1).equalsIgnoreCase("1"))
                ret = ret + "0001";
            else if(hex.substring(i, i+1).equalsIgnoreCase("2"))
                ret = ret + "0010";
            else if(hex.substring(i, i+1).equalsIgnoreCase("3"))
                ret = ret + "0011";
            else if(hex.substring(i, i+1).equalsIgnoreCase("4"))
                ret = ret + "0100";
            else if(hex.substring(i, i+1).equalsIgnoreCase("5"))
                ret = ret + "0101";
            else if(hex.substring(i, i+1).equalsIgnoreCase("6"))
                ret = ret + "0110";
            else if(hex.substring(i, i+1).equalsIgnoreCase("7"))
                ret = ret + "0111";
            else if(hex.substring(i, i+1).equalsIgnoreCase("8"))
                ret = ret + "1000";
            else if(hex.substring(i, i+1).equalsIgnoreCase("9"))
                ret = ret + "1001";
            else if(hex.substring(i, i+1).equalsIgnoreCase("A"))
                ret = ret + "1010";
            else if(hex.substring(i, i+1).equalsIgnoreCase("B"))
                ret = ret + "1011";
            else if(hex.substring(i, i+1).equalsIgnoreCase("C"))
                ret = ret + "1100";
            else if(hex.substring(i, i+1).equalsIgnoreCase("D"))
                ret = ret + "1101";
            else if(hex.substring(i, i+1).equalsIgnoreCase("E"))
                ret = ret + "1110";
            else if(hex.substring(i, i+1).equalsIgnoreCase("F"))
                ret = ret + "1111";
        return ret;
    }
    
    public String cvtBinToHex(String bin){
        String ret = "";
        int len = bin.length();
        if(len % 4 != 0)
            for(int i = 0; i < len % 4; i++)
                bin = "0"+bin;
        for(int i = 0; i < bin.length(); i += 4)
            if(bin.substring(i,i+4).equalsIgnoreCase("0000"))
                ret = ret + "0";
            else if(bin.substring(i,i+4).equalsIgnoreCase("0001"))
                ret = ret + "1";
            else if(bin.substring(i,i+4).equalsIgnoreCase("0010"))
                ret = ret + "2";
            else if(bin.substring(i,i+4).equalsIgnoreCase("0011"))
                ret = ret + "3";
            else if(bin.substring(i,i+4).equalsIgnoreCase("0100"))
                ret = ret + "4";
            else if(bin.substring(i,i+4).equalsIgnoreCase("0101"))
                ret = ret + "5";
            else if(bin.substring(i,i+4).equalsIgnoreCase("0110"))
                ret = ret + "6";
            else if(bin.substring(i,i+4).equalsIgnoreCase("0111"))
                ret = ret + "7";
            else if(bin.substring(i,i+4).equalsIgnoreCase("1000"))
                ret = ret + "8";
            else if(bin.substring(i,i+4).equalsIgnoreCase("1001"))
                ret = ret + "9";
            else if(bin.substring(i,i+4).equalsIgnoreCase("1010"))
                ret = ret + "A";
            else if(bin.substring(i,i+4).equalsIgnoreCase("1011"))
                ret = ret + "B";
            else if(bin.substring(i,i+4).equalsIgnoreCase("1100"))
                ret = ret + "C";
            else if(bin.substring(i,i+4).equalsIgnoreCase("1101"))
                ret = ret + "D";
            else if(bin.substring(i,i+4).equalsIgnoreCase("1110"))
                ret = ret + "E";
            else if(bin.substring(i,i+4).equalsIgnoreCase("1111"))
                ret = ret + "F";
        return ret;
    }
}
