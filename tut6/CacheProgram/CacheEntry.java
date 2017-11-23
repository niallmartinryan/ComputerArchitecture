import java.util.HashMap;
import java.util.Map;
public class CacheEntry{
	public HashMap<String , CacheValue> entryMap ;					// Might have to change to String 
	public int size;
	public int currentSize;
	public int words;												// words per line
	public CacheEntry(int K, int words){
		entryMap = new HashMap<String, CacheValue>(K);
		size =K;
		currentSize=0;
	}
	// Takes in tag
	public int addEntry(String tag,String offset, String value){					// bad name... for method
		if(entryMap.containsKey(tag)){										// CACHE HIT!!!
				if(entryMap.get(tag).priority==1){
					// do nothing
					System.out.println("HIT on "+ tag );
					return 1;
				}
				else{
					System.out.println("HIT on "+ tag );
					updatePriority(tag);
					return 1;
				}
			}																// check if tag is present
		else if(currentSize == size){										// Cache Miss!!!
			System.out.println("MISS - This set is full, must remove -" + tag);		// only occurs if a miss occurs
			updateValue(tag, value, true);
			updatePriority(tag);
			return 0;
		}
		else{
			System.out.println("MISS - " + tag);
			updateValue(tag,value, false);
			updatePriority(tag);
			return 0;
		}
	}
	// if the cache set is full and the tag isnt there.. evict and change value
	public void updateValue(String tag, String value, boolean full){
		Map.Entry<String, CacheValue> toRemove = null;
		if(full == true){															// means we have to evict as the current set is full
			for(Map.Entry<String, CacheValue> entry : entryMap.entrySet()){
				if(entry.getValue().priority== null){
					
				}
				else{
					if(entry.getValue().priority == size){
						toRemove = entry;
					}
				}
			}
			if(toRemove!=null){
				entryMap.remove(toRemove.getKey());
				System.out.println("removed");
				CacheValue toAdd = new CacheValue(Integer.parseInt(value) , 1, words);			// if its just being added priority gets changed to 1 immedidately
				if(currentSize!=size){
					currentSize += 1;
				}
				entryMap.put(tag, toAdd);
			}	
		}
		else{
			CacheValue toAdd = new CacheValue(Integer.parseInt(value), 1, words);
			entryMap.put(tag, toAdd);
			if(currentSize!=size){
				currentSize += 1;
			}
		}
		// for(){

		// }
	}
	// the parameter is the tag which was recently added or used
	// entryMap.values() returns a Collection of the values
	public void updatePriority(String tagged){
		for(Map.Entry<String, CacheValue> entry : entryMap.entrySet()){
			if(entry== null){
				System.out.println("Null");
			}
			if(entry.getKey()== tagged){
				//System.out.println("New key or accessed Key");				
				entry.getValue().priority = 1; 
			}
			else{
				int pr;	// priority
				//System.out.println(entry.getValue());			// this will return a cacheTag
				if(entry.getValue().priority == size){
					// do nothing
				}
				else{
					pr = 1+entry.getValue().priority;
					entry.getValue().priority = pr;
				}
			}
		}
		return;
	}





}