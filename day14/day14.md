# 代码随想录算法训练营第十四天| 二叉树的递归遍历

## 二叉树的递归遍历
[链接](https://leetcode.cn/problems/binary-tree-preorder-traversal/)  
[链接](https://leetcode.cn/problems/binary-tree-postorder-traversal/)  
[链接](https://leetcode.cn/problems/binary-tree-inorder-traversal/submissions/528474006/)  
一些基本基本的二叉树操作和它的遍历。
```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> l = new ArrayList<>();
        if(root == null){
            return l;
        }
        List<Integer> ans = traversal(root, l);
        return ans;
    }
    private List<Integer> traversal(TreeNode node, List<Integer> l){
        l.add(node.val); //前序在这
        if(node.left != null){
            traversal(node.left, l);
        }
        l.add(node.val); //中序在这
        if(node.right != null){
            traversal(node.right, l);
        }
        l.add(node.val); //后序在这
        return l;
    }
}
```

## 迭代遍历
[链接](https://leetcode.cn/problems/binary-tree-inorder-traversal/)  
因为编译器在处理递归的时候，实际的原理和stack一样，FILO, 所以我们可以自己用stack来实现遍历。  
中序遍历比前序更难，因为访问的节点和处理的节点不同。  
写的思路是，把所有节点放入过一次stack，pop出来时记录在ArrayList里。  
因为左边最先输出，所以每次当放入一个节点到stack时，把它左侧的所有左子节点依次放入；  
而每次提取出一个节点n时，因为可以保证它左侧的一定比他先出来，所以只需检查它的右侧节点，如果存在，则放入(依据上一行，也会放入n.right左侧的所有左子节点)这样就保证了n的母节点左侧所有元素比他先出来，而右侧比他后出来。
```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> l = new ArrayList<>();
        if(root == null){
            return l;
        }
        Stack<TreeNode> st = new Stack<>();
        st = pushToSt(st, root);
        TreeNode currNode;
        while(!st.isEmpty()){
            currNode = st.pop();
            l.add(currNode.val);
            if(currNode.right != null){
                st = pushToSt(st, currNode.right);
            }
        }
        return l;
    }
    private Stack<TreeNode> pushToSt(Stack<TreeNode> st, TreeNode node){
        st.push(node);
        while(node.left != null){
            node = node.left;
            st.push(node);
        }
        return st;
    }
}
```



2024.0430
