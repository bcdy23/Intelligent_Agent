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
public class CIterativePath extends CMDP {

    public CIterativePath(CMap pObjMap, CAgent pObjAgent) {
        super(pObjMap, pObjAgent);
    }

    @Override
    public int calculateOptimalPolicy() {

        double dblDiff = 100.0;

        int intIteration = 0;

        while (dblDiff > 0.036) {

            CPolicy[][] aryNewPolicy = new CPolicy[objMap.getHeight()][objMap.getWidth()];

            dblDiff = 0;

            for (int intCounter = objMap.getHeight() - 1; intCounter >= 0; intCounter--) {
                for (int intCount = 0; intCount < objMap.getWidth(); intCount++) {
                    double dblDelta = Math.abs(setOptimalPath(aryNewPolicy, intCount, intCounter, 0.99));

                    dblDiff = (dblDelta > dblDiff) ? dblDelta : dblDiff;

                }
            }

            lstPolicy.add(aryNewPolicy);

            intIteration++;

//            if (intIteration == 66) {
//                break;
//            }
        }

        return intIteration;
    }

    public double setOptimalPath(CPolicy[][] pAryNewPolicy, int pIntX, int pIntY, double pDblDiscountValue) {

        CState objGrid = objMap.getGrid(pIntX, pIntY);

        if (objGrid.getType() == EGridType.TERMINAL || objGrid.getType() == EGridType.WALL) {
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
