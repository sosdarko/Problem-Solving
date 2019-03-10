from time import time
import random

import special_binary_string
import letter_phone_number
import longest_palindromic_subset
import combination_sum

def test1():
    sol = special_binary_string.Solution()
    s = sol.makeLargestSpecial("11011000")
    print(s)

def test2():
    sol = letter_phone_number.Solution()
    x = sol.letterCombinations('237')
    print(x)
    print(len(x))

def test3():
    sol = longest_palindromic_subset.Solution()
    cases = ["cbbd","babad","ww", "wwq", "aqa", "qwertytrewq1", "aaaa","azwdzwmwcqzgcobeeiphemqbjtxzwkhiqpbrprocbppbxrnsxnwgikiaqutwpftbiinlnpyqstkiqzbggcsdzzjbrkfmhgtnbujzszxsycmvipjtktpebaafycngqasbbhxaeawwmkjcziybxowkaibqnndcjbsoehtamhspnidjylyisiaewmypfyiqtwlmejkpzlieolfdjnxntonnzfgcqlcfpoxcwqctalwrgwhvqvtrpwemxhirpgizjffqgntsmvzldpjfijdncexbwtxnmbnoykxshkqbounzrewkpqjxocvaufnhunsmsazgibxedtopnccriwcfzeomsrrangufkjfzipkmwfbmkarnyyrgdsooosgqlkzvorrrsaveuoxjeajvbdpgxlcrtqomliphnlehgrzgwujogxteyulphhuhwyoyvcxqatfkboahfqhjgujcaapoyqtsdqfwnijlkknuralezqmcryvkankszmzpgqutojoyzsnyfwsyeqqzrlhzbc"]
    for s in cases:
        #print(time())
        print(s)
        print('->', sol.longestPalindrome(s))

def test4():
    sol = combination_sum.Solution()
    big = list(range(2,10))
    cases = [[[2,3,5], 8], [[2,3,6,7],7], [big, 13]]
    for case in cases:
        sol.__init__
        x = sol.combinationSum(case[0], case[1])
        print('case:', case[0], case[1])
        for l in x:
            print(l)

test4()