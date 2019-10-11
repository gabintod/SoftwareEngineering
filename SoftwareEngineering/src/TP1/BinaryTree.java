package TP1;

public class BinaryTree<T> extends BinaryNode
{
    public BinaryTree(Object value)
    {
        super(value);
    }

    public BinaryTree(Object value, BinaryNode leftChild, BinaryNode rightChild)
    {
        super(value, leftChild, rightChild);
    }

    public void putNode(BinaryNode<T> parent, BinaryNode<T> child, boolean rightChild)
    {
        if (find(parent.getValue()) == null)
            return;

        if (find(child.getValue()) != null)
            return;

        if (rightChild)
            parent.setRightChild(child);
        else
            parent.setLeftChild(child);
    }

    public void setValue(BinaryNode<T> node, T v)
    {
        if (find(node.getValue()) == null)
            return;

        if (find(v) != null)
            return;

        node.setValue(v);
    }

    public void print()
    {
        this.print("", "");
    }
}