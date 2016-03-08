/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ia.assignment1.path;

import com.ia.assignment1.agent.CAgent;
import com.ia.assignment1.map.CGrid;
import com.ia.assignment1.map.CMap;
import com.ia.assignment1.map.EDirection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Bryden
 */
public class CIterativePath {

    public static double setOptimalPath(CAgent pObjAgent, CMap pObjMap, int pIntX, int pIntY, double pDblDiscountValue) {

        if (pIntX == 3 && pIntY == 2) {
            int x = 10;
            return 0;
        } else if (pIntX == 3 && pIntY == 1) {
            return 0;
        }

        double dblValue = pObjMap.getGridRewardValue(pIntX, pIntY);
        double dblOriginal = pObjMap.getGridValuePrevState(pIntX, pIntY);

        CGrid objUp = new CGrid(0.0, EDirection.UP);
        CGrid objDown = new CGrid(0.0, EDirection.DOWN);
        CGrid objLeft = new CGrid(0.0, EDirection.LEFT);
        CGrid objRight = new CGrid(0.0, EDirection.RIGHT);

        List<CGrid> lstGrid = new ArrayList<>();

        lstGrid.add(objUp);
        lstGrid.add(objDown);
        lstGrid.add(objLeft);
        lstGrid.add(objRight);

        double dblNorthUtility = pObjMap.getGridValuePrevState(pIntX, pIntY + 1);
        double dblSouthUtility = pObjMap.getGridValuePrevState(pIntX, pIntY - 1);
        double dblWestUtility = pObjMap.getGridValuePrevState(pIntX - 1, pIntY);
        double dblEastUtility = pObjMap.getGridValuePrevState(pIntX + 1, pIntY);

        objUp.setValue((pObjAgent.getPCorrect() * dblNorthUtility) + (pObjAgent.getPLeft() * dblWestUtility) + (pObjAgent.getPRight() * dblEastUtility));
        objDown.setValue((pObjAgent.getPCorrect() * dblSouthUtility) + (pObjAgent.getPLeft() * dblEastUtility) + (pObjAgent.getPRight() * dblWestUtility));
        objLeft.setValue((pObjAgent.getPCorrect() * dblWestUtility) + (pObjAgent.getPLeft() * dblSouthUtility) + (pObjAgent.getPRight() * dblNorthUtility));
        objRight.setValue((pObjAgent.getPCorrect() * dblEastUtility) + (pObjAgent.getPLeft() * dblNorthUtility) + (pObjAgent.getPRight() * dblSouthUtility));

        Collections.sort(lstGrid);

        dblValue += pDblDiscountValue * lstGrid.get(3).getValue();

        pObjMap.setValue(pIntX, pIntY, dblValue, lstGrid.get(3).getDirection());

        return dblValue - dblOriginal;
    }

}
