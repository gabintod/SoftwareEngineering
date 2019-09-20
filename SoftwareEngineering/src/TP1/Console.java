package TP1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

import static com.sun.javafx.util.Utils.split;

public class Console
{
    public static final String UNKNOWN_COMMAND = "Unknown command";

    private  final String PROMPT;
    private boolean running;
    private HashMap<String, Command> commands;

    private Scanner scanner;
    private String entry;
    private String[] entryArgs;

    public Console(String prompt)
    {
        this.scanner = new Scanner(System.in);
        this.PROMPT = prompt;
        this.commands = new HashMap<>();
        this.addCommand(new Command("help", "HELP\nhelp : display commands aviables\nhelp <command name> : display command description")
        {
            @Override
            public int run(String[] args)
            {
                if (args.length > 2)
                {
                    displayError(TOO_MANY_ARGS);
                    return 1;
                }

                if (args.length == 2)
                {
                    Command command = commands.get(args[1]);
                    if (command == null)
                    {
                        displayError(UNKNOWN_COMMAND);
                        return 2;
                    }
                    System.out.println(command.getDESCRIPTION());
                    return 0;
                }

                ArrayList<String> commandNames = new ArrayList<>(commands.keySet());
                commandNames.sort(Comparator.naturalOrder());
                for (String commandName : commandNames)
                {
                    System.out.println(commandName);
                }

                return 0;
            }
        }); // Help command
        this.addCommand(new Command("exit", "EXIT\nexit : exit console")
        {
            @Override
            public int run(String[] args)
            {
                running = false;
                return 0;
            }
        }); // Exit command
    }

    public void run()
    {
        this.running = true;

        Command command;
        while (this.running)
        {
            System.out.print("\n" + PROMPT);
            this.entry = scanner.nextLine();
            this.entryArgs = split(entry, " ");

            command = this.commands.get(entryArgs[0]);
            if (command == null)
                displayError(UNKNOWN_COMMAND);
            else
                command.run(entryArgs);
        }
    }

    public void addCommand(Command command)
    {
        this.commands.put(command.getNAME(), command);
    }

    public static void displayError(String message)
    {
        System.out.println(ConsoleColors.RED + "Error : " + message + ConsoleColors.RESET);
    }
}
