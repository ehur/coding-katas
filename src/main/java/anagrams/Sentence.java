package anagrams;


import java.util.HashMap;

class Sentence{
    private final String words;
    private final Integer hash;
    private static HashMap<Character, Integer> lettersToHash = makeLettersToHashes();

    public Sentence(String words){
        this.words = words.toLowerCase().replace(" ", "");
        hash = getHash(this.words);
    }
    Boolean isAnagramOf(Sentence other){
        return hash.equals(other.hash);
    }

    private static Integer getHash(String sentence){
        Integer hash = 1;
        for(char letter:sentence.toCharArray()){
            Integer letterHash = lettersToHash.get(letter);
            hash *= letterHash;
        }
        return hash;
    }

    private static HashMap<Character,Integer> makeLettersToHashes(){
        HashMap<Character,Integer> lettersToHash = new HashMap<Character, Integer>();
        for(int i=97;i<=122;i++){
            lettersToHash.put((char)i, i);
        }
        return lettersToHash;
    }
}
