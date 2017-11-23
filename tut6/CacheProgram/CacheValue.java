public class CacheValue{
	
	Integer [] values;						// Might have to change this to a String too :/ or use hex somehow
	Integer priority;					
	public CacheValue(Integer value, Integer priority, int words){
		this.values = new Integer[words];										// might have to clone the array :/
		Integer temp = value;
		for(int i=0;i<words;i++){
			values[i] = temp; 
			temp += 4;
		}
		this.priority = priority; 
	}
}