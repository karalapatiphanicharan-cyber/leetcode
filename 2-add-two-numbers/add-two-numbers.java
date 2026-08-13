class Solution 
{
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) 
    {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) 
        {
            int digit1;
            int digit2;
            if (l1 != null) 
            {
                digit1 = l1.val;
            } 
            else 
            {
                digit1 = 0;
            }

            if (l2 != null) 
            {
                digit2 = l2.val;
            }
            else
             {
                digit2 = 0;
            }
            int sum = digit1 + digit2 + carry;
            int digit = sum % 10;
            carry = sum / 10;
            current.next = new ListNode(digit);
            current = current.next;
            if (l1 != null) 
            {
                l1 = l1.next;
            }
            if (l2 != null) 
            {
                l2 = l2.next;
            }
        }

        return dummy.next;
    }
}