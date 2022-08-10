import java.util.*;

public class EfficientMarkov extends BaseMarkov {

	//variables

	private Map<String,ArrayList<String>> myMap;

	//initialize variables in constructor
	public EfficientMarkov(int order) {
		super(order);
		myMap= new HashMap<>();
	}

	/**
	 * Default constructor has order 3
	 */
	public EfficientMarkov() {
		this(3);
	}

	@Override
	public void setTraining(String text) {

		super.setTraining(text);

		//clears map each time method is called
		myMap.clear();

		int index = 0;  // location where search for key in text starts

		//empty String to put at end of Array
		String PSEUDO_EOS = "";

		for (int i = 0; i < myText.length() - myOrder + 1; i++) {

			//Initialize new ArrayList each time
			ArrayList<String> follows = new ArrayList<String>();

			//Find substring
			String keys = myText.substring(index, index + myOrder);

			//puts every key into array
			myMap.putIfAbsent(keys,follows);

			//put empty String when loop gets to end of the array
			if(index + myOrder == myText.length()){
				myMap.get(keys).add(PSEUDO_EOS);
				break;
			}


			//If keys already has a corresponding ArrayList, add new value found to ArrayList
			else {
				myMap.get(keys).add(myText.substring(index + myOrder, index + myOrder + 1));
			}
			index++;
		}
	}

	@Override
	public ArrayList<String> getFollows(String key){

			//return if not found
			if(!myMap.containsKey(key)){
				throw new NoSuchElementException(key + " not in map");}

			else{
			return myMap.get(key);}

		}

	}


