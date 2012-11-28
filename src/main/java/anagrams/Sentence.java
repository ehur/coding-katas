package anagrams;

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
            char [] sortedOtherChars = Utils.sortChars(other.sentenceWords.toLowerCase().toCharArray());
            if (Utils.sortedCharsAreEqual(sortedOtherChars,  Utils.sortChars(sentenceWords.toLowerCase().toCharArray())))
                return true;
        }
        return false;
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