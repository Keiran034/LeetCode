package day02;

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int lp = 0;
        int rp = 0;
        int ans = nums.length + 1;
        int sum = nums[0];
        while(rp < nums.length){
            if(sum >= target){
                ans = Math.min(ans, rp - lp + 1);
                if(lp == rp){
                    rp ++;
                    if(rp < nums.length){ sum += nums[rp];}
                }
                sum -= nums[lp];
                lp ++;
            }
            else{
                rp ++;
                if(rp < nums.length){
                    sum += nums[rp];
                }
                else{break;}
            }
        }
        if(ans > nums.length){return 0;}
        else{return ans;}
    }
}
