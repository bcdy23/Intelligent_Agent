/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ia.assignment1.map;

/**
 *
 * @author Bryden
 */
public class CMap {

    private final int intWidth;
    private final int intHeight;

    private final CState[][] aryGrids;

    public CMap(int pIntWidth, int pIntHeight, double pDblBaseValue) {
        intWidth = pIntWidth;
        intHeight = pIntHeight;

        aryGrids = new CState[intHeight][intWidth];

        for (int intCount = 0; intCount < intWidth * intHeight; intCount++) {
            aryGrids[intCount / intWidth][intCount % intWidth] = new CState(pDblBaseValue);
        }

        aryGrids[1][1].setType(EGridType.WALL);
        aryGrids[1][2].setType(EGridType.WALL);
        aryGrids[1][3].setType(EGridType.WALL);
        aryGrids[4][4].setType(EGridType.WALL);
        aryGrids[5][1].setType(EGridType.WALL);

    }

    public void setValues(int[] pIntGridPos, double pDblValue) {
        for (int intCount = 0; intCount < pIntGridPos.length; intCount++) {
            aryGrids[pIntGridPos[intCount] / intWidth][pIntGridPos[intCount] % intWidth].setReward(pDblValue);
        }
    }

    public void setValue(int pIntX, int pIntY, double pDblValue, EDirection objDirection) {
        aryGrids[pIntY][pIntX].setReward(pDblValue);
    }

    public CState[][] getGridValue() {
        return aryGrids;
    }

    public CState getGrid(int pIntX, int pIntY) {
        return aryGrids[pIntY][pIntX];
    }

    public int getHeight() {
        return intHeight;
    }

    public int getWidth() {
        return intWidth;
    }

}
