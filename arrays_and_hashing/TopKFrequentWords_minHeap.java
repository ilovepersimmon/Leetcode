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

        // solution 2: use a heap of size k, kick out the smaller freq one, or lexi greater one

        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> (
            wordFreq.get(a) != wordFreq.get(b) ? Integer.compare(wordFreq.get(a), wordFreq.get(b)) : b.compareTo(a)
        ));
        for (String key : wordFreq.keySet()) {
            pq.offer(key);

            // if size > k, kick out the less freq one, or the same freq but lexi greater one
            if (pq.size() > k) {
                pq.poll();
            }
        }

        List<String> result = new LinkedList<>();
        while (!pq.isEmpty()) {
            result.addFirst(pq.poll());
        }
        return result;
    }
}
