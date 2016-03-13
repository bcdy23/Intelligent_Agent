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

//Class to store state of gridworld
public class CState {

    //Reward of a state
    private double dblReward;

    //Type of a state
    private EGridType objType;

    public CState(double pDblValue) {
        this(pDblValue, EGridType.NORMAL);
    }

    public CState(double pDblReward, EGridType pObjGridType) {
        dblReward = pDblReward;
        objType = pObjGridType;
    }

    public double getReward() {
        return dblReward;
    }

    public EGridType getType() {
        return objType;
    }

    public void setReward(double pDblValue) {
        dblReward = pDblValue;
    }

    public void setType(EGridType pObjType) {
        objType = pObjType;
    }
}
