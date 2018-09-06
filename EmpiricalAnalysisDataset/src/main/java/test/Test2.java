/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author angelo
 */
public class Test2 {

    public static <T> List<List<T>> zip(List<List<T>>... lists) {
        List<List<T>> zipped = new ArrayList<List<T>>();
        for (List<List<T>> all_list : lists) {
            for (List<T> list : all_list) {
                for (int i = 0, listSize = list.size(); i < listSize; i++) {
                    List<T> list2;
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

    public static void main(String[] args) {

        List<Integer> x = Arrays.asList(0, 2, 3);
        List<Integer> y = Arrays.asList(0, 5, 6);
        List<Integer> z = Arrays.asList(0);
        List<List<Integer>> all_lists = new ArrayList<>();
        all_lists.add(x);
        all_lists.add(y);
        all_lists.add(z);
        List<List<Integer>> zipped = zip(all_lists);
        System.out.println(zipped);
    }

}
