package anagrams;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AnagramTest {

    @Test
    public void twoWordsAreAnagrams(){
        assertTrue(new Sentence("same").isAnagramOf(new Sentence("same")));
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

        private final String sentenceWords;

        public Sentence(String arrest) {
            sentenceWords = arrest;
        }

        Boolean isAnagramOf(Sentence other){
            if (other.equals(this) )
                return true;
            else
            {
                char [] sortedOtherChars = sortedArray(other.sentenceWords.toLowerCase());
                if ( arraysAreEquivalent(sortedOtherChars, sortedArray(sentenceWords.toLowerCase()))    )
                    return true;
            }
            return false;
        }

        private boolean arraysAreEquivalent(char[] first, char[] second) {
            for (int i = 0; i < first.length; i ++) {
                if (first[i] != second[i] ) return false;
            }
            return true;
        }

        private char[] sortedArray(String input){
            char [] sorted = input.toCharArray();
            Arrays.sort(sorted);
            return sorted;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Sentence sentence = (Sentence) o;

            if (sentenceWords != null ? !sentenceWords.equals(sentence.sentenceWords) : sentence.sentenceWords != null)
                return false;

            return true;
        }

        @Override
        public int hashCode() {
            return sentenceWords != null ? sentenceWords.hashCode() : 0;
        }
    }

    class Utils{

        public char[] sortChars(char[] chars){
            Arrays.sort(chars);
            return chars;
        }

        public Boolean sortedCharsAreEqual(char[] first, char[] second){
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
