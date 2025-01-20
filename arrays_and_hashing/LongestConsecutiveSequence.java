class Solution {
    /**
        128. Longest Consecutive Sequence
        longest consecutive sequence, require O(n) time
        put all nums in a set, and check if num is start (if (num-1) is in set, it's start)
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            seen.add(num);
        }

        int longest = 0;
        for (int num : seen) {
            if (!seen.contains(num - 1)) {
                // is start
                int next = num;
                int currLen = 0;
                // each num is part of a sequence only once
                while (seen.contains(next)) {
                    next++;
                    currLen++;
                }
                longest = Math.max(longest, currLen);
            }
        }
        return longest;
    }
}
