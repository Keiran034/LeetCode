package day02;

class Solution {
    public int[] sortedSquares(int[] nums) {
        int right = 0;
        while(right < nums.length-1 && nums[right] < 0){
            right ++;
        }
        int left = right - 1;
        int[] new_nums = new int[nums.length];
        for(int i=0; i<nums.length; i++){
            System.out.print(left);
            System.out.print(",");
            System.out.println(right);
            if(left >= 0 && (right == nums.length || nums[left]*nums[left] <= nums[right]*nums[right])){
                new_nums[i] = nums[left]*nums[left];
                left --;
            }
            else{
                new_nums[i] = nums[right]*nums[right];
                right++;
            }
        }
        return new_nums;
    }
}