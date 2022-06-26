import java.util.ArrayList;
import java.util.Random;

class Main {
    public static void main(String[] args) {
        int seed = 100;
        Random rand = new Random(seed);
        int sequenceSize = 10000;

        int m = 100;
        int maxint = Integer.MAX_VALUE;
        ArrayList<HashFn> hashes = new ArrayList<>();
        hashes.add(new HashFn() {
            public int calculateIndex(int value, int arrSize) {
                return value % arrSize;
            }
        });
        // TODO - add the hash function specified in the project description to the "hashes" ArrayList

		hashes.add(new HashFn() {
			public int calculateIndex(int value, int arrSize) {

				int firstResult = (value ^ (value >>> 13)) * 0x7feb352d;

				int secondResult = (firstResult ^ (firstResult)) * 0x7feb352d;

				int thirdResult = secondResult ^ (secondResult >>> 13);

				if (thirdResult < 0) {
					thirdResult =thirdResult * (-1);
				}

				return thirdResult % arrSize;
				
				//calculates step by steps then if negative makes it positive finaly returns module of array size
			}
		});
        
        CountingBloomFilter f = new CountingBloomFilter(m, maxint, hashes);
        BloomFilter bf = new BloomFilter(m, hashes);

        for (int i = 0; i < sequenceSize; i++) {
            int randNo = (rand.nextInt() & Integer.MAX_VALUE) % sequenceSize;

            bf.add(randNo);
            f.add(randNo);
        }

        for (int i = 0; i < sequenceSize / 100; i++) {
            int randNo = (rand.nextInt() & Integer.MAX_VALUE) % sequenceSize;
            System.out.println("number: " + randNo +
                    ", counting: " + f.lookup(randNo, 200) +
                    ", bloom: " + bf.lookup(randNo));
        }
    }
}
