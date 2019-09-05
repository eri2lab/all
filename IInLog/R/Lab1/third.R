#1.3
#a
vec <- c(seq(3, 6, length.out=5), 
         rep(c(2, -5.1, -33), 2), 
         7/42 + 2)

#b
firstLastIndexes = c(1, length(vec))
firstLast <- vec[firstLastIndexes]

#c
woFirstLast <- vec[-firstLastIndexes]

#d
reVec <- c(firstLast[1], woFirstLast, firstLast[2])

#e
vec <- sort(vec)

#f
sort(vec, decreasing=TRUE) == vec[length(vec):1]

#g
g <- woFirstLast[c(rep(3,3), rep(6,4), length(woFirstLast))]
newVec <- vec
newVec[c(1, 5:7, length(newVec))] = 99:95
