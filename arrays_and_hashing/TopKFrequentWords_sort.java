class Solution {
    // top k frequent words, return an answer sorted by freq from 
    //   highest to lowest, words with same freq by lexi order
    // return order sorted --> sort or heap solution
    //   solution 1: sort, nlogn, and then return from k (customized sort)
    //   solution 2: heap of size k

    public List<String> topKFrequent(String[] words, int k) {
        // O(n) for tally freq
        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }

        // solution 1: sort
        // get the unique words
        String[] uniqWords = new String[wordFreq.size()];
        int i = 0;
        for (String key : wordFreq.keySet()) {
            uniqWords[i++] = key;
        }
        // sort by freq descending, if same freq, smaller one first
        Arrays.sort(uniqWords, (a, b) -> (wordFreq.get(a) == wordFreq.get(b) ? a.compareTo(b) : wordFreq.get(b) - wordFreq.get(a)));

        List<String> result = new LinkedList<>();
        for (int j = 0; j < k; j++) {
            result.addLast(uniqWords[j]);
        }
        return result;
    }
}
