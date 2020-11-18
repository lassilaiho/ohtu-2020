package laskin;

import java.util.HashMap;
import java.util.Map;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Tapahtumankuuntelija implements EventHandler {
    private Button undo;
    private Sovelluslogiikka app = new Sovelluslogiikka();

    private Map<Button, Command> commands = new HashMap<Button, Command>();
    private Command previousCommand;

    public Tapahtumankuuntelija(TextField resultField, TextField inputField, Button plusButton, Button minusButton,
            Button clearButton, Button undoButton) {
        this.undo = undoButton;
        commands.put(plusButton, new Sum(resultField, inputField, clearButton, undoButton, app));
        commands.put(minusButton, new Difference(resultField, inputField, clearButton, undoButton, app));
        commands.put(clearButton, new Clear(resultField, inputField, clearButton, undoButton, app));
    }

    @Override
    public void handle(Event event) {
        if (event.getTarget() == undo) {
            if (previousCommand != null) {
                previousCommand.undo();
                previousCommand = null;
            }
        } else {
            var command = commands.get((Button) event.getTarget());
            command.execute();
            previousCommand = command;
        }
    }
}
