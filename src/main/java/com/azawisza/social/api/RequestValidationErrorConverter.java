package com.azawisza.social.api;

import com.azawisza.social.api.dto.ApplicationError;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static com.google.common.base.Joiner.on;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Created by azawisza
 */
@Component
public class RequestValidationErrorConverter {

    public ApplicationError convert(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        String errorsMessages = fieldErrors.stream()
                .map(formatError())
                .sorted(Comparator.naturalOrder())
                .reduce("", (accumulated, add) -> add + " " + accumulated);
        return new ApplicationError()
                .withMessage(errorsMessages)
                .withCode(BAD_REQUEST.name());
    }

    private Function<FieldError, String> formatError() {
        return fieldError -> on("").join(fieldError.getObjectName(), fieldError.getField(), fieldError.getCode());
    }

}
