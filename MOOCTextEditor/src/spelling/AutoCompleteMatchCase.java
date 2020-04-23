package spelling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 *
 * @author You
 */
public class AutoCompleteMatchCase implements Dictionary, AutoComplete {

    private TrieNode root;
    private int size;


    public AutoCompleteMatchCase() {
        root = new TrieNode();
    }


    /**
     * Insert a word into the trie.
     * For the basic part of the assignment (part 2), you should convert the
     * string to all lower case before you insert it.
     * <p>
     * This method adds a word by creating and linking the necessary trie nodes
     * into the trie, as described outlined in the videos for this week. It
     * should appropriately use existing nodes in the trie, only creating new
     * nodes when necessary. E.g. If the word "no" is already in the trie,
     * then adding the word "now" would add only one additional node
     * (for the 'w').
     *
     * @return true if the word was successfully added or false if it already exists
     * in the dictionary.
     */
    public boolean addWord(String word) {
        char[] wordLetters = word.toCharArray();
        TrieNode curNode = root;
        for (int i = 0; i < wordLetters.length; i++) {
            TrieNode children = curNode.insert(wordLetters[i]);
            if (children == null) {
                children = curNode.getChild(wordLetters[i]);
            }
            curNode = children;
        }
        if (curNode.endsWord()) {
            return false;
        }
        curNode.setEndsWord(true);
        return true;

    }

    /**
     * Return the number of words in the dictionary.  This is NOT necessarily the same
     * as the number of TrieNodes in the trie.
     */
    public int size() {
        return recursiveCount(root);
    }

    private int recursiveCount(TrieNode trieNode) {
        if (trieNode == null) {
            return 0;
        }
        int numOfWords = 0;
        if (trieNode.endsWord()) {
            numOfWords++;
        }
        for (Character letter : trieNode.getValidNextCharacters()) {
            numOfWords += recursiveCount(trieNode.getChild(letter));
        }
        return numOfWords;
    }


    /**
     * Returns whether the string is a word in the trie, using the algorithm
     * described in the videos for this week.
     */
    @Override
    public boolean isWord(String s) {
        char[] wordLetters = s.toCharArray();
        TrieNode curNode = root;
        for (int i = 0; i < wordLetters.length; i++) {
            curNode = curNode.getChild(wordLetters[i]);
            if (curNode == null) {
                return false;
            }
        }
        return curNode.endsWord();
    }

    /**
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions
     * of the prefix string. All legal completions must be valid words in the
     * dictionary. If the prefix itself is a valid word, it is included
     * in the list of returned words.
     * <p>
     * The list of completions must contain
     * all of the shortest completions, but when there are ties, it may break
     * them in any order. For example, if there the prefix string is "ste" and
     * only the words "step", "stem", "stew", "steer" and "steep" are in the
     * dictionary, when the user asks for 4 completions, the list must include
     * "step", "stem" and "stew", but may include either the word
     * "steer" or "steep".
     * <p>
     * If this string prefix is not in the trie, it returns an empty list.
     *
     * @param prefix         The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */
    @Override
    public List<String> predictCompletions(String prefix, int numCompletions) {
        // TODO: Implement this method
        // This method should implement the following algorithm:
        // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
        //    empty list
        // 2. Once the stem is found, perform a breadth first search to generate completions
        //    using the following algorithm:
        //    Create a queue (LinkedList) and add the node that completes the stem to the back
        //       of the list.
        //    Create a list of completions to return (initially empty)
        //    While the queue is not empty and you don't have enough completions:
        //       remove the first Node from the queue
        //       If it is a word, add it to the completions list
        //       Add all of its child nodes to the back of the queue
        // Return the list of completions
		List<String> completions = new ArrayList<String>();
		TrieNode curNode = root;
        boolean allCaps = false;
        boolean firstIsCap = false;
		if (!prefix.equals("")) {

            if (prefix.equals(prefix.toUpperCase())) {
                allCaps = true;
                prefix = prefix.toUpperCase().substring(0,1)+prefix.toLowerCase().substring(1);
            }else if (prefix.equals(prefix.toUpperCase().substring(0,1)+prefix.toLowerCase().substring(1))) {
                firstIsCap = true;
            }
            char[] wordLetters = prefix.toCharArray();

			for (int i = 0; i < wordLetters.length; i++) {
				curNode = curNode.getChild(wordLetters[i]);
				if (curNode == null) {
					break;
				}
			}

            if (curNode == null ) {
                if (allCaps) {
                    prefix = prefix.toLowerCase();
                } else if (firstIsCap) {
                    prefix = prefix.toLowerCase();
                } else {
                    return completions;
                }
                curNode = root;
                for (int i = 0; i < wordLetters.length; i++) {
                    curNode = curNode.getChild(wordLetters[i]);
                    if (curNode == null) {
                        return  completions;
                    }
                }
            }
		}


        LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
        queue.add(curNode);
        while (!queue.isEmpty() && numCompletions > 0) {
            curNode = queue.remove();

            if (curNode.endsWord()) {
                String curText = curNode.getText();
                if (allCaps) {
                    completions.add(curText.toUpperCase());
                } else if (firstIsCap) {
                    completions.add(curText.toUpperCase().substring(0,1)+curText.substring(1));
                } else {
                    completions.add(curText);
                }

                numCompletions--;
            }
            for (Character character : curNode.getValidNextCharacters()) {
                queue.add(curNode.getChild(character));
            }
        }
        return completions;
    }

    // For debugging
    public void printTree() {
        printNode(root);
    }

    /**
     * Do a pre-order traversal from this node down
     */
    public void printNode(TrieNode curr) {
        if (curr == null)
            return;

        System.out.println(curr.getText());

        TrieNode next = null;
        for (Character c : curr.getValidNextCharacters()) {
            next = curr.getChild(c);
            printNode(next);
        }
    }


}