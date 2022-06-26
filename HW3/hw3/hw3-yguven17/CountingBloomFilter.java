import java.util.Arrays;
import java.util.ArrayList;

public class CountingBloomFilter {

    private int m;
    private int bound;
    private int[] counts;
    private ArrayList<HashFn> hashes;

    public CountingBloomFilter(int m, int bound) {
        this.m = m;
        this.bound = bound;
        this.counts = new int[m];
        this.hashes = new ArrayList<>();
    }

    public CountingBloomFilter(int m, int bound, ArrayList<HashFn> hashes) {
        this.m = m;
        this.counts = new int[m];
        this.hashes = hashes;
        this.bound = bound;
    }

    public void addHashFn(HashFn h) {
        hashes.add(h);
    }

    public void add(int value) {
        // TODO
    	
		for (HashFn hasfunctions : hashes) {

			int index = hasfunctions.calculateIndex(value, m);

			if (counts[index] < bound) {

				counts[index] += 1;
			}
		}
		
		// gets indexs then chrcks for upper bound then increses
       
    }

    public boolean lookup(int value, int threshold) {
        // TODO
    	
		for (HashFn hasfunctions : hashes) {

			int index = hasfunctions.calculateIndex(value, m);

			if (counts[index] < threshold) {

				return false;
			}
		}
		return true;
		
		//gets index then checks for if occurs less then tresshold time then returns 
		
    }

    @Override
    public String toString() {
        return Arrays.toString(counts);
    }
}
