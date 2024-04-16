package day01;
class Solution {
    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        int i = 0;
        while(i < len){
            if(nums[i] == val){
                for(int j=i; j<len-1; j++){
                    nums[j] = nums[j+1];
                }
                len --;
            }
            else{i++;}
        }
        return len;
    }
}