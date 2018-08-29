/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swlab.ic.uff.br.Controller;

import java.util.ArrayList;

/**
 *
 * @author angelo
 */
public class CalculateTFIDFTypes implements CalculateTFIDF {

    @Override
    public Double calculateTF(Feature feature, Dataset dataset) {
        Double sum = 0.0;
        ArrayList<Types> list_types = dataset.getType().getTypes();
        for(Types type: list_types){
            Double frequen = type.getFrquen();
            sum = sum + frequen;
        }
        
        double tf = feature.getFrquen() / sum;
        return tf;
    }

    @Override
    public Double calculateIDF(Feature feature, ArrayList<Dataset> datasets, String name_dataset) {
        Double idf = 0.0;
        for (Dataset dataset : datasets) {
            if (!dataset.getNome().equals(name_dataset)) {
                ArrayList<Types> types = dataset.getType().getTypes();
                for (Types type : types) {
                    if (type.getId() == feature.getId()) {
                        idf = idf + 1;
                    }
                }

            }

        }
        return idf;
    }

    @Override
    public Double calculateIDFTest(Feature feature, ArrayList<Dataset> datasets, String name_dataset) {
        Double idf = 0.0;
        for (Dataset dataset : datasets) {
            if (!dataset.getNome().equals(name_dataset)) {
                ArrayList<Types> types = dataset.getType().getTypes();
                for (Types type : types) {
                    if (type.getName().equals(feature.getName())) {
                        idf = idf + 1;
                    }
                }

            }

        }
        return idf;
        
    }
    
}
