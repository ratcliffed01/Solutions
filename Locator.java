// to compile C:\JobTests\yospace\locator>javac -cp ../ Locator.java

//Please write a concrete Java implementation of the Locator interface detailed below.
//Your class, LocatorSolution, must find the index of an item efficiently from within a sorted array of candidates. 
//The implementation must be written from first principles, and must not make use of classes not explicitly mentioned in 
//the interface definition. The solution should adhere to the specification below when supplied with reasonable inputs and 
//must provide a no-throw guarantee.
/**
 * Locator interface definition
 */

package locator;

/**
 * Implementers can identify the index of a String efficiently within a sorted array of candidates.
 */
public interface Locator
{

    /**
     * @param itemSought
     *        the item for which the index is sought
     * @param candidates
     *        a sorted array of items, from within which the itemSought must be found
     * @return the zero based index of the itemSought within the array of candidates. In the event that
     *        the itemSought is not found in the candidates array an index of -1 is returned.
     */
    public int getIndex(String itemSought, String[] candidates);

}

