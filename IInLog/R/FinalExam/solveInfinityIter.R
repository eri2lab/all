# Решить задачу принятия решений методом
# полного перебора при бесконечном числе этапов.
#
# @param [Matrix] p Матрица переходных вероятностей.
# @param [Matrix] p Матрица доходов.
#
# @return [Number] Ожидаемый годовой доход.
solveInfinityIter <- function(p, r) {
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
  
  #Вывод данных в консоль.
  cat("Входные данные. Матрица переходных вероятностей:\n")
  print(p)
  cat("\n")
  cat("Входные данные. Матрица доходов:\n")
  print(r)
  cat(paste("\n\nОжидаемый доход за 1 этап: ",toString(rowsSum)))
  cat("\n\nСоставлена система уравнений:\nМатрица коэффициентов:\n")
  print(A)
  cat("Вектор решений:\n")
  print(B)
  cat("\nРешение системы уравнений")
  print(solution)
  cat(paste("\nОжидаемый годовой доход по каждой переменной: ", toString(income), "\n"))
  cat(paste("Итоговый ожидаемый годовой доход: ", toString(totalIncome), "\n"))
  return(totalIncome)
}