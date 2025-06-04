package travelnote.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static Map<String, Object> setDetails(HttpServletRequest request, Exception exception, HttpStatus status) {
        StackTraceElement origin = exception.getStackTrace()[0];
        Map<String, Object> map = new HashMap<>();
        map.put("httpMethod", request.getMethod());
        map.put("requestUri", request.getRequestURI());
        map.put("statusCode", status.toString());
        map.put("sourceClass", origin.getClassName());
        map.put("sourceMethod", origin.getMethodName());
        map.put("exceptionClass", exception.getClass().getSimpleName());
        map.put("exceptionMessage", exception.getMessage());
        return map;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleUnexpectedException(Exception e, WebRequest request) {
        ProblemDetail problemDetail = getProblemDetail(e, request);
        log.error("Unexpected error", e);
        ProblemDetail detailsToSend = ProblemDetail.forStatus(problemDetail.getStatus());
        return ResponseEntity.of(detailsToSend).build();
    }

    private ProblemDetail getProblemDetail(Exception e, WebRequest request) {
        try {
            ProblemDetail problemDetail = (ProblemDetail) Objects.requireNonNull(super.handleException(e, request))
                    .getBody();
            problemDetail.setProperties(setDetails(getCurrentHttpRequest(), e, HttpStatus.INTERNAL_SERVER_ERROR));
            return problemDetail;
        } catch (Exception ex) {
            return ProblemDetail.forStatusAndDetail(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "예기치 못한 오류가 발생하였습니다."
            );
        }
    }

    private HttpServletRequest getCurrentHttpRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(
                RequestContextHolder.getRequestAttributes())).getRequest();
    }
} 