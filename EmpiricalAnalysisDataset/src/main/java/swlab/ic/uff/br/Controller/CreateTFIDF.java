/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swlab.ic.uff.br.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import swlab.ic.uff.br.Dao.DatasetDAO;

/**
 *
 * @author angelo
 */
public class CreateTFIDF {

    public static ArrayList<Double> createTFIDFTest(ArrayList<Integer> vector_features,
            ArrayList<Feature> set, ArrayList<Dataset> all_datasets, Dataset dataset, int entrada) {
        ArrayList<Double> vector_tfidf = new ArrayList<>();
        for (int i = 0; i < vector_features.size(); i++) {
            vector_tfidf.add(0.0);
        }
        for (Feature feature : set) {
            if (entrada == 1) {
                if (vector_features.contains(feature.getId())) {
                    int position = vector_features.indexOf(feature.getId());
                    CalculeTFIDFLS calculate = new CalculeTFIDFLS();
                    Double tf = calculate.calculateTF(feature, dataset);
                    Double idf = calculate.calculateIDF(feature, all_datasets, dataset.getNome());
                    Double tf_tdf = tf * idf;
                    vector_tfidf.set(position, tf_tdf);
                }
            }else if(entrada == 2){
                if(vector_features.contains(feature.getId())){
                    int position = vector_features.indexOf(feature.getId());
                    CalculateTFIDFTypes calculate = new CalculateTFIDFTypes();
                    Double tf = calculate.calculateTF(feature, dataset);
                    Double idf = calculate.calculateIDF(feature, all_datasets, dataset.getNome());
                    Double tf_tdf = tf * idf;
                    vector_tfidf.set(position, tf_tdf);
                }
            }

        }
        return vector_tfidf;
    }

    public static ArrayList<Double> calculareTFIDF_LS(ArrayList<Linkset> feature_ls,
            ArrayList<Integer> vector_features, Dataset dataset,
            ArrayList<Dataset> datasets, String name_dataset) {
        ArrayList<Double> vector_tfidf = new ArrayList<>();
        DatasetDAO datasetdao = new DatasetDAO();

        for (int i = 0; i < vector_features.size(); i++) {
            vector_tfidf.add(0.0);
        }

        for (Linkset ls : feature_ls) {
            if (vector_features.contains(ls.getId())) {
                int position = vector_features.indexOf(ls.getId());
                CalculeTFIDFLS calculate = new CalculeTFIDFLS();
                Double tf = calculate.calculateTF(ls, dataset);
                Double idf = calculate.calculateIDF(ls, datasets, name_dataset);
                Double tf_tdf = tf * idf;
                vector_tfidf.set(position, tf_tdf);
            }

        }

        return vector_tfidf;

    }
    
    public static ArrayList<Double> calculateTFIDF_Types(ArrayList<Types> feature_types,
            ArrayList<Integer> vector_features, Dataset dataset,
            ArrayList<Dataset> datasets, String name_dataset) {
        
        ArrayList<Double> vector_tfidf = new ArrayList<>();
        

        for (int i = 0; i < vector_features.size(); i++) {
            vector_tfidf.add(0.0);
        }
        
        for (Types type : feature_types) {
                try{
                    int position = vector_features.indexOf(type.getId());
                    CalculateTFIDFTypes calculate = new CalculateTFIDFTypes();
                    Double tf = calculate.calculateTF(type, dataset);
                    Double idf = calculate.calculateIDF(type, datasets, name_dataset);
                    Double tf_tdf = tf * idf;
                    vector_tfidf.set(position, tf_tdf);
                    
                }catch(Throwable e){
                    continue;
                }
        }
        
        return vector_tfidf;
        
    }
    
    

    public static HashMap<Dataset, ArrayList<Double>> createTF_IDF(ArrayList<Integer> vector_features,
            ArrayList<Dataset> list_datasets, ArrayList<String> datasets, int entrada) {
        
        
        HashMap<Dataset, ArrayList<Double>> tf_idf = new HashMap<>();
        for (Dataset dataset : list_datasets) {
            //System.out.println(dataset.getNome());
            if(entrada == 1){
                ArrayList<Linkset> feature_ls = dataset.getType().getLinkset();
                ArrayList<Double> vector_tfidf = calculareTFIDF_LS(feature_ls, vector_features, dataset,
                    list_datasets, dataset.getNome());
                
                tf_idf.put(dataset, vector_tfidf);
                
            }else if(entrada == 2){
                
                ArrayList<Types> feature_type = dataset.getType().getTypes();
                ArrayList<Double> vector_tfidf = calculateTFIDF_Types(feature_type, vector_features, dataset,
                    list_datasets, dataset.getNome());
                tf_idf.put(dataset, vector_tfidf);
            }
            

        }
        return tf_idf;

    }

}
