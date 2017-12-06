# Knapsack problem
Решение задачи о ранце 0/1 с помощью **алогритма имитации отжига**.

Алгоритм следующий:

1. Создать начальное решение;
2. Оценить решение;
3. Изменить решение случайным образом;
4. Оценить новое решение;
5. Критерий допуска;
6. Уменьше температуры.

Программа практически полностью повторяет данный алгоритм. 

**INPUT:**
- количество индексов для ответа;
- размерность рюкзака;
- пары вес-стоимость.

**OUTPUT:**
- список, содержащий лучшую стоимость и комбинацию 0/1.

Для небольшого количества предметов (~10) была выбрана **начальная температура** равная 100.0 и определена как константа. Для большего количества итераций необходимо эксперементировать со значением температуры.  

**Конечная темература** была приравнена к 0.5 для того, чтобы алгоритм не выполнялся дольше, чем это необходимо.
