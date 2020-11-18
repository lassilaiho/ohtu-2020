package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Command {
    protected TextField resultField;
    protected TextField inputField;
    protected Button clearButton;
    protected Button undoButton;
    protected Sovelluslogiikka app;
    protected int previousResult;

    public Command(TextField resultField, TextField inputField, Button clearButton, Button undoButton,
            Sovelluslogiikka app) {
        this.resultField = resultField;
        this.inputField = inputField;
        this.clearButton = clearButton;
        this.undoButton = undoButton;
        this.app = app;
    }

    public abstract void execute();

    public void undo() {
        app.setTulos(previousResult);
        syncStateToUI();
    }

    protected int parseInput() {
        try {
            return Integer.parseInt(inputField.getText());
        } catch (Exception e) {
            return 0;
        }
    }

    protected void syncStateToUI() {
        inputField.setText("");
        resultField.setText("" + app.tulos());
        if (app.tulos() == 0) {
            clearButton.disableProperty().set(true);
        } else {
            clearButton.disableProperty().set(false);
        }
        undoButton.disableProperty().set(false);
    }

    protected void saveAppState() {
        previousResult = app.tulos();
    }
}
