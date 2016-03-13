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
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Bryden
 */
//Abstract class for both value and policy algorithms
public abstract class CMDP {

    //Storing all policies and utility learnt
    protected final List<CPolicy[][]> lstPolicy = new ArrayList<>();

    protected final CMap objMap;
    protected final CAgent objAgent;

    protected final double dblDiscount;

    public CMDP(CMap pObjMap, CAgent pObjAgent, double pDblDiscount) {
        objMap = pObjMap;
        objAgent = pObjAgent;
        dblDiscount = pDblDiscount;

        CPolicy[][] aryInitPolicy = new CPolicy[pObjMap.getHeight()][pObjMap.getWidth()];

        //Create initial policy of 0 utility and Up Direction
        for (int intRow = 0; intRow < pObjMap.getHeight(); intRow++) {
            for (int intCol = 0; intCol < pObjMap.getWidth(); intCol++) {
                CPolicy objPolicy = new CPolicy(0, EDirection.UP);

                aryInitPolicy[intRow][intCol] = objPolicy;
            }
        }

        lstPolicy.add(aryInitPolicy);
    }

    //Get Utility value of previous policy
    //Will return original value if parameters out of bounds
    //Allow agent to stay at same position when out of bounds
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

    //Get the neighbouring utilities based on direction
    //Will return original posiiton if wall or out of bounds detected
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

    //Find all possible utilities and policy of a given grid
    //Return a list of policy in all directions
    //in order of direction enum
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

    //Get current utilities based on policy
    //Use list return by all possible policy and get the value related by direction
    public CPolicy getCurrentPolicy(int pIntX, int pIntY) {

        CPolicy objPolicy = lstPolicy.get(lstPolicy.size() - 1)[pIntY][pIntX];

        List<CPolicy> lstStatePolicy = this.getPossiblePolicy(pIntX, pIntY);

        return lstStatePolicy.get(objPolicy.getDirection().getValue() - 1);
    }

    //Get best policy of a given grid
    //Use comparable interface to sort the value to find optimal utility
    //Ordered from smallest to largest
    public CPolicy getOptimalPolicy(int pIntX, int pIntY) {
        List<CPolicy> lstStatePolicy = this.getPossiblePolicy(pIntX, pIntY);
        Collections.sort(lstStatePolicy);

        return lstStatePolicy.get(lstStatePolicy.size() - 1);
    }

    //Get final trained policy
    public CPolicy[][] getOptimalPolicy() {
        return lstPolicy.get(lstPolicy.size() - 1);
    }

    //Get all training policy
    public List<CPolicy[][]> getAllPolicies() {
        return lstPolicy;
    }

    public void savePolicyToFile() {

        DecimalFormat df = new DecimalFormat("0.000");

        StringBuilder objSB = new StringBuilder();

        for (CPolicy[][] aryPolicy : lstPolicy) {
            for (int intRow = 0; intRow < aryPolicy.length; intRow++) {
                for (int intCol = 0; intCol < aryPolicy[intRow].length; intCol++) {

                    if (aryPolicy[intRow][intCol] == null) {
                        objSB.append("0\t");
                    } else {
                        objSB.append(df.format(aryPolicy[intRow][intCol].getUtility()));
                        objSB.append("\t");
                    }
                }
            }

            objSB.append("\r\n");

        }

        try (PrintWriter out = new PrintWriter("utilities.txt")) {
            out.println(objSB);
        } catch (FileNotFoundException ex) {
        }

    }

    public abstract int calculateOptimalPolicy();

}
