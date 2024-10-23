
% ШАБЛОН ДЛЯ ТЕОРЕТИЧЕСКИХ ГРАФИКОВ

n = 0:100000;

t1 = n.^2;
t2 = n.^(3/2);
t3 = n .* log(n);


figure; 
hold on; 


plot(n, t1, '-r','LineWidth', 2, 'DisplayName', 'Best - T = n^2'); 
plot(n, t2, '-g', 'LineWidth', 2, 'DisplayName', 'Average - T = n^(3/2)'); 
plot(n, t3, '-b', 'LineWidth', 2, 'DisplayName', 'Worst - T = n*log(n)'); )


xlabel('n'); 
ylabel('t');
title('ShellSort - Последовательность Шелла'); 
legend show; 
grid on; 
hold off;