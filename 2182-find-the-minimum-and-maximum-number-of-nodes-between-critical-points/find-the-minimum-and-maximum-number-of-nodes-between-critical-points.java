class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) 
    {
            ListNode previous = head;
            ListNode current = head.next;
            int index =1;
            int first =-1;
            int last =-1;
            int minDistance = Integer.MAX_VALUE;
            while(current.next!=null)
            {
                ListNode next = current.next;
                if((current.val>previous.val && current.val>next.val) || 
                (current.val<previous.val && current.val <next.val))
                {
                    if(first==-1)
                    {
                        first = index;
                        last = index;
                    }
                    else
                    {
                        int Distance = index-last;
                        minDistance = Math.min(minDistance,Distance);
                        last = index;
                    }
                }
                previous = current;
                current = current.next;
                index++;
            }
            if(first == last)
            {
                return new int []{-1,-1};
            }
            int maxDistance = last - first;
            return new int[]{minDistance,maxDistance};
        }
    }