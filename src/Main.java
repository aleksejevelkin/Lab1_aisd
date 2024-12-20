import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

class Sorts {

    static class ShellSortPratt {

        // Функция для генерации последовательности Пратта
        private static int[] generatePrattSequence(int n) {
            ArrayList<Integer> pratt = new ArrayList<>();
            int i = 1, j = 1;

            // Генерация шагов Пратта в пределах размера массива n
            while (i <= n) {
                j = i;
                while (j <= n) {
                    pratt.add(j);
                    j *= 3;
                }
                i *= 2;
            }

            // Преобразуем список в массив и сортируем его по убыванию
            int[] sequence = pratt.stream().mapToInt(x -> x).sorted().toArray();
            return sequence;
        }

        public static void shellSortPratt(int[] array) {
            int n = array.length;
            long startTime = System.currentTimeMillis();
            int[] gaps = generatePrattSequence(n); // Получаем последовательность Пратта
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;

            System.out.println(time+'\n');
            // Основной цикл сортировки
            for (int gap : gaps) {
                for (int i = gap; i < n; i++) {
                    int temp = array[i];
                    int j = i;
                    while (j >= gap && array[j - gap] > temp) {
                        array[j] = array[j - gap];
                        j -= gap;
                    }
                    array[j] = temp;
                }
            }
        }

    }




    static class HeapSort {

        public static void heapSort(int[] array) {
            int n = array.length;
            for (int i = n / 2 - 1; i >= 0; i--) {
                heapify(array, n, i);
            }

            for (int i = n - 1; i > 0; i--) {

                int temp = array[0];
                array[0] = array[i];
                array[i] = temp;

                heapify(array, i, 0);
            }
        }


        private static void heapify(int[] array, int n, int i) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;


            if (left < n && array[left] > array[largest]) {
                largest = left;
            }


            if (right < n && array[right] > array[largest]) {
                largest = right;
            }


            if (largest != i) {

                int swap = array[i];
                array[i] = array[largest];
                array[largest] = swap;

                heapify(array, n, largest);
            }
        }
    }

    public static void hibbardSort(int[] array) {
        int n = array.length;
        int k = 1;

         //Находим максимальное значение k для h_k < n
        while ((1 << k) - 1 < n) k++;

        for (int gap = (1 << (k - 1)) - 1; gap > 0; gap = (1 << --k) - 1) {
            for (int i = gap; i < n; i++) {
                int temp = array[i];
                int j;
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = temp;
            }
        }
    }


    public static void shellSort(int[] array) {

        for (int gap = array.length / 2; gap > 0; gap /= 2) {

            for (int i = gap; i < array.length; i++) {
                int temp = array[i];
                int j = i;

                while (j >= gap && array[j - gap] > temp) {
                    array[j] = array[j - gap];
                    j -= gap;
                }
                array[j] = temp;
            }
        }
    }

    static class merge_sort{
        public static void mergeSort(int[] array, int left, int right) {
            if (left < right) {
                int mid = (left + right) / 2;
                mergeSort(array, left, mid);
                mergeSort(array, mid + 1, right);
                merge(array, left, mid, right);
            }
        }

        public static void merge(int[] array, int left, int mid, int right) {

            int n1 = mid - left + 1;
            int n2 = right - mid;

            int[] leftArray = new int[n1];
            int[] rightArray = new int[n2];

            System.arraycopy(array, left, leftArray, 0, n1);
            System.arraycopy(array, mid + 1, rightArray, 0, n2);

            int i = 0, j = 0, k = left;

            while (i < n1 && j < n2) {
                if (leftArray[i] <= rightArray[j]) {
                    array[k] = leftArray[i];
                    i++;
                } else {
                    array[k] = rightArray[j];
                    j++;
                }
                k++;
            }

            while (i < n1) {
                array[k] = leftArray[i];
                i++;
                k++;
            }

            while (j < n2) {
                array[k] = rightArray[j];
                j++;
                k++;
            }
        }

    }

    public static void quickSort(int[] arr, int low, int high) {
        if (arr == null || arr.length == 0)
            return;

        if (low >= high)
            return;

        // выбираем опорный элемент
        int middle = low + (high - low) / 2;
        int pivot = arr[middle];

        //делаем left < pivot и right > pivot
        int i = low, j = high;
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }

            while (arr[j] > pivot) {
                j--;
            }

            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        // рекурсивно сортируем обе части
        if (low < j)
            quickSort(arr, low, j);

        if (high > i)
            quickSort(arr, i, high);
    }


    public static void selectionSort(int[] sortArr) {
        for (int i = 0; i < sortArr.length; i++) {
            int pos = i;
            int min = sortArr[i];

            for (int j = i + 1; j < sortArr.length; j++) {
                if (sortArr[j] < min) {
                    pos = j;
                    min = sortArr[j];
                }
            }
            sortArr[pos] = sortArr[i];

            sortArr[i] = min;
        }
    }

    public static void insertionSort(int[] sortArr) {
        int j;

        for (int i = 1; i < sortArr.length; i++) {

            int swap = sortArr[i];
            for (j = i; j > 0 && swap < sortArr[j - 1]; j--) {
               // элементы отсортированного сегмента перемещаем вперёд, если они больше элемента для вставки
                sortArr[j] = sortArr[j - 1];
            }
            sortArr[j] = swap;
        }
    }

    public static void bubbleSort3(int[] array) {
        int n = array.length; // Получаем длину массива
       //  Внешний цикл проходит по всему массиву
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;  //Переменная для отслеживания изменений в массиве
            // Внутренний цикл сравнивает элементы попарно и меняет их местами, если они расположены в неправильном порядке
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    // Меняем элементы местами, если текущий элемент больше следующего
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;  //Отмечаем, что произошло изменение
                }
            }
            // Если на текущей итерации не произошло обменов, массив уже отсортирован, выходим из цикла
            if (!swapped) {
                break;
            }
        }
    }

}

