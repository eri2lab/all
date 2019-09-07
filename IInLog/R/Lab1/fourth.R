#1.4
#Пункт a.
vec <- c(2, 0.5, 1, 2, 0.5, 1, 2, 0.5, 1)
vec <- vec * c (0.5, 2, 1)
vec

# Пункт b.
fahrenheit = c(45, 77, 20, 19, 101, 120, 212)
celcius = 5/9 * (fahrenheit - 32)
celcius

# Пункт c.
c <- rep(c(2,4,6), 2) * rep(c(1,2), each=3)
c

# Пункт d.
length(c) / 2
c[-c(1, length(c))] = c(-0.1, -100)
c
