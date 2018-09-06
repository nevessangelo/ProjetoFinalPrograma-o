/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swlab.ic.uff.br.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author angelo
 */
public class Creates {
    
    public static <T> ArrayList<ArrayList<T>> zip(ArrayList<ArrayList<T>>... lists) {
        ArrayList<ArrayList<T>> zipped = new ArrayList<ArrayList<T>>();
        for (ArrayList<ArrayList<T>> all_list : lists) {
            for (List<T> list : all_list) {
                for (int i = 0, listSize = list.size(); i < listSize; i++) {
                    ArrayList<T> list2;
                    if (i >= zipped.size()) {
                        zipped.add(list2 = new ArrayList<T>());
                    } else {
                        list2 = zipped.get(i);
                    }
                    list2.add(list.get(i));
                }
            }
        }
        return zipped;
    }

    public static ArrayList<Double> mean_lists(ArrayList<ArrayList<Double>> lists) {
        ArrayList<Double> list_return = new ArrayList<>();
        for(ArrayList<Double> nDCGZip: lists){
            Double sum = 0.0;
            Integer size = nDCGZip.size();
            for(Double number: nDCGZip){
                sum = sum + number;
            }
            Double mean = 0.0;
            if(sum == 0){
                mean = 0.0;
            }else{
                mean = sum / size;
            }
           
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
        }else if (entrada == 3){
            for (Dataset dataset : all_datasets_tranning) {
                ArrayList<Linkset> linksets = dataset.getType().getLinkset();
                ArrayList<Types> types = dataset.getType().getTypes();
                
                for(Linkset linkset: linksets){
                    if(!vector_features.contains(linkset.getId())){
                        vector_features.add(linkset.getId());
                    }
                }
                
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
                    ArrayList<ArrayList<Feature>> sets = Sets.GenerateSets(types_sets, 5, 12); 
                    hmap.put(dataset, sets);
                    
                }
            }
            
        }else if(entrada == 3){
            for (Dataset dataset : test) {
                ArrayList<Types> types = dataset.getType().getTypes();
                ArrayList<Feature> types_sets = new ArrayList<>();
                
                
            }
            
            
            
        }

        return hmap;
    }

}
