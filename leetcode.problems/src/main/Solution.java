package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode it1 = l1;
        ListNode it2 = l2;
        ListNode head = new ListNode(0);
        ListNode current = head;
        int carry = 0;
        int a, b;
        while(it1 != null || it2 != null) {
            if (it1 != null) {
                a = it1.val;
            } else {
                a = 0;
            }
            if (it2 != null) {
                b = it2.val;
            } else {
                b = 0;
            }
            current.val = a + b + carry;
            if (current.val >= 10) {
                carry = 1;
                current.val = current.val - 10;
            } else {
                carry = 0;
            }
            if (it1 != null) {
                it1 = it1.next;
            }
            if (it2 != null) {
                it2 = it2.next;
            }
            if (it1 != null || it2 != null) {
                current.next = new ListNode(0);
                current = current.next;
            } else {
                if (carry==1) {
                    current.next = new ListNode(1);
                }
                break;
            }
        }
        return head;
    }
    public int lengthOfLongestSubstring(String s) {
        HashSet subs = new HashSet<>();
        int curr_len = 0, len = 0;
        int i = 0, last_beg = 0;
        char c;
        while (i<s.length()) {
            c = s.charAt(i);
            if (!subs.contains(c)) {
                subs.add(c);
                curr_len += 1;
                ++i;
            }
            else {
                if (curr_len > len) {
                    len = curr_len;
                }
                curr_len = 0;
                subs.clear();
                // beacuse char c was found somewhere in s(last_beg:i)
                // find last occurence of char c in that substring and start again with next character
                i = last_beg + s.substring(last_beg, i).lastIndexOf(c) + 1;
                last_beg = i;
            }
        }
        if (curr_len > len) {
            len = curr_len;
        }
        return len;
    }
    /* zig-zag pattern, numRowes=4, s=PAYPALISHIRING
        P     I    N
        A   L S  I G
        Y A   H R
        P     I
        convert to: PINALSIGYAHRPI (read row by row)
    */
    public String convert(String s, int numRows) {
        String result="";
        int strLen = s.length();
        int index=0, k=0;
        if (numRows<=1 || numRows>=strLen) {
            return s;
        }
        for (int i=0; i<numRows; i++) {
            index = i;
            if (i==0 || i==numRows-1) {
                k = 0;
            }
            else {
                k = 2*(numRows - (i+1));
            }
            while (index<strLen) {
                result += s.charAt(index);
                if (k != 0 && index+k < strLen) {
                    result += s.charAt(index+k);
                }
                index += 2*numRows - 2;
            }
        }
        return result;
    }

    public String longestCommonPrefix(String[] strs) {
        String ret = "";
        char c = 0;
        int ind = 0;
        boolean end = false;
        if (strs.length == 0) {
            return "";
        }
        while (!end) {
            c = 0;
            for (String s : strs) {
                if (ind > s.length()) {
                    end = true;
                    break;
                }
                if (c==0) {
                    c = s.charAt(ind);
                    continue;
                }
                if (c != s.charAt(ind)) {
                    end = true;
                    break;
                }
            }
            ret += c;
        }
        return ret;
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode currNode;
        int length = 0, k = 0;
        if (head == null) {
            return null;
        }
        currNode = head;
        while (currNode != null) {
            currNode = currNode.next;
            length++;
        }
        if (length < n || n <= 0) {
            return head;
        }
        if (length==1) {
            return null;
        }
        // from now on, list is at least of size 2
        currNode = null;
        k = 0;
        // find (n+1)th node from the end
        while(k != length-n) {
            if (currNode==null) {
                currNode = head;
            } else {
                currNode = currNode.next;
            }
            k++;
        }
        if (k==0) {
            // this means that nth node frome end is the head, so return next of it
            return head.next;
        }
        else {
            if (currNode.next != null) {
                currNode.next = currNode.next.next;
            } else {
                System.out.println("this should never happen!");
            }
        }
        return head;
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode n1 = head, n3, h = head.next, last1 = head;
        while (n1.next != null && n1.next.next != null) {
            n3 = n1.next.next;
            n1.next.next = n1;
            n1.next = n3.next;
            last1 = n1;
            n1 = n3;
        }
        if (n1.next != null) {
            n1.next.next = n1;
            n1.next = null;
        }
        else {
            last1.next = n1;
        }
        return h;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode kThNode = head;

        ListNode tempHead = head, n = null, tmp = null, lastEnd = null;

        ListNode[] kNodes = new ListNode[k];
        int i = 0;

        mainLoop:
        while (tempHead != null) {
            i = 0;
            n = tempHead;
            // store next k nodes; break if there is not enough nodes
            while (i<k) {
                kNodes[i] = n;
                if (n == null) {
                    break mainLoop;
                }
                n = n.next;
                i++;
            }
            // remember kth node as this is the new head and will be returned in the end
            if (tempHead == head) {
                kThNode = kNodes[k-1];
            }
            i -= 1;
            // remember where kth node of the cycle points
            tmp = kNodes[i].next;
            // reverse nodes
            while (i>0) {
                kNodes[i].next = kNodes[i-1];
                i--;
            }
            // first node, that now is in the end, should point to (k+1)th node
            kNodes[0].next = tmp;
            // start next cycle with (k+1)th node
            tempHead = tmp;
            // on next cycle, we should connect last node of the previous cycle to the current reversed cycle
            if (lastEnd != null) {
                lastEnd.next = kNodes[k-1];
            }
            lastEnd = kNodes[0];
        }
        return kThNode;
    }

    private int FindIndex(String s, int index, String[] words, List<Integer> unusedIndices) {
        int j;
        boolean found;
        int wordLen = words[0].length();

        if (index+wordLen > s.length()) {
            return -1;
        }

        found = false;
        j = 0;

        for (Integer i : unusedIndices) {
            if (s.substring(index, index+wordLen).equals(words[i])) {
                found = true;
                j = i;
                break;
            }
        }

        if (!found) {
            j = -1;
        }

        return j;
    }

    // https://leetcode.com/problems/substring-with-concatenation-of-all-words/
    public List<Integer> findSubstring(String s, String[] words) {
        // subInd is the result
        List<Integer> subind = new ArrayList<Integer>();

        if (s.equals("") || words.length == 0) {
            return subind;
        }
        int wordLen = words[0].length();
        if (s.length() < wordLen*words.length) {
            return subind;
        }

        List<Integer> unusedWords = new ArrayList<Integer>();
        int i=0, j=0, ind;

        while (i+wordLen <= s.length()) {
            //System.out.printf("i=%d, time = %d\n",i,System.currentTimeMillis());
            j = 0;
            ind = i;
            unusedWords = IntStream.rangeClosed(0, words.length-1).boxed().collect(Collectors.toList());
            while (unusedWords.size() > 0) {
                j = FindIndex(s, ind, words, unusedWords);
                if (j>=0) {
                    unusedWords.remove((Integer)j);
                    ind += wordLen;
                }
                else {
                    break;
                }
            }
            if (unusedWords.size() == 0) {
                subind.add(i);
            }
            i++;
        }
        return subind;
    }

    // https://leetcode.com/problems/minimum-window-substring/
    public String minWindow(String s, String t) {
        if (t.length()==0 || s.length()==0) {
            return "";
        }

        String sSolution = "";
        HashMap<Character,Integer> allChars=new HashMap<>();
        HashMap<Character,Integer> unusedChars=new HashMap<>();
        int ind=0;
        Integer val;
        int tLen = t.length();

        for (char c:t.toCharArray()) {
            val = allChars.get(c);
            if (val == null) {
                allChars.put(c,1);
            }
            else {
                allChars.put(c,val+1);
            }
        }

        ind = 0;
        int beg=-1, second=-1, count=0;
        boolean inWindow=false, removed=false;
        Character c;
        unusedChars.putAll(allChars);
        while (ind < s.length()) {
            c = s.charAt(ind);
            if (!allChars.containsKey(c)) {
                ind++;
                if (!inWindow) {
                    beg++;
                }
                continue;
            }
            else {
                inWindow = true;
                val = unusedChars.get(c);
                if (val != null && val > 0) {
                    unusedChars.put(c, val-1);
                    removed = true;
                }
                if (removed) {
                    count++;
                }
                else {
                    if (count==1) {
                        beg = ind;
                    }
                    ind++;
                    continue;
                }
                if (count==1) {
                    beg = ind;
                }
                else {
                    if (count==2) {
                        second = ind;
                    }
                }
                if (count == tLen) {
                    unusedChars.putAll(allChars);
                    count = 0;
                    if (ind-beg < sSolution.length() || sSolution.length()==0) {
                        sSolution = s.substring(beg, ind+1);
                    }
                    if (second>0) {
                        beg = second;
                        ind = second;
                    }
                    else {
                        ind++;
                        inWindow = false;
                    }
                }
                else {
                    ind++;
                }
            }
        }
        return sSolution;
    }
}