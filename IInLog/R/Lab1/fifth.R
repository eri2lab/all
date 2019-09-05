#1.5
#a
a = matrix(c(4.3, 3.1, 8.2, 8.2, 3.2, 0.9, 1.6, 6.5), nrow=4, ncol=2, byrow=TRUE)

#b
dim(a[-1,])

#c
a[,2] = sort(a[,2])

#d
matrix(a[-4,-1])

#e
e <- a[3:4,]

#f
a[c(4,1), c(2,1)] = 1/2 * diag(e)
