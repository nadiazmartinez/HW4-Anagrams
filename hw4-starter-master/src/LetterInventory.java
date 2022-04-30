//Nadia Martinez

public class LetterInventory {

    private static final int ALPHABET_LENGTH = 26;

    private int[] counters;
    int totalCount;

    /**
     * add comments
     */
    LetterInventory() {
        /* Allocate space for the array */
        counters = new int[ALPHABET_LENGTH];
        /* Iterate over array with ForEach loop to assign 0's */
        for(int i:counters) {
            i = 0;
        }
        totalCount = 0;
    }

    /**
     * add comments
     */
    LetterInventory(String input) {
        /* Setup array */
        this();
        /* Iterate over the input String to allocate the appropriate counters */
        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            /* Consider only letter characters */
            if (Character.isLetter(c)) {
                c = Character.toLowerCase(c);
                /* convenient way to get indices from character values */
                counters[getIndex(c)]++;
                totalCount++;
            }
        }
    }

    /**
     * add comments
     */
    public int get(char c) {
        if(!Character.isLetter(c)) {
            throw new IllegalArgumentException("LetterInventory.get() can not accept characters outside of the alphabet.");
        }
        return counters[getIndex(c)];
    }

    /**
     * add comments
     */
    public void set(char c, int count) {
        if(!Character.isLetter(c)) {
            throw new IllegalArgumentException("LetterInventory.set() can not accept characters outside of the alphabet.");
        } else if (count < 0) {
            throw new IllegalArgumentException("Can not accept negative values.");
        }
        /* Modify the total count by delta of the counter of the letter */
        totalCount += count - counters[getIndex(c)];
        counters[getIndex(c)] = count;
    }

    /**
     * add ccomments
     */
    public int size() {
        return totalCount;
    }

    /**
     * add comments
     */
    public boolean isEmpty() {
        return totalCount == 0;
    }

    public String toString() {
        String buffer = "[";
        for(int i = 0; i < ALPHABET_LENGTH; i++) {
            for(int j = 0; j < counters[i]; j++) {
                buffer += (char)('a' + i);
            }
        }
        return buffer + ']';
    }

    /**
     * add comments
     */
    public LetterInventory add(LetterInventory other) {
        LetterInventory toReturn = new LetterInventory();
        for(int i = 0; i < ALPHABET_LENGTH; i++) {
            toReturn.counters[i] = counters[i] + other.counters[i];
        }
        toReturn.totalCount = totalCount + other.totalCount;
        return toReturn;
    }

    /**
     * add comments
     */
    public LetterInventory subtract(LetterInventory other) {
        LetterInventory toReturn = new LetterInventory();
        for(int i = 0; i < ALPHABET_LENGTH; i++) {
            if(counters[i] - other.counters[i] < 0) {
                return null;
            } else {
                toReturn.counters[i] = counters[i] - other.counters[i];
            }
        }
        toReturn.totalCount = totalCount - other.totalCount;
        return toReturn;
    }

    /**
     * add comments
     */
    public double getLetterPercentage(char c) {
        return get(c) / (double)totalCount;
    }

    /**
     * add comments
     */
    private static int getIndex(char c) {
        c = Character.toLowerCase(c);
        return (int)c - (int)'a';
    }
}