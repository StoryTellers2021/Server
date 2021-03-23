package utils;

import java.util.List;

public class InputValidator {

    final String input;

    InputValidator(final String input) {
        this.input = input;
    }

    public boolean validate(final List<String> problems) {
        if (this.isBlank()) {
            problems.add(this.getBlankInputProblem());
            return false;
        }
        if (this.isInvalid()) {
            problems.add(this.getInvalidInputProblem());
            return false;
        }
        return true;
    }

    public boolean isBlank() {
        return this.input.length() == 0;
    }

    public boolean isInvalid() {
        return false;
    }

    public String getBlankInputProblem() {
        return "INPUT_BLANK";
    }

    public String getInvalidInputProblem() {
        return "INPUT_INVALID";
    }
}
