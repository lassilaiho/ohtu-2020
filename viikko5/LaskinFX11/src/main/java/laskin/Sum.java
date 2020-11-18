package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Sum extends Command {
    public Sum(TextField resultField, TextField inputField, Button clearButton, Button undoButton,
            Sovelluslogiikka app) {
        super(resultField, inputField, clearButton, undoButton, app);
    }

    @Override
    public void execute() {
        app.plus(parseInput());
        syncStateToUI();
    }
}
