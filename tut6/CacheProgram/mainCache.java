import java.util.HashMap;
import java.util.Map;
public class mainCache{


	static Integer [] testValues = {					// may have to change how this works.
		0,4,8,
		
	};
	static Long [] hexValues = {
		0x0000L, 0x0004L, 0x000CL, 0x2200L, 0x00d0L, 0x00e0L, 0x1130L,
		0x0028L, 0x113cL, 0x2204L, 0x0010L, 0x0020L, 0x0004L, 0x0040L, 0x2208L,
		0x0008L, 0x00a0L, 0x0004L, 0x1104L, 0x0028L, 0x000cL, 0x0084L, 0x000cL, 
		0x3390L, 0x00b0L, 0x1100L, 0x0028L, 0x0064L, 0x0070L, 0x00d0L, 0x0008L,
		0x3394L
	};


	public static void main(String [] args){
		// System.out.println("hex for 0x000c = "+ hexValues[2]);
		Integer j = (int) (long) hexValues[2];
		// System.out.println("hex for 0x000c = "+ decimal2hex(j));
		Cache cache = new Cache(16, 4, 2); //
		for(int i=0;i<hexValues.length;i++){
			cache.getValue(decimal2hex((int) (long) hexValues[i]));
			//printEntries(cache);
		}
		System.out.println("\nHits = "+ cache.hits);
		System.out.println("Misses = "+ cache.misses);
		System.out.println("Attempts = "+ cache.attempts);
	}
	public static String decimal2hex(int d) {
    String digits = "0123456789ABCDEF";
    if (d <= 0) return "0";
    int base = 16;   // flexible to change in any base under 16
    String hex = "";
    while (d > 0) {
        int digit = d % base;              // rightmost digit
        hex = digits.charAt(digit) + hex;  // string concatenation
        d = d / base;
    }
    return hex;
	}
	public static int hex2Decimal(String s){
		String digits = "0123456789ABCDEF";
    	s = s.toUpperCase();
    	int val = 0;
    	for (int i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
        	int d = digits.indexOf(c);
        	val = 16*val + d;
     	}
     	return val;
	}
	// public static void printEntries(Cache cache){
	// 	for(int i=0;i<cache.cacheEntries.length;i++){
	// 		System.out.println("i = " +i);
	// 		HashMap<String, CacheValue> map = cache.cacheEntries[i].entryMap;
	// 			for (Map.Entry<String,CacheValue> entry : map.entrySet()) {
 //  					String key = entry.getKey();
	//   				System.out.print(" - " + key);
	//   				// do stuff
	// 			}
	// 			System.out.println(" ");
	// 	}
		
	// }


}