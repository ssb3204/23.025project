package Command_Pattern;

public interface Command {
    void execute(int index);
    void undo();
}
