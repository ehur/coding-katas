package kata.anagrams;

import java.util.Arrays;

class Utils{

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