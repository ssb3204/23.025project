package Command_Pattern;

public class Button {
    private Command [] command;

    public Button(){
        this.command = new Command[3];
    }
    public void setButton(int index, Command command){
        this.command[index] = command;
    }

    public void click(int action, int index){
        command[action].execute(index);
    }
}
