package TP1;

public class Main
{
    public static void main(String[] args)
    {
        Console console = new Console("> ");
        console.addCommand(new BTREEConsole());

        console.run();
    }
}
