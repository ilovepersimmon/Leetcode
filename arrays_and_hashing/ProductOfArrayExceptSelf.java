class Solution {
    // product of array excpet for self
    // include 0? overflow? Otherwise, we can do a product, and then divide self
    //  a b c d 

    // solution 1: prePrd and postPrd
    //    preProduct: 1, a, ab, abc
    //   postProduct: bcd, dc, d, 1
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        int n = nums.length;
        int[] preProduct = new int[n];
        preProduct[0] = 1;
        for (int i = 1; i < n; i++) {
            preProduct[i] = preProduct[i - 1] * nums[i - 1];
        }

        int[] postProduct = new int[n];
        postProduct[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            postProduct[i] = postProduct[i + 1] * nums[i + 1];
        }

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = preProduct[i] * postProduct[i];
        }
        return result;
    }

    // solution 2: use one extra array
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        int n = nums.length;
        int[] preProduct = new int[n];
        preProduct[0] = 1;
        for (int i = 1; i < n; i++) {
            preProduct[i] = preProduct[i - 1] * nums[i - 1];
        }

        int postProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            preProduct[i] *= postProduct;
            postProduct *= nums[i];
        }
        return preProduct;
    }
}
