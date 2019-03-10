# https://leetcode.com/problems/letter-combinations-of-a-phone-number/

# Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

'''
idea:
first make list of available letters list
   '27' -> [[a,b,c], [p,q,r,s]]
   traverse all possible indexes in the following way:
     start with 00
     add 1 to the first index
     if it's greater than length of first list, reset it and carry one to the next, and so on
     e.g. 00, 10, 20, 01, 11, 21, 02, 12, 22, 03, 13, 23
     witch corresponds to:
     ap, bp, cp, aq, bq, cq, etc...
'''

from typing import List

class Solution:
    key_map = {
        '2':['a','b','c'], '3':['d','e','f'],
        '4':['g','h','i'], '5':['j','k','l'], '6':['m','n','o'],
        '7':['p','q','r','s'], '8':['t','u','v'], '9':['w','x','y','z']}

    def letterCombinations(self, digits):
        dig = [self.key_map[digits[i]] for i in range(len(digits))]
        print (dig)
        n = len(digits)
        ret = []
        # vector of indices
        ind = [0] * n
        done = False
        while not done:
            d = [dig[k][ind[k]] for k in range(n)]
            ret.append(''.join(d))
            # calculate the next index vector; if it's all zeroes, we're done
            i = 0
            carry = True
            while carry:
                ind[i] += 1
                if ind[i] >= len(dig[i]):
                    ind[i] = 0
                    i += 1
                    if i >= n:
                        done = True
                        break
                else:
                    carry = False

        return ret