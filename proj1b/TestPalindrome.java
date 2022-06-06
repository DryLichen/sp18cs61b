import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {

    /** You must use this palindrome, and not instantiate
     new Palindromes, or the autograder might be upset.*/
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String str1 = "abcd";
        String str2 = "abccba";
        String str3 = "AbcbA";

        assertEquals(true, palindrome.isPalindrome(""));
        assertEquals(true, palindrome.isPalindrome("a"));
        assertEquals(false, palindrome.isPalindrome(str1));
        assertEquals(true, palindrome.isPalindrome(str2));
    }

    @Test
    public void testIsPalindromeOffByOne() {
        assertEquals(true, palindrome.isPalindrome("flake", new OffByOne()));
        assertEquals(false, palindrome.isPalindrome("flaKe", new OffByOne()));
        assertEquals(false, palindrome.isPalindrome("aaa", new OffByOne()));
        assertEquals(true, palindrome.isPalindrome("AmlB", new OffByOne()));
    }
}
