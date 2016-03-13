/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ia.assignment1.path;

import com.ia.assignment1.agent.CAgent;
import com.ia.assignment1.map.CState;
import com.ia.assignment1.map.CMap;

/**
 *
 * @author Bryden
 */

//Class to perform Value Iterative methods
public class CIterativePath extends CMDP {

    //Max Error Allowed
    double dblErrorBound;

    public CIterativePath(CMap pObjMap, CAgent pObjAgent, double dblDiscount, double pDblMaxError) {
        super(pObjMap, pObjAgent, dblDiscount);
        
        //Forumla of epi w.r.t discount value
        dblErrorBound = pDblMaxError * (1 - dblDiscount) / dblDiscount;
    }

    public double getErrorRate() {
        return dblErrorBound;
    }

    //Method to find optimal policy from gridworld
    @Override
    public int calculateOptimalPolicy() {

        double dblDiff = 400.0;
        double dblMin = 300.0;

        int intIteration = 0;

        //Converage Critieria using semi norm value
        //Allow for faster termination as compare to max norm
        //Return number of iterations
        while ((dblDiff - dblMin) > dblErrorBound) {

            CPolicy[][] aryNewPolicy = new CPolicy[objMap.getHeight()][objMap.getWidth()];

            dblDiff = 0;
            dblMin = 100;

            for (int intCounter = objMap.getHeight() - 1; intCounter >= 0; intCounter--) {
                for (int intCount = 0; intCount < objMap.getWidth(); intCount++) {
                    
                    //Get abs value of error rate
                    double dblDelta = Math.abs(setOptimalPath(aryNewPolicy, intCount, intCounter, dblDiscount));

                    // Value of 0 implies wall or terminal nodes.
                    // Optimal Policy will be achieve long before error rate become zero
                    if (dblDelta == 0) {
                        continue;
                    }

                    //Store max and min diff in Utility of currrent and previous
                    dblDiff = (dblDelta > dblDiff) ? dblDelta : dblDiff;

                    dblMin = (dblDelta < dblMin) ? dblDelta : dblMin;

                }
            }

            //Add final policy for further learning
            lstPolicy.add(aryNewPolicy);

            intIteration++;
        }

        return intIteration;
    }

    //Method to find and set optimal path and utility for a single grid
    //Return the error from previous utility.
    public double setOptimalPath(CPolicy[][] pAryNewPolicy, int pIntX, int pIntY, double pDblDiscountValue) {

        CState objGrid = objMap.getGrid(pIntX, pIntY);

        if (objGrid.getType().getValue() > 50) {
            return 0;
        }

        double dblValue = objGrid.getReward();

        double dblOriginal = this.getUtilityPrevState(pIntX, pIntY);

        CPolicy objOptimalPolicy = this.getOptimalPolicy(pIntX, pIntY);

        dblValue += pDblDiscountValue * objOptimalPolicy.getUtility();

        pAryNewPolicy[pIntY][pIntX] = new CPolicy(dblValue, objOptimalPolicy.getDirection());

        return dblValue - dblOriginal;
    }

}
