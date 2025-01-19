package at.spengergasse.springtest.presentation.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserNotFoundError {
    public Long id;
    public String message;
}
