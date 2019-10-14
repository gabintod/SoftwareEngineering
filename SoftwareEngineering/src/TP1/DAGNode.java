package TP1;

import java.util.ArrayList;

public class DAGNode<T>
{
    private T value;
    private ArrayList<DAGNode> children;

    public DAGNode(T value)
    {
        this.value = value;
        children = new ArrayList<>();
    }

    // @TODO
    DAGNode<T> find(T v)
    {
        return null;
    }

    // @TODO
    DAGNode<T> LCA(DAGNode<T> n1, DAGNode<T> n2)
    {
        return null;
    }

    public T getValue()
    {
        return value;
    }

    public ArrayList<DAGNode> getChildren()
    {
        return children;
    }

    // @TODO
    public DAGNode<T> putChild(DAGNode<T> child)
    {
        return null;
    }

    @Override
    public String toString()
    {
        return "DAGNode{" +
                "value=" + value +
                ", children=" + children +
                '}';
    }
}
