/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ia.assignment1.path;

import com.ia.assignment1.map.EDirection;

/**
 *
 * @author Bryden
 */

//Class to store utility value and policy
public class CPolicy implements Comparable {

    private double dblUtility;
    private EDirection objPathDirection;

    public CPolicy(double pDblUtility, EDirection pObjDirection) {
        dblUtility = pDblUtility;
        objPathDirection = pObjDirection;
    }

    public void setUtility(double pDblUtility) {
        dblUtility = pDblUtility;
    }

    public double getUtility() {
        return dblUtility;
    }

    public void setDirection(EDirection pObjPathDirection) {
        objPathDirection = pObjPathDirection;
    }

    public EDirection getDirection() {
        return objPathDirection;
    }

    //Comparable to allow sorting to find best utility
    @Override
    public int compareTo(Object t) {
        CPolicy objCompareGrid = (CPolicy) t;

        if (this.getUtility() > objCompareGrid.getUtility()) {
            return 1;
        } else if (this.getUtility() < objCompareGrid.getUtility()) {
            return -1;
        }

        return 0;
    }
}
