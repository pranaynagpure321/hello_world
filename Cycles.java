import java.util.Arrays;
import java.util.LinkedList;

public class Cycles {

    int[][] adj;
    int v;
    String cycles = "";

    LinkedList<String> pastCycles;

    public  Cycles(int v){

        this.v = v;

        adj = new int[v][v];

        pastCycles = new LinkedList<String>();
    }

    public void addEdge(int source , int dest){

        adj[source][dest] = 1;

    }

    public boolean cyclesUtil(int i, boolean[] visited , boolean[] recStack , int vertex ) {

        if (recStack[i]) {
            return true;
        }

        if (visited[i]){
            return false;
        }

        visited[i] = true;

        recStack[i] = true;

        int col = 0;

        for (Integer c : adj[i]) {
            if (c==1 && cyclesUtil(col,visited, recStack,vertex) && col!=i) {

                cycles += i;

                    if (!isRecOne(recStack,i)){
                        recStack[i] = false;
                        visited[i]=false;
                    return true;}

                    else {
                        printCycle(vertex);

                    }

            }

            col++;
        }

        recStack[i] = false;
        return  false;

    }

    static boolean isRecOne(boolean[] recStack, int i ){
        int count = 0;

        for (int  c=0 ;c<recStack.length ; c++){

            if (recStack[c])
                count++;

            if(count>1)
                return false;

        }

        return true;

    }

    public void printCycle(int vertex)
    {
       String reverse =  "";

        for (int c =cycles.length()-1 ; c>=0 ; c--)
             reverse += cycles.charAt(c);

        if (pastCycles.isEmpty())
            pastCycles.add(reverse);

        else if (!isSame(reverse)){
            pastCycles.add(cycles);
        }

        cycles = ""+vertex;

       // System.out.println( );

    }

    public  boolean isSame(String cycles){

       char[] arr = cycles.toCharArray();
       boolean found = true;

       for(String  countedCycles : pastCycles) {


               for (int i = 0; i < cycles.length() - 1; i++) {
                   char c = cycles.charAt(i);
                   if(countedCycles.length()!=cycles.length())
                   {
                       found = false;
                       break;
                   }
                   if (countedCycles.contains(c + "")) {
                       found = found && true;
                   } else {
                       found = found && false;
                       break;

                   }

               }





           if (found)
           { return true; }


       }

     return  false;

    }

    public void cycles(){

        boolean[] recStack = new boolean[v];
        boolean[] visited  = new boolean[v];

        boolean[] dfsdone = new boolean[v];


        for (int i =0 ;i< v ;i++)
        {

            if (dfsdone[i])
                continue;
            cycles =""+ i;
            cyclesUtil(i,visited,recStack,i);

            for (int  j =0 ; j < v ;j++ )
                visited[j] = false;

            dfsdone[i] =true;
        }


    }


    public static void main(String[] args) {

                Cycles graph = new Cycles(4);

                graph.addEdge(0, 1);
                //graph.addEdge(0, 2);
                graph.addEdge(1, 2);
                graph.addEdge(2, 0);
                graph.addEdge(2, 3);
                graph.addEdge(3, 2);

                //graph.pastCycles.add("0120");



                graph.cycles();

                for (String a : graph.pastCycles)
                    System.out.println(a);




    }
}
