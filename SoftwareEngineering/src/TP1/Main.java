package TP1;

public class Main
{
    public static void main(String[] args)
    {
        Console console = new Console("> ");

        console.addCommand(new Command("btree", "BINARY TREE" +
                "\nbtree root <value> : set/reset root with specified value" +
                "\nbtree set <node> <value> : set specified node value" +
                "\nbtree setc <node> <value> <left|right> : set specified node child to the specified value" +
                "\nbtree rmv <value> : remove the first node from the left with specified value" +
                "\nbtree rmvc <node> <left|right> : remove the specified node child" +
                "\nbtree print : print tree" +
                "\nbtree height : return tree height" +
                "\nbtree count : return the number of nodes in the tree")
        {
            public static final String NODE_NOT_FOUND = "Node not found";
            public static final String MISSING_ROOT = "Missing root";
            private BinaryTree<String> btree;

            @Override
            public int run(String[] args)
            {
                if (args.length > 5)
                {
                    Console.displayError(TOO_MANY_ARGS);
                    return 1;
                }

                if (args.length < 2)
                {
                    Console.displayError(MISSING_ARGS);
                    return 1;
                }

                BinaryTree<String> node;
                switch (args[1])
                {
                    case "root":
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

                        btree = new BinaryTree<>(args[2]);
                        System.out.println("root set");
                        return 0;

                    case "set":
                        if (args.length > 4)
                        {
                            Console.displayError(TOO_MANY_ARGS);
                            return 1;
                        }

                        if (args.length < 4)
                        {
                            Console.displayError(MISSING_ARGS);
                            return 1;
                        }

                        if (btree == null)
                        {
                            Console.displayError(MISSING_ROOT);
                            return 2;
                        }

                        node = btree.find(args[2]);

                        if (node == null)
                        {
                            Console.displayError(NODE_NOT_FOUND);
                            return 2;
                        }

                        node.setValue(args[3]);
                        System.out.println("node value change");
                        return 0;

                    case "setc":
                        if (args.length < 5)
                        {
                            Console.displayError(MISSING_ARGS);
                            return 1;
                        }

                        if (btree == null)
                        {
                            Console.displayError(MISSING_ROOT);
                            return 2;
                        }

                        node = btree.find(args[2]);

                        if (node == null)
                        {
                            Console.displayError(NODE_NOT_FOUND);
                            return 2;
                        }

                        switch (args[4])
                        {
                            case "left":
                                node.setLeftChild(new BinaryTree<String>(args[3]));
                                System.out.println("left child set");
                                return 0;

                            case "right":
                                node.setRightChild(new BinaryTree<String>(args[3]));
                                System.out.println("right child set");
                                return 0;

                            default:
                                Console.displayError("Unknown arg \"" + args[4] + "\"");
                                return 2;
                        }

                    case "rmv":
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

                        if (btree.getValue().equals(args[2]))
                        {
                            btree = null;
                            System.out.println("root removed");
                            return 0;
                        }

                        if (btree.remove(args[2]))
                        {
                            System.out.println("node removed");
                            return 0;
                        }
                        else
                        {
                            Console.displayError(NODE_NOT_FOUND);
                            return 2;
                        }

                    case "rmvc":
                        if (args.length < 4)
                        {
                            Console.displayError(MISSING_ARGS);
                            return 1;
                        }

                        if (btree == null)
                        {
                            Console.displayError(MISSING_ROOT);
                            return 2;
                        }

                        node = btree.find(args[2]);

                        if (node == null)
                        {
                            Console.displayError(NODE_NOT_FOUND);
                            return 2;
                        }

                        switch (args[3])
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
                                Console.displayError("Unknown arg \"" + args[3] + "\"");
                                return 2;
                        }

                    case "print":
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

                        btree.print("", "");
                        return 0;

                    case "height":
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

                        System.out.println(btree.getHeight());
                        return 0;

                    case "count":
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

                        System.out.println(btree.getNodeCount());
                        return 0;
                }

                Console.displayError("Unknown error");
                return 2;
            }
        });

        console.run();
    }
}
