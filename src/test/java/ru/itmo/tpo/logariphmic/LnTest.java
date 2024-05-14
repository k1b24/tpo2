package ru.itmo.tpo.logariphmic;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.itmo.tpo.trigonometry.Cos;
import ru.itmo.tpo.trigonometry.Csc;
import ru.itmo.tpo.trigonometry.CscTest;
import ru.itmo.tpo.trigonometry.Sin;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LnTest {

    private final Ln ln = new Ln();

    private static Stream<InputData> setUpTestsData() {
        return Stream.of(
                new InputData(BigDecimal.valueOf(1), BigDecimal.valueOf(0), BigDecimal.valueOf(1e-5)),
                new InputData(BigDecimal.valueOf(2), BigDecimal.valueOf(0.693147), BigDecimal.valueOf(1e-5))
        );
    }

    @ParameterizedTest
    @MethodSource("setUpTestsData")
    void calculate_returnsCorrectAnswer(InputData inputData) {
        assertEquals(0, inputData.expected.setScale(3, RoundingMode.HALF_UP).compareTo(ln.calculate(inputData.x, inputData.precision).setScale(3, RoundingMode.HALF_UP)));
    }

    private record InputData(BigDecimal x, BigDecimal expected, BigDecimal precision) {}
}
