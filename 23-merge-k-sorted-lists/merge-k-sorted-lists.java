class Solution {
    public ListNode mergeKLists(ListNode[] lists) 
    {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b)->a.val-b.val);
        for(ListNode list:lists)
        {
            if(list!=null)
            {
                pq.offer(list);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while(!pq.isEmpty())
        {
            ListNode smallest = pq.poll();
            current.next = smallest;
            current = current.next;
            if(smallest.next!=null)
            {
                pq.offer(smallest.next);
            }
        }
        return dummy.next;
    }
}