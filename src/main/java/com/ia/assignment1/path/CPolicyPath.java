/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ia.assignment1.path;

import com.ia.assignment1.agent.CAgent;
import com.ia.assignment1.map.CState;
import com.ia.assignment1.map.CMap;
import com.ia.assignment1.map.EGridType;

/**
 *
 * @author Bryden
 */
public class CPolicyPath extends CMDP {

    public CPolicyPath(CMap pObjMap, CAgent pObjAgent) {
        super(pObjMap, pObjAgent);
    }

    @Override
    public int calculateOptimalPolicy() {
        int intIteration = 0;

        int intChanges = 10;

        while (intChanges > 0) {

            System.out.println(intChanges);

            for (int intTraining = 0; intTraining < 10; intTraining++) {

                CPolicy[][] aryNewPolicy = new CPolicy[objMap.getHeight()][objMap.getWidth()];

                for (int intCounter = objMap.getHeight() - 1; intCounter >= 0; intCounter--) {
                    for (int intCount = 0; intCount < objMap.getWidth(); intCount++) {
                        trainCurrentUtility(aryNewPolicy, intCount, intCounter, 0.99);
                    }
                }

                lstPolicy.add(aryNewPolicy);
            }

            CPolicy[][] aryNewPolicy = new CPolicy[objMap.getHeight()][objMap.getWidth()];

            intChanges = 0;
            for (int intCounter = objMap.getHeight() - 1; intCounter >= 0; intCounter--) {
                for (int intCount = 0; intCount < objMap.getWidth(); intCount++) {
                    intChanges += updateCurrentUtility(aryNewPolicy, intCount, intCounter, 0.99);;
                }
            }

            lstPolicy.add(aryNewPolicy);

            intIteration++;

            if (intIteration > 500) {
                break;
            }
        }

        return intIteration;
    }

    public int trainCurrentUtility(CPolicy[][] pAryPolicy, int pIntX, int pIntY, double pDblDiscountValue) {

        CState objGrid = objMap.getGrid(pIntX, pIntY);

        if (objGrid.getType() == EGridType.TERMINAL) {
            return 0;
        }

        if (objGrid.getType() == EGridType.WALL) {
            return 0;
        }

        double dblValue = objGrid.getReward();

        CPolicy objCurrentPolicy = this.getCurrentPolicy(pIntX, pIntY);

        pAryPolicy[pIntY][pIntX] = new CPolicy(dblValue + pDblDiscountValue * objCurrentPolicy.getUtility(), objCurrentPolicy.getDirection());
        return 0;
    }

    public int updateCurrentUtility(CPolicy[][] pAryPolicy, int pIntX, int pIntY, double pDblDiscountValue) {

        CState objGrid = objMap.getGrid(pIntX, pIntY);

        if (objGrid.getType() == EGridType.TERMINAL) {
            return 0;
        }

        if (objGrid.getType() == EGridType.WALL) {
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
