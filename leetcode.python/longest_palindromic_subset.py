# https://leetcode.com/problems/longest-palindromic-substring/

'''
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Example 2:

Input: "cbbd"
Output: "bb"
'''

class Solution:
    def longestPalindrome(self, s: str) -> str:
        maxPal = ''

        n = len(s)

        if n <= 1:
            return ''

        i = 0
        while i < n-1:
            k = 0
            # here we're looking for odd palindrome
            while i-k-1>=0 and i+k+1<n:
                if s[i-k-1] == s[i+k+1]:
                    k += 1
                else:
                    break
            # k will tell how far are we from the central point
            if k > 0:
                if 2*k+1 > len(maxPal):
                    maxPal = s[i-k:i+k+1]
            k = 0
            # we're looking here for even palindrome
            if s[i] == s[i+1]:
                # the logic of k here is slightly different
                while i-k-1 >= 0 and i+k+2 < n:
                    if s[i-k-1] == s[i+k+2]:
                        k += 1
                    else:
                        break
                if 2*k+2 > len(maxPal):
                    maxPal = s[i-k:i+k+2]

            i += 1

        return maxPal