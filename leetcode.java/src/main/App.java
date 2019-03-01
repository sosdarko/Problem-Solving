package main;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class App {
    private static final Solution SOLUTION = new Solution();

    private static String makeLargeString(int size) {
    	byte[] array = new byte[size]; // length is bounded by 7
        new Random().nextBytes(array);
        IntStream.range(0, size).forEach(i -> array[i] = (byte) (65 + (128 + array[i])%30));
        String s = new String(array, Charset.forName("ASCII"));
    	return s;
    }
    
    private static void AddTwoNumbers() {
        ListNode l1 = new ListNode(new int[] {5});
        ListNode l2 = new ListNode(new int[] {5});
        //
        Solution s = SOLUTION;
        ListNode l3 = s.addTwoNumbers(l1,l2);
        l3.Print(10);
    }

    private static void LengthOfLongestSubstring() {
        /*String s = "abcabcbb";
        System.out.println(s);
        System.out.println(SOLUTION.lengthOfLongestSubstring(s));
        s = "pwwkew";
        System.out.println(s);
        System.out.println(SOLUTION.lengthOfLongestSubstring(s));*/
        System.out.println(SOLUTION.lengthOfLongestSubstring("abcabcbb"));
    }

    private static void ZigZagRead() {
        String s = "PAYPALISHIRING";
        System.out.println(SOLUTION.convert(s, 3));
        System.out.println(SOLUTION.convert(s, 4));
        System.out.println(SOLUTION.convert(s, 5));
        System.out.println(SOLUTION.convert(s, 6));
    }

    private static void RemoveNthFromEnd() {
        ListNode l1 = new ListNode(new int[] {5,1,324,23,4324});
        ListNode l2 = null;
        l2 = SOLUTION.removeNthFromEnd(l1, 5);
        l2.Print(100);
    }

    private static void SwapPairs() {
        ListNode l1 = new ListNode(new int[] {1,2,3,4,5,6,7});
        ListNode l2 = null;
        l2 = SOLUTION.swapPairs(l1);
        l2.Print(100);
    }

    private static void ReverseKGroup() {
        ListNode l1 = new ListNode(new int[] {1,2,3});
        ListNode l2 = SOLUTION.reverseKGroup(l1, 5);
        l2.Print(20);
    }

    private static void FindSubstring() {
        String s = "wordgoodgoodgoodbestword";
        String[] words = {"word","good","best","good"};

        System.out.printf("%d %d\n", s.length(), words.length);
        System.out.println(SOLUTION.findSubstring(s, words));
    }

    private static void MinWindow() {
        String s;
        String t;
        //s = "acbbaca";
        //t = "aba";
        //s = "bba";
        //t = "ab";
        s = "bdab";
        t = "ab";
        //s = makeLargeString(600000);
        //t = makeLargeString(60);
        //s = "ADOBECODEBANC";
        //t = "ABC";
        //s = "a";
        //t = "a";
        /* "acbbaca" "aba" */
		long time = System.currentTimeMillis();
        //System.out.println(SOLUTION.minWindow(s, t));
        String res = SOLUTION.minWindow(s, t);
        System.out.println("t=" + t);
        System.out.println(res);
        System.out.println(System.currentTimeMillis()-time);
    }
    
    private static void MaxArea() {
    	int[] height = {1,8,6,2,5,4,8,3,7};
    	System.out.println(SOLUTION.maxArea(height));
    }

    private static void Trap() {
    	int[] height = {0,1,0,3,1,0,1,3,2,1,2,1};
    	System.out.println(SOLUTION.trap(height));
    }
    
    private static void MinSwapsCouples() {
    	int[] row = {0,2,1,3};// {5,4,2,6,3,1,0,7};
    	System.out.println(SOLUTION.minSwapsCouples(row));
    }
    private static void IsNumber() {
    	String s = " 005047e+6e8";
    	System.out.println(SOLUTION.isNumber(s));
    }
    
    private static void FullJustify() {
    	String words[] = {"This", "is", "an", "example", "of", "text", "justification."};
    	String words2[] = {"What","must","be","acknowledgment","shall","be"};
    	String words3[] = {"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"};
    	String words4[] = {"ask","not","what","your","country","can","do","for","you","ask","what","you","can","do","for","your","country"};
    	List<String> r = SOLUTION.fullJustify(words4, 16);
    	for (String s:r) {
    		System.out.println(s);
    	}
    }
    
    public static void main(String[] args) {
        FullJustify();
    }
}