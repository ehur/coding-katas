package anagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Sentence {

    String m_words;
    List<Character> stringList = new ArrayList<Character>();

    Sentence(String words) {

        m_words = words.toLowerCase();
        for (char c : m_words.toCharArray()) {
            if(c != " ".charAt(0)) {

                stringList.add(c);
            }
        }

    }

    Boolean isAnagramOf(Sentence other) {
        List<Character> temp = other.stringList;

        if (temp.size() == stringList.size()) {


            for (char c : stringList) {
                for (char d : other.stringList) {
                    if (c == d) {

                        temp.remove(temp.indexOf(d));
                        break;

                    }

                }

            }

            if (temp.isEmpty()) {
                return true;
            }
        }
        return false;
    }

}
