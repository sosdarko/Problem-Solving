class hanoi:
    moves = 0
    def hanoi_r(self, n, a, b):
        if n == 1:
            self.moves += 1
            print(self.moves, ':', '1 from', a, 'to', b)
        else:
            c = 6 - (a+b)
            self.hanoi_r(n-1, a, c)
            self.moves += 1
            print(self.moves, ':', n, 'from', a, 'to', b)
            self.hanoi_r(n-1, c, b)

h = hanoi()

h.hanoi_r(10, 1, 2)
print(h.maxdepth)