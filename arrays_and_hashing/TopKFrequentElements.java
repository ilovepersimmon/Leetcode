class Solution {
    /***
        given an array nums and an integer k, return the k most frequent elements. can be returned in any order
        
        most frequent, hash map to get freq of each element
        most frequent k, sort by freq or some other way (heap or quick select)

        hash map to get freq, O(n) time and space
        solution 1: sort, O(nlogn)
        solution 2: min heap of size k (kick out the less freq one), O(nlogk) time
        solution 3: quick select, O(n) time
     */
    
    Map<Integer, Integer> numFreq = new HashMap<>();

    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        // tally the frequency
        for (int num : nums) {
            numFreq.put(num, numFreq.getOrDefault(num, 0) + 1);
        }

        int n = numFreq.size();
        int[] uniqNums = new int[n];
        int i = 0;
        for (int num : numFreq.keySet()) {
            uniqNums[i++] = num;
        }

        // shuffle
        shuffle(uniqNums);
        // top k freq, top (n-k) infreq
        quickSelect(uniqNums, n - k);
        // copy from (n - k) to n
        return Arrays.copyOfRange(uniqNums, n - k, n);
    }

    private void quickSelect(int[] nums, int k) {
        int lo = 0, hi = nums.length - 1;

        while (lo < hi) {
            int mid = partition(nums, lo, hi);

            if (mid < k) {
                lo = mid + 1;
            } else if (mid > k) {
                hi = mid - 1;
            } else {
                return;
            }
        }
    }

    private int partition(int[] nums, int lo, int hi) {
        int i = lo, j = hi + 1;
        int pivotIdx = lo;

        while (true) {
            while (i < hi && less(++i, pivotIdx, nums));
            while (j > lo && less(pivotIdx, --j, nums));
            if (i >= j) {
                break;
            }
            exch(nums, i, j);
        }
        exch(nums, lo, j);
        return j;
    }

    private boolean less(int i, int j, int[] nums) {
        return numFreq.get(nums[i]) < numFreq.get(nums[j]);
    }

    private void exch(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void shuffle(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            int randIdx = (int) (Math.random() * (i + 1));
            exch(nums, i, randIdx);
        }
    }
}
