# https://leetcode.com/problems/special-binary-string/

class Solution:
    def isSpecial(self, str):
        num0 = 0
        num1 = 0
        for x in str:
            if x=='0':
                num0 += 1
            if x=='1':
                num1 += 1
            if num0 > num1:
                return False
        if num0 != num1:
            return False
        else:
            return True

    def makeLargestSpecial(self, S: str) -> str:
        if not self.isSpecial(S):
            return 'not special'
        return 'special'