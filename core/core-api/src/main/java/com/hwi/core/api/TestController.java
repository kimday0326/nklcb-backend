package com.hwi.core.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hwi.client.grpc.GrpcClientService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class TestController {

	private final GrpcClientService grpcClientService;

	@GetMapping("/health")
	public String health() {
		return "OK";
	}

	@GetMapping("/grpc-test")
	public String test() {
		grpcClientService.crawlArticles("https://d2.naver.com/d2.atom");
		return "testOK";
	}
}
