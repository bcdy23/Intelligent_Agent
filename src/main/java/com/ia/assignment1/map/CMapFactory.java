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

    //Create map based on textfile
    public static CMap loadMap(String pStrFilePath) {

        CMap objMap;

        if (pStrFilePath.isEmpty() || pStrFilePath.equalsIgnoreCase("default")) {
            objMap = new CMap(6, 6, -0.04);

            int[] aryBadPos = {10, 15, 20, 25, 29};

            int[] aryBadCliff = {0, 6, 12, 18};

            int[] aryGoodPos = {17, 22, 27, 30, 32, 35};
            int[] aryWallPos = {7, 8, 9, 28, 31};

            objMap.setGrid(aryBadPos, -1, EGridType.BAD);
            //objMap.setGrid(aryBadCliff, -50, EGridType.BAD);
            objMap.setGrid(aryGoodPos, 1, EGridType.GOOD);
            objMap.setGrid(aryWallPos, 0, EGridType.WALL);

            return objMap;
        }

        if (pStrFilePath.isEmpty() || pStrFilePath.equalsIgnoreCase("Custom 1")) {
            objMap = new CMap(6, 6, -0.04);

            int[] aryBadPos = {10, 15, 20, 25, 29};

            int[] aryBadCliff = {0, 6, 12, 18};

            int[] aryGoodPos = {17, 22, 27, 30, 32, 35};
            int[] aryWallPos = {7, 8, 9, 28, 31};

            objMap.setGrid(aryBadPos, -1, EGridType.BAD);
            objMap.setGrid(aryBadCliff, -50, EGridType.BAD);
            objMap.setGrid(aryGoodPos, 1, EGridType.GOOD);
            objMap.setGrid(aryWallPos, 0, EGridType.WALL);

            return objMap;
        }

        if (pStrFilePath.isEmpty() || pStrFilePath.equalsIgnoreCase("Custom 2")) {
            objMap = new CMap(8, 8, -0.04);

            int[] aryBadPos = {20, 25, 29, 44, 54};

            int[] aryBadCliff = {0, 1, 2, 3, 4, 5, 6, 7};

            int[] aryGoodPos = {17, 30, 35, 56, 59, 63};
            int[] aryWallPos = {9, 28, 31, 57, 58};

            objMap.setGrid(aryBadPos, -1, EGridType.BAD);
            //objMap.setGrid(aryBadCliff, -50, EGridType.BAD);
            objMap.setGrid(aryGoodPos, 1, EGridType.GOOD);
            objMap.setGrid(aryWallPos, 0, EGridType.WALL);

            return objMap;
        }

        if (pStrFilePath.isEmpty() || pStrFilePath.equalsIgnoreCase("Custom 3")) {
            objMap = new CMap(6, 6, -0.04);

            int[] aryBadPos = {0, 1, 2, 3, 4, 5, 6, 12, 18, 24, 30, 31, 32, 33, 34, 35, 29, 23, 17, 11};

            //int[] aryGoodPos = {8,10,13,20,22, 27};
            int[] aryGoodPos = {8,13,22, 27};
            
            //int[] aryWallPos = {7, 8, 9, 28, 31};

            objMap.setGrid(aryBadPos, -1, EGridType.BAD);
            objMap.setGrid(aryGoodPos, 1, EGridType.GOOD);
            //objMap.setGrid(aryWallPos, 0, EGridType.WALL);

            return objMap;
        }

        objMap = new CMap(0, 0, 0);

        return objMap;
    }

}
