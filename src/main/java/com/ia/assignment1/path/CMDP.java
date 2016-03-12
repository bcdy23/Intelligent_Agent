/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ia.assignment1.path;

import com.ia.assignment1.agent.CAgent;
import com.ia.assignment1.map.CMap;
import com.ia.assignment1.map.CState;
import com.ia.assignment1.map.EDirection;
import com.ia.assignment1.map.EGridType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Bryden
 */
public abstract class CMDP {

    protected final List<CPolicy[][]> lstPolicy = new ArrayList<>();

    protected final CMap objMap;
    protected final CAgent objAgent;

    public CMDP(CMap pObjMap, CAgent pObjAgent) {
        objMap = pObjMap;
        objAgent = pObjAgent;

        CPolicy[][] aryInitPolicy = new CPolicy[pObjMap.getHeight()][pObjMap.getWidth()];

        for (int intRow = 0; intRow < pObjMap.getHeight(); intRow++) {
            for (int intCol = 0; intCol < pObjMap.getWidth(); intCol++) {
                CPolicy objPolicy = new CPolicy(0, EDirection.UP);

                aryInitPolicy[intRow][intCol] = objPolicy;
            }
        }

        lstPolicy.add(aryInitPolicy);
    }

    public double getUtilityPrevState(int pIntX, int pIntY) {
        if (pIntX >= objMap.getWidth()) {
            pIntX = objMap.getWidth() - 1;
        } else if (pIntX < 0) {
            pIntX = 0;
        }

        if (pIntY >= objMap.getHeight()) {
            pIntY = objMap.getHeight() - 1;
        } else if (pIntY < 0) {
            pIntY = 0;
        }

        return lstPolicy.get(lstPolicy.size() - 1)[pIntY][pIntX].getUtility();
    }

    public double getNeightbourUtility(int pIntX, int pIntY, EDirection pObjDirection) {
        int intX = pIntX;
        int intY = pIntY;

        switch (pObjDirection) {
            case UP:
                intY += 1;
                break;
            case DOWN:
                intY -= 1;
                break;
            case LEFT:
                intX -= 1;
                break;
            case RIGHT:
                intX += 1;
                break;
        }

        if (intX >= objMap.getWidth()) {
            intX = objMap.getWidth() - 1;
        } else if (intX < 0) {
            intX = 0;
        }

        if (intY >= objMap.getHeight()) {
            intY = objMap.getHeight() - 1;
        } else if (intY < 0) {
            intY = 0;
        }

        CState objNeighbour = objMap.getGrid(intX, intY);

        if (objNeighbour.getType() == EGridType.WALL) {
            return getUtilityPrevState(pIntX, pIntY);
        }

        return getUtilityPrevState(intX, intY);
    }

    public List<CPolicy> getPossiblePolicy(int pIntX, int pIntY) {
        CPolicy objUp = new CPolicy(0.0, EDirection.UP);
        CPolicy objDown = new CPolicy(0.0, EDirection.DOWN);
        CPolicy objLeft = new CPolicy(0.0, EDirection.LEFT);
        CPolicy objRight = new CPolicy(0.0, EDirection.RIGHT);

        List<CPolicy> lstGrid = new ArrayList<>();

        lstGrid.add(objUp);
        lstGrid.add(objDown);
        lstGrid.add(objLeft);
        lstGrid.add(objRight);

        double dblNorthUtility = getNeightbourUtility(pIntX, pIntY, EDirection.UP);
        double dblSouthUtility = getNeightbourUtility(pIntX, pIntY, EDirection.DOWN);
        double dblWestUtility = getNeightbourUtility(pIntX, pIntY, EDirection.LEFT);
        double dblEastUtility = getNeightbourUtility(pIntX, pIntY, EDirection.RIGHT);

        objUp.setUtility((objAgent.getPCorrect() * dblNorthUtility) + (objAgent.getPLeft() * dblWestUtility) + (objAgent.getPRight() * dblEastUtility));
        objDown.setUtility((objAgent.getPCorrect() * dblSouthUtility) + (objAgent.getPLeft() * dblEastUtility) + (objAgent.getPRight() * dblWestUtility));
        objLeft.setUtility((objAgent.getPCorrect() * dblWestUtility) + (objAgent.getPLeft() * dblSouthUtility) + (objAgent.getPRight() * dblNorthUtility));
        objRight.setUtility((objAgent.getPCorrect() * dblEastUtility) + (objAgent.getPLeft() * dblNorthUtility) + (objAgent.getPRight() * dblSouthUtility));

        return lstGrid;
    }

    public CPolicy getCurrentPolicy(int pIntX, int pIntY) {

        CPolicy objPolicy = lstPolicy.get(lstPolicy.size() - 1)[pIntY][pIntX];

        List<CPolicy> lstStatePolicy = this.getPossiblePolicy(pIntX, pIntY);

        return lstStatePolicy.get(objPolicy.getDirection().getValue() - 1);
    }

    public CPolicy getOptimalPolicy(int pIntX, int pIntY) {
        List<CPolicy> lstStatePolicy = this.getPossiblePolicy(pIntX, pIntY);
        Collections.sort(lstStatePolicy);

        return lstStatePolicy.get(lstStatePolicy.size() - 1);
    }

    public CPolicy[][] getOptimalPolicy() {
        return lstPolicy.get(lstPolicy.size() - 1);
    }

    public List<CPolicy[][]> getAllPolicies() {
        return lstPolicy;
    }

    public abstract int calculateOptimalPolicy();

}
