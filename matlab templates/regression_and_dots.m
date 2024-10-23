
% ШАБЛОН ДЛЯ РЕГРЕССИИ И ЭКСПЕРИМЕНТАЛЬНЫХ ТОЧЕК НА ГРАФИКЕ

x = [0, 10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000];

y_1 = [0, 1, 0, 2, 1, 2, 2, 3, 3, 3, 4];

p1 = polyfit(x, y_1, 2);

x_values = linspace(0, 100000, 1000); 

y_values_1 = polyval(p1, x_values); 

figure;

hold on;

plot(x_values, y_values_1, 'DisplayName', 'График функции временной сложности', 'LineWidth', 1.5); 

plot(x, y_1, 'o', 'MarkerSize', 8, 'DisplayName', 'Экспериментальные точки', 'MarkerEdgeColor', 'k', 'MarkerFaceColor', 'r');


xlabel('N'); 

ylabel('T(N)'); 

title('MergeSort - reverse'); 

legend show; 

grid on; 