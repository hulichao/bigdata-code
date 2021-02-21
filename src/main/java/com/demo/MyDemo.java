package com.demo;

import java.util.Arrays;

public class MyDemo {
    public static void main(String[] args) {
        int[] faces = {1, 8, 8, 5, 2, 3, 1};
//        Arrays.sort(faces, (x, y) -> (y - x));
        quickSort(faces);
//
        for (Integer face : faces) {
            System.out.println(face);
        }
    }

    private static void quickSort(int[] arr) {
       quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int i, int j) {
        //找轴点
        if (i < j) {
            int viot = partition(arr, i, j);
            quickSort(arr, i, viot - 1);
            quickSort(arr, viot + 1, j);
        }
    }

    private static int partition(int[] arr, int i, int j) {
        int t = arr[i];
        while (i < j) {
            while (i < j && arr[j] >= t)
                j--;
            swap(arr, i, j);
            while (i < j && arr[i] <= t)
                i++;
            swap(arr, i, j);
        }
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
