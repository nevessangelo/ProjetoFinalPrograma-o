/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import java.util.HashMap;
import swlab.ic.uff.br.Dao.DatasetDAO;

/**
 *
 * @author angelo
 */
public class Test {
    
    public static void main(String[] args) {
        DatasetDAO datasetDAO = new DatasetDAO();
        ArrayList<String> allFeatures = datasetDAO.getAllFeatures();
        
        HashMap<Integer, String> index = new HashMap<>();
        Integer indexCount = 1;
        
        for(String feature: allFeatures){
            index.put(indexCount, feature);
            indexCount++;
        }
        
    }
    
}
