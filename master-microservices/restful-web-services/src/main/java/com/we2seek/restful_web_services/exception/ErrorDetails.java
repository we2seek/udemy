package com.we2seek.restful_web_services.exception;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime timestamp, int status, String message, String details) {
}
