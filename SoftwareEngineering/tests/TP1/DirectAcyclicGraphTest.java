package TP1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class DirectAcyclicGraphTest
{
    DirectAcyclicGraph<Integer> dag;
    DAGNode<Integer>[] nodes;
    boolean wellInstanced;

    @BeforeEach
    void setUp()
    {
        nodes = new DAGNode[13];
        nodes[0] = dag = new DirectAcyclicGraph<>(1);

        for (int i=1; i<nodes.length; i++)
        {
            nodes[i] = new DAGNode<>(i+1);
        }

        wellInstanced = (dag.putChild(nodes[0], nodes[1]) != null);
        wellInstanced = wellInstanced && (dag.putChild(nodes[0], nodes[2]) != null);
        wellInstanced = wellInstanced && (dag.putChild(nodes[1], nodes[3]) != null);
        wellInstanced = wellInstanced && (dag.putChild(nodes[3], nodes[5]) != null);
        wellInstanced = wellInstanced && (dag.putChild(nodes[2], nodes[4]) != null);
        wellInstanced = wellInstanced && (dag.putChild(nodes[4], nodes[6]) != null);
        wellInstanced = wellInstanced && (dag.putChild(nodes[4], nodes[7]) != null);
        wellInstanced = wellInstanced && (dag.putChild(nodes[6], nodes[9]) != null);
        wellInstanced = wellInstanced && (dag.putChild(nodes[9], nodes[8]) != null);
        wellInstanced = wellInstanced && (dag.putChild(nodes[9], nodes[12]) != null);
        wellInstanced = wellInstanced && (dag.putChild(nodes[9], nodes[10]) != null);
        wellInstanced = wellInstanced && (dag.putChild(nodes[10], nodes[11]) != null);
    }

    @Test
    void structural()
    {
        // well instanced
        assertTrue(wellInstanced);

        //  well formed
        assertTrue(nodes[0].getChildren().contains(nodes[1]));
        assertTrue(nodes[0].getChildren().contains(nodes[2]));
        assertTrue(nodes[1].getChildren().contains(nodes[3]));
        assertTrue(nodes[3].getChildren().contains(nodes[5]));
        assertTrue(nodes[2].getChildren().contains(nodes[4]));
        assertTrue(nodes[4].getChildren().contains(nodes[6]));
        assertTrue(nodes[4].getChildren().contains(nodes[7]));
        assertTrue(nodes[6].getChildren().contains(nodes[9]));
        assertTrue(nodes[9].getChildren().contains(nodes[8]));
        assertTrue(nodes[9].getChildren().contains(nodes[12]));
        assertTrue(nodes[9].getChildren().contains(nodes[10]));
        assertTrue(nodes[10].getChildren().contains(nodes[11]));
    }

    @Test
    void LCA()
    {
    }
}