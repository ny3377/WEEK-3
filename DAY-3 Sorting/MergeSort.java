import java.util.*;
class MergeSort{
    public static void mergeSort(int[] arr,int left, int right){
        if(left<right){
            int mid = (left+right)/2;
            mergeSort(arr,left,mid);
            mergeSort(arr,mid+1,right);
            merge(arr,left,mid,right);
        }
    }

    public static void merge(int[] arr,int left,int mid,int right){
        int n1 = mid-left+1;
        int n2 = right - mid;

        int[] larr = new int[n1];
        int[] rarr = new int[n2];
        for(int i=0;i<n1;i++){
            larr[i] = arr[left+i];
        }
        for(int j=0;j<n2;j++){
            rarr[j] = arr[mid+1+j];
        }
        int i=0,j=0,k=left;

        while(i<n1 && j<n2){
            if(larr[i]<=rarr[j]){
                arr[k]=larr[i];
                i++;
            }else{
                arr[k]=rarr[j];
                j++;
            }
            k++;
        }

        while (i<n1){
            arr[k]=larr[i];
            i++;
            k++;
        }
        while(j<n2){
            arr[k]=rarr[j];
            j++;
            k++;
        }
    }

    public static void printArr(int[] arr){
        for(int price:arr){
            System.out.print(price+" ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        int[] bookPrice = {435, 373,254,856,586};
        int length = bookPrice.length;
        mergeSort(bookPrice,0,length-1);
        printArr(bookPrice);
    }
}