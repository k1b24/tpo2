package ru.itmo.tpo.trigonometry;

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
public class TanTest {

    @Mock
    private Sin sinMock;
    @Mock
    private Cos cosMock;

    @Test
    void calculate_correctlyCallsSinAndCosMock() {
        Tan tan = new Tan(sinMock, cosMock);
        when(sinMock.calculate(any(), any())).thenReturn(BigDecimal.ONE);
        when(cosMock.calculate(any(), any())).thenReturn(BigDecimal.ONE);

        tan.calculate(BigDecimal.ONE, BigDecimal.valueOf(0.001));

        verify(sinMock, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
        verify(cosMock, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
    }

    private static Stream<InputData> setUpTestsData() {
        return Stream.of(
                new InputData(BigDecimal.valueOf(0), BigDecimal.valueOf(0, 6), BigDecimal.valueOf(1e-5)),
                new InputData(BigDecimal.valueOf(1), BigDecimal.valueOf(1.55740), BigDecimal.valueOf(1e-4)),
                new InputData(BigDecimal.valueOf(-1), BigDecimal.valueOf(-1.55740), BigDecimal.valueOf(1e-4))
        );
    }

    @ParameterizedTest
    @MethodSource("setUpTestsData")
    void calculate_returnsCorrectAnswer(InputData inputData) {
        Sin sin = new Sin();
        Tan tan = new Tan(sin, new Cos(sin));
        assertEquals(0, inputData.expected.setScale(3, RoundingMode.HALF_UP).compareTo(tan.calculate(inputData.x, inputData.precision).setScale(3, RoundingMode.HALF_UP)));
    }

    private record InputData(BigDecimal x, BigDecimal expected, BigDecimal precision) {}
}
