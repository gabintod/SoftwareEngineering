package TP1;

public class BTREEConsole extends Console
{
    public static final String NODE_NOT_FOUND = "Node not found";
    public static final String MISSING_ROOT = "Missing root";
    private BinaryTree<String> btree;

    public BTREEConsole()
    {
        super("btree", "BINARY TREE CONSOLE\ninterprete binary tree commands", ConsoleColors.CYAN + "BTREE" + ConsoleColors.RESET + "> ");

        this.addCommand(new Command("root", "SET ROOT\nroot <value> : set/reset root with specified value")
        {
            @Override
            public int run(String[] args)
            {
                if (args.length > 2)
                {
                    Console.displayError(TOO_MANY_ARGS);
                    return 1;
                }

                if (args.length < 2)
                {
                    Console.displayError(MISSING_ARGS);
                    return 1;
                }

                btree = new BinaryTree<>(args[1]);
                System.out.println("root set");
                return 0;
            }
        });
        this.addCommand(new Command("set", "SET NODE\nset <node> <value> : set specified node value")
        {
            @Override
            public int run(String[] args)
            {
                if (args.length > 3)
                {
                    Console.displayError(TOO_MANY_ARGS);
                    return 1;
                }

                if (args.length < 3)
                {
                    Console.displayError(MISSING_ARGS);
                    return 1;
                }

                if (btree == null)
                {
                    Console.displayError(MISSING_ROOT);
                    return 2;
                }

                BinaryTree<String> node = btree.find(args[1]);

                if (node == null)
                {
                    Console.displayError(NODE_NOT_FOUND);
                    return 2;
                }

                node.setValue(args[2]);
                System.out.println("node value change");
                return 0;
            }
        });
        this.addCommand(new Command("setc", "SET CHILD\nsetc <node> <value> <left|right> : set specified node child to the specified value")
        {
            @Override
            public int run(String[] args)
            {
                if (args.length < 4)
                {
                    Console.displayError(MISSING_ARGS);
                    return 1;
                }

                if (args.length > 4)
                {
                    Console.displayError(TOO_MANY_ARGS);
                    return 1;
                }

                if (btree == null)
                {
                    Console.displayError(MISSING_ROOT);
                    return 2;
                }

                BinaryTree<String> node = btree.find(args[1]);

                if (node == null)
                {
                    Console.displayError(NODE_NOT_FOUND);
                    return 2;
                }

                switch (args[3])
                {
                    case "left":
                        node.setLeftChild(new BinaryTree<String>(args[2]));
                        System.out.println("left child set");
                        return 0;

                    case "right":
                        node.setRightChild(new BinaryTree<String>(args[2]));
                        System.out.println("right child set");
                        return 0;

                    default:
                        Console.displayError("Unknown arg \"" + args[3] + "\"");
                        return 2;
                }
            }
        });
        this.addCommand(new Command("rmv", "REMOVE\nrmv <value> : remove the first node from the left with specified value")
        {
            @Override
            public int run(String[] args)
            {
                if (args.length < 2)
                {
                    Console.displayError(MISSING_ARGS);
                    return 1;
                }

                if (args.length > 2)
                {
                    Console.displayError(TOO_MANY_ARGS);
                    return 1;
                }

                if (btree == null)
                {
                    Console.displayError(MISSING_ROOT);
                    return 2;
                }

                if (btree.getValue().equals(args[1]))
                {
                    btree = null;
                    System.out.println("root removed");
                    return 0;
                }

                if (btree.remove(args[1]))
                {
                    System.out.println("node removed");
                    return 0;
                }
                else
                {
                    Console.displayError(NODE_NOT_FOUND);
                    return 2;
                }
            }
        });
        this.addCommand(new Command("rmvc", "REMOVE CHILD\nrmvc <node> <left|right> : remove the specified node child")
        {
            @Override
            public int run(String[] args)
            {
                if (args.length < 3)
                {
                    Console.displayError(MISSING_ARGS);
                    return 1;
                }

                if (args.length > 3)
                {
                    Console.displayError(TOO_MANY_ARGS);
                    return 1;
                }

                if (btree == null)
                {
                    Console.displayError(MISSING_ROOT);
                    return 2;
                }

                BinaryTree<String> node = btree.find(args[1]);

                if (node == null)
                {
                    Console.displayError(NODE_NOT_FOUND);
                    return 2;
                }

                switch (args[2])
                {
                    case "left":
                        node.setLeftChild(null);
                        System.out.println("left child removed");
                        return 0;

                    case "right":
                        node.setRightChild(null);
                        System.out.println("right child removed");
                        return 0;

                    default:
                        Console.displayError("Unknown arg \"" + args[2] + "\"");
                        return 2;
                }
            }
        });
        this.addCommand(new Command("print", "PRINT\nprint : print tree")
        {
            @Override
            public int run(String[] args)
            {
                if (args.length > 1)
                {
                    Console.displayError(TOO_MANY_ARGS);
                    return 1;
                }

                if (btree == null)
                {
                    Console.displayError(MISSING_ROOT);
                    return 2;
                }

                btree.print("", "");
                return 0;
            }
        });
        this.addCommand(new Command("height", "BINARY TREE HEIGHT\nheight : return tree height")
        {
            @Override
            public int run(String[] args)
            {
                if (args.length > 1)
                {
                    Console.displayError(TOO_MANY_ARGS);
                    return 1;
                }

                if (btree == null)
                {
                    Console.displayError(MISSING_ROOT);
                    return 2;
                }

                System.out.println(btree.getHeight());
                return 0;
            }
        });
        this.addCommand(new Command("count", "COUNT NODES\ncount : return the number of nodes in the tree")
        {
            @Override
            public int run(String[] args)
            {
                if (args.length > 1)
                {
                    Console.displayError(TOO_MANY_ARGS);
                    return 1;
                }

                if (btree == null)
                {
                    Console.displayError(MISSING_ROOT);
                    return 2;
                }

                System.out.println(btree.getNodeCount());
                return 0;
            }
        });
        this.addCommand(new Command("lca", "LOWEST COMMON ANCESTOR\nlca <node1> <node2> : find the lowest common ancestor of the two specified nodes")
        {
            @Override
            public int run(String[] args)
            {
                if (args.length < 3)
                {
                    Console.displayError(MISSING_ARGS);
                    return 1;
                }

                if (btree == null)
                {
                    Console.displayError(MISSING_ROOT);
                    return 2;
                }

                BinaryTree<String> n1 = btree.find(args[1]);
                if (n1 == null)
                {
                    Console.displayError("Node \"" + args[1] + "\"not found");
                    return 2;
                }

                BinaryTree<String> n2 = btree.find(args[2]);
                if (n2 == null)
                {
                    Console.displayError("Node \"" + args[2] + "\" not found");
                    return 2;
                }

                System.out.println("Lowest common ancestor : " + btree.lowestCommonAncestor(n1, n2).getValue());

                return 0;
            }
        });
    }

    @Override
    public int run(String[] args)
    {
        btree = null;
        return super.run(args);
    }
}
