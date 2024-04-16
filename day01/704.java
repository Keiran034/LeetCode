package day01;
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = (left + right) / 2;
        while(left <= right){
            if(nums[mid] == target){return mid;}
            // 解决left+1 = right后无限循环left=mid
            if(left == mid){
                if(nums[right] == target){return right;}
                else{return -1;}
            }
            if(nums[mid] > target){
                right = mid;
            }
            else{
                left = mid;
            }
            mid = (left + right) / 2;
        }
        return -1;
    }
}

class Solution {
    public int search(int[] nums, int target) {
        if(nums.length == 1){
            if(nums[0] == target){return 0;}
            else{return -1;}
        }
        int mid = (0 + (nums.length-1)) / 2;
        if(nums[mid] == target){return mid;}
        if(target<nums[0] || target>nums[nums.length-1]){return -1;}
        int result, residual;
        if(nums[mid] < target){
            result = search(Arrays.copyOfRange(nums, mid+1, nums.length), target);
            residual = mid + 1;
        }
        else{
            result = search(Arrays.copyOfRange(nums, 0, mid), target);
            residual = 0;
        }
        if(result == -1){return -1;}
        else{return result + residual;}
    }
}