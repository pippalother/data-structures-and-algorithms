
/**
 * A WordGram represents a sequence of strings
 * just as a String represents a sequence of characters
 * 
 * @author Pippa Lother
 *
 */
public class WordGram {
	
	private String[] myWords;   
	private String myToString;  // cached string
	private int myHash;         // cached hash value

	/**
	 * Create WordGram by creating instance variable myWords and copying
	 * size strings from source starting at index start
	 * @param source is array of strings from which copying occurs
	 * @param start starting index in source for strings to be copied
	 * @param size the number of strings copied
	 */
	public WordGram(String[] source, int start, int size) {
		myWords = new String[size];
		for (int i = 0; i<size; i++)
		{
			myWords[i] = source[start+i];
		}
		myToString = String.join(" ", myWords);
		myHash = myToString.hashCode();

		// TODO: initialize all instance variable
	}

	/**
	 * Return string at specific index in this WordGram
	 * @param index in range [0..length() ) for string 
	 * @return string at index
	 */
	public String wordAt(int index) {
		if (index < 0 || index >= myWords.length) {
			throw new IndexOutOfBoundsException("bad index in wordAt "+index);
		}
		return myWords[index];
	}

	/**
	 * Complete this comment
	 * @return
	 */
	public int length(){
		// TODO: change this
		return myWords.length;
	}


	/**
	 * Complete appropriate comment here
	 * @param o
	 * @return
	 */
	@Override
	public boolean equals(Object o) {
		if (! (o instanceof WordGram) || o == null){
			return false;
		}
		// TODO: Complete this method
		WordGram wg = (WordGram) o;
		if(wg.myWords.length == this.myWords.length)
		{
			for (int i = 0; i<wg.myWords.length; i++)
			{
				if(!(wg.myWords[i].equals(this.myWords[i])))
				{
					return false;
				}

			}
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		// TODO: complete this method
		if(myHash==0)
		{
			return this.toString().hashCode();
		}
		return myHash;
	}
	

	/**
	 * Create and complete this comment
	 * @param last is last String of returned WordGram
	 * @return
	 */
	public WordGram shiftAdd(String last) {
		WordGram wg = new WordGram(myWords,0, myWords.length);
		// TODO: Complete this method

		for (int i = 0; i<myWords.length-1; i++)
		{

			wg.myWords[i]=this.myWords[i+1];

		}
		wg.myWords[myWords.length-1] = last;


		return wg;
	}

	@Override
	public String toString(){
		// TODO: Complete this method
		return myToString;
	}
}
