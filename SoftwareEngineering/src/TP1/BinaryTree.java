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

    public BinaryTree<T> lowestCommonAncestor(BinaryTree<T> n1, BinaryTree<T> n2)
    {
        if ((n1 == null) || (n2 == null))
            return null;

        if ((this.equals(n1) || this.equals(n2)))
            return this;

        BinaryTree<T> leftLCA = (this.leftChild != null) ? this.leftChild.lowestCommonAncestor(n1, n2) : null;
        BinaryTree<T> rightLCA = (this.rightChild != null) ? this.rightChild.lowestCommonAncestor(n1, n2) : null;

        if ((leftLCA != null) && (rightLCA != null))
            return this;

        return (leftLCA != null) ? leftLCA : rightLCA;
    }

    public int getHeight()
    {
        if ((leftChild == null) && (rightChild == null))
            return 1;

        if (leftChild == null)
            return rightChild.getHeight()+1;

        if (rightChild == null)
            return leftChild.getHeight()+1;

        return Math.max(leftChild.getHeight()+1, rightChild.getHeight()+1);
    }

    public int getNodeCount()
    {
        if ((leftChild == null) && (rightChild == null))
            return 1;

        if (leftChild == null)
            return rightChild.getNodeCount()+1;

        if (rightChild == null)
            return leftChild.getNodeCount()+1;

        return leftChild.getNodeCount() + rightChild.getNodeCount() + 1;
    }

    public BinaryTree<T> find(T value)
    {
        if (this.value.equals(value))
            return this;

        if ((leftChild == null) && (rightChild == null))
            return null;

        if (leftChild == null)
            return rightChild.find(value);

        if (rightChild == null)
            return leftChild.find(value);

        BinaryTree<T> left = leftChild.find(value);

        return (left == null) ? rightChild.find(value) : left;
    }

    public boolean remove(T value)
    {
        if ((leftChild != null) && (leftChild.getValue().equals(value)))
        {
            leftChild = null;
            return true;
        }

        if ((rightChild != null) && (rightChild.getValue().equals(value)))
        {
            rightChild = null;
            return true;
        }

        if ((leftChild != null) && (rightChild != null))
        {
            boolean left = leftChild.remove(value);

            if (left)
                return true;

            return rightChild.remove(value);
        }

        if (leftChild != null)
            return leftChild.remove(value);

        if (rightChild != null)
            return rightChild.remove(value);

        return false;
    }

    public void print(String prefix, String childrenPrefix)
    {
        System.out.println(prefix + value);
        if ((leftChild != null) && (rightChild != null))
        {
            rightChild.print(childrenPrefix + "├── ", childrenPrefix + "│   ");
            leftChild.print(childrenPrefix + "└── ", childrenPrefix + "    ");
        }
        else if (leftChild != null)
            leftChild.print(childrenPrefix + "└── ", childrenPrefix + "    ");
        else if (rightChild != null)
            rightChild.print(childrenPrefix + "└── ", childrenPrefix + "    ");
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
