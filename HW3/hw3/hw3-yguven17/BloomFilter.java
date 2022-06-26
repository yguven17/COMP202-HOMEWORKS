import java.util.ArrayList;

public class BloomFilter extends CountingBloomFilter {

	public BloomFilter(int m, ArrayList<HashFn> hashes) {
		super(m, 1, hashes);
	}

	public boolean lookup(int value) {

		return super.lookup(value, 1);
	}

}

// 1 means in function there is no tressholds
// so i cbasicly use it super functions to implement