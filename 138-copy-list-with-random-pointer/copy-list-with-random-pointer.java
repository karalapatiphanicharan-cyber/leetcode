class Solution {
    public Node copyRandomList(Node head) 
    {
        if(head == null)
        return null;

        HashMap<Node,Node> map = new HashMap<>();
        Node current = head;
        while(current!=null)
        {
            Node copy = new Node(current.val);
            map.put(current,copy);
            current = current.next;
        }
        current = head;
        while(current!=null)
        {
           Node copy = map.get(current);
           copy.next = map.get(current.next);
           copy.random = map.get(current.random);
           current = current.next;
        }
        return map.get(head);
    }
}