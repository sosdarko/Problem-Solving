package main;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.xml.stream.events.Characters;

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class Solution {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode it1 = l1;
		ListNode it2 = l2;
		ListNode head = new ListNode(0);
		ListNode current = head;
		int carry = 0;
		int a, b;
		while (it1 != null || it2 != null) {
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
				if (carry == 1) {
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
		while (i < s.length()) {
			c = s.charAt(i);
			if (!subs.contains(c)) {
				subs.add(c);
				curr_len += 1;
				++i;
			} else {
				if (curr_len > len) {
					len = curr_len;
				}
				curr_len = 0;
				subs.clear();
				// beacuse char c was found somewhere in s(last_beg:i)
				// find last occurence of char c in that substring and start again with next
				// character
				i = last_beg + s.substring(last_beg, i).lastIndexOf(c) + 1;
				last_beg = i;
			}
		}
		if (curr_len > len) {
			len = curr_len;
		}
		return len;
	}

	/*
	 * zig-zag pattern, numRowes=4, s=PAYPALISHIRING P I N A L S I G Y A H R P I
	 * convert to: PINALSIGYAHRPI (read row by row)
	 */
	public String convert(String s, int numRows) {
		String result = "";
		int strLen = s.length();
		int index = 0, k = 0;
		if (numRows <= 1 || numRows >= strLen) {
			return s;
		}
		for (int i = 0; i < numRows; i++) {
			index = i;
			if (i == 0 || i == numRows - 1) {
				k = 0;
			} else {
				k = 2 * (numRows - (i + 1));
			}
			while (index < strLen) {
				result += s.charAt(index);
				if (k != 0 && index + k < strLen) {
					result += s.charAt(index + k);
				}
				index += 2 * numRows - 2;
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
				if (c == 0) {
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
		if (length == 1) {
			return null;
		}
		// from now on, list is at least of size 2
		currNode = null;
		k = 0;
		// find (n+1)th node from the end
		while (k != length - n) {
			if (currNode == null) {
				currNode = head;
			} else {
				currNode = currNode.next;
			}
			k++;
		}
		if (k == 0) {
			// this means that nth node frome end is the head, so return next of it
			return head.next;
		} else {
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
		} else {
			last1.next = n1;
		}
		return h;
	}

	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode kThNode = head;

		ListNode tempHead = head, n = null, tmp = null, lastEnd = null;

		ListNode[] kNodes = new ListNode[k];
		int i = 0;

		mainLoop: while (tempHead != null) {
			i = 0;
			n = tempHead;
			// store next k nodes; break if there is not enough nodes
			while (i < k) {
				kNodes[i] = n;
				if (n == null) {
					break mainLoop;
				}
				n = n.next;
				i++;
			}
			// remember kth node as this is the new head and will be returned in the end
			if (tempHead == head) {
				kThNode = kNodes[k - 1];
			}
			i -= 1;
			// remember where kth node of the cycle points
			tmp = kNodes[i].next;
			// reverse nodes
			while (i > 0) {
				kNodes[i].next = kNodes[i - 1];
				i--;
			}
			// first node, that now is in the end, should point to (k+1)th node
			kNodes[0].next = tmp;
			// start next cycle with (k+1)th node
			tempHead = tmp;
			// on next cycle, we should connect last node of the previous cycle to the
			// current reversed cycle
			if (lastEnd != null) {
				lastEnd.next = kNodes[k - 1];
			}
			lastEnd = kNodes[0];
		}
		return kThNode;
	}

	private int FindIndex(String s, int index, String[] words, List<Integer> unusedIndices) {
		int j;
		boolean found;
		int wordLen = words[0].length();

		if (index + wordLen > s.length()) {
			return -1;
		}

		found = false;
		j = 0;

		for (Integer i : unusedIndices) {
			if (s.substring(index, index + wordLen).equals(words[i])) {
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
		if (s.length() < wordLen * words.length) {
			return subind;
		}

		List<Integer> unusedWords = new ArrayList<Integer>();
		int i = 0, j = 0, ind;

		while (i + wordLen <= s.length()) {
			// System.out.printf("i=%d, time = %d\n",i,System.currentTimeMillis());
			j = 0;
			ind = i;
			unusedWords = IntStream.rangeClosed(0, words.length - 1).boxed().collect(Collectors.toList());
			while (unusedWords.size() > 0) {
				j = FindIndex(s, ind, words, unusedWords);
				if (j >= 0) {
					unusedWords.remove((Integer) j);
					ind += wordLen;
				} else {
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
		if (t.length() == 0 || s.length() == 0 || s.length() < t.length()) {
			return "";
		}
		String sSolution = "";
		// allChars is map of characters in t with their occurence number as value
		TreeMap<Character, Integer> allChars = new TreeMap<>();
		// windowChars represents counts of chars in window that also could be found in
		// t
		TreeMap<Character, Integer> windowChars = new TreeMap<>();
		Integer val;
		int tLen = t.length(), sLen = s.length();
		for (char c : t.toCharArray()) {
			val = allChars.get(c);
			if (val == null) {
				allChars.put(c, 1);
			} else {
				allChars.put(c, val + 1);
			}
			windowChars.put(c, 0);
		}
		// System.out.println(System.currentTimeMillis()-time);
		int diff = 0, beg = 0, end = 0, count = 0;
		Character c;
		// find initial window that covers all chars in t
		// first find the beginning
		while (beg < sLen) {
			c = s.charAt(beg);
			if (allChars.containsKey(c)) {
				break;
			}
			beg++;
		}
		if (beg + tLen > sLen) {
			return "";
		}
		// secondly, find the end of the intial window
		count = 0;
		while (end < sLen) {
			c = s.charAt(end);
			val = windowChars.get(c);
			if (val != null) {
				diff = allChars.get(c) - windowChars.get(c);
				windowChars.put(c, val + 1);
				if (diff == 1) {
					count++;
					if (count == allChars.size()) {
						break;
					}
				}
			}
			end++;
		}
		if (count == allChars.size()) {
			sSolution = s.substring(beg, end + 1);
		} else {
			return "";
		}
		Character missingChar = null;
		// could be either S=shrinking or E=expanding
		char moveType;
		moveType = 'S';
		boolean found = false;
		c = null;
		while (beg <= end && end < sLen) {
			if (moveType == 'S') {
				missingChar = s.charAt(beg);
				// move start of the window until we find another char from t
				beg++;
				while (beg <= end) {
					c = s.charAt(beg);
					if (windowChars.containsKey(c)) {
						break;
					}
					beg++;
				}
				if (c == null) {
					break;
				}
				//
				if (windowChars.containsKey(c)) {
					val = windowChars.get(missingChar);
					windowChars.put(missingChar, val - 1);
					if (windowChars.get(missingChar) >= allChars.get(missingChar)) {
						if (end - beg < sSolution.length()) {
							sSolution = s.substring(beg, end + 1);
						}
						// keep shrinking
						continue;
					} else {
						moveType = 'E';
					}
				}
			} else {
				// keep expanding until we find the missing char
				end++;
				found = false;
				while (end < sLen) {
					c = s.charAt(end);
					if (c.equals(missingChar)) {
						found = true;
						break;
					} else {
						val = windowChars.get(c);
						if (val != null) {
							windowChars.put(c, val + 1);
						}
					}
					end++;
				}
				if (found) {
					val = windowChars.get(missingChar);
					windowChars.put(missingChar, val + 1);
					if (windowChars.get(missingChar) >= allChars.get(missingChar)) {
						if (end - beg < sSolution.length()) {
							sSolution = s.substring(beg, end + 1);
						}
						moveType = 'S';
						continue;
					} else {
						// if we found missing char and its not enough to cover all chars
						// then exit; this perheps should never happen
						break;
					}
				}
				// didn't found missing char, the search should end
				else {
					break;
				}
			}
		}
		return sSolution;
	}

	public int maxArea(int[] height) {
		int m = 0;
		int i, j, b;

		for (i = 0; i < height.length; i++) {
			for (j = i + 1; j < height.length; j++) {
				b = height[i];
				if (b > height[j]) {
					b = height[j];
				}
				if (b * (j - i) > m) {
					m = b * (j - i);
				}
			}
		}

		return m;
	}

	// https://leetcode.com/problems/trapping-rain-water/
	public int trap(int[] height) {
		int result = 0, tempres = 0, currh;
		int i, j;

		currh = 1;
		for (i = 0; i < height.length; i++) {
			currh = 1;
			while (currh <= height[i]) {
				tempres = 0;
				for (j = i + 1; j < height.length; j++) {
					if (height[j] >= currh) {
						tempres = j - i - 1;
						// System.out.printf("i=%d, j=%d, h(i)=%d, h(j)=%d\n", i, j, height[i],
						// height[j]);
						break;
					}
				}
				result += tempres;
				currh++;
			}
		}
		return result;
	}

	public int myCouple(int me) {
		if (me % 2 == 0) {
			return me + 1;
		}
		return me - 1;
	}

	// https://leetcode.com/problems/couples-holding-hands/
	public int minSwapsCouples(int[] row) {
		int result = 0;

		ArrayList<Integer> notPaired = new ArrayList<>();

		// find all not paired
		for (int i = 0; i < row.length; i += 2) {
			if (myCouple(row[i]) != row[i + 1]) {
				notPaired.add(i);
			}
		}
		// if all are paired, return 0
		if (notPaired.size() == 0) {
			return 0;
		}

		int ind1, ind3, other, pair, temp;
		boolean found;
		while (notPaired.size() > 0) {
			// get indices of first non-paired couple
			ind1 = notPaired.get(0);
			// find non paired couple that cross-matches this one
			pair = 1;
			found = false;
			other = -1;
			while (pair < notPaired.size()) {
				ind3 = notPaired.get(pair);
				if (myCouple(row[ind1]) == row[ind3]) {
					other = ind3;
					found = true;
				}
				if (myCouple(row[ind1]) == row[ind3 + 1]) {
					other = ind3 + 1;
					found = true;
				}
				if (found) {
					// if we swap row[ind2] with row[ind3] or row[ind4], we have one swapping more
					result++;
					notPaired.remove(0);
					temp = row[ind1 + 1];
					row[ind1 + 1] = row[other];
					row[other] = temp;
					// but maybe other pair is coupled too
					if (myCouple(row[ind3]) == row[ind3 + 1]) {
						// we've removed 0th element so (pair)th element drops one place down
						notPaired.remove(pair - 1);
					}
					break;
				} else {
					pair++;
				}
			}
		}

		return result;
	}

	// https://leetcode.com/problems/valid-number/
	public boolean isNumber(String s) {
		boolean result = true;
		boolean needMore = true;
		boolean afterE = false;
		boolean afterDot;
		int i = 0;
		String t = s.trim();
		// if there's nothing but a whitespace, it's not the number
		if (t.length() == 0) {
			return false;
		}
		// this is initial set of possible chars, i.e. this is the list of chars that
		// could be in the first position
		Set<Character> possibleChars = new HashSet<Character>(
				Arrays.asList('+', '-', '.', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
		Character c;
		result = true;
		needMore = true;
		afterE = false;
		afterDot = false;
		while (i < t.length()) {
			c = t.charAt(i);
			if (!possibleChars.contains(c)) {
				result = false;
				break;
			} else {
				switch (c) {
				case '+':
				case '-':
					possibleChars.remove((Character) '+');
					possibleChars.remove((Character) '-');
					needMore = true;
					break;
				case 'e':
					possibleChars.remove((Character) 'e');
					possibleChars.remove((Character) '+');
					possibleChars.remove((Character) '-');
					possibleChars.remove((Character) '.');
					afterE = true;
					needMore = true;
					break;
				case '.':
					possibleChars.remove((Character) '.');
					possibleChars.remove((Character) 'e');
					possibleChars.remove((Character) '+');
					possibleChars.remove((Character) '-');
					needMore = true;
					afterDot = true;
					break;
				default: // here goes digits
					possibleChars.remove((Character) '+');
					possibleChars.remove((Character) '-');
					if (!afterE && !afterDot) {
						possibleChars.add((Character) 'e');
						possibleChars.add((Character) '.');
					}
					if (afterDot && !afterE) {
						possibleChars.add((Character) 'e');
					}
					needMore = false;
				}
			}
			i++;
		}

		if (needMore) {
			result = false;
		}

		return result;
	}

}