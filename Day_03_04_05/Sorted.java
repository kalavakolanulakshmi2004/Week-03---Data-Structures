import java.util.*;
public class Sorted {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter no.of elements: ");
        int n= sc.nextInt();
        int[] arr =new int[n];
        System.out.print("Enter array elements: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int[] pattern ={2,1,8,3};
        List<Integer> res = new ArrayList<>();
        int[] freq = new int[100];
        for(int num: arr){
            freq[num]++;
        }
        for(int num: pattern){
            while(freq[num]>0){
                res.add(num);
                freq[num]--;
            }
        }
        for(int i=0;i< freq.length;i++){
            while(freq[i]>0){
                res.add(i);
                freq[i]--;
            }
        }
        System.out.println("Result is: "+ res);
    }
}