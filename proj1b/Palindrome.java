public class Palindrome {

    public Deque<Character> wordToDeque(String word) {

        Deque <Character> dq = new ArrayDeque<>();

        for(int i = 0; i < word.length(); i++) {
            //inserts the ith item of word into Deque
            dq.addLast(word.charAt(i));
        }
        return dq;
    }


    public boolean isPalindrome(String word) {
        Deque<Character> dq = wordToDeque(word);

        boolean result = isPalindromeHelper(dq);

        return result;
    }

    private boolean isPalindromeHelper(Deque<Character> dq){
        if(dq.size() == 1 || dq.size() == 0 ){
            return true;
        } else{
            //removeFirst & removeLast returns the the item type
            //Compare if they're equal then move to the next Last/First item
            if(dq.removeFirst() == dq.removeLast()) {
                return isPalindromeHelper(dq);
            }
            else{
                return false;
            }
        }
    } //isPalidromeHelper


    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> dq = wordToDeque(word);

        boolean result = isPalindromeHelper(dq, cc);

        return result;
    }

    private boolean isPalindromeHelper(Deque<Character> dq, CharacterComparator cc){
        if(dq.size() == 1 || dq.size() == 0 ) {
            return true;
        }
        else{
            if(cc.equalChars(dq.removeFirst(), dq.removeLast())){
                return isPalindromeHelper(dq, cc);
            }
            else{
                return false;
            }
        }
    }


    }

