install.packages("lpSolve")
library(lpSolve)

f.obj <- c(30, 18, 45, 20)
f.con <- matrix(c(0,0,1,0,
                  1,0,0,0,
                  0,1,0,1,
                  0,1,0,1,
                  1,0,0,1), nrow=5, byrow=TRUE)
f.dir <- c("<=",
           ">=",
           "<=",
           ">=",
           "<=")
f.rhs <- c(12, 25, 50, 40, 30)

result <- lp ("max", f.obj, f.con, f.dir, f.rhs)

print(result)
print(result$solution)

# Proverka graficheckogo metoda.
library(lpSolve)
x.obj <- c(2,3)
x.con <- matrix(c(1,1,
                  3,1,
                  1,5,
                  1,0,
                  1,0,
                  0,1,
                  0,1), nrow=7, byrow=TRUE)

x.dir <- c("<=",
           ">=",
           ">=",
           ">=",
           "<=",
           ">=",
           "<=")

x.rhs <- c(4,4,4,0,3,0,3)

res2 <- lp("min", x.obj, x.con, x.dir, x.rhs)
print(res2)
print(res2$solution)