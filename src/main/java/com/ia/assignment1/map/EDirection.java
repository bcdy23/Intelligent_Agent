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

//Enum to store policy path direction
public enum EDirection {
    UP(1),
    DOWN(2),
    LEFT(3),
    RIGHT(4);

    private final int intValue;

    EDirection(int pIntValue) {
        intValue = pIntValue;
    }
    
    public int getValue()
    {
        return intValue;
    }
    
}
