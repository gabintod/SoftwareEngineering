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
        if (v == null)
            return null;

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

    ArrayList<DAGNode<T>> LCA(DAGNode<T> n1, DAGNode<T> n2)
    {
        ArrayList<LCAPack> packs;
        ArrayList<DAGNode<T>> nodes = null;
        if ((packs = subLCA(n1, n2)) != null)
        {
            nodes = new ArrayList<>();
            for (LCAPack p : packs)
                if (p.isComplete())
                    nodes.add(p.expeditor);
        }
        if (nodes.size() > 0)
            return nodes;
        return null;
    }

    ArrayList<LCAPack> subLCA(DAGNode<T> n1, DAGNode<T> n2)
    {
        if ((n1 == null) || (n2 == null))
            return null;

        ArrayList<LCAPack> savedPacks = new ArrayList<>();

        if (this.equals(n1))
            savedPacks.add(new LCAPack(this, true, false));
        else if (this.equals(n2))
            savedPacks.add(new LCAPack(this, false, true));

        ArrayList<LCAPack> packs;
        for (DAGNode<T> child : children)
        {
            if ((packs = child.subLCA(n1, n2)) != null)
            {
                for (LCAPack p : packs)
                {
                    if (p.isComplete())
                        savedPacks.add(p);
                    else
                    {
                        if (savedPacks.size() > 0)
                        {
                            for (LCAPack pack : savedPacks)
                            {
                                if ((!pack.isComplete()) && (p.canComplete(pack)))
                                {
                                    p.complete(pack, this);
                                    savedPacks.add(p);
                                    break;
                                }
                            }
                        }
                        else
                            savedPacks.add(p);
                    }
                }
            }
        }

        for (LCAPack pack : savedPacks)
        {
            if (pack.isComplete())
            {
                int maxDist = pack.dist;
                // remove non completed packs, define max dist, increment dist
                for (int i=savedPacks.size()-1; i>=0; i--)
                {
                    if (!savedPacks.get(i).isComplete())
                        savedPacks.remove(i);
                    else
                    {
                        savedPacks.get(i).dist ++;
                        if (savedPacks.get(i).dist > maxDist)
                            maxDist = savedPacks.get(i).dist;
                    }
                }
                //  delete non max dist packs
                for (int i=savedPacks.size()-1; i>=0; i--)
                {
                    if (savedPacks.get(i).dist != maxDist)
                        savedPacks.remove(i);
                }
                return savedPacks;
            }
        }

        if (savedPacks.size() > 0)
            return savedPacks;

        return null;
    }

    class LCAPack
    {
        DAGNode<T> expeditor;
        boolean n1;
        boolean n2;
        int dist; // distance from conpletion

        public LCAPack(DAGNode<T> expeditor, boolean n1, boolean n2)
        {
            this.expeditor = expeditor;
            this.n1 = n1;
            this.n2 = n2;
            dist = 0;
        }

        public boolean isComplete()
        {
            return n1 && n2;
        }

        public boolean canComplete(LCAPack pack)
        {
            return (n1 || pack.n1) && (n2 || pack.n2);
        }

        public void complete(LCAPack pack, DAGNode<T> exp)
        {
            expeditor = exp;
            n1 = n1 || pack.n1;
            n2 = n2 || pack.n2;
        }
    }

    void print(String prefix, String childrenPrefix)
    {
        System.out.println(prefix + value);
        for (int i=0; i<children.size(); i++)
        {
            if (i == children.size()-1)
                children.get(i).print(childrenPrefix + "└── ", childrenPrefix + "    ");
            else
                children.get(i).print(childrenPrefix + "├── ", childrenPrefix + "│   ");
        }
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
        if ((child == null) || (child.find(getValue()) != null))
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
