/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mips64bm.rtd;

import java.util.LinkedList;

/**
 *
 * @author jonats
 */
public class BitOps {
    String[][] isaOpcodeTable = {
        {"DADDU", "r", "000000", "101101","alu"},
        {"DSUBU", "r", "000000", "101111","alu"},
        {"DADDIU", "i", "011001","","alu"},
        {"LD", "i", "110111","","ls"},
        {"SD", "i", "111111","","ls"},
        {"J", "j", "000010","","branch"}
    };
    
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
     
    public String getFuncFunc(String fend){
        String ret = "";
        for(int i = 0; i < isaOpcodeTable.length; i++){
            if(isaOpcodeTable[i][3].equalsIgnoreCase(fend)){
                ret = isaOpcodeTable[i][0];
            }
        }
        return ret;
    }

    public String getBinOperator(String fend){
        String ret = "";
        for(int i = 0; i < isaOpcodeTable.length; i++){
            if(isaOpcodeTable[i][2].equalsIgnoreCase(fend)){
                ret = isaOpcodeTable[i][0];
            }
        }
        return ret;
    }
    
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
    
    public String getOpcodeOperationType(String opcode){
        String ret = "";
        for(int i = 0; i < isaOpcodeTable.length; i++)
            if(isaOpcodeTable[i][2].equalsIgnoreCase(opcode))
                ret = isaOpcodeTable[i][4];
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
    
    public int getWidth(String wc){
        int ret = 0;
        if(wc.equalsIgnoreCase("b"))
            ret = 1;
        if(wc.equalsIgnoreCase("d"))
            ret = 8;
        if(wc.equalsIgnoreCase("h"))
            ret = 2;
        if(wc.equalsIgnoreCase("w"))
            ret = 4;
        return ret;
    }
    
    public String zeroExtend64(String hex){
        String ret = "";
        ret = hex;
        for(int i = hex.length(); i < 16; i++)
            ret = "0" + ret;
        return ret;
    }
    
    public String signExtend64(String hex){
        String ret = "";
        String inBin = cvtHexToBin(hex);
        for(int i = hex.length(); i < 64; i++)
            inBin = inBin.substring(0,1) + inBin;
        ret = cvtBinToHex(inBin);
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
    
    public boolean isHexNeg(String hex){
        hex = hex.substring(0,1);
        if(hex.equalsIgnoreCase("8")||
                hex.equalsIgnoreCase("9")||
                hex.equalsIgnoreCase("a")||
                hex.equalsIgnoreCase("b")||
                hex.equalsIgnoreCase("c")||
                hex.equalsIgnoreCase("d")||
                hex.equalsIgnoreCase("e")||
                hex.equalsIgnoreCase("f"))
            return true;
        return false;
    }
    
    public String getInstrIndex(LinkedList<String[]> labelTable, String label){
        String ret = "";
        for(int i = 0; i < labelTable.size(); i++){
            if(label.equals(labelTable.get(i)[0])){
                //System.out.println("FOUNDMATCH");
                int adr = Integer.parseInt(labelTable.get(i)[1], 16);
                adr = adr / 4;
                String address = Integer.toHexString(adr);
                address = cvtHexToBin(address);
                for(int j=address.length(); j < 26; j++)
                    address = "0"+address;
                //System.out.println(address);
                ret = address;
            }
        }
        return ret;
    }
    
    public String getInstrxnOpcodeBin(String instrxn, LinkedList<String[]> labelTable){
        String ret = "";
        instrxn = instrxn.replaceAll(",", " ").replaceAll("\\s+"," ");
        String brk[] = instrxn.split(" ");
        String type = getOperatorType(brk[0]);
        if(type.equalsIgnoreCase("j"))
            ret = getOperatorOpcode(brk[0]) + getInstrIndex(labelTable, brk[1]);
        else if(type.equalsIgnoreCase("r"))
            ret = getOperatorOpcode(brk[0]) + getRxBin(brk[2])+ getRxBin(brk[3])+ getRxBin(brk[1]) + "00000" + getOperatorFunc(brk[0]);
        else if(type.equalsIgnoreCase("i")){
            String immed = brk[3].replace("0x", "");
            for(int i = immed.length(); i < 16; i++)
                if(isHexNeg(immed))
                    immed = "1" + immed;
                else
                    immed = "0" + immed;
            ret = getOperatorOpcode(brk[0]) + getRxBin(brk[2])+ getRxBin(brk[1]) + immed;
        }
        return ret;
    }
    
    public String getInstrxnOpcodeHex(String instrxn, LinkedList<String[]> labelTable){
        String bin = getInstrxnOpcodeBin(instrxn, labelTable);
        return cvtBinToHex(bin);
    }
 
    public LinkedList<String[]> getLabelTableCS(String[] src, int lends){
        int adr = lends;
        LinkedList<String[]> ret = new LinkedList<String[]>();
        for(int i = 0; i < src.length; i++){
            String lcofw = src[i].substring(src[i].length()-1,src[i].length());
            if(lcofw.equalsIgnoreCase(":")){
                String[] nr = {src[i].substring(0,src[i].length()-1), ""+adr};
                ret.add(nr);
                ////System.out.println(src[i] + adr);
            }else if(src[i].equalsIgnoreCase(".data") || src[i].equalsIgnoreCase(".code"))
                adr += 0;
            else
                adr += 4;    
        }
        return ret;
    }    
    
    public LinkedList<String[]> getLabelTableDS(String[] src){
        int adr = 0;
        LinkedList<String[]> ret = new LinkedList<String[]>();
        for(int i = 0; i < src.length; i++){
            String lcofw = src[i].substring(src[i].length()-1,src[i].length());
            if(lcofw.equalsIgnoreCase(":")){
                String[] nr = {src[i].substring(0,src[i].length()-1), ""+adr};
                ret.add(nr);
                ////System.out.println(src[i] + adr);
            }else if(src[i].equalsIgnoreCase(".data") || src[i].equalsIgnoreCase(".code"))
                adr += 0;
            else
                adr++;    
        }
        return ret;
    }
    
    public LinkedList<String[]> getSeparatedSegments(String src[]){
        LinkedList<String[]> ret = new LinkedList<String[]>();
        LinkedList<String> ds = new LinkedList<String>();
        LinkedList<String> cs = new LinkedList<String>();
        boolean isCs = false;
        for(int i = 0; i < src.length; i++){
            if(src[i].equalsIgnoreCase(".code"))
                isCs = true;
            if(isCs)
                cs.add(src[i]);
            else
                ds.add(src[i]);
        }
        String[] css = cs.toArray(new String[cs.size()]);
        String[] dss = ds.toArray(new String[ds.size()]);
        ret.add(dss);
        ret.add(css);
        return ret;
    }
    
    public int getDSLen(String[] ds){
        int ret = 0;
        for(int i = 0; i < ds.length; i++){
           String lcofw =ds[i].substring(ds[i].length()-1,ds[i].length());
           if(lcofw.equalsIgnoreCase(":") || ds[i].equalsIgnoreCase(".data"))
               ret += 0;
           else if(ds[i].equalsIgnoreCase(".code"))
               break;
           else
               ret++;
        }
        return ret;
    }
    
    public LinkedList<String[]> getInstrxnTable(String[] cs, int cstart){
        int adr = cstart;
        LinkedList<String[]> ret = new LinkedList<String[]>();
        for(int i = 0; i < cs.length; i++){
           String lcofw =cs[i].substring(cs[i].length()-1,cs[i].length());
           if(!(lcofw.equalsIgnoreCase(":") || cs[i].equalsIgnoreCase(".code"))){
               String address = Integer.toHexString(adr);
               String[] temp = {cs[i], address};
               ret.add(temp);
               adr += 4;
           }           
        }
        return ret;
    }
    
    public LinkedList<String[]> getProgramBytes(String[] ds, String[] cs, LinkedList<String[]> labelTable){
        LinkedList<String[]> ret = new LinkedList<String[]>();
        int adr = 0;
        for(int i = 0; i < ds.length; i++){
            String lcofw =ds[i].substring(ds[i].length()-1,ds[i].length());
            if(!(ds[i].equalsIgnoreCase(".data") || lcofw.equalsIgnoreCase(":"))){
                String address = Integer.toHexString(adr);
                String[] temp = {ds[i].toUpperCase(), address};
                ret.add(temp);
                adr++;
            }
        }
        for(int i = 0; i < cs.length; i++){
            String lcofw =cs[i].substring(cs[i].length()-1,cs[i].length());
            if(!(cs[i].equalsIgnoreCase(".code") || lcofw.equalsIgnoreCase(":"))){
                String opp = getInstrxnOpcodeHex(cs[i], labelTable).toUpperCase();
                for(int j = 0; j < 7; j += 2){
                    String address = Integer.toHexString(adr);
                    String[] temp = {opp.substring(j,j+2).toUpperCase(), address};
                    ret.add(temp);
                    adr++;
                }
                    
            }
        }           
        return ret;
    }
}
