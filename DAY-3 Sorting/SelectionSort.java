import java.util.*;
class SelectionSort{
    public static void selectionSort(int[] scores){
        int n = scores.length;
        for(int i = 0;i<n-i;i++){
            int minIndex = i;
            for(int j =i+1;j<n;j++){
                if(scores[j]<scores[minIndex]){
                    minIndex=j;
                }
            }
            int temp = scores[minIndex];
            scores[minIndex]=scores[i];
            scores[i]=temp;
        }

    }
    public static void printArr(int[] arr){
        for(int score:arr){
            System.out.print(score+" ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        int[] scores = {74,99,24,56,21,12,12};
        selectionSort(scores);
        printArr(scores);
    }
}