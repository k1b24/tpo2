package ru.itmo.tpo.trigonometry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class CosTest {

    @Mock
    private Sin sinMock;

    @Test
    void calculate_correctlyCallsSinMock() {
        Cos cos = new Cos(sinMock);
        when(sinMock.calculate(any(), any())).thenReturn(BigDecimal.ONE);

        cos.calculate(BigDecimal.ONE, BigDecimal.valueOf(0.001));

        verify(sinMock, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
    }

    private static Stream<InputData> setUpTestsData() {
        return Stream.of(
                new InputData(BigDecimal.valueOf(0), BigDecimal.valueOf(1), BigDecimal.valueOf(1e-5)),
                new InputData(BigDecimal.valueOf(1), BigDecimal.valueOf(0.540302), BigDecimal.valueOf(1e-5)),
                new InputData(BigDecimal.valueOf(-1), BigDecimal.valueOf(0.540302), BigDecimal.valueOf(1e-5))
        );
    }

    @ParameterizedTest
    @MethodSource("setUpTestsData")
    void calculate_returnsCorrectAnswer(InputData inputData) {
        Cos cos = new Cos(new Sin());
        assertEquals(0, inputData.expected.setScale(3, RoundingMode.HALF_UP).compareTo(cos.calculate(inputData.x, inputData.precision).setScale(3, RoundingMode.HALF_UP)));
    }

    private record InputData(BigDecimal x, BigDecimal expected, BigDecimal precision) {}
}
