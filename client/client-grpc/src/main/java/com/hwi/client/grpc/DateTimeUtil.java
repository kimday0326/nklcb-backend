package com.hwi.client.grpc;

import static lombok.AccessLevel.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class DateTimeUtil {
	public static LocalDateTime parseToLocalDateTime(String dateTimeString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		return LocalDateTime.parse(dateTimeString, formatter);
	}
}
