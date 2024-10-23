
% ШАБЛОН ДЛЯ РЕГРЕССИИ

x = [0, 10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000];

y_1 = [0, 2, 2, 2, 4, 5, 6, 7, 9, 10, 11];
y_2 = [0, 1, 0, 1, 1, 1, 1, 1, 1, 2, 2];
y_3 = [0, 1, 2, 3, 5, 5, 7, 7, 9, 10, 11];
y_4 = [0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2];

p1 = polyfit(x, y_1, 2);
p2 = polyfit(x, y_2, 2);
p3 = polyfit(x, y_3, 2);
p4 = polyfit(x, y_4, 2);


x_values = linspace(0, 100000, 1000); 

y_values_1 = polyval(p1, x_values); 
y_values_2 = polyval(p2, x_values); 
y_values_3 = polyval(p3, x_values); 
y_values_4 = polyval(p4, x_values); 


figure;

hold on;

plot(x_values, y_values_1, 'DisplayName', 'random', 'LineWidth', 2); 
plot(x_values, y_values_2, 'DisplayName', 'sorted', 'LineWidth', 2); 
plot(x_values, y_values_3, 'DisplayName', '90/10', 'LineWidth', 2); 
plot(x_values, y_values_4, 'DisplayName', 'reverse', 'LineWidth', 2); 



xlabel('N'); 

ylabel('T(N)');

title('ShellSort - Последовательность Шелла'); 

legend show; % 

grid on; % 