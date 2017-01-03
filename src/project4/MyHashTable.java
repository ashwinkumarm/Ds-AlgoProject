package project4;


public class MyHashTable<AnyType> {

	HashEntry<AnyType>[] array;
	private int theSize;
	private int occupied;

	MyHashTable() {
		this(10);
	}

	MyHashTable(int i) {
		allocateArray(i);
	}

	public void allocateArray(int size) {

		array = new HashEntry[nextPrime(size)];
	}

	private static int nextPrime(int n) {
		if (n % 2 == 0)
			n++;

		for (; !isPrime(n); n += 2)
			;

		return n;
	}

	private static boolean isPrime(int n) {
		if (n == 2 || n == 3)
			return true;

		if (n == 1 || n % 2 == 0)
			return false;

		for (int i = 3; i * i <= n; i += 2)
			if (n % i == 0)
				return false;

		return true;
	}

	private int myhash(AnyType x) {
		int hashVal = x.hashCode();

		hashVal %= array.length;
		if (hashVal < 0)
			hashVal += array.length;

		return hashVal;
	}

	private int findPos(AnyType x) {
		int pos = myhash(x);

		while (array[pos] != null && !array[pos].data.equals(x)) {
			pos++;
			if( pos >= array.length )
                pos -= array.length;
			
		}
		return pos;
	}

	public boolean insert(AnyType x) {
		int pos = findPos(x);

		if (isActive(pos))
			return false;

		array[pos] = new HashEntry<>(x, true);
		theSize++;

		if (++occupied > array.length / 2)
			rehash();

		return true;

	}

	private void rehash() {
		HashEntry<AnyType>[] oldArray = array;

		allocateArray(2 * oldArray.length);
		occupied = 0;
		theSize = 0;

		for (HashEntry<AnyType> entry : oldArray)
			if (entry != null && entry.isActive)
				insert(entry.data);
	}

	private boolean isActive(int currentPos) {
		return array[currentPos] != null && array[currentPos].isActive;
	}
	
	 public boolean contains( AnyType x )
	    {
	        int currentPos = findPos( x );
	        return isActive( currentPos );
	    }

	public class HashEntry<AnyType> {
		AnyType data;
		boolean isActive;

		HashEntry(AnyType data, boolean isActive) {
			this.data = data;
			this.isActive = isActive;
		}

	}
	
	public static void main(String[] args){
		MyHashTable hash = new MyHashTable();
		hash.insert(10);
		hash.insert(11);
		hash.insert(20);
		hash.insert(22);
		hash.contains(20);
		hash.contains(22);
	}
		

}
