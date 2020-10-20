import java.util.*;

public class Zadanie5 {

    public static void reverse(int[] data) {
        for (int left = 0, right = data.length - 1; left < right; left++, right--) {
            int temp = data[left];
            data[left]  = data[right];
            data[right] = temp;
        }
    }

    public static int[] randomArrayGenerator(int n)
    {
        Random rand = new Random();
        int[] randomArray = new int[n];
        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = rand.nextInt();
        }
        return randomArray;
    }

    public static int[] upArrayGenerator(int n)
    {
        int[] sortedArray = new int[n];
        for (int i = 0; i < sortedArray.length; i++) {
            sortedArray[i] = i;
        }
        return sortedArray;
    }

    public static int[] downArrayGenerator(int n)
    {
        int[] sortedArray = new int[n];
        for (int i = 0; i < sortedArray.length; i++) {
        sortedArray[i] = i;
        }
        reverse(sortedArray);
        return sortedArray;
    }

    /*public static void insertionSort(int[] input, int i) {
        if (i <= 1) {
            return;
        }
        insertionSort(input, i - 1);
        int key = input[i - 1];
        int j = i - 2;
        while (j >= 0 && input[j] > key) {
            input[j + 1] = input[j];
            j = j - 1;
        }
        input[j + 1] = key;
    }*/

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i - 1;
            int temp = array[i];
            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
    }

    public static void mergeSort(int[] array, int low, int mid, int high) {
        int leftArray[] = new int[mid - low + 1];
        int rightArray[] = new int[high - mid];

        for (int i = 0; i < leftArray.length; i++)
            leftArray[i] = array[low + i];
        for (int i = 0; i < rightArray.length; i++)
            rightArray[i] = array[mid + i + 1];

        int leftIndex = 0;
        int rightIndex = 0;

        for (int i = low; i < high + 1; i++) {
            if (leftIndex < leftArray.length && rightIndex < rightArray.length) {
                if (leftArray[leftIndex] < rightArray[rightIndex]) {
                    array[i] = leftArray[leftIndex];
                    leftIndex++;
                } else {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            } else if (leftIndex < leftArray.length) {
                array[i] = leftArray[leftIndex];
                leftIndex++;
            } else if (rightIndex < rightArray.length) {
                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
    }

    public static void measureTime()
    {
        System.out.println("Get one number (int): ");
        Scanner scan = new Scanner(System.in);
        int numberOfElements = scan.nextInt();

        int[] randomArray = randomArrayGenerator(numberOfElements);
        int[] sortedArrayUp = upArrayGenerator(numberOfElements);
        int[] sortedArrayDown = downArrayGenerator(numberOfElements);

        System.out.println("Sorter Array Up: " + Arrays.toString(sortedArrayUp));
        System.out.println("Sorter Array Down: " + Arrays.toString(sortedArrayDown));
        System.out.println("Random Array: " + Arrays.toString(randomArray));



        System.out.println("InsertionSort times:");
        int[] sortedArrayUpIns = sortedArrayUp;
        int[] sortedArrayDownIns = sortedArrayDown;
        int[] randomArrayIns = randomArray;

        System.out.println("Sorted Array (Up): ");
        long startTime1 = System.nanoTime();
        insertionSort(sortedArrayUpIns);
        //System.out.println(Arrays.toString(sortedArrayUpIns));
        long estimatedTime1 = System.nanoTime() - startTime1;
        System.out.println("Time: " + estimatedTime1);

        System.out.println("Sorted Array (Down): ");
        long startTime2 = System.nanoTime();
        insertionSort(sortedArrayDownIns);
        //System.out.println(Arrays.toString(sortedArrayDownIns));
        long estimatedTime2 = System.nanoTime() - startTime2;
        System.out.println("Time: " + estimatedTime2);

        System.out.println("Random Array (Up): ");
        long startTime3 = System.nanoTime();
        insertionSort(randomArrayIns);
        //System.out.println(Arrays.toString(randomArrayIns));
        long estimatedTime3 = System.nanoTime() - startTime3;
        System.out.println("Time: " + estimatedTime3);



        System.out.println("MergeSort times:");
        int[] sortedArrayUpMer = sortedArrayUp;
        int[] sortedArrayDownMer = sortedArrayDown;
        int[] randomArrayMer = randomArray;

        System.out.println("Sorted Array (Up): ");
        long startTime4 = System.nanoTime();
        mergeSort(sortedArrayUpMer, 0, (sortedArrayUpMer.length-1)/2,sortedArrayUpMer.length-1);
        //System.out.println(Arrays.toString(sortedArrayUpMer));
        long estimatedTime4 = System.nanoTime() - startTime4;
        System.out.println("Time: " + estimatedTime4);

        System.out.println("Sorted Array (Down): ");
        long startTime5 = System.nanoTime();
        mergeSort(sortedArrayDownMer, 0, (sortedArrayDownMer.length-1)/2,sortedArrayDownMer.length-1);
        //System.out.println(Arrays.toString(sortedArrayDownMer));
        long estimatedTime5 = System.nanoTime() - startTime5;
        System.out.println("Time: " + estimatedTime5);

        System.out.println("Random Array (Up): ");
        long startTime6 = System.nanoTime();
        mergeSort(randomArrayMer, 0, (randomArrayMer.length-1)/2,randomArrayMer.length-1);
        //System.out.println(Arrays.toString(randomArrayMer));
        long estimatedTime6 = System.nanoTime() - startTime6;
        System.out.println("Time: " + estimatedTime6);

    }
}
