install.packages("lpSolve")
library(lpSolve)

f.transportCost <- matrix(c(7, 43, 39, 10,
                            10, 33, 46, 16,
                            46, 17, 27, 47,
                            0, 0, 0, 0),
                          nrow=4, byrow=TRUE)

f.consum <- c(38, 30, 19, 87)
f.volume <- c(41, 22, 61, 50)
f.signs <- rep("=", 4)


result <- lp.transport(f.transportCost,"min", f.signs, 
                       f.volume, f.signs, f.consum)

print(result)
print(result$solution)