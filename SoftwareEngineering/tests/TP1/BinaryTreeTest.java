package TP1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BinaryTreeTest
{
    private static final int nbNodes = 15;

    private static BinaryTree<Integer> btree;
    private static BinaryNode<Integer>[] nodes;

    @BeforeEach
    void init()
    {
        btree = new BinaryTree<>(0);
        nodes = new BinaryNode[nbNodes];
        nodes[0] = btree;

        for (int i = 1; i < nbNodes; i ++)
        {
            nodes[i] = new BinaryNode<>(i);

            btree.putNode(nodes[(i-1)/2], nodes[i], ((i-1)%2 != 0));
        }

        btree.print();
    }

    @Test
    void basics()
    {
        // well formed btree
        assertEquals(nodes[0].getLeftChild(), nodes[1]);
        assertEquals(nodes[0].getRightChild(), nodes[2]);

        // no recursion possible
        btree.putNode(nodes[3], nodes[0], true);
        assertEquals(nodes[3].getRightChild(), nodes[8]);

        // no add of pre-existing value
        btree.putNode(nodes[3], nodes[10], true);
        assertEquals(nodes[3].getRightChild(), nodes[8]);
    }

    @Test
    void LCA()
    {
        assertEquals(btree.lowestCommonAncestor(nodes[1], nodes[2]), nodes[0]);
        assertEquals(btree.lowestCommonAncestor(nodes[3], nodes[2]), nodes[0]);
        assertEquals(btree.lowestCommonAncestor(nodes[7], nodes[3]), nodes[3]);
        assertEquals(btree.lowestCommonAncestor(nodes[9], nodes[9]), nodes[9]);
        assertEquals(btree.lowestCommonAncestor(nodes[7], nodes[10]), nodes[1]);
        assertEquals(btree.lowestCommonAncestor(btree.lowestCommonAncestor(nodes[7], nodes[10]), nodes[12]), nodes[0]);
    }
}