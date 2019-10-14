package TP1;

public class DirectAcyclicGraph<T> extends DAGNode
{
    public DirectAcyclicGraph(T value)
    {
        super(value);
    }

    public DAGNode<T> putChild(DAGNode<T> parent, DAGNode<T> child)
    {
        if (find(parent.getValue()) == null)
            return null;

        return parent.putChild(child);
    }
}
