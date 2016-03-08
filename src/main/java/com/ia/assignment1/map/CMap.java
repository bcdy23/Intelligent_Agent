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

    double[][] aryGrids;
    double[][] aryGrids2;
    double[][] aryGridsReward;

    EDirection[][] aryPath;

    public CMap(int pIntWidth, int pIntHeight, double pDblBaseValue) {
        intWidth = pIntWidth;
        intHeight = pIntHeight;

        aryGrids = new double[intHeight][intWidth];
        aryGrids2 = new double[intHeight][intWidth];
        aryGridsReward = new double[intHeight][intWidth];

        aryPath = new EDirection[intHeight][intWidth];

        for (int intCount = 0; intCount < intWidth * intHeight; intCount++) {
            aryGrids[intCount / intWidth][intCount % intWidth] = pDblBaseValue;
            aryGridsReward[intCount / intWidth][intCount % intWidth] = pDblBaseValue;
            aryPath[intCount / intWidth][intCount % intWidth] = EDirection.UP;
        }
    }

    public void setValues(int[] pIntGridPos, double pDblValue) {
        for (int intCount = 0; intCount < pIntGridPos.length; intCount++) {
            aryGrids[pIntGridPos[intCount] / intWidth][pIntGridPos[intCount] % intWidth] = pDblValue;
            aryGrids2[pIntGridPos[intCount] / intWidth][pIntGridPos[intCount] % intWidth] = pDblValue;
            aryGridsReward[pIntGridPos[intCount] / intWidth][pIntGridPos[intCount] % intWidth] = pDblValue;
        }
    }

    public void commitState() {
        for (int intCount = 0; intCount < intWidth * intHeight; intCount++) {
            aryGrids2[intCount / intWidth][intCount % intWidth] = aryGrids[intCount / intWidth][intCount % intWidth];
        }
    }

    public void setValue(int pIntX, int pIntY, double pDblValue, EDirection objDirection) {
        aryGrids[pIntY][pIntX] = pDblValue;
        aryPath[pIntY][pIntX] = objDirection;
    }

    public double getGridRewardValue(int pIntX, int pIntY) {
        if (pIntX >= intWidth) {
            pIntX = intWidth - 1;
        } else if (pIntX < 0) {
            pIntX = 0;
        }

        if (pIntY >= intHeight) {
            pIntY = intHeight - 1;
        } else if (pIntY < 0) {
            pIntY = 0;
        }

        return aryGridsReward[pIntY][pIntX];
    }

    public double getGridValuePrevState(int pIntX, int pIntY) {
        if (pIntX >= intWidth) {
            pIntX = intWidth - 1;
        } else if (pIntX < 0) {
            pIntX = 0;
        }

        if (pIntY >= intHeight) {
            pIntY = intHeight - 1;
        } else if (pIntY < 0) {
            pIntY = 0;
        }

        return aryGrids2[pIntY][pIntX];
    }

    public double getGridValue(int pIntX, int pIntY) {
        if (pIntX >= intWidth) {
            pIntX = intWidth - 1;
        } else if (pIntX < 0) {
            pIntX = 0;
        }

        if (pIntY >= intHeight) {
            pIntY = intHeight - 1;
        } else if (pIntY < 0) {
            pIntY = 0;
        }

        return aryGrids[pIntY][pIntX];
    }

    public double[][] getGridValue() {
        return aryGrids;
    }

    public EDirection[][] getPathValue() {
        return aryPath;
    }

    public int getHeight() {
        return intHeight;
    }

    public int getWidth() {
        return intWidth;
    }

}
