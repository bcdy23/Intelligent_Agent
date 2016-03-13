/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ia.assignment1.path;

import com.ia.assignment1.agent.CAgent;
import com.ia.assignment1.map.CMap;

/**
 *
 * @author Bryden
 */
public class CMDPFactory {

    //Return algo based on selection
    public static CMDP getMDP(String pStrAlgo, CMap pObjMap, CAgent pObjAgent, double pDblDiscount, double pDblMaxError) {

        switch (pStrAlgo.toLowerCase()) {
            case "value":
                return new CIterativePath(pObjMap, pObjAgent, pDblDiscount, pDblMaxError);
            case "policy":
                return new CPolicyPath(pObjMap, pObjAgent, pDblDiscount);
            default:
                return new CIterativePath(pObjMap, pObjAgent, pDblDiscount, pDblMaxError);
        }

    }

}
