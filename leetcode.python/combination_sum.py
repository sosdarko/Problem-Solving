"""
https://leetcode.com/problems/combination-sum/
Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
"""

import copy
from typing import List

class Solution:
    def __init__(self):
        self.target = 0
        self.candidates = []
        self.result = []
        self.N = 0

    def comb_sum_recur(self, interim_result: List[int], last_index: int, current_sum: int):
        for i in range(last_index, self.N):
            x = self.candidates[i]
            if x == 0:
                print("zeroes not allowed!")
                return
            if current_sum + x == self.target:
                interim_result.append(x)
                # we have to deepcopy the interim result only when adding it to the result set
                self.result.append(copy.deepcopy(interim_result))
                interim_result.pop() # we're using this as a stack
            elif current_sum + x < self.target:
                interim_result.append(x)
                current_sum += x
                # the new recursion call
                self.comb_sum_recur(interim_result, i, current_sum)
                # set interim results back to original
                interim_result.pop()
                current_sum -= x

    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        self.target = target
        self.candidates = sorted(candidates)
        self.N = len(candidates)
        self.result.clear()
        #self.result.extend(self.N)
        # the recursive call
        self.comb_sum_recur([],0,0)
        # return the result
        return self.result
