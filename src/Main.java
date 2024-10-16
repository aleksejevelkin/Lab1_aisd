import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

class Sorts {

    static class merge_sort{
        public static void mergeSort(int[] array, int left, int right) {
            if (left < right) {
                int mid = (left + right) / 2;
                mergeSort(array, left, mid);
                mergeSort(array, mid + 1, right);
                merge(array, left, mid, right);
            }
        }

        // Метод для слияния двух отсортированных частей массива
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




    public static void quickSort(int[] sortArr, int low, int high) {
        //завершить,если массив пуст или уже нечего делить
        if (sortArr.length == 0 || low >= high) return;

        //выбираем опорный элемент
        int middle = low + (high - low) / 2;
        int border = sortArr[middle];

        //разделияем на подмассивы и меняем местами
        int i = low, j = high;
        while (i <= j) {
            while (sortArr[i] < border) i++;
            while (sortArr[j] > border) j--;
            if (i <= j) {
                int swap = sortArr[i];
                sortArr[i] = sortArr[j];
                sortArr[j] = swap;
                i++;
                j--;
            }
        }

        //рекурсия для сортировки левой и правой части
        if (low < j) quickSort(sortArr, low, j);
        if (high > i) quickSort(sortArr, i, high);
    }

    public static void selectionSort(int[] sortArr) {
        for (int i = 0; i < sortArr.length; i++) {
            int pos = i;
            int min = sortArr[i];
            //цикл выбора наименьшего элемента
            for (int j = i + 1; j < sortArr.length; j++) {
                if (sortArr[j] < min) {
                    //pos - индекс наименьшего элемента
                    pos = j;
                    min = sortArr[j];
                }
            }
            sortArr[pos] = sortArr[i];
            //меняем местами наименьший с sortArr[i]
            sortArr[i] = min;
        }
    }

    public static void insertionSort(int[] sortArr) {
        int j;
        //сортировку начинаем со второго элемента, т.к. считается, что первый элемент уже отсортирован
        for (int i = 1; i < sortArr.length; i++) {
            //сохраняем ссылку на индекс предыдущего элемента
            int swap = sortArr[i];
            for (j = i; j > 0 && swap < sortArr[j - 1]; j--) {
                //элементы отсортированного сегмента перемещаем вперёд, если они больше элемента для вставки
                sortArr[j] = sortArr[j - 1];
            }
            sortArr[j] = swap;
        }
    }


    public static void bubbleSort(int[] sortArr) {
        for (int i = 0; i < sortArr.length - 1; i++) {
            for (int j = 0; j < sortArr.length - i - 1; j++) {
                if (sortArr[j + 1] < sortArr[j]) {
                    int swap = sortArr[j];
                    sortArr[j] = sortArr[j + 1];
                    sortArr[j + 1] = swap;
                }
            }
        }
    }

    public static void bubbleSort3(int[] array) {
        int n = array.length; // Получаем длину массива
        // Внешний цикл проходит по всему массиву
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false; // Переменная для отслеживания изменений в массиве
            // Внутренний цикл сравнивает элементы попарно и меняет их местами, если они расположены в неправильном порядке
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    // Меняем элементы местами, если текущий элемент больше следующего
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true; // Отмечаем, что произошло изменение
                }
            }
            // Если на текущей итерации не произошло обменов, массив уже отсортирован, выходим из цикла
            if (!swapped) {
                break;
            }
        }
    }

    public static void bubbleSort2(int[] arr) {
        int swapov = 0;
        int obhodov = 0;
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            obhodov++;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Меняем местами arr[j] и arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                    swapov++;
                }
            }

            // Если за проход не было обменов, массив уже отсортирован
            if (!swapped) {
                break;
            }

        }
        System.out.print("Свапов: " + swapov + "\n");
    }
}

class Functions{

    public static void almost(int[] array){

        for(int i = 0; i<array.length; i++){
            array[i] = i;
        }

        Functions.shuffle(array);

        Sorts.quickSort(array, array.length/100*90, array.length-1);

    }

