package com.hwi.client.grpc;

import static lombok.AccessLevel.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class DateTimeUtil {
	public static String parseToLocalDateTime(LocalDateTime dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		return dateTime.format(formatter);
	}
}
