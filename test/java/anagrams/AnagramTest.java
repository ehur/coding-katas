package anagrams;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

package anagrams;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AnagramTest {

    @Test
    public void twoWordsAreAnagrams_wordAnagrams(){
        assertTrue("Same words", new Sentence("frog").isAnagramOf(new Sentence("frog")));
        assertTrue("Anagram words", new Sentence("arrest").isAnagramOf(new Sentence("rarest")));
        assertTrue("Anagram words with uppercase letters", new Sentence("Fresher").isAnagramOf(new Sentence("Refresh")));
    }

    @Test
    public void twoWordsAreNotAnagrams(){
        assertFalse("Not anagram and different length", new Sentence("bag").isAnagramOf(new Sentence("handlebars")));
        assertFalse("Not anagram but same length", new Sentence("fish").isAnagramOf(new Sentence("frog")));
    }

    @Test
    public void twoSentencesAreAnagrams(){
        assertTrue("Sentences are anagrams", new Sentence("able was I").isAnagramOf(new Sentence("i saw Elba")));
    }

    @Test
    public void twoSentencesAreNotAnagrams(){
        assertFalse("Sentences are not anagrams", new Sentence("I am a fish").isAnagramOf(new Sentence("I am not a fish")));
    }



    static class Utils{

        public static char[] sortChars(char[] chars){
            Arrays.sort(chars);
            return chars;
        }

        public static Boolean sortedCharsAreEqual(char[] first, char[] second){
            if(first.length != second.length){
                return false;
            }
            for(int i=0;i<first.length;i++){
                if(first[i] != second[i]) return false;
            }
            return true;
        }
    }
}
