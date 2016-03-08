/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ia.assignment1;

import com.ia.assignment1.agent.CAgent;
import com.ia.assignment1.test.CTest;
import com.ia.assignment1.map.CMap;
import com.ia.assignment1.map.EDirection;
import com.ia.assignment1.path.CIterativePath;

/**
 *
 * @author Bryden
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        CMap objMap = new CMap(4, 3, -0.00);
        CAgent objAgent = new CAgent(0.8, 0.1, 0.1);

        int[] aryBadPos = {7};

        int[] aryGoodPos = {11};

        objMap.setValues(aryBadPos, -1);
        objMap.setValues(aryGoodPos, 1);

        double[][] dblGrid = objMap.getGridValue();

        double dblDiff = 1.0;

        int intIteration = 0;

        while (dblDiff > 0.00001) {

            dblDiff = 0;

            for (int intCounter = objMap.getHeight() - 1; intCounter >= 0; intCounter--) {
                for (int intCount = 0; intCount < objMap.getWidth(); intCount++) {
                    dblDiff += CIterativePath.setOptimalPath(objAgent, objMap, intCount, intCounter, 0.9);
                }
            }

            objMap.commitState();
            
            intIteration++;

            if (intIteration == 50) {
                break;
            }

            System.out.println("");

            for (int intCounter = objMap.getHeight() - 1; intCounter >= 0; intCounter--) {
                for (int intCount = 0; intCount < objMap.getWidth(); intCount++) {
                    System.out.print(dblGrid[intCounter][intCount] + " ");
                }

                System.out.println("");
            }

            EDirection[][] dblPath = objMap.getPathValue();

            for (int intCounter = objMap.getHeight() - 1; intCounter >= 0; intCounter--) {
                for (int intCount = 0; intCount < objMap.getWidth(); intCount++) {
                    System.out.print(dblPath[intCounter][intCount].getValue() + " ");
                }

                System.out.println("");
            }

        }

        System.out.println("");

        for (int intCounter = objMap.getHeight() - 1; intCounter >= 0; intCounter--) {
            for (int intCount = 0; intCount < objMap.getWidth(); intCount++) {
                System.out.print(dblGrid[intCounter][intCount] + " ");
            }

            System.out.println("");
        }

        EDirection[][] dblPath = objMap.getPathValue();

        for (int intCounter = objMap.getHeight() - 1; intCounter >= 0; intCounter--) {
            for (int intCount = 0; intCount < objMap.getWidth(); intCount++) {
                System.out.print(dblPath[intCounter][intCount].getValue() + " ");
            }

            System.out.println("");
        }
    }

}
