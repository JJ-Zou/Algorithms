package com.zjj.Algorithms4TH.Chapter2_Sort.Comparable;

public class ComparableSort {

    /**
     * 冒泡排序
     * 改进的带标识的双向冒泡排序
     *
     * @param a
     */
    public static void bubbleSort(Comparable[] a) {
        int len = a.length;
        int left = 0;
        int right = len - 1;
        boolean flag;
        while (left < right) {
            flag = true;
            for (int i = left; i < right; i++) {
                if (less(a[i + 1], a[i])) {
                    swap(a, i, i + 1);
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
            right--;
            flag = true;
            for (int i = right; i > left; i--) {
                if (less(a[i], a[i - 1])) {
                    swap(a, i - 1, i);
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
            left++;
        }
    }

    /**
     * 选择排序
     *
     * @param a
     */
    public static void selectSort(Comparable[] a) {
        int len = a.length;
        for (int i = 0; i < len; i++) {
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (less(a[j], a[minIndex])) {
                    minIndex = j;
                }
            }
            swap(a, i, minIndex);
        }
    }

    /**
     * 插入排序
     *
     * @param a
     */
    public static void insertSort(Comparable[] a) {
        int len = a.length;
        for (int i = 0; i < len; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                swap(a, j, j - 1);
            }
        }
    }

    /**
     * 希尔排序
     *
     * @param a
     */
    public static void shellSort(Comparable[] a) {
        int len = a.length;
        int h = 1;
        while (h < len / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < len; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    swap(a, j, j - h);
                }
            }
            h /= 3;
        }
    }

    /**
     * 自顶向下的归并排序
     *
     * @param a
     */
    public static void mergeSort(Comparable[] a) {
        Comparable[] support = a.clone();
        mergeSort(a, support, 0, a.length);
    }

    private static void mergeSort(Comparable[] a, Comparable[] support, int left, int right) {
        int length = right - left;
        //数组长度小于7时改为插入排序
        if (length < 7) {
            for (int i = left; i < right; i++) {
                for (int j = i; j > left && less(a[j], a[j - 1]); j--) {
                    swap(a, j, j - 1);
                }
            }
            return;
        }
        int mid = (left + right) >>> 1;
        //小数组的插入排序结果放入support中
        //分治法
        //对左半边排序
        mergeSort(support, a, left, mid);
        //对右半边排序
        mergeSort(support, a, mid, right);
        //若a[mid - 1] <= a[mid] 则数组已有序
        if (support[mid - 1].compareTo(support[mid]) <= 0) {
            System.arraycopy(support, left, a, left, length);
            return;
        }
        //归并结果放入a中
        for (int i = left, p = left, q = mid; i < right; i++) {
            if (q >= right || (p < mid && less(support[p], support[q]))) {
                a[i] = support[p++];
            } else {
                a[i] = support[q++];
            }
        }
    }

    /**
     * 自底而上的归并排序
     *
     * @param a
     */
    public static void mergeSortBU(Comparable[] a) {
        int length = a.length;
        Comparable[] support = new Comparable[length];
        //归并的小数组大小从1开始倍增
        for (int size = 1; size < length; size *= 2) {
            for (int left = 0; left < length - size; left += size * 2) {
                //防止右侧溢出
                int high = (left + size * 2 < length) ? left + size * 2 : length;
                int mid = left + size;
                //若需要归并的部分已经有序则跳过
                if (a[mid - 1].compareTo(a[mid]) <= 0) {
                    continue;
                }
                //每次归并前将需要归并的部分放入辅助数组
                System.arraycopy(a, left, support, left, high - left);
                for (int i = left, p = left, q = mid; i < high; i++) {
                    if (q >= high || (p < mid && less(support[p], support[q]))) {
                        a[i] = support[p++];
                    } else {
                        a[i] = support[q++];
                    }
                }
            }
        }
    }

    /**
     * 快速排序
     *
     * @param a
     */
    public static void quickSort(Comparable[] a) {
        quickSort(a, 0, a.length - 1);
    }

    private static void quickSort(Comparable[] a, int left, int right) {
        if (right - left < 6) {
            for (int i = left; i <= right; i++) {
                for (int j = i; j > left && less(a[j], a[j - 1]); j--) {
                    swap(a, j, j - 1);
                }
            }
            return;
        }
        //二向切分
//        int pivot = partition(a, left, right);
        //三向切分
        int low = left;
        int high = right;
        int i = left + 1;
        Comparable v = a[left];
        while (i <= high) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                swap(a, low++, i++);
            } else if (cmp > 0) {
                swap(a, i, high--);
            } else {
                i++;
            }
        }
        quickSort(a, left, low - 1);
        quickSort(a, high + 1, right);
//        quickSort(a, left, pivot - 1);
//        quickSort(a, pivot + 1, right);
    }

    private static int partition(Comparable[] a, int left, int right) {
        int low = left;
        int high = right + 1;
        Comparable v = a[left];
        while (true) {
            while (less(a[++low], v)) {
                if (low == right) {
                    break;
                }
            }
            while (less(v, a[--high])) {
                if (high == left) {
                    break;
                }
            }
            if (low >= high) {
                break;
            }
            swap(a, low, high);
        }
        swap(a, left, high);
        return high;
    }

    /**
     * 堆排序
     *
     * @param a
     */
    public static void heapSort(Comparable[] a) {
        int length = a.length;
        int mid = (length - 1) >>> 1;
        for (int i = mid; i >= 0; i--) {
            sink(a, i, length - 1);
        }
        while (length > 1) {
            swap(a, 0, --length);
            sink(a, 0, length - 1);
        }
    }

    private static void sink(Comparable[] a, int left, int right) {
        while (left < ((right + 1) >>> 1)) {
            int max = (left << 1) + 1;
            if (max < right && less(a[max], a[max + 1])) {
                max++;
            }
            if (!less(a[left], a[max])) {
                break;
            }
            swap(a, left, max);
            left = max;
        }
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

}
