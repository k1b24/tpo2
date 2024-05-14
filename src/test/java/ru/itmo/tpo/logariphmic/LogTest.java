package ru.itmo.tpo.logariphmic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LogTest {

    @Mock
    private Ln lnMock;

    @Test
    void calculate_correctlyCallsSinAndCosMock() {
        Log log = new Log(lnMock, 2);
        when(lnMock.calculate(any(), any())).thenReturn(BigDecimal.ONE);

        log.calculate(BigDecimal.ONE, BigDecimal.valueOf(0.001));

        verify(lnMock, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
    }

    private static Stream<InputData> setUpTestsData() {
        return Stream.of(
                new InputData(BigDecimal.valueOf(1), BigDecimal.valueOf(0), BigDecimal.valueOf(1e-5), 2),
                new InputData(BigDecimal.valueOf(6), BigDecimal.valueOf(1.113282), BigDecimal.valueOf(1e-5), 5)
        );
    }

    @ParameterizedTest
    @MethodSource("setUpTestsData")
    void calculate_returnsCorrectAnswer(InputData inputData) {
        Log log = new Log(new Ln(), inputData.base);
        assertEquals(0, inputData.expected.setScale(3, RoundingMode.HALF_UP).compareTo(log.calculate(inputData.x, inputData.precision).setScale(3, RoundingMode.HALF_UP)));
    }

    private record InputData(BigDecimal x, BigDecimal expected, BigDecimal precision, int base) {}

}
