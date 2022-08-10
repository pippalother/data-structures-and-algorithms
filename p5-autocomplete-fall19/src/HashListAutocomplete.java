import java.util.*;

/**
 * Pippa Lother
 * 11/18/2019
 * Autocomplete class using HashList for O(1) runtime
 */
public class HashListAutocomplete implements Autocompletor {
    private static final int MAX_PREFIX = 10;
    private Map<String, List<Term>> myMap;
    private int mySize;

    /**
     * @param terms   terms is the string that is given
     * @param weights is the array of double
     */
    public HashListAutocomplete(String[] terms, double[] weights) {
        if (terms == null) {
            throw new NullPointerException("terms is null");
        }
        if (weights == null) {
            throw new NullPointerException("terms is null");
        }
        initialize(terms, weights);
    }

    /**
     * @param prefix the string being used
     * @param k      integer used in determining arguments
     * @return
     */
    @Override
    public List<Term> topMatches(String prefix, int k) {


        if (!(myMap.containsKey(prefix))) {
            return new ArrayList<Term>();
        }

        if (prefix.length() > MAX_PREFIX) {
            prefix = prefix.substring(0, MAX_PREFIX);
        }

        List<Term> first = myMap.get(prefix);
        List<Term> second = first.subList(0, Math.min(k, first.size()));

        return second;


    }

    /**
     * @param terms   is array of Strings for words in each Term
     * @param weights is corresponding weight for word in terms
     */
    @Override
    public void initialize(String[] terms, double[] weights) {

        myMap = new HashMap<String, List<Term>>();

        for (int i = 0; i < terms.length; i++) {
            String first = terms[i];

            for (int j = 0; j < Math.min(MAX_PREFIX, first.length()) + 1; j++) {
                if (first.length() >= j) {

                    String second = first.substring(0, j);

                    Term weighted = new Term(terms[i], weights[i]);

                    if (!myMap.containsKey(second)) {
                        myMap.put(second, new ArrayList<Term>());
                    }
                    myMap.get(second).add(weighted);
                }
            }
        }
        Comparator<Term> reverse = Comparator.comparing(Term::getWeight).reversed();
        for (String key : myMap.keySet()) {
            Collections.sort(myMap.get(key), reverse);
        }
    }

    /**
     * @return the int of mySize
     */
    @Override
    public int sizeInBytes() {
        if (mySize == 0) {
            Set<String> keys = myMap.keySet();

            for (String x : keys) {

                mySize += BYTES_PER_CHAR * x.length();

                for (Term term : myMap.get(x)) {

                    mySize += BYTES_PER_DOUBLE + BYTES_PER_CHAR * term.getWord().length();
                }
            }
        }
        return mySize;

    }
}