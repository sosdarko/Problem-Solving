def fib(n):
    if n <= 2:
        return 1
    a = 1
    b = 1
    for i in range(n-2):
        a, b = b, a+b
    return b

f = fib(17)
print(f)