/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int max = Integer.MIN_VALUE;
        //find max length;
        for(String s : asciis) {
            if(s.length() > max) {
                max = s.length();
            }
        }

        String[] sorted = new String[asciis.length];

        for (int i = 0; i < asciis.length; i++) {
            sorted[i] = asciis[i];
        }

        for (int i = max - 1; i >= 0; i-= 1) {
            sorted = sortHelperLSD(sorted, i);
        }
        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static String[] sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int[] counts = new int[256];
        for (String s : asciis) {
            if(s.length() - 1 < index) {
                counts[0] += 1; //treat as 0 if length < index;
            } else {
                counts[s.charAt(index)] += 1;
            }
        }

        int[] starts = new int[256];
        int pos = 0;
        for (int i = 0; i < starts.length; i++) {
            starts[i] = pos;
            pos += counts[i];
        }

        String[] sort = new String[asciis.length];

        for (String i : asciis) {
            int place;
            if(i.length() - 1 < index) {
                place = starts[0];
                starts[0] += 1;
            } else {
                place = starts[i.charAt(index)];
                starts[i.charAt(index)] += 1;
            }
            sort[place] = i;
        }


        return sort;
    }


    public static void main(String[] args) {
        String[] test = new String[]{"fat", "fed", "fak",
                "cat", "sit", "sat", "sad", "zac", "zas"};
        String[] str1 = sort(test);
        for (String s : str1) {
            System.out.println(s);
        }
    }
}
