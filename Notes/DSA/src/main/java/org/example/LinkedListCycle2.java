package org.example;
/**
 * Definition for singly-linked list.

 */

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
         val = x;
         next = null;
     }
 }

class Solution1 {
    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

}
public class LinkedListCycle2 {
}
