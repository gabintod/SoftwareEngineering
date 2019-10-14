package TP1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class DirectAcyclicGraphTest
{
    DirectAcyclicGraph<Integer> dag;
    DAGNode<Integer>[] nodes;
    boolean wellInstancied;

    @BeforeEach
    void setUp()
    {
        nodes = new DAGNode[13];
        nodes[0] = dag = new DirectAcyclicGraph<>(1);

        for (int i=1; i<nodes.length; i++)
        {
            nodes[i] = new DAGNode<>(i+1);
        }

        wellInstancied = (dag.putChild(nodes[0], nodes[1]) != null);
        wellInstancied = wellInstancied && (dag.putChild(nodes[0], nodes[2]) != null);
        wellInstancied = wellInstancied && (dag.putChild(nodes[1], nodes[3]) != null);
        wellInstancied = wellInstancied && (dag.putChild(nodes[3], nodes[5]) != null);
        wellInstancied = wellInstancied && (dag.putChild(nodes[2], nodes[4]) != null);
        wellInstancied = wellInstancied && (dag.putChild(nodes[4], nodes[6]) != null);
        wellInstancied = wellInstancied && (dag.putChild(nodes[4], nodes[7]) != null);
        wellInstancied = wellInstancied && (dag.putChild(nodes[6], nodes[9]) != null);
        wellInstancied = wellInstancied && (dag.putChild(nodes[9], nodes[8]) != null);
        wellInstancied = wellInstancied && (dag.putChild(nodes[9], nodes[12]) != null);
        wellInstancied = wellInstancied && (dag.putChild(nodes[9], nodes[10]) != null);
        wellInstancied = wellInstancied && (dag.putChild(nodes[10], nodes[11]) != null);
    }

    @Test
    void structural()
    {
        assertTrue(wellInstancied);
    }

    @Test
    void LCA()
    {
    }
}