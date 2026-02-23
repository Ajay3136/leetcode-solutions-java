package org.example.String;

import java.util.*;

public class FindSubstring {

    static List<Integer> findSubstring(String s, String[] words) {
        int wordlen = words[0].length();
        int totalWords = words.length;
        int totalLen = wordlen*totalWords;
        List<Integer> res = new ArrayList<>();

        Map<String, Integer> wordMap = new HashMap<>();
        for (String word: words) {
            wordMap.put(word, wordMap.getOrDefault(word , 0)+1);
        }

        for (int i = 0; i < wordlen; i++) {
            int left = i;
            int count = 0;
            Map<String, Integer> windowMap = new HashMap<>();
            for(int right = i; right+wordlen <= s.length(); right+=wordlen) {
                String sub = s.substring(right, right+wordlen);
                if(wordMap.containsKey(sub)) {
                    windowMap.put(sub, windowMap.getOrDefault(sub, 0) +1);
                    count++;

                    while(windowMap.get(sub) > wordMap.get(sub)) {
                        String leftWord = s.substring(left, left+wordlen);
                        windowMap.put(leftWord, windowMap.get(leftWord) - 1);
                        left += wordlen;
                        count--;
                    }

                    if(count == totalWords) {
                        res.add(left);
                    }

                } else {
                    windowMap.clear();
                    count = 0;
                    left = right+wordlen;
                }
            }
        }
        return res;

    }

    public static void main(String[] args) {
        String s = "wordgoodgoodgoodbestword";
        String[] words = {"word","good","best","good"};
        System.out.println(findSubstring(s, words));
    }

}
