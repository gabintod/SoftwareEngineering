package TP1;

public class BinaryTree<T>
{
    private T value;
    private BinaryTree<T> leftChild;
    private BinaryTree<T> rightChild;

    public BinaryTree(T value)
    {
        this.value = value;
    }

    public BinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightChild)
    {
        this(value);
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public T getValue()
    {
        return value;
    }

    public void setValue(T value)
    {
        this.value = value;
    }

    public BinaryTree<T> getLeftChild()
    {
        return leftChild;
    }

    public void setLeftChild(BinaryTree<T> leftChild)
    {
        this.leftChild = leftChild;
    }

    public BinaryTree<T> getRightChild()
    {
        return rightChild;
    }

    public void setRightChild(BinaryTree<T> rightChild)
    {
        this.rightChild = rightChild;
    }

    @Override
    public String toString()
    {
        return "BinaryTree{" +
                "value=" + value +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                '}';
    }
}
