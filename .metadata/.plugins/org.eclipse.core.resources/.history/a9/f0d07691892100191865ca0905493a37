package main;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    ListNode(int[] x) {
        this.val = x[0];
        ListNode n = this;
        for (int i = 1; i < x.length; i++) {
            n.next = new ListNode(x[i]);
            n = n.next;
        }
    }
    public void Print(int maxPrint) {
        ListNode n = this;
        int i=0;
        while (n != null && i < maxPrint) {
            System.out.printf("%d,", n.val);
            n = n.next;
            i++;
        }
        System.out.println();
    }

    public int LengthK(int K) {
        int lengthK=0;
        ListNode h = this;

        while (h != null && lengthK < K) {
            h = h.next;
            lengthK++;
        }

        return lengthK;
    }

    public ListNode GetKthNode(int k) {
        ListNode ret = null;
        int i = 0;

        while (ret != null && i < k) {
            ret = ret.next;
            i++;
        }

        return ret;
    }
}