class Functions{


    public static void almost(int[] array){

        Functions.array_randomizer(array);

        Sorts.quickSort(array, 0, array.length/100*90);

    }

    public static void clearFile(String filePath) {
        File file = new File(filePath);
        try (FileWriter writer = new FileWriter(file)) {
           //  Записываем пустую строку в файл
            writer.write("");
            System.out.println("Файл успешно очищен: " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка при очистке файла: " + e.getMessage());
        }
    }


    public static void reverse_sort_array(int[] array){

        for(int i = 0; i<array.length; i++){
            array[i] = i;
        }

        Functions.reverse_sort(array);

    }

    public static void array_sort(int[] array){
        for(int i = 0; i<array.length; i++){
            array[i] = i;
        }
        Sorts.quickSort(array, 0, array.length-1);
    }

    public static void array_randomizer(int[] array){

        for(int i = 0; i<array.length; i++){
            array[i] = i;
        }

        Functions.shuffle(array);

    }

    public static long get_bubble_time(int[] array){
        long startTime = System.currentTimeMillis();
        Sorts.bubbleSort3(array);
        long endTime = System.currentTimeMillis();

        return endTime - startTime;
    }

    public static long get_selection_time(int[] array){
        long startTime = System.currentTimeMillis();
        Sorts.selectionSort(array);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static long get_insertion_time(int[] array){
        long startTime = System.currentTimeMillis();
        Sorts.insertionSort(array);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static long get_quick_time(int[] array){
        long startTime = System.currentTimeMillis();
        Sorts.quickSort(array, 0, array.length-1);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static long get_merge_time(int[] array){
        long startTime = System.currentTimeMillis();
        Sorts.merge_sort.mergeSort(array, 0, array.length-1);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static long get_shell_time(int[] array){
        long startTime = System.currentTimeMillis();
        Sorts.shellSort(array);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static long get_hibbard_time(int[] array){
        long startTime = System.currentTimeMillis();
        Sorts.hibbardSort(array);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }


    public static long get_pratt_time(int[] array){
        long startTime = System.currentTimeMillis();
        Sorts.ShellSortPratt.shellSortPratt(array);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }


    public static long get_heap_time(int[] array){
        long startTime = System.currentTimeMillis();
        Sorts.HeapSort.heapSort(array);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }



    public static void writeStringToFile(String content, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(content); // Записываем строку в файл
            writer.newLine(); // Добавляем новую строку (опционально)
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    public static void writeArrayToFile(int[] array, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (int elem : array) {
                writer.write(String.valueOf(elem)); // Записываем элемент
                writer.write(", "); // Добавляем пробел между элементами
            }
            writer.newLine(); // Добавляем новую строку после записи массива
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    public static void reverse_sort(int[] sortArr){

        for (int i = 0; i < sortArr.length / 2; i++) {
           //  Сохраняем текущее значение
            int temp = sortArr[i];
        //     Меняем местами элементы
            sortArr[i] = sortArr[sortArr.length - 1 - i];
            sortArr[sortArr.length - 1 - i] = temp;
        }
    }
    public static void shuffle(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
           //  Генерируем случайный индекс от 0 до i
            int j = random.nextInt(i + 1);
            // Меняем местами элементы array[i] и array[j]
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}

class Main {

    public static void main(String[] args) {

        int counter = 0;

        int[] random_bubble_time = new int[11];
        int[] sorted_bubble_time = new int[11];
        int[] almost_sorted_bubble_time = new int[11];
        int[] reverse_sorted_bubble_time = new int[11];

        int[] random_insertion_time = new int[11];
        int[] sorted_insertion_time = new int[11];
        int[] almost_sorted_insertion_time = new int[11];
        int[] reverse_sorted_insertion_time = new int[11];

        int[] random_selection_time = new int[11];
        int[] sorted_selection_time = new int[11];
        int[] almost_sorted_selection_time = new int[11];
        int[] reverse_sorted_selection_time = new int[11];

        int[] random_merge_time = new int[11];
        int[] sorted_merge_time = new int[11];
        int[] almost_sorted_merge_time = new int[11];
        int[] reverse_sorted_merge_time = new int[11];

        int[] random_quick_time = new int[11];
        int[] sorted_quick_time = new int[11];
        int[] almost_sorted_quick_time = new int[11];
        int[] reverse_sorted_quick_time = new int[11];

        int[] random_shell_time = new int[11];
        int[] sorted_shell_time = new int[11];
        int[] almost_sorted_shell_time = new int[11];
        int[] reverse_sorted_shell_time = new int[11];

        int[] random_heap_time = new int[11];
        int[] sorted_heap_time = new int[11];
        int[] almost_sorted_heap_time = new int[11];
        int[] reverse_sorted_heap_time = new int[11];

        int[] random_hibbard_time = new int[11];
        int[] sorted_hibbard_time = new int[11];
        int[] almost_sorted_hibbard_time = new int[11];
        int[] reverse_sorted_hibbard_time = new int[11];

        int[] random_pratt_time = new int[11];
        int[] sorted_pratt_time = new int[11];
        int[] almost_sorted_pratt_time = new int[11];
        int[] reverse_sorted_pratt_time = new int[11];




        for (int n=0; n<100001; n = n + 10000) {

            int[] sortArr = new int[n + 1];

            Functions.array_randomizer(sortArr);  //рандомный массив
            random_bubble_time[counter] = (int)Functions.get_bubble_time(sortArr);
            

            Functions.array_sort(sortArr);

            sorted_bubble_time[counter] = (int)Functions.get_bubble_time(sortArr);


            Functions.almost(sortArr);

            almost_sorted_bubble_time[counter] = (int) Functions.get_bubble_time(sortArr);


            Functions.reverse_sort_array(sortArr);

            reverse_sorted_bubble_time[counter] = (int) Functions.get_bubble_time(sortArr);


            Functions.array_randomizer(sortArr);

            random_insertion_time[counter] = (int) Functions.get_insertion_time(sortArr);
            

            Functions.array_sort(sortArr);

            sorted_insertion_time[counter] = (int) Functions.get_insertion_time(sortArr);


            Functions.almost(sortArr);

            almost_sorted_insertion_time[counter] = (int) Functions.get_insertion_time(sortArr);


            Functions.reverse_sort_array(sortArr);

            reverse_sorted_insertion_time[counter] = (int) Functions.get_insertion_time(sortArr);


            Functions.array_randomizer(sortArr);

            random_selection_time[counter] = (int) Functions.get_selection_time(sortArr);
            

            Functions.array_sort(sortArr);

            sorted_selection_time[counter] = (int) Functions.get_selection_time(sortArr);


            Functions.almost(sortArr);

            almost_sorted_selection_time[counter] = (int) Functions.get_selection_time(sortArr);


            Functions.reverse_sort_array(sortArr);

            reverse_sorted_selection_time[counter] = (int) Functions.get_selection_time(sortArr);


            Functions.array_randomizer(sortArr);

            random_merge_time[counter] = (int) Functions.get_merge_time(sortArr);
            

            Functions.array_sort(sortArr);

            sorted_merge_time[counter] = (int) Functions.get_merge_time(sortArr);


            Functions.almost(sortArr);

            almost_sorted_merge_time[counter] = (int) Functions.get_merge_time(sortArr);


            Functions.reverse_sort_array(sortArr);

            reverse_sorted_merge_time[counter] = (int) Functions.get_merge_time(sortArr);



            Functions.array_randomizer(sortArr);

            random_quick_time[counter] = (int) Functions.get_quick_time(sortArr);
            

            Functions.array_sort(sortArr);

            sorted_quick_time[counter] = (int) Functions.get_quick_time(sortArr);


            Functions.almost(sortArr);

            almost_sorted_quick_time[counter] = (int) Functions.get_quick_time(sortArr);


            Functions.reverse_sort_array(sortArr);

            reverse_sorted_quick_time[counter] = (int) Functions.get_quick_time(sortArr);


            Functions.array_randomizer(sortArr);

            random_shell_time[counter] = (int) Functions.get_shell_time(sortArr);
            

            Functions.array_sort(sortArr);

            sorted_shell_time[counter] = (int) Functions.get_shell_time(sortArr);


            Functions.almost(sortArr);

            almost_sorted_shell_time[counter] = (int) Functions.get_shell_time(sortArr);


            Functions.reverse_sort_array(sortArr);

            reverse_sorted_shell_time[counter] = (int) Functions.get_shell_time(sortArr);


            Functions.array_randomizer(sortArr);

            random_heap_time[counter] = (int) Functions.get_heap_time(sortArr);
            

            Functions.array_sort(sortArr);

            sorted_heap_time[counter] = (int) Functions.get_heap_time(sortArr);

            Functions.almost(sortArr);

            almost_sorted_heap_time[counter] = (int) Functions.get_heap_time(sortArr);

            Functions.reverse_sort_array(sortArr);

            reverse_sorted_heap_time[counter] = (int) Functions.get_heap_time(sortArr);


            Functions.array_randomizer(sortArr);

            random_hibbard_time[counter] = (int) Functions.get_hibbard_time(sortArr);
            

            Functions.array_sort(sortArr);

            sorted_hibbard_time[counter] = (int) Functions.get_hibbard_time(sortArr);

            Functions.almost(sortArr);

            almost_sorted_hibbard_time[counter] = (int) Functions.get_hibbard_time(sortArr);

            Functions.reverse_sort_array(sortArr);

            reverse_sorted_hibbard_time[counter] = (int) Functions.get_hibbard_time(sortArr);


            Functions.array_randomizer(sortArr);

            random_pratt_time[counter] = (int) Functions.get_pratt_time(sortArr);
            

            Functions.array_sort(sortArr);

            sorted_pratt_time[counter] = (int) Functions.get_pratt_time(sortArr);

            Functions.almost(sortArr);

            almost_sorted_pratt_time[counter] = (int) Functions.get_pratt_time(sortArr);

            Functions.reverse_sort_array(sortArr);

            reverse_sorted_pratt_time[counter] = (int) Functions.get_pratt_time(sortArr);


            counter++;

        }

        Functions.clearFile("//Users/aleksejevelkin/IdeaProjects/laba1/src/bubble.txt");
        
        Functions.writeStringToFile("Random array, bubble_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/bubble.txt");
        Functions.writeArrayToFile(random_bubble_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/bubble.txt");

        Functions.writeStringToFile("Sorted array, bubble_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/bubble.txt");
        Functions.writeArrayToFile(sorted_bubble_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/bubble.txt");

        Functions.writeStringToFile("Almost sorted array, bubble_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/bubble.txt");
        Functions.writeArrayToFile(almost_sorted_bubble_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/bubble.txt");

        Functions.writeStringToFile("Reverse-sorted array, bubble_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/bubble.txt");
        Functions.writeArrayToFile(reverse_sorted_bubble_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/bubble.txt");



        Functions.clearFile("/Users/aleksejevelkin/IdeaProjects/laba1/src/insertion.txt");

        Functions.writeStringToFile("Random array, insertion_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/insertion.txt");
        Functions.writeArrayToFile(random_insertion_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/insertion.txt");

        Functions.writeStringToFile("Sorted array, insertion_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/insertion.txt");
        Functions.writeArrayToFile(sorted_insertion_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/insertion.txt");

        Functions.writeStringToFile("Almost sorted array, insertion_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/insertion.txt");
        Functions.writeArrayToFile(almost_sorted_insertion_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/insertion.txt");

        Functions.writeStringToFile("Reverse-sorted array, insertion_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/insertion.txt");
        Functions.writeArrayToFile(reverse_sorted_insertion_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/insertion.txt");



        Functions.clearFile("/Users/aleksejevelkin/IdeaProjects/laba1/src/selection.txt");

        Functions.writeStringToFile("Random array, selection_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/selection.txt");
        Functions.writeArrayToFile(random_selection_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/selection.txt");

        Functions.writeStringToFile("Sorted array, selection_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/selection.txt");
        Functions.writeArrayToFile(sorted_selection_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/selection.txt");

        Functions.writeStringToFile("Almost sorted array, selection_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/selection.txt");
        Functions.writeArrayToFile(almost_sorted_selection_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/selection.txt");

        Functions.writeStringToFile("Reverse-sorted array, selection_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/selection.txt");
        Functions.writeArrayToFile(reverse_sorted_selection_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/selection.txt");



        Functions.clearFile("/Users/aleksejevelkin/IdeaProjects/laba1/src/merge.txt");

        Functions.writeStringToFile("Random array, merge_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/merge.txt");
        Functions.writeArrayToFile(random_merge_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/merge.txt");

        Functions.writeStringToFile("Sorted array, merge_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/merge.txt");
        Functions.writeArrayToFile(sorted_merge_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/merge.txt");

        Functions.writeStringToFile("Almost sorted array, merge_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/merge.txt");
        Functions.writeArrayToFile(almost_sorted_merge_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/merge.txt");

        Functions.writeStringToFile("Reverse-sorted array, merge_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/merge.txt");
        Functions.writeArrayToFile(reverse_sorted_merge_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/merge.txt");

        Functions.clearFile("/Users/aleksejevelkin/IdeaProjects/laba1/src/quick.txt");

        Functions.writeStringToFile("Random array, quick_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/quick.txt");
        Functions.writeArrayToFile(random_quick_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/quick.txt");

        Functions.writeStringToFile("Sorted array, quick_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/quick.txt");
        Functions.writeArrayToFile(sorted_quick_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/quick.txt");

        Functions.writeStringToFile("Almost sorted array, quick_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/quick.txt");
        Functions.writeArrayToFile(almost_sorted_quick_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/quick.txt");

        Functions.writeStringToFile("Reverse-sorted array, quick_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/quick.txt");
        Functions.writeArrayToFile(reverse_sorted_quick_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/quick.txt");

        Functions.clearFile("/Users/aleksejevelkin/IdeaProjects/laba1/src/shell.txt");

        Functions.writeStringToFile("Random array, shell_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/shell.txt");
        Functions.writeArrayToFile(random_shell_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/shell.txt");

        Functions.writeStringToFile("Sorted array, shell_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/shell.txt");
        Functions.writeArrayToFile(sorted_shell_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/shell.txt");

        Functions.writeStringToFile("Almost sorted array, shell_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/shell.txt");
        Functions.writeArrayToFile(almost_sorted_shell_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/shell.txt");

        Functions.writeStringToFile("Reverse-sorted array, shell_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/shell.txt");
        Functions.writeArrayToFile(reverse_sorted_shell_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/shell.txt");



        Functions.clearFile("/Users/aleksejevelkin/IdeaProjects/laba1/src/heap.txt");

        Functions.writeStringToFile("Random array, heap_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/heap.txt");
        Functions.writeArrayToFile(random_heap_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/heap.txt");

        Functions.writeStringToFile("Sorted array, heap_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/heap.txt");
        Functions.writeArrayToFile(sorted_heap_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/heap.txt");

        Functions.writeStringToFile("Almost sorted array, heap_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/heap.txt");
        Functions.writeArrayToFile(almost_sorted_heap_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/heap.txt");

        Functions.writeStringToFile("Reverse-sorted array, heap_sort:", "/Users/aleksejevelkin/IdeaProjects/laba1/src/heap.txt");
        Functions.writeArrayToFile(reverse_sorted_heap_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/heap.txt");



        Functions.clearFile("/Users/aleksejevelkin/IdeaProjects/laba1/src/hibbard.txt");

        Functions.writeStringToFile("Random array, shell_sort(Hibbard sequence):", "/Users/aleksejevelkin/IdeaProjects/laba1/src/hibbard.txt");
        Functions.writeArrayToFile(random_hibbard_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/hibbard.txt");

        Functions.writeStringToFile("Sorted array, shell_sort(Hibbard sequence):", "/Users/aleksejevelkin/IdeaProjects/laba1/src/hibbard.txt");
        Functions.writeArrayToFile(sorted_hibbard_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/hibbard.txt");

        Functions.writeStringToFile("Almost sorted array, shell_sort(Hibbard sequence):", "/Users/aleksejevelkin/IdeaProjects/laba1/src/hibbard.txt");
        Functions.writeArrayToFile(almost_sorted_hibbard_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/hibbard.txt");

        Functions.writeStringToFile("Reverse-sorted array, shell_sort(Hibbard sequence):", "/Users/aleksejevelkin/IdeaProjects/laba1/src/hibbard.txt");
        Functions.writeArrayToFile(reverse_sorted_hibbard_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/hibbard.txt");




        Functions.clearFile("/Users/aleksejevelkin/IdeaProjects/laba1/src/pratt.txt");

        Functions.writeStringToFile("Random array, shell_sort(Pratt sequence):", "/Users/aleksejevelkin/IdeaProjects/laba1/src/pratt.txt");
        Functions.writeArrayToFile(random_pratt_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/pratt.txt");

        Functions.writeStringToFile("Sorted array, shell_sort(Pratt sequence):", "/Users/aleksejevelkin/IdeaProjects/laba1/src/pratt.txt");
        Functions.writeArrayToFile(sorted_pratt_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/pratt.txt");

        Functions.writeStringToFile("Almost sorted array, shell_sort(Pratt sequence):", "/Users/aleksejevelkin/IdeaProjects/laba1/src/pratt.txt");
        Functions.writeArrayToFile(almost_sorted_pratt_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/pratt.txt");

        Functions.writeStringToFile("Reverse-sorted array, shell_sort(Pratt sequence):", "/Users/aleksejevelkin/IdeaProjects/laba1/src/pratt.txt");
        Functions.writeArrayToFile(reverse_sorted_pratt_time, "/Users/aleksejevelkin/IdeaProjects/laba1/src/pratt.txt");

    }

}

