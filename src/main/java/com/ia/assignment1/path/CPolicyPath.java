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
import com.ia.assignment1.map.EGridType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Bryden
 */
public class CPolicyPath {

//    public static CGrid[][] evaluatePolicy(CAgent pObjAgent, EDirection[][] pObjPolicy, CGrid[][] pAryPrevState, CMap pObjMap) {
//        
//        CGrid[][] objState = new CGrid[pObjMap.getHeight()][pObjMap.getWidth()];
//        
//        for (int intCounter = pObjMap.getHeight() - 1; intCounter >= 0; intCounter--) {
//            for (int intCount = 0; intCount < pObjMap.getWidth(); intCount++) {
//                objState[intCounter][intCount].setDirection(pAryPrevState[intCounter][intCount].getDirection);
//                getCurrentUtility(objState[intCounter][intCount], pObjMap, intCount, intCounter, pObjAgent);
//            }
//        }
//    }
//    
//    public static double getCurrentUtility(CGrid pObjGrid, CMap pObjMap, int pIntX, int pIntY, CAgent pObjAgent) {
//        double dblNorthUtility = pObjMap.getNeighbourGridUtility(pIntX, pIntY, EDirection.UP);
//        double dblSouthUtility = pObjMap.getNeighbourGridUtility(pIntX, pIntY, EDirection.DOWN);
//        double dblWestUtility = pObjMap.getNeighbourGridUtility(pIntX, pIntY, EDirection.LEFT);
//        double dblEastUtility = pObjMap.getNeighbourGridUtility(pIntX, pIntY, EDirection.RIGHT);
//        
//        switch (pObjGrid.getDirection()) {
//            case UP:
//                pObjGrid.setValue((pObjAgent.getPCorrect() * dblNorthUtility) + (pObjAgent.getPLeft() * dblWestUtility) + (pObjAgent.getPRight() * dblEastUtility));
//                break;
//            case DOWN:
//                pObjGrid.setValue((pObjAgent.getPCorrect() * dblSouthUtility) + (pObjAgent.getPLeft() * dblEastUtility) + (pObjAgent.getPRight() * dblWestUtility));
//                break;
//            case LEFT:
//                pObjGrid.setValue((pObjAgent.getPCorrect() * dblWestUtility) + (pObjAgent.getPLeft() * dblSouthUtility) + (pObjAgent.getPRight() * dblNorthUtility));
//                break;
//            case RIGHT:
//                pObjGrid.setValue((pObjAgent.getPCorrect() * dblEastUtility) + (pObjAgent.getPLeft() * dblNorthUtility) + (pObjAgent.getPRight() * dblSouthUtility));
//                break;
//        }
//        
//        return pObjGrid.getValue();
//    }
    public static int updateCurrentUtility(CAgent pObjAgent, CMap pObjMap, int pIntX, int pIntY, double pDblDiscountValue) {

        CGrid objGrid = pObjMap.getGrid(pIntX, pIntY);

        if (objGrid.getType() == EGridType.TERMINAL) {
            return 0;
        }

        if (objGrid.getType() == EGridType.WALL) {
            return 0;
        }

        double dblValue = pObjMap.getGridRewardValue(pIntX, pIntY);

        CGrid objUp = new CGrid(0.0, EDirection.UP);
        CGrid objDown = new CGrid(0.0, EDirection.DOWN);
        CGrid objLeft = new CGrid(0.0, EDirection.LEFT);
        CGrid objRight = new CGrid(0.0, EDirection.RIGHT);

        List<CGrid> lstGrid = new ArrayList<>();

        lstGrid.add(objUp);
        lstGrid.add(objDown);
        lstGrid.add(objLeft);
        lstGrid.add(objRight);

        double dblNorthUtility = pObjMap.getNeighbourGridUtility(pIntX, pIntY, EDirection.UP);
        double dblSouthUtility = pObjMap.getNeighbourGridUtility(pIntX, pIntY, EDirection.DOWN);
        double dblWestUtility = pObjMap.getNeighbourGridUtility(pIntX, pIntY, EDirection.LEFT);
        double dblEastUtility = pObjMap.getNeighbourGridUtility(pIntX, pIntY, EDirection.RIGHT);

        objUp.setValue((pObjAgent.getPCorrect() * dblNorthUtility) + (pObjAgent.getPLeft() * dblWestUtility) + (pObjAgent.getPRight() * dblEastUtility));
        objDown.setValue((pObjAgent.getPCorrect() * dblSouthUtility) + (pObjAgent.getPLeft() * dblEastUtility) + (pObjAgent.getPRight() * dblWestUtility));
        objLeft.setValue((pObjAgent.getPCorrect() * dblWestUtility) + (pObjAgent.getPLeft() * dblSouthUtility) + (pObjAgent.getPRight() * dblNorthUtility));
        objRight.setValue((pObjAgent.getPCorrect() * dblEastUtility) + (pObjAgent.getPLeft() * dblNorthUtility) + (pObjAgent.getPRight() * dblSouthUtility));

        double dblCurrentUtility = lstGrid.get(objGrid.getDirection().getValue() - 1).getValue();
        EDirection objDirection = lstGrid.get(objGrid.getDirection().getValue() - 1).getDirection();

        Collections.sort(lstGrid);

        double dblMaxUtility = lstGrid.get(3).getValue();

        if (dblMaxUtility > dblCurrentUtility) {

            System.out.print(objGrid.getDirection().getValue() + " to " + lstGrid.get(3).getDirection().getValue() + " ");

            pObjMap.setValue(pIntX, pIntY, dblValue + pDblDiscountValue * dblMaxUtility, lstGrid.get(3).getDirection());
            return 1;
        }

        pObjMap.setValue(pIntX, pIntY, dblValue + pDblDiscountValue * dblCurrentUtility, objDirection);
        return 0;
    }

    public static double setOptimalPath(CAgent pObjAgent, CMap pObjMap, int pIntX, int pIntY, double pDblDiscountValue) {

        CGrid objGrid = pObjMap.getGrid(pIntX, pIntY);

        if (objGrid.getType() == EGridType.TERMINAL) {
            return 0;
        }

        if (objGrid.getType() == EGridType.WALL) {
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

        double dblNorthUtility = pObjMap.getNeighbourGridUtility(pIntX, pIntY, EDirection.UP);
        double dblSouthUtility = pObjMap.getNeighbourGridUtility(pIntX, pIntY, EDirection.DOWN);
        double dblWestUtility = pObjMap.getNeighbourGridUtility(pIntX, pIntY, EDirection.LEFT);
        double dblEastUtility = pObjMap.getNeighbourGridUtility(pIntX, pIntY, EDirection.RIGHT);

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
