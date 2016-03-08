/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ia.assignment1.agent;

/**
 *
 * @author Bryden
 */
public class CAgent {

    double dblDeviateLeft;
    double dblDeviateRight;

    double dblCorrect;

    public CAgent(double pDblCorrect, double pDblDeviateRight, double pDblDeviateLeft) {
        dblCorrect = pDblCorrect;

        dblDeviateLeft = pDblDeviateLeft;
        dblDeviateRight = pDblDeviateRight;
    }

    public double getPLeft() {
        return dblDeviateLeft;
    }

    public double getPRight() {
        return dblDeviateRight;
    }

    public double getPCorrect() {
        return dblCorrect;
    }    
}
