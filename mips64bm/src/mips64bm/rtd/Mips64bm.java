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
public class Mips64bm {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //new RuntimeData();
        System.out.println(new BitOps().cvtBinToHex("0101"));
        System.out.println(new BitOps().cvtHexToBin("ABA"));
        System.out.println(new BitOps().getRxBin("r1"));
        System.out.println(new BitOps().getRxReg("00011"));
        System.out.println(new BitOps().getOperatorOpcode("daddu"));
        System.out.println(new BitOps().getOpcodeOperator("111111"));
        System.out.println(new BitOps().getOperatorFunc("DADDU"));
        System.out.println(new BitOps().getFuncOperator("101111"));
        System.out.println(new BitOps().getOperatorType("DADDU"));
        System.out.println(new BitOps().getOpcodeType("000000"));
        
    }
    
}
