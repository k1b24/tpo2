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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CotTest {

    @Mock
    private Tan tanMock;

    @Test
    void calculate_correctlyCallsSinAndCosMock() {
        Cot cot = new Cot(tanMock);
        when(tanMock.calculate(any(), any())).thenReturn(BigDecimal.ONE);

        cot.calculate(BigDecimal.ONE, BigDecimal.valueOf(0.001));

        verify(tanMock, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
    }

    private static Stream<InputData> setUpTestsData() {
        return Stream.of(
                new InputData(BigDecimal.valueOf(1), BigDecimal.valueOf(0.642092), BigDecimal.valueOf(1e-5)),
                new InputData(BigDecimal.valueOf(-1), BigDecimal.valueOf(-0.642092), BigDecimal.valueOf(1e-5)),
                new InputData(BigDecimal.valueOf(0.5), BigDecimal.valueOf(1.830487), BigDecimal.valueOf(1e-5))
        );
    }

    @ParameterizedTest
    @MethodSource("setUpTestsData")
    void calculate_returnsCorrectAnswer(InputData inputData) {
        Cot cot = new Cot(new Tan(new Sin(), new Cos(new Sin())));
        assertEquals(0, inputData.expected.setScale(3, RoundingMode.HALF_UP).compareTo(cot.calculate(inputData.x, inputData.precision).setScale(3, RoundingMode.HALF_UP)));
    }

    private record InputData(BigDecimal x, BigDecimal expected, BigDecimal precision) {}
}
