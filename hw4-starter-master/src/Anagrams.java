import java.util.*;

/**
 * Nadia Martinez
 * 4/22/22
 */
public class Anagrams {
    private List<String> orderedDictionary;
    private Map<String, LetterInventory> inventoryMap;

    /**
     *constructor uses a map which conserves time and space
     */
    public Anagrams(List<String> dictionary) {
        orderedDictionary = new LinkedList<>(dictionary);
        inventoryMap = new HashMap<>();
        for(String s : dictionary) {
            inventoryMap.put(s, new LetterInventory(s));

        }
    }

    /**
     * prints all answers of the anagram with given max
     */
    public void print(String text, int max) {
        explore(new LetterInventory(text), new LinkedList<String>(),
                pruneDictionary(text), max);
    }

    private List<String> pruneDictionary(String text){
        List<String> prunedDict = new LinkedList<>();
        LetterInventory textInventory = new LetterInventory(text);

        for(String word: orderedDictionary){
            if(textInventory.subtract(inventoryMap.get(word)) != null) {
                prunedDict.add(word);
            }
        }
        return prunedDict;
    }

    /**
     * explores Anagram solution given remaining text (remaining) and current chosen words (chosen)
     * stores answers
     * guarantees all answers at most max elements
     */
    private void explore(LetterInventory remaining, List<String> chosen,
                         List<String> prunedDictionary, int max){
        if(remaining.isEmpty()) {
            System.out.println(chosen);
        } else if( max == 0 || chosen.size() < max ){
            for (String s : prunedDictionary) {
                LetterInventory newRemaining = remaining.subtract(inventoryMap.get(s));

                if (newRemaining != null) {
                    chosen.add(s);
                    explore(newRemaining, chosen, prunedDictionary, max);
                    chosen.remove(chosen.size() - 1);
                }
            }
        }
    }
}