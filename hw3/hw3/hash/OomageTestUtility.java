package hw3.hash;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        Map<Integer, Integer> allBuckets = new HashMap<>();

        for(Oomage o : oomages){
            int hash = o.hashCode();
            int bucket = (hash & 0x7FFFFFFF) % M;
            if(allBuckets.containsKey(bucket)){ //checks if allbuckets already has bucket.
                int num = allBuckets.get(bucket);
                allBuckets.put(bucket, num + 1);
            }else{
                allBuckets.put(bucket, 1);
            }

        }
        int N = oomages.size();
        for(int bucket : allBuckets.keySet()){
            if(allBuckets.get(bucket) >= N/2.5 || allBuckets.get(bucket) <= N/50){
                return false;
            }
        }
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        return true;
    }
}
