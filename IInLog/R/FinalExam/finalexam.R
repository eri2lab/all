setClass(Class="Strategy",
         representation(
           strategy = "data.frame",
           income_matrix = "matrix",
           probability_matrix = "matrix"
         ))

# Решить задачу принятия решений методом
# полного перебора при бесконечном числе этапов.
#
# @param [Matrix] p Матрица переходных вероятностей.
# @param [Matrix] p Матрица доходов.
#
# @return [Number] Ожидаемый годовой доход.
solve_infinity_iter <- function(p, r) {
  # Находим ожидаемый доход за 1 этап.
  rowsSum = rowSums(p * r)
  
  # Составляем систему уравнений. Для этого сначала транспонируем матрицу переходов.
  # После этого вычитаем 1 по главной диагонали, чтобы в векторе B были нули, а не a1, a2, a3.
  # После этого заменяем избыточное уравнение на a1 + a2 + a3 = 1.
  transposed = t(p)
  dimension = nrow(transposed)
  A = transposed + diag(-1, ncol=dimension, nrow=dimension)
  A = matrix(rbind(head(A, dimension - 1), rep(1, dimension)), ncol = 3)
  B = rep(0, dimension)
  B[dimension] = 1
  
  # Находим  решение системы уравнений
  solution = solve(A,B)
  
  #Находим ожидаемый доход, умножая решение системы на ожидаемый доход за 1 этап.
  income = solve(A,B) * rowsSum
  totalIncome = sum(income)
  
  return(totalIncome)
}


get_strategies_combinations <- function(states_count, 
                                        strategies_count) {
  l <- rep(list(1:strategies_count), states_count)
  return(expand.grid(l))
}



make_matrix_from_combination <- function(combination, matrices) {
  len = nrow(matrices[[1]])
  container = vector("list", len)
  for (i in 1:len) {
    strategy = combination[[i]]
    container[[i]] <- matrices[[strategy]][i,]
  }
  return(do.call(rbind, container))
}

get_combinations <- function (probabilities, incomes) {
  strategies_count = length(probabilities)
  states_count = ncol(probabilities[[1]])
  combinations = get_strategies_combinations(states_count, strategies_count)
  combinations_count <- nrow(combinations)
  
  container <- vector("list", combinations_count)
  for (i in 1:combinations_count) {
    combination = combinations[i,]
    probability = make_matrix_from_combination(combination, probabilities)
    income = make_matrix_from_combination(combination, incomes)
    container[[i]] <- new("Strategy",
                          strategy = combination,
                          income_matrix = income,
                          probability_matrix = probability)
  }
  
  return(container)
}


radio_probability = matrix(c(0.4, 0.5, 0.1,
                             0.1, 0.7, 0.2,
                             0.1, 0.2, 0.7), nrow=3, byrow=TRUE)

radio_income = matrix(c(400, 520, 600,
                        300, 400, 700,
                        200, 250, 500), nrow=3, byrow=TRUE)


television_probability = matrix(c(0.7, 0.2, 0.1,
                             0.3, 0.6, 0.1,
                             0.1, 0.7, 0.2), nrow=3, byrow=TRUE)

television_income = matrix(c(1000, 1300, 1600,
                        800, 1000, 1700,
                        600, 700, 1100), nrow=3, byrow=TRUE)


newspaper_probability = matrix(c(0.2, 0.5, 0.3,
                             0, 0.7, 0.3,
                             0, 0.2, 0.8), nrow=3, byrow=TRUE)

newspaper_income = matrix(c(400, 530, 710,
                        350, 450, 800,
                        250, 400, 650), nrow=3, byrow=TRUE)


probabilities = list(radio_probability, television_probability, newspaper_probability)
incomes = list(radio_income, television_income, newspaper_income)
combinations = get_combinations(probabilities, incomes)

for (combination in combinations) {
  probability = combination@probability_matrix
  income = combination@income_matrix
  strategy = combination@strategy
  
  solution = solve_infinity_iter(probability, income)
  #Вывод данных в консоль.
  cat(paste("Стратегия: ", toString(strategy), "\n"))
  cat("Входные данные. Матрица переходных вероятностей:\n")
  print(probability)
  cat("Входные данные. Матрица доходов:\n")
  print(income)
  cat(paste("Итоговый ожидаемый годовой доход: ", toString(solution), "\n\n\n\n\n"))
}