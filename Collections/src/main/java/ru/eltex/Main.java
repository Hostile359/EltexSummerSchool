package ru.eltex;

import java.util.*;

public class Main{
    
    private static final Integer N = 1000000;
    private static final Double dt= 1e-9;
    
    public static void main(String args[]) {
        
        System.out.printf("\nHasSet vs. HashMap\n\n");
        System.out.printf("Collection    |  Add (sec)  | Remove (sec)\n");
        
        //HashSet test
        HashSet<Integer> hs = new HashSet<Integer>();
        
        long start = System.nanoTime();
        
        for(int i = 0; i < N; i++)
            hs.add(i);
            
        long end = System.nanoTime();
        Double res = (end - start) * dt;
        System.out.printf("HashSet       |%13.6f|", res );
        
        start = System.nanoTime();
        
        for(int i = 0; i < N; i++)
            hs.remove(i);
            
        end = System.nanoTime();
        res = (end - start) * dt;
        System.out.printf("%13.6f\n", res );
        
        
        //HashMap test
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        
        start = System.nanoTime();
        
        for(int i = 0; i < N; i++)
            hm.put(i, i);
            
        end = System.nanoTime();
        res = (end - start) * dt;
        System.out.printf("HashMap       |%13.6f|", res );
        
        start = System.nanoTime();
        
        for(int i = 0; i < N; i++)
            hm.remove(i);
            
        end = System.nanoTime();
        res = (end - start) * dt;
        System.out.printf("%13.6f\n", res );
        
        
        
        System.out.printf("\n\nLinkedList vs. ArrayList vs. TreeSet\n\n");
        System.out.printf("Collection    |  Add (sec)  | Remove (sec)\n");
        
        //LinkedList test
        LinkedList<Integer> ll = new LinkedList<Integer>();
        
        start = System.nanoTime();
        
        for(int i = 0; i < N; i++)
            ll.add(i);
            
        end = System.nanoTime();
        res = (end - start) * dt;
        System.out.printf("LinkedList    |%13.6f|", res );
        
        start = System.nanoTime();
        
        for(int i = N - 1; i >= 0; i--)
            ll.remove(i);
            
        end = System.nanoTime();
        res = (end - start) * dt;
        System.out.printf("%13.6f\n", res );
        

        //ArrayList test
        ArrayList<Integer> al = new ArrayList<Integer>();
        
        start = System.nanoTime();
        
        for(int i = 0; i < N; i++)
            al.add(i);
            
        end = System.nanoTime();
        res = (end - start) * dt;
        System.out.printf("ArrayList     |%13.6f|", res );
        
        start = System.nanoTime();
        
        for(int i = N - 1; i >= 0; i--)
            al.remove(i);
            
        end = System.nanoTime();
        res = (end - start) * dt;
        System.out.printf("%13.6f\n", res );
        
        
        //TreeSet test
        TreeSet<Integer> ts = new TreeSet<Integer>();
        
        start = System.nanoTime();
        
        for(int i = 0; i < N; i++)
            ts.add(i);
            
        end = System.nanoTime();
        res = (end - start) * dt;
        System.out.printf("TreeSet       |%13.6f|", res );
        
        start = System.nanoTime();
        
        for(int i = N - 1; i >= 0; i--)
            ts.remove(i);
            
        end = System.nanoTime();
        res = (end - start) * dt;
        System.out.printf("%13.6f\n", res );
    }
}
