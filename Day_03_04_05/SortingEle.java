import java.util.*;
public class SortingEle {
    public static int method(int[] arr, boolean[] track) {
        int max = Integer.MIN_VALUE;
        int index = -1;
        for(int i=0;i<arr.length;i++){
            if(!track[i] && max< arr[i]){
                max = arr[i];
                index = i;
            }
        }
        return index;
    }
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter no.of elements: ");
        int n= sc.nextInt();
        int[] arr = new int[n];
        boolean[] track = new boolean[n];
        int[] sorted = new int[n];
        System.out.println("Enter "+ n + " elements: ");
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        for(int i=0;i<n;i++){
            int index =  method(arr ,track);
            if(index > -1 && !track[index]){
                sorted[i] =arr[index];
                track[index] =true;
            }
        }
        System.out.println("Sorted data: " + Arrays.toString(sorted));
    }
}