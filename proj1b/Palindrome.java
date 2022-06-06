public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        if (word == "") {
            return deque;
        }

        String[] strings = word.split("");
        for (String string : strings) {
            deque.addLast(string.charAt(0));
        }

        return deque;
    }

    /** decide whether a word is a palindrome */
    public boolean isPalindrome(String word) {
        if (word == "" || word.length() == 1) {
            return true;
        }

        Deque<Character> deque = wordToDeque(word);
        return isPalindrome(deque, deque.size());
    }
    /** helper method for isPalindrome */
    private boolean isPalindrome(Deque<Character> deque, int size) {
        if (size < 2) {
            return true;
        }
        if (deque.removeLast() == deque.removeFirst()) {
            return isPalindrome(deque, size - 2);
        }

        return false;
    }

    /** offByOne palindrome */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == "" || word.length() == 1) {
            return true;
        }

        Deque<Character> deque = wordToDeque(word);
        int size = deque.size();
        for (int i = size; i > 1; i -= 2) {
            if (!cc.equalChars(deque.removeFirst(), deque.removeLast())) {
                return false;
            }
        }

        return true;
    }

}
