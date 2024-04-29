# 代码随想录算法训练营第十二天| 239. 滑动窗口最大值, 347.前 K 个高频元素

## 239. 滑动窗口最大值
[链接](https://leetcode.cn/problems/sliding-window-maximum/)  
用滑动窗口，因为每次窗口滑动元素会一出一进，所以想到用队列queue.  
这道题主要的难点应该在于如何判断出窗口的是否是之前的最大值。不能简单的通过是否相等来判断否则如果窗口中还有等值的数就会出问题。  
我自己写的时候的想法是我会在每一次记录下最大值的时候额外维护一个整数用于记录它的idx，在等值元素进入窗口时就更新这个idx来规避这个问题。但这样就引出了新的问题，如果确定了最大值离开窗口，那么怎么获取现在窗口内的最大值？似乎只能再去遍历窗口，我认为这样会让他的复杂度变成`O(n^2)`，不可接受。  
```
class Solution {
    class MyDeque{
        Deque<Integer> deq = new LinkedList<>();
        void poll(int val){
            if(!deq.isEmpty() && val == deq.peek()){
                deq.poll();
            }
        }
        void add(int val){
            while(!deq.isEmpty() && deq.getLast() < val){
                deq.removeLast();
            }
            deq.add(val);
        }
        int peek(){return deq.peek();}
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length-k+1];
        MyDeque mydeq = new MyDeque();
        for(int i=0; i<k; i++){
            mydeq.add(nums[i]);
        }
        ans[0] = mydeq.peek();
        for(int i=k; i<nums.length; i++){
            mydeq.add(nums[i]);
            mydeq.poll(nums[i-k]);
            ans[i-k+1] = mydeq.peek();
        }
        return ans;
    }
}

```
### 题解
题解的这个Deque首先就用的出乎我的意料，因为没有想到queue还能这样删除尾部元素。  
它的核心是“从出口到入口递减”和“只维护可能成为最大值的元素”，如何实现呢？在新元素进入时，删掉前面所有小于它的元素。因为如果前面的元素小于它，首先这些元素就已经失去了成为窗口内最大值的可能性。

## 347.前 K 个高频元素
[链接](https://leetcode.cn/problems/top-k-frequent-elements/)  
用map记录元素和频次，然后用冒泡排序维护递增数组存储k个频次最高的元素。这样遍历元素时比较它和数组第一位元素的频次，判断是否可以替代掉第一位。
但是这样子在最后两个测试里时间超限了，是冒泡排序还不够？
```
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : nums){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        // 从小到大sort的数组
        int[] ans = new int[k];
        int i=0;
        for(int n : map.keySet()){
            if(i<k){
                ans[i] = n;
                if(i == k-1){
                    ans = sortByMap(map, ans);
                }
            }
            else if(map.get(n) > map.get(ans[0])){
                ans[0] = n;
                ans = sortByMap(map, ans);
            }
            i++;
        }
        return ans;
    }
    private int[] sortByMap(Map<Integer, Integer> map, int[] ans){
        int k = ans.length;
        int temp;
        for(int i=0; i<k-1; i++){
            for(int j=0; j<k-1-i; j++){
                if(map.get(ans[j]) > map.get(ans[j+1])){
                    temp = ans[j];
                    ans[j] = ans[j+1];
                    ans[j+1] = temp;
                }
            }
        }
        return ans;
    }
}
```

### 题解
思路一样，但PriorityQueue是完全没见过完全不会用。

2024.04.29
