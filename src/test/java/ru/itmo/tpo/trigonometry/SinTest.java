package ru.itmo.tpo.trigonometry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SinTest {

    private final Sin sin = new Sin();

    private static Stream<InputData> setUpTestsData() {
        return Stream.of(
                new InputData(BigDecimal.valueOf(0), BigDecimal.valueOf(0, 6), BigDecimal.valueOf(1e-5)),
                new InputData(BigDecimal.valueOf(1), BigDecimal.valueOf(0.841471), BigDecimal.valueOf(1e-5)),
                new InputData(BigDecimal.valueOf(-1), BigDecimal.valueOf(-0.841471), BigDecimal.valueOf(1e-5))
        );
    }

    @ParameterizedTest
    @MethodSource("setUpTestsData")
    void calculate_returnsCorrectAnswer(InputData inputData) {
        assertEquals(inputData.expected, sin.calculate(inputData.x, inputData.precision));
    }

    private record InputData(BigDecimal x, BigDecimal expected, BigDecimal precision) {}
}
