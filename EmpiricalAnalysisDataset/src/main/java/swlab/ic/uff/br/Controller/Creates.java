/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swlab.ic.uff.br.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author angelo
 */
public class Creates {

    public static ArrayList<Double> mean_lists(ArrayList<ArrayList<Double>> lists, int size) {
        ArrayList<Double> list_return = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Double sum = 0.0;
            for (ArrayList<Double> ndcg : lists) {
                Double number = ndcg.get(i);
                sum = sum + number;
            }
            Double mean = sum / size;
            list_return.add(mean);
        }

        return list_return;

    }

    public static ArrayList<Integer> VectorFeatures(ArrayList<Dataset> all_datasets_tranning, int entrada) {

        ArrayList<Integer> vector_features = new ArrayList<>();
        ArrayList<String> datasets = new ArrayList<>();

        for (Dataset dataset : all_datasets_tranning) {
            String nome_dataset = dataset.getNome();
            datasets.add(nome_dataset);
        }

        if (entrada == 1) {
            for (Dataset dataset : all_datasets_tranning) {
                ArrayList<Linkset> linksets = dataset.getType().getLinkset();

                for (Linkset linkset : linksets) {
                    String aux_dataset = linkset.getName() + "-uni-mannheim";
                    if ((datasets.contains(aux_dataset)) && (!vector_features.contains(linkset.getId()))) {
                        vector_features.add(linkset.getId());
                    }

                }
            }
        }else if (entrada == 2) {
            for (Dataset dataset : all_datasets_tranning) {
                ArrayList<Types> types = dataset.getType().getTypes();
                for (Types type : types) {
                    if (!vector_features.contains(type.getId())) {
                        vector_features.add(type.getId());
                    }
                }
            }
        }
        return vector_features;

    }

    public static HashMap<Dataset, ArrayList<ArrayList<Feature>>> CreateSets(ArrayList<Dataset> test, ArrayList<Integer> vector_features,
            int entrada) {

        HashMap<Dataset, ArrayList<ArrayList<Feature>>> hmap = new HashMap<Dataset, ArrayList<ArrayList<Feature>>>();
        if(entrada == 1){
            for (Dataset dataset : test) {
                
                ArrayList<Linkset> linksets = dataset.getType().getLinkset();
            
                ArrayList<Feature> linksets_sets = new ArrayList<>();
                Iterator itr = linksets.iterator();
                while (itr.hasNext()) {
                    Linkset linkset = (Linkset) itr.next();
                    String aux_dataset = linkset.getName() + "-uni-mannheim";
                    if (vector_features.contains(linkset.getId())) {
                        linksets_sets.add(linkset);
                    }
                    ArrayList<ArrayList<Feature>> sets = Sets.GenerateSets(linksets_sets, 5, 5);
                    hmap.put(dataset, sets);
                }
                
                
            }
            
        }else if(entrada == 2){
            for (Dataset dataset : test) {
               
                ArrayList<Types> types = dataset.getType().getTypes();
                ArrayList<Feature> types_sets = new ArrayList<>();
                Iterator itr = types.iterator();
                while (itr.hasNext()) {
                    
                    Types type = (Types) itr.next();
                    if (vector_features.contains(type.getId())) {
                        types_sets.add(type);
                    }
                    ArrayList<ArrayList<Feature>> sets = Sets.GenerateSets(types_sets, 5, 15); 
                    hmap.put(dataset, sets);
                    
                }
            }
            
        }

        return hmap;
    }

}
