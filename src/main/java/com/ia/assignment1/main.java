/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ia.assignment1;

import com.ia.assignment1.test.CTest;
import com.ia.assignment1.map.CMap;

/**
 *
 * @author Bryden
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        CMap objMap = new CMap(5, 5, -0.04);

        int[] aryBadPos = {5, 9, 21};

        int[] aryGoodPos = {1, 24};

        objMap.setValues(aryBadPos, -10);
        objMap.setValues(aryGoodPos, 10);

        double [][] dblGrid = objMap.getGridValue();
        
        for (int intCounter = objMap.getHeight() - 1; intCounter >= 0; intCounter--) {
            for (int intCount = 0; intCount < objMap.getWidth(); intCount++) {
                System.out.print(dblGrid[intCounter][intCount] + " ");
            }
            
            System.out.println("");
        }
    }

}
