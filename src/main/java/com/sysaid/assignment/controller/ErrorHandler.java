package com.sysaid.assignment.controller;

import com.sysaid.assignment.exception.TaskNotFoundException;
import com.sysaid.assignment.exception.UserNotFoundException;
import com.sysaid.assignment.exception.WrongTaskTypeException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler({WrongTaskTypeException.class,})
    public ResponseEntity<ExceptionDto> e400(Exception ex) {
        return this.handleException(ex, HttpStatus.BAD_REQUEST);
    }

    public ErrorHandler() {
    }

    @ExceptionHandler({UserNotFoundException.class, TaskNotFoundException.class})
    public ResponseEntity<ExceptionDto> e404(Exception ex) {
        return this.handleException(ex, HttpStatus.NOT_FOUND);
    }


    private ResponseEntity<ExceptionDto> handleException(Exception ex, HttpStatus status) {
        return new ResponseEntity<>(ErrorHandler.ExceptionDto.of(status, ex.getMessage()), status);
    }

    @Setter
    @Getter
    private static class ExceptionDto {
        private int code;
        private String message;

        static ExceptionDto of(HttpStatus status, String message) {
            ExceptionDto dto = new ExceptionDto();
            dto.setCode(status.value());
            dto.setMessage(message);
            return dto;
        }

        public ExceptionDto() {
        }

        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof ExceptionDto)) {
                return false;
            } else {
                ExceptionDto other = (ExceptionDto)o;
                if (!other.canEqual(this)) {
                    return false;
                } else if (this.getCode() != other.getCode()) {
                    return false;
                } else {
                    Object this$message = this.getMessage();
                    Object other$message = other.getMessage();
                    if (this$message == null) {
                        if (other$message != null) {
                            return false;
                        }
                    } else if (!this$message.equals(other$message)) {
                        return false;
                    }

                    return true;
                }
            }
        }

        protected boolean canEqual(final Object other) {
            return other instanceof ExceptionDto;
        }

        public int hashCode() {
            boolean PRIME = true;
            int result = 1;
            result = result * 59 + this.getCode();
            Object $message = this.getMessage();
            result = result * 59 + ($message == null ? 43 : $message.hashCode());
            return result;
        }

        public String toString() {
            return "ErrorHandler.ExceptionDto(code=" + this.getCode() + ", message=" + this.getMessage() + ")";
        }
    }
}
