package anagrams;

import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AnagramTest {

    @Test
    public void twoWordsAreAnagrams(){
        assertTrue("Anagram words", new Sentence("arrest").isAnagramOf(new Sentence("rarest")));
    }

    @Test
    public void anagramsAreCaseInsensitive(){
        assertTrue("Anagram words with uppercase letters", new Sentence("Fresher").isAnagramOf(new Sentence("Refresh")));
    }

    @Test
    public void twoWordsOfDifferentLengthsAreNotAnagrams(){
        assertFalse("Not anagram and different length", new Sentence("bag").isAnagramOf(new Sentence("handlebars")));
    }

    @Test
    public void twoWordsOfSameLengthsAreNotAnagrams(){
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

    @Test
    public void spacesAreIgnored() {
        assertTrue("Sentences are anagrams", new Sentence("a leb was I").isAnagramOf(new Sentence("i saw Elba")));
    }

}
