package TP1;

public class Main
{
    public static void main(String[] args)
    {
        BinaryTree<String> me = new BinaryTree<>("me");
        me.setLeftChild(new BinaryTree<>("mother"));
        me.setRightChild(new BinaryTree<>("father"));

        System.out.println(me);
    }
}
