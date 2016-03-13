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

//Enum to store state type
//Value > 50 ares states that does not affect policy such as wall and terminal states
public enum EGridType {
    NORMAL(1),
    WALL(99),
    GOOD(2),
    BAD(3),
    START(4),
    TERMINAL(98);
    
    private final int intValue;

    EGridType(int pIntValue) {
        intValue = pIntValue;
    }
    
    public int getValue()
    {
        return intValue;
    }
    
}
