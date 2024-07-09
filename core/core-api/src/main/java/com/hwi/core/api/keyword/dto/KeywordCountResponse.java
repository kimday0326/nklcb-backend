package com.hwi.core.api.keyword.dto;

//  List<Map> wordList = <Map>[
//     {'word': 'Apple', 'value': 100},
//     {'word': 'Samsung', 'value': 60},
//     {'word': 'Intel', 'value': 55},
//     {'word': 'Tesla', 'value': 50},
//     {'word': 'AMD', 'value': 40},
//     {'word': 'Google', 'value': 35},
//     {'word': 'Qualcomm', 'value': 31},
//     {'word': 'Netflix', 'value': 27},
//     {'word': 'Meta', 'value': 27},
//     {'word': 'Amazon', 'value': 26},
//     {'word': 'Microsoft', 'value': 25},
//     {'word': 'Facebook', 'value': 23},
//     {'word': 'Twitter', 'value': 20},
//     {'word': 'GitHub', 'value': 18},
//     {'word': 'Dart', 'value': 15},
//     {'word': 'Flutter', 'value': 12},
//     {'word': 'Android', 'value': 10},
//     {'word': 'iOS', 'value': 8},
//     {'word': 'Java', 'value': 6},
//     {'word': 'Python', 'value': 4},
//     {'word': 'JavaScript', 'value': 2},
//   ];
public record KeywordCountResponse(
	String word,
	Long value
) {
	public static KeywordCountResponse of(String word, Long value) {
		return new KeywordCountResponse(word, value);
	}
}
