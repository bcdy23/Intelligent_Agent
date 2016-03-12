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
public class CGrid implements Comparable {

    private EDirection objDirection;
    
    private double dblValue;

    private EGridType objType;

    public CGrid(double pDblValue) {
        this(pDblValue, EDirection.UP, EGridType.NORMAL);
    }

    public CGrid(double pDblValue, EDirection pObjDirection) {
        this(pDblValue, pObjDirection, EGridType.NORMAL);
    }

    public CGrid(double pDblValue, EDirection pObjDirection, EGridType pObjGridType) {
        objDirection = pObjDirection;
        dblValue = pDblValue;
        objType = pObjGridType;
    }

    public EDirection getDirection() {
        return objDirection;
    }

    public double getValue() {
        return dblValue;
    }

    public EGridType getType() {
        return objType;
    }

    public void setDirection(EDirection pObjDirection) {
        objDirection = pObjDirection;
    }

    public void setValue(double pDblValue) {
        dblValue = pDblValue;
    }

    public void setType(EGridType pObjType) {
        objType = pObjType;
    }

    @Override
    public int compareTo(Object t) {
        CGrid objCompareGrid = (CGrid) t;

        if (this.getValue() > objCompareGrid.getValue()) {
            return 1;
        } else if (this.getValue() < objCompareGrid.getValue()) {
            return -1;
        }

        return 0;
    }
}
