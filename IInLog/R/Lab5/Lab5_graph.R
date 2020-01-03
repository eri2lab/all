install.packages("igraph")
library(igraph)

# Задаем матрицу.
P <- matrix(c(0.18, 0.18, 0.36, 0.23, 0.05,
              0.43, 0.13, 0.06, 0.27, 0.11,
              0.29, 0.20, 0.17, 0.28, 0.06,
              0.15, 0.34, 0.03, 0.20, 0.28,
              0.22, 0.19, 0.21, 0.18, 0.20),
            nrow=5, ncol=5, byrow = TRUE)

# Строим график.
x = graph_from_adjacency_matrix(P, mode = c("directed"), weighted = TRUE)
plot(x)
