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

    DAGNode<T> find(T v)
    {
        if (value.equals(v))
            return this;

        DAGNode<T> node;

        for (DAGNode<T> child : children)
        {
            node = child.find(v);
            if (node != null)
                return node;
        }

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

    public DAGNode<T> putChild(DAGNode<T> child)
    {
        if (find(child.getValue()) != null)
            return null;

        children.add(child);
        return child;
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
