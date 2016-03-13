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

//Class to store the state of grid world map
public class CMap {

    //Value to store the height and width of gridworld
    private final int intWidth;
    private final int intHeight;

    //Array to store the states of gridworld
    private final CState[][] aryGrids;

    public CMap(int pIntWidth, int pIntHeight, double pDblBaseValue) {
        intWidth = pIntWidth;
        intHeight = pIntHeight;

        aryGrids = new CState[intHeight][intWidth];

        for (int intCount = 0; intCount < intWidth * intHeight; intCount++) {
            aryGrids[intCount / intWidth][intCount % intWidth] = new CState(pDblBaseValue);
        }
    }

    //Methods to set state type and its corresponding value
    public void setGrid(int[] pIntGridPos, double pDblReward, EGridType pObjType) {
        for (int intCount = 0; intCount < pIntGridPos.length; intCount++) {
            aryGrids[pIntGridPos[intCount] / intWidth][pIntGridPos[intCount] % intWidth].setReward(pDblReward);
            aryGrids[pIntGridPos[intCount] / intWidth][pIntGridPos[intCount] % intWidth].setType(pObjType);
        }
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
