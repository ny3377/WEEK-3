import java.util.*;
class InsertionSort{

    public static void insertionSort(int[] arr){
        int l = arr.length;
        for(int i=1;i<l;i++){
            int key = arr[i];
            int j = i-1;
            while(j>=0 && arr[j]>key){
                arr[j+1]=arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }

    public static void printIds(int[] arr){
        for(int id:arr){
            System.out.print(id+" ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        int[] empIds = {765,775,758,725};
        insertionSort(empIds);
        printIds(empIds);
    }
}