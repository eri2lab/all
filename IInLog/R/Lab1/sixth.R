#1.6
# Пункт a.
x = matrix(c(1, 2, 7, 2, 4, 6), ncol=2)
y = matrix(c(10, 30, 50, 20, 40, 60), ncol=2)
2/7 * (x-y)

# Пункт b.
a = matrix(c(1, 2, 7))
b = matrix(c(3, 4, 8))

# Подпункт ii.
t(a) %*% b

# Подпункт iii.
t(b) %*% (a %*% t(a))

# Подпункт v.
(b %*% t(b)) + (a %*% t(a)) - 100 * diag(3)

# Пункт c.
A = matrix(0, nrow = 4, ncol = 4)
diag(A) = c(2, 3, 5, -1)

solve(A) %*% A - diag(4)
