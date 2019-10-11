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
    void testing()
    {
        
    }
}