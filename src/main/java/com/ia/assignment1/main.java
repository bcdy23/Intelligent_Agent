/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ia.assignment1;

import com.ia.assignment1.agent.CAgent;
import com.ia.assignment1.map.CMap;
import com.ia.assignment1.map.CMapFactory;
import com.ia.assignment1.path.CIterativePath;
import com.ia.assignment1.path.CMDP;
import com.ia.assignment1.path.CPolicy;

/**
 *
 * @author Bryden
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        CMap objMap = CMapFactory.loadMap("");

        CAgent objAgent = new CAgent(0.8, 0.1, 0.1);

        CMDP objMDP = new CIterativePath(objMap, objAgent, 0.99, 3.6);

        int intTotalIterations = objMDP.calculateOptimalPolicy();

        System.out.println(intTotalIterations);

        CPolicy[][] aryBestPolicy = objMDP.getOptimalPolicy();

        for (int intCounter = objMap.getHeight() - 1; intCounter >= 0; intCounter--) {
            for (int intCount = 0; intCount < objMap.getWidth(); intCount++) {

                CPolicy aryPolicy = aryBestPolicy[intCounter][intCount];

                if (aryPolicy == null) {
                    System.out.print("       WALL       ");
                    continue;
                }

                System.out.print(aryPolicy.getUtility() + " ");
            }

            System.out.println("");
        }

        for (int intCounter = objMap.getHeight() - 1; intCounter >= 0; intCounter--) {
            for (int intCount = 0; intCount < objMap.getWidth(); intCount++) {
                
                 CPolicy aryPolicy = aryBestPolicy[intCounter][intCount];

                if (aryPolicy == null) {
                    System.out.print("WALL ");
                    continue;
                }

                System.out.print(aryPolicy.getDirection() + " ");
                
            }

            System.out.println("");
        }
    }

}
