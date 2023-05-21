package Command_Pattern;

public class Button {
    private Command [] command;
    private Command undoCommand;
    public Button(){
        this.command = new Command[3];

        Command noCommand = new NoCommand();
        for(int i = 0; i < 3; i++){
            command[i] = noCommand;
        }
        undoCommand = noCommand;
    }

    public void setButton(int index, Command command){
        this.command[index] = command;
    }

    public void click(int action, int index){
        command[action].execute(index);
        undoCommand = command[action];
    }

    public void undoButtonWasPushed(){
        undoCommand.undo();
    }
}
