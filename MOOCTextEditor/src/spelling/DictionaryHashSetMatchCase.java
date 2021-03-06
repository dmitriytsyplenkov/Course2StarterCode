/**
 * 
 */
package spelling;

import java.util.HashSet;


/**
 * A class that implements the Dictionary interface with a HashSet
 */
public class DictionaryHashSetMatchCase implements Dictionary
{

    private HashSet<String> words;

	public DictionaryHashSetMatchCase()
	{
	    words = new HashSet<String>();
	}
	
    /** Add this word to the dictionary.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
	@Override
	public boolean addWord(String word) 
	{
		return words.add(word);
	}

	/** Return the number of words in the dictionary */
    @Override
	public int size()
	{
    	 return words.size();
	}
	
	/** Is this a word according to this dictionary? */
    @Override
	public boolean isWord(String s) {
		if (s.equals(s.toUpperCase())) {
			return words.contains(s.toLowerCase()) || words.contains(s.substring(0,1)+s.toLowerCase().substring(1));
		}
    	return words.contains(s) ||  words.contains(s.toLowerCase().substring(0,1)+s.substring(1));
	}
	
   
}
