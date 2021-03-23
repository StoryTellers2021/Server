package utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.List;

public class ReturnTemplate<T> {

    interface AccountServerReturnProcessor<T> {
        T processRequest(final List<String> problems);
    }

    private final List<String> problems;
    private T result;

    ReturnTemplate() {
        this.problems = new LinkedList<>();
    }

    ReturnTemplate<T> validateAndProcessRequest(final InputValidator inputValidator, final AccountServerReturnProcessor<T> processor) {
        if (inputValidator.validate(this.problems))
                this.result = processor.processRequest(this.problems);
        else this.problems.add("OPERATION_NOT_SUPPORTED");
        return this;
    }

    @JsonProperty("problems")
    public List<String> getProblems() {
        return this.problems;
    }

    @JsonProperty("result")
    public T getResult() {
        return this.result;
    }

}
