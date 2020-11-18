package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Difference extends Command {
    public Difference(TextField resultField, TextField inputField, Button clearButton, Button undoButton,
            Sovelluslogiikka app) {
        super(resultField, inputField, clearButton, undoButton, app);
    }

    @Override
    public void execute() {
        saveAppState();
        app.miinus(parseInput());
        syncStateToUI();
    }
}
