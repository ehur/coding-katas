package anagrams;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AnagramTest {

    @Test
    public void twoWordsAreAnagrams_wordAnagrams(){
        assertTrue(new Sentence("frog").isAnagramOf(new Sentence("frog")), "Words are the same");
        assertTrue(new Sentence("arrest").isAnagramOf(new Sentence("rarest")), "Words are anagrams");
        assertTrue(new Sentence("Fresher").isAnagramOf(new Sentence("Refresh")), "Words are anagrams, uppercase letters");;
    }

    @Test
    public void twoWordsAreNotAnagrams(){
        assertFalse(new Sentence("bag").isAnagramOf(new Sentence("handlebars")), "Not anagrams and different lengths");
        assertFalse(new Sentence("fish").isAnagramOf(new Sentence("frog")), "Not anagrams but same length");
    }

    @Test
    public void twoSentencesAreAnagrams(){
        assertTrue(new Sentence("able was I").isAnagramOf(new Sentence("i saw Elba")), "Sentences are anagrams");
    }

    @Test
    public void twoSentencesAreNotAnagrams(){
        assertFalse(new Sentence("I am a fish").isAnagramOf(new Sentence("I am not a fish")), "Sentences are not anagrams");
    }

}
