import java.util.Arrays;
import java.util.Scanner;

public class CODE1 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int t= Integer.parseInt(in.nextLine());

        for (int i = 0 ; i<t ; i++ ) {

            int n = in.nextInt();

            int q = in.nextInt();

            int[] arr = inputarr(in,n);

            int[] querys = inputarr(in,q);

            for (int j = 0; j < q; j++) {


                     String direction = direction(querys,j,arr);
                     if (direction.contains("No"))
                         continue;
                     else if (direction.contains("-1"))
                         performOperation(arr,querys[j]);

                     else performUptoOperation(arr,querys[j],direction);


            }

            printArr(arr);
        }


    }


    public static String direction(int[] queries,int currentQueryindex,int[] arr){

        int upto = -1;

        if (currentQueryindex==0)
            return  ""+upto;

       for (int i = currentQueryindex-1 ; i>=0 ; i-- )
       {
           int previousQury = queries[i];
           int currentQuery  = queries[currentQueryindex];
           if (currentQuery > previousQury && arr[previousQury]>arr[currentQuery])
               return "No need";
           else if(currentQuery < previousQury && arr[previousQury]>arr[currentQuery])
               upto =previousQury;
       }

       return ""+upto;
    }

    public static void printArr(int[] arr){

        System.out.println(Arrays.toString(arr));
    }

    public static int[] inputarr(Scanner in,int n ){

        int[] arr = new int[n];

        for (int  i = 0 ;i < n ;i++){

            arr[i] = in.nextInt();
        }

        return arr;

    }

    public static void performUptoOperation(int[] arr, int q,String upto){

        if (arr[q]==0)
            return;

        int u = Integer.parseInt(upto);

        for (int i = q+1 ;i<u ;i++) {
            if (arr[i] == 0)
                continue;
            else if (arr[i] < arr[q])
                arr[i] = 0;
        }

    }

    public static void performOperation(int[] arr, int q){

        if (arr[q]==0)
            return;


        for (int i = q+1 ;i<arr.length ;i++) {
            if (arr[i] == 0)
                continue;
            else if (arr[i] < arr[q])
                arr[i] = 0;
        }

    }
}
