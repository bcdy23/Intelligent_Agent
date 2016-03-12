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
public class CMapFactory {

    public static CMap loadMap(String pStrFilePath) {

        CMap objMap;

        if (pStrFilePath.isEmpty()) {
            objMap = new CMap(6, 6, -0.04);

            int[] aryBadPos = {10, 15, 20, 25, 29};

            int[] aryGoodPos = {17, 22, 27, 30, 32, 35};

            objMap.setValues(aryBadPos, -1);
            objMap.setValues(aryGoodPos, 1);

            return objMap;
        }

        objMap = new CMap(0, 0, 0);

        return objMap;
    }

}
