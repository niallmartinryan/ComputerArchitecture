import java.util.HashMap;
import java.lang.Math;
public class Cache{
	public int L;				// bytes per line
	public int words;			// words per line
	public int K;				// Number of directories
	public int N;				// Number of Sets
	public int hits;
	public int misses;
	public int attempts;
	//  Going to implement Bit-PLRU for pseudo LRU (Least Recently Used) 
	
	public CacheEntry [] cacheEntries; // The rows in this array are the sets

	public Cache(int L, int K, int N){
		this.L = L;
		this.words = (int) (Math.log(L) / Math.log(2));
		this.K = K;
		this.N = N;
		cacheEntries = new CacheEntry[N];
		for(int i=0; i<N;i++){
			cacheEntries[i] = new CacheEntry(K, words);
		}
		hits =0;
		misses =0;
		attempts =0;
	}

	public int getValue(String value){
		System.out.println("		value - " + value);
		int setNum = getSet(value);
		System.out.println("		set -- "+ setNum);
		String tag, offset;
		if(value.length()<2){
			tag = "0";
			offset = value;
		}
		else{
			tag = value.substring(0,value.length()-1);
			offset = value.substring(value.length()-2, value.length());
		}
		if(cacheEntries[setNum].addEntry(tag, offset, Integer.toString(1 + (int)(Math.random() * 1000)))==1){
			hits +=1;
		}
		else{
			misses +=1;
		}
		attempts +=1;
		return -1;
	}
	public int getNthDigit(int number, int base, int n) {    
  		return (int) ((number / Math.pow(base, n - 1)) % base);
	}
	public int getSet(String value){
		if(value.length()>= 2){
			String res = Character.toString(value.charAt((value.length()-2)));
			return mainCache.hex2Decimal(res)%N;
		}
		else{
			return 0;
		}
	}
	 




}