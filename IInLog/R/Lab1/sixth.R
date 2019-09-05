#1.6

unitMatrix = function(n) { 
  m <<- matrix(0, nrow=n, ncol=n)
  diag(m) = 1
  return(m)
}

#a
x = matrix(c(1, 2, 7, 2, 4, 6), ncol=2)
y = matrix(c(10, 30, 50, 20, 40, 60), ncol=2)
2/7 * (x-y)

#b
a = matrix(c(1, 2, 7))
b = matrix(c(3, 4, 8))

t(a) %*% b

t(b) %*% (a %*% t(a))

(b %*% t(b)) + (a %*% t(a)) - 100 * unitMatrix(3)

#c
A = matrix(0, nrow = 4, ncol = 4)
diag(A) = c(2, 3, 5, -1)

solve(A) %*% A - unitMatrix(4)
