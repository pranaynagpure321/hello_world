
 class Node{

    int data;
    Node left;
    Node right;

}

class BST{


   public Node GetnewNode(int data){

       Node node = new Node();
       node.data =data;
       node.left=null;
       node.right=null;
       return node;
   }

     public Node  insert(Node node,int data){

        if (node == null)
            return GetnewNode(data);

        else if (data < node.data)
            node.left =insert(node.left,data);

        else
        {
            node.right =insert(node.right,data);
        }

        return node;
    }


    public Node delete(Node node,int data){

       if (node == null)
           return null;

       else if(data < node.data )
           node.left = delete(node.left,data);


       else if(data>node.data){
           node.right = delete(node.right , data);
       }

       else {

           if (node.left == null || node.right == null){

               Node temp = null;

               temp = node.right==null ? node.left : node.right;


               if (temp == null)
                   return null;
               else return temp;



           }

           else {

               Node succesor = getSuccessor(node);
               node.data = succesor.data;
               node.right = delete(node.right,succesor.data);
               return node;

           }
       }

       return node;
    }

    public Node getSuccessor(Node node){

       if (node == null)
           return null;

       Node temp= node.right;

       while (temp.left !=null){
           temp = temp.left;
       }

       return  temp;


    }


    public void inorder(Node node){

       if (node==null)
           return;


       inorder(node.left);
        System.out.print(node.data+",");
        inorder(node.right);
    }

    public void preorder(Node node){

       if (node==null)
           return;

        System.out.print(node.data+",");
        preorder(node.left);
        preorder(node.right);
    }

    public void postorder(Node node){

       if (node==null)
           return;

        postorder(node.left);
        postorder(node.right);

        System.out.print(node.data+",");
    }

    public boolean isPresent(Node node, int data){

       if (node==null)
           return false;

       boolean ispresent = false;


       while (node!=null){

           if (node.data> data)
               node = node.left;

           else if (node.data<data)
               node = node.right;

           else {
               ispresent = true;
               break;
           }


       }

       return ispresent;


    }

    public Node getParentNode(Node node,int data){

       if (node==null)
           return null;

       Node parentNode= null;

       while (node!=null){
           if (data< node.data) {
               parentNode = node;
               node= node.left;
           }

          else if (data> node.data){
               parentNode = node;
               node = node.right;
           }
           else break;
       }

       return node!= null ? parentNode : null;
    }



}

public class BstApp {

   static Node root;

    public static void main(String[] args) {

       BST a = new BST();

        root = a.insert(root,8);
         a.insert(root,3);
         a.insert(root,1);
         a.insert(root,6);
         a.insert(root,4);
         a.insert(root,7);
         a.insert(root,10);
         a.insert(root,14);
         a.insert(root,13);

        // root = a.delete(root, 8);

        a.inorder(root);
        System.out.println();
        a.preorder(root);
        System.out.println();
        a.postorder(root);
        System.out.println();

        Node parent =  a.getParentNode(root,100);

        if (parent==null)
            System.out.println("does not exist");
        else System.out.println(parent.data);


    }
}
