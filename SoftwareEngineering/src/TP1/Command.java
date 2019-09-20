package TP1;

public abstract class Command
{
    public static final String MISSING_ARGS = "Missing argument(s)";
    public static final String TOO_MANY_ARGS = "Too many argument(s)";

    private final String NAME;
    private final String DESCRIPTION;

    public Command(String name, String description)
    {
        this.NAME = name;
        this.DESCRIPTION = description;
    }

    public abstract int run(String args[]);

    public String getNAME()
    {
        return NAME;
    }

    public String getDESCRIPTION()
    {
        return DESCRIPTION;
    }

    @Override
    public String toString()
    {
        return "Command{" +
                "NAME='" + NAME + '\'' +
                ", DESCRIPTION='" + DESCRIPTION + '\'' +
                '}';
    }
}
