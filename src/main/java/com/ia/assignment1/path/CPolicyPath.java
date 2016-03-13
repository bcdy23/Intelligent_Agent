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
//Class to perform Policy Iterative methods
public class CPolicyPath extends CMDP {

    public CPolicyPath(CMap pObjMap, CAgent pObjAgent, double dblDiscount) {
        super(pObjMap, pObjAgent, dblDiscount);
    }

    //Method to find optimal policy from gridworld
    @Override
    public int calculateOptimalPolicy() {
        int intIteration = 0;

        int intChanges = 10;

        //Stop once policy does not change from previous value
        while (intChanges > 0) {
            
            //Perform policy evaluation for k times
            for (int intTraining = 0; intTraining < 10; intTraining++) {

                CPolicy[][] aryNewPolicy = new CPolicy[objMap.getHeight()][objMap.getWidth()];

                for (int intCounter = objMap.getHeight() - 1; intCounter >= 0; intCounter--) {
                    for (int intCount = 0; intCount < objMap.getWidth(); intCount++) {
                        trainCurrentUtility(aryNewPolicy, intCount, intCounter, dblDiscount);
                    }
                }

                //Policy training are not consider as final policy
                //Thus it is removed and added
                if (intIteration < lstPolicy.size()) {
                    lstPolicy.remove(intIteration);
                }
                lstPolicy.add(aryNewPolicy);
            }

            //New Policy being calculated
            CPolicy[][] aryNewPolicy = new CPolicy[objMap.getHeight()][objMap.getWidth()];

            intChanges = 0;
            for (int intCounter = objMap.getHeight() - 1; intCounter >= 0; intCounter--) {
                for (int intCount = 0; intCount < objMap.getWidth(); intCount++) {
                    intChanges += updateCurrentUtility(aryNewPolicy, intCount, intCounter, dblDiscount);;
                }
            }

            //Add final trained policy at interation value
            lstPolicy.remove(intIteration);
            lstPolicy.add(aryNewPolicy);

            intIteration++;

            //Set upper bound of iteration
            if (intIteration > 500) {
                break;
            }
        }

        return intIteration;
    }

    //Train the utility based on current policy
    //Prevent the algo from stopping at local minimum
    public int trainCurrentUtility(CPolicy[][] pAryPolicy, int pIntX, int pIntY, double pDblDiscountValue) {

        CState objGrid = objMap.getGrid(pIntX, pIntY);

        if (objGrid.getType().getValue() > 50) {
            return 0;
        }

        double dblValue = objGrid.getReward();

        CPolicy objCurrentPolicy = this.getCurrentPolicy(pIntX, pIntY);

        pAryPolicy[pIntY][pIntX] = new CPolicy(dblValue + pDblDiscountValue * objCurrentPolicy.getUtility(), objCurrentPolicy.getDirection());
        return 0;
    }

    //find the optimal policy and utility
    //Return 1 if changed in utility detected
    public int updateCurrentUtility(CPolicy[][] pAryPolicy, int pIntX, int pIntY, double pDblDiscountValue) {

        CState objGrid = objMap.getGrid(pIntX, pIntY);

        if (objGrid.getType().getValue() > 50) {
            return 0;
        }

        double dblValue = objGrid.getReward();

        CPolicy objOptimalPolicy = this.getOptimalPolicy(pIntX, pIntY);

        CPolicy objCurrentPolicy = this.getCurrentPolicy(pIntX, pIntY);

        if (objOptimalPolicy.getUtility() > objCurrentPolicy.getUtility()) {
            pAryPolicy[pIntY][pIntX] = new CPolicy(dblValue + pDblDiscountValue * objOptimalPolicy.getUtility(), objOptimalPolicy.getDirection());
            return 1;
        }

        pAryPolicy[pIntY][pIntX] = new CPolicy(dblValue + pDblDiscountValue * objCurrentPolicy.getUtility(), objCurrentPolicy.getDirection());
        return 0;
    }

}
