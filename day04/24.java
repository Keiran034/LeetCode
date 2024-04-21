package day04;

class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){return head;}
        ListNode node1 = head;
        ListNode node2 = node1.next;
        ListNode next = node2.next;
        ListNode prev = head;
        
        node1.next = next;
        node2.next = node1;
        head = node2;
        while(next != null){
            prev = node1;
            node1 = next;
            node2 = node1.next;
            if(node2 == null){return head;}
            next = node2.next;

            node1.next = next;
            node2.next = node1;
            prev.next = node2;
        }
        return head;
    }
}