    public static void clearFile(String filePath) {
        File file = new File(filePath);
        try (FileWriter writer = new FileWriter(file)) {
            // Записываем пустую строку в файл
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

    public static void almost_sort_array(int[] array){

        for(int i = 0; i<array.length; i++){
            array[i] = i;
        }

        Functions.almost_sort(array);
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

    public static void almost_sort(int[] sortArr){

        for (int i = 0; i<sortArr.length-1; i = i + 20) {

            int temp = sortArr[i];

            sortArr[i] = sortArr[i + 10];
            sortArr[i + 10] = temp;
        }

    }

    public static void reverse_sort(int[] sortArr){

        for (int i = 0; i < sortArr.length / 2; i++) {
            // Сохраняем текущее значение
            int temp = sortArr[i];
            // Меняем местами элементы
            sortArr[i] = sortArr[sortArr.length - 1 - i];
            sortArr[sortArr.length - 1 - i] = temp;
        }
    }
    public static void shuffle(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            // Генерируем случайный индекс от 0 до i
            int j = random.nextInt(i + 1);
            // Меняем местами элементы array[i] и array[j]
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}

class Main {

    public static void main(String args[]) {

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

        int[] random_quick_time = new int[11];
        int[] sorted_quick_time = new int[11];
        int[] almost_sorted_quick_time = new int[11];
        int[] reverse_sorted_quick_time = new int[11];

        int[] random_merge_time = new int[11];
        int[] sorted_merge_time = new int[11];
        int[] almost_sorted_merge_time = new int[11];
        int[] reverse_sorted_merge_time = new int[11];





        for (int n=0; n<100001; n = n + 10000){

            int[] sortArr = new int[n+1];

            Functions.array_randomizer(sortArr); // рандомный массив
            random_bubble_time[counter] = (int)Functions.get_bubble_time(sortArr);
            System.out.print("Время выполнения алгоритма bubble sort с случайным массивом при N = " + n + ": " + Functions.get_bubble_time(sortArr) + " МС. \n");

            Functions.array_sort(sortArr);

            sorted_bubble_time[counter] = (int)Functions.get_bubble_time(sortArr);

            System.out.print("Время выполнения алгоритма bubble sort с отсортированным массивом при N = " + n + ": " + Functions.get_bubble_time(sortArr) + " МС.\n \n");

            Functions.almost(sortArr);

            almost_sorted_bubble_time[counter] = (int)Functions.get_bubble_time(sortArr);
            System.out.print("Время выполнения алгоритма bubble sort с почти отсортированным массивом при N = " + n + ": " + Functions.get_bubble_time(sortArr) + " МС.\n \n");

            Functions.reverse_sort_array(sortArr);

            reverse_sorted_bubble_time[counter] = (int)Functions.get_bubble_time(sortArr);

            System.out.print("Время выполнения алгоритма bubble sort с обратно отсортированным массивом при N = " + n + ": " + Functions.get_bubble_time(sortArr) + " МС.\n \n");



            Functions.array_randomizer(sortArr);

            random_insertion_time[counter] = (int)Functions.get_insertion_time(sortArr);

            System.out.print("Время выполнения алгоритма insertion sort для рандомного массива при N = " + n + ": " + 0 + " МС.\n \n");

            Functions.array_sort(sortArr);

            sorted_insertion_time[counter] = (int)Functions.get_insertion_time(sortArr);

            System.out.print("Время выполнения алгоритма insertion sort для sort массива при N = " + n + ": " + 0 + " МС.\n \n");

           // Functions.almost(sortArr);

            Functions.almost(sortArr);

            almost_sorted_insertion_time[counter] = (int)Functions.get_insertion_time(sortArr);

            System.out.print("Время выполнения алгоритма insertion sort для почти sort массива при N = " + n + ": " + 0 + " МС.\n \n");

            Functions.reverse_sort_array(sortArr);

            reverse_sorted_insertion_time[counter] = (int)Functions.get_insertion_time(sortArr);

            System.out.print("Время выполнения алгоритма insertion sort для reverse sort массива при N = " + n + ": " + 0 + " МС.\n \n");



            Functions.array_randomizer(sortArr);

            random_selection_time[counter] = (int)Functions.get_selection_time(sortArr);

            System.out.print("Время выполнения алгоритма selection sort для рандомного массива при N = " + n + ": " + 0 + " МС.\n \n");

            Functions.array_sort(sortArr);

            sorted_selection_time[counter] = (int)Functions.get_selection_time(sortArr);

            System.out.print("Время выполнения алгоритма selection sort для sort массива при N = " + n + ": " + 0 + " МС.\n \n");

            Functions.almost(sortArr);

            almost_sorted_selection_time[counter] = (int)Functions.get_selection_time(sortArr);

            System.out.print("Время выполнения алгоритма selection sort для почти sort массива при N = " + n + ": " + 0 + " МС.\n \n");

            Functions.reverse_sort_array(sortArr);

            reverse_sorted_selection_time[counter] = (int)Functions.get_selection_time(sortArr);

            System.out.print("Время выполнения алгоритма selection sort для reverse sort массива при N = " + n + ": " + 0 + " МС.\n \n");







            Functions.array_randomizer(sortArr);

            random_quick_time[counter] = (int)Functions.get_quick_time(sortArr);

            System.out.print("Время выполнения алгоритма quick sort для рандомного массива при N = " + n + ": " + 0 + " МС.\n \n");

            Functions.array_sort(sortArr);

            sorted_quick_time[counter] = (int)Functions.get_quick_time(sortArr);

            System.out.print("Время выполнения алгоритма quick sort для sort массива при N = " + n + ": " + 0 + " МС.\n \n");

            Functions.almost(sortArr);

            almost_sorted_quick_time[counter] = (int)Functions.get_quick_time(sortArr);

            System.out.print("Время выполнения алгоритма quick sort для почти sort массива при N = " + n + ": " + 0 + " МС.\n \n");

            Functions.reverse_sort_array(sortArr);

            reverse_sorted_quick_time[counter] = (int)Functions.get_quick_time(sortArr);

            System.out.print("Время выполнения алгоритма quick sort для reverse sort массива при N = " + n + ": " + 0 + " МС.\n \n");


            Functions.array_randomizer(sortArr);

            random_merge_time[counter] = (int)Functions.get_merge_time(sortArr);

            System.out.print("Время выполнения алгоритма merge sort для рандомного массива при N = " + n + ": " + 0 + " МС.\n \n");

            Functions.array_sort(sortArr);

            sorted_merge_time[counter] = (int)Functions.get_merge_time(sortArr);

            System.out.print("Время выполнения алгоритма merge sort для sort массива при N = " + n + ": " + 0 + " МС.\n \n");

            Functions.almost(sortArr);

            almost_sorted_merge_time[counter] = (int)Functions.get_merge_time(sortArr);

            System.out.print("Время выполнения алгоритма merge sort для почти sort массива при N = " + n + ": " + 0 + " МС.\n \n");

            Functions.reverse_sort_array(sortArr);

            reverse_sorted_merge_time[counter] = (int)Functions.get_merge_time(sortArr);

            System.out.print("Время выполнения алгоритма merge sort для reverse sort массива при N = " + n + ": " + 0 + " МС.\n \n");

            counter++;

        }


        Functions.clearFile("//Users/aleksejevelkin/IdeaProjects/laba1/src/bubble.txt");

        Functions.writeStringToFile("Random array, bubble_sort:", "//Users/aleksejevelkin/IdeaProjects/laba1/src/bubble.txt");
        Functions.writeArrayToFile(random_bubble_time, "//Users/aleksejevelkin/IdeaProjects/laba1/src/bubble.txt");

        Functions.writeStringToFile("Sorted array, bubble_sort:", "//Users/aleksejevelkin/IdeaProjects/laba1/src/bubble.txt");
        Functions.writeArrayToFile(sorted_bubble_time, "//Users/aleksejevelkin/IdeaProjects/laba1/src/bubble.txt");

        Functions.writeStringToFile("Almost sorted array, bubble_sort:", "//Users/aleksejevelkin/IdeaProjects/laba1/src/bubble.txt");
        Functions.writeArrayToFile(almost_sorted_bubble_time, "//Users/aleksejevelkin/IdeaProjects/laba1/src/bubble.txt");

        Functions.writeStringToFile("Reverse-sorted array, bubble_sort:", "//Users/aleksejevelkin/IdeaProjects/laba1/src/bubble.txt");
        Functions.writeArrayToFile(reverse_sorted_bubble_time, "//Users/aleksejevelkin/IdeaProjects/laba1/src/bubble.txt");


        Functions.clearFile("//Users/aleksejevelkin/IdeaProjects/laba1/src/insertion.txt");

        Functions.writeStringToFile("Random array, insertion_sort:", "//Users/aleksejevelkin/IdeaProjects/laba1/src/insertion.txt");
        Functions.writeArrayToFile(random_insertion_time, "//Users/aleksejevelkin/IdeaProjects/laba1/src/insertion.txt");

        Functions.writeStringToFile("Sorted array, insertion_sort:", "//Users/aleksejevelkin/IdeaProjects/laba1/src/insertion.txt");
        Functions.writeArrayToFile(sorted_insertion_time, "//Users/aleksejevelkin/IdeaProjects/laba1/src/insertion.txt");

        Functions.writeStringToFile("Almost sorted array, insertion_sort:", "//Users/aleksejevelkin/IdeaProjects/laba1/src/insertion.txt");
        Functions.writeArrayToFile(almost_sorted_insertion_time, "//Users/aleksejevelkin/IdeaProjects/laba1/src/insertion.txt");

        Functions.writeStringToFile("Reverse-sorted array, insertion_sort:", "//Users/aleksejevelkin/IdeaProjects/laba1/src/insertion.txt");
        Functions.writeArrayToFile(reverse_sorted_insertion_time, "//Users/aleksejevelkin/IdeaProjects/laba1/src/insertion.txt");


        Functions.clearFile("//Users/aleksejevelkin/IdeaProjects/laba1/src/selection.txt");

        Functions.writeStringToFile("Random array, selection_sort:", "//Users/aleksejevelkin/IdeaProjects/laba1/src/selection.txt");
        Functions.writeArrayToFile(random_selection_time, "//Users/aleksejevelkin/IdeaProjects/laba1/src/selection.txt");

        Functions.writeStringToFile("Sorted array, selection_sort:", "//Users/aleksejevelkin/IdeaProjects/laba1/src/selection.txt");
        Functions.writeArrayToFile(sorted_selection_time, "//Users/aleksejevelkin/IdeaProjects/laba1/src/selection.txt");

        Functions.writeStringToFile("Almost sorted array, selection_sort:", "//Users/aleksejevelkin/IdeaProjects/laba1/src/selection.txt");
        Functions.writeArrayToFile(almost_sorted_selection_time, "//Users/aleksejevelkin/IdeaProjects/laba1/src/selection.txt");

        Functions.writeStringToFile("Reverse-sorted array, selection_sort:", "//Users/aleksejevelkin/IdeaProjects/laba1/src/selection.txt");
        Functions.writeArrayToFile(reverse_sorted_selection_time, "//Users/aleksejevelkin/IdeaProjects/laba1/src/selection.txt");


        Functions.clearFile("//Users/aleksejevelkin/IdeaProjects/laba1/src/quick.txt");

        Functions.writeStringToFile("Random array, quick_sort:", "//Users/aleksejevelkin/IdeaProjects/laba1/src/quick.txt");
        Functions.writeArrayToFile(random_quick_time, "//Users/aleksejevelkin/IdeaProjects/laba1/src/quick.txt");

        Functions.writeStringToFile("Sorted array, quick_sort:", "//Users/aleksejevelkin/IdeaProjects/laba1/src/quick.txt");
        Functions.writeArrayToFile(sorted_quick_time, "//Users/aleksejevelkin/IdeaProjects/laba1/src/quick.txt");

        Functions.writeStringToFile("Almost sorted array, quick_sort:", "//Users/aleksejevelkin/IdeaProjects/laba1/src/quick.txt");
        Functions.writeArrayToFile(almost_sorted_quick_time, "//Users/aleksejevelkin/IdeaProjects/laba1/src/quick.txt");

        Functions.writeStringToFile("Reverse-sorted array, quick_sort:", "//Users/aleksejevelkin/IdeaProjects/laba1/src/quick.txt");
        Functions.writeArrayToFile(reverse_sorted_quick_time, "//Users/aleksejevelkin/IdeaProjects/laba1/src/quick.txt");


        Functions.clearFile("//Users/aleksejevelkin/IdeaProjects/laba1/src/merge.txt");

        Functions.writeStringToFile("Random array, merge_sort:", "//Users/aleksejevelkin/IdeaProjects/laba1/src/merge.txt");
        Functions.writeArrayToFile(random_merge_time, "//Users/aleksejevelkin/IdeaProjects/laba1/src/merge.txt");

        Functions.writeStringToFile("Sorted array, merge_sort:", "//Users/aleksejevelkin/IdeaProjects/laba1/src/merge.txt");
        Functions.writeArrayToFile(sorted_merge_time, "//Users/aleksejevelkin/IdeaProjects/laba1/src/merge.txt");

        Functions.writeStringToFile("Almost sorted array, merge_sort:", "//Users/aleksejevelkin/IdeaProjects/laba1/src/merge.txt");
        Functions.writeArrayToFile(almost_sorted_merge_time, "//Users/aleksejevelkin/IdeaProjects/laba1/src/merge.txt");

        Functions.writeStringToFile("Reverse-sorted array, merge_sort:", "//Users/aleksejevelkin/IdeaProjects/laba1/src/merge.txt");
        Functions.writeArrayToFile(reverse_sorted_merge_time, "//Users/aleksejevelkin/IdeaProjects/laba1/src/merge.txt");


    }

}
