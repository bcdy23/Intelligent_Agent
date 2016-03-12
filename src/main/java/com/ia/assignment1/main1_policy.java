/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ia.assignment1;

import com.ia.assignment1.agent.CAgent;
import com.ia.assignment1.map.CGrid;
import com.ia.assignment1.map.CMap;
import com.ia.assignment1.map.EDirection;
import com.ia.assignment1.path.CIterativePath;
import com.ia.assignment1.path.CPolicyPath;

/**
 *
 * @author Bryden
 */
public class main1_policy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        CMap objMap = new CMap(6, 6, -0.04);
        CAgent objAgent = new CAgent(0.8, 0.1, 0.1);

        int[] aryBadPos = {10, 15, 20, 25, 29};

        int[] aryGoodPos = {17, 22, 27, 30, 32, 35};

        objMap.setValues(aryBadPos, -1);
        objMap.setValues(aryGoodPos, 1);

        CGrid[][] dblGrid = objMap.getGridValue();

        int intIteration = 0;

        int intChanges = 10;

        for (int intCounter = objMap.getHeight() - 1; intCounter >= 0; intCounter--) {
            for (int intCount = 0; intCount < objMap.getWidth(); intCount++) {
                CPolicyPath.updateCurrentUtility(objAgent, objMap, intCount, intCounter, 0.99);
            }
        }
        objMap.commitState();
        while (intChanges > 0) {

            System.out.println(intChanges);

            if (intChanges > 10) {
                int x = 0;
            }

            intChanges = 0;
            for (int intCounter = objMap.getHeight() - 1; intCounter >= 0; intCounter--) {
                for (int intCount = 0; intCount < objMap.getWidth(); intCount++) {
                    int intReturn = CPolicyPath.updateCurrentUtility(objAgent, objMap, intCount, intCounter, 0.99);
                    if (intReturn > 0) {
                        System.out.print("Change at " + intCount + " " + intCounter + "; ");
                    }

                    intChanges += intReturn;
                    
                }
            }

            System.out.println("");
            
            objMap.commitState();

            intIteration++;

            if (intIteration > 500) {
                break;
            }

        }

        System.out.println(intIteration);

        System.out.println("");

        for (int intCounter = objMap.getHeight() - 1; intCounter >= 0; intCounter--) {
            for (int intCount = 0; intCount < objMap.getWidth(); intCount++) {
                System.out.print(dblGrid[intCounter][intCount].getValue() + " ");
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
