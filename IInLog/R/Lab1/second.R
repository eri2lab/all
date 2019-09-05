#1.2
# a.
mySeq <- seq(5, -11, -0.3)

# b.
mySeq <- rev(mySeq)

# c.
myRep <- rep(c(-1, 3, -5, 7, -9), 2, each=10)
sort(myRep, TRUE)

# d.
d <- c(seq(6, 12), 
       rep(5.3, 3), -3, 
       seq(102, length(myRep),length.out=9))

# e.
length(d)
