package anagrams;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AnagramTest {

    @Test
    public void twoWordsAreAnagrams(){
        assertTrue(new Sentence("arrest").isAnagramOf(new Sentence("rarest")));
        assertTrue(new Sentence("fresher").isAnagramOf(new Sentence("refresh")));
    }


    @Test
    public void twoWordsAreNotAnagrams(){
        assertFalse(new Sentence("fish").isAnagramOf(new Sentence("frog")));
        assertFalse(new Sentence("bag").isAnagramOf(new Sentence("handlebars")));
    }

    @Test
    public void twoSentencesAreAnagrams(){
        assertTrue(new Sentence("able was I").isAnagramOf(new Sentence("i saw Elba")));
    }

    @Test
    public void twoSentencesAreNotAnagrams(){
        assertFalse(new Sentence("I am a fish").isAnagramOf(new Sentence("I am not a fish")));
    }




    class Sentence{
        Boolean isAnagramOf(Sentence other){

        }
    }
}
