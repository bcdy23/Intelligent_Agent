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

    public CMap(int pIntWidth, int pIntHeight, double pDblBaseValue) {
        intWidth = pIntWidth;
        intHeight = pIntHeight;

        aryGrids = new double[intHeight][intWidth];

        for (int intCount = 0; intCount < intWidth * intHeight; intCount++) {
            aryGrids[intCount / intWidth][intCount % intHeight] = pDblBaseValue;
        }
    }

    public void setValues(int[] pIntGridPos, double pDblValue) {
        for (int intCount = 0; intCount < pIntGridPos.length; intCount++) {
            aryGrids[pIntGridPos[intCount] / intWidth][pIntGridPos[intCount] % intHeight] = pDblValue;
        }
    }

    public double[][] getGridValue() {
        return aryGrids;
    }

    public int getHeight() {
        return intHeight;
    }

    public int getWidth() {
        return intWidth;
    }

}
