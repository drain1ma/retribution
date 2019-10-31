import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        
        Scanner scnr = new Scanner(System.in);
        
        int n = scnr.nextInt(); 
        int m = scnr.nextInt(); 
        int p = scnr.nextInt(); 
        
        int[][] judges = new int[n][2]; 
        int[][] tars = new int[m][2]; 
        int[][] feathers = new int[p][2]; 
        
        for(int i = 0; i < n; i++) {
            judges[i][0] = scnr.nextInt(); 
            judges[i][1] = scnr.nextInt(); 
        }
        
        for(int i = 0; i < m; i++) {
            tars[i][0] = scnr.nextInt(); 
            tars[i][1] = scnr.nextInt(); 
        }
        
        for(int i = 0; i < p; i++) {
            feathers[i][0] = scnr.nextInt(); 
            feathers[i][1] = scnr.nextInt(); 
        }
        
        Map<Double, List<int[]>> dist_tars = new HashMap<>(); 
        Map<Double, List<int[]>> dist_feathers = new HashMap<>(); 
        
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                double dist = distance(judges[i], tars[j]); 
                if (dist_tars.containsKey(dist)) {
                    dist_tars.get(dist).add(new int[] {i, j}); 
                }else {
                    dist_tars.put(dist, new ArrayList(Arrays.asList(new int[]{i, j}))); 
                }
            }
            for (int j = 0; j < p; j++) {
                double dist = distance(judges[i], feathers[j]); 
                if (dist_feathers.containsKey(dist)) {
                    dist_feathers.get(dist).add(new int[] {i, j}); 
                }else {
                    dist_feathers.put(dist, new ArrayList(Arrays.asList(new int[]{i, j}))); 
                }
            }
        }   
        List<Double> ord_dist_tars = new ArrayList<>(dist_tars.keySet()); 
        List<Double> ord_dist_feathers = new ArrayList<>(dist_feathers.keySet()); 
        Collections.sort(ord_dist_tars); 
        Collections.sort(ord_dist_feathers); 
        
        
        double total = 0; 
        Set<Integer> visited_j = new HashSet<>();
        Set<Integer> visited_tar = new HashSet<>(); 
        Set<Integer> visited_feather = new HashSet<>(); 
        
        for(double d : ord_dist_tars) {
            for(int[] judge_tars : dist_tars.get(d)) {
                if (!visited_j.contains(judge_tars[0]) && !visited_tar.contains(judge_tars[1])){
                    total += d; 
                    visited_j.add(judge_tars[0]); 
                    visited_tar.add(judge_tars[1]); 
                }
            }
        }
        visited_j.clear(); 
        
        for(double d : ord_dist_feathers) {
            for(int[] judge_feather : dist_feathers.get(d)) {
                if (!visited_j.contains(judge_feather[0]) && !visited_feather.contains(judge_feather[1])){
                    total += d; 
                    visited_j.add(judge_feather[0]); 
                    visited_feather.add(judge_feather[1]); 
                }
            }
        }
        
        System.out.println((float)total);
    }
    
    
    
    
    
    static double distance(int[] a, int[] b) {
        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b [1], 2));
    }
}