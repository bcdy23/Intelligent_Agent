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
public enum EGridType {
    NORMAL(1),
    TERMINAL(2),
    WALL(3),
    START(4);
    
    private final int intValue;

    EGridType(int pIntValue) {
        intValue = pIntValue;
    }
    
    public int getValue()
    {
        return intValue;
    }
    
}
