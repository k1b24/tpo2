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
public class CscTest {

    @Mock
    private Sin sinMock;

    @Test
    void calculate_correctlyCallsSinAndCosMock() {
        Csc csc = new Csc(sinMock);
        when(sinMock.calculate(any(), any())).thenReturn(BigDecimal.ONE);

        csc.calculate(BigDecimal.ONE, BigDecimal.valueOf(0.001));

        verify(sinMock, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
    }

    private static Stream<InputData> setUpTestsData() {
        return Stream.of(
                new InputData(BigDecimal.valueOf(1), BigDecimal.valueOf(1.188395), BigDecimal.valueOf(1e-5)),
                new InputData(BigDecimal.valueOf(-1), BigDecimal.valueOf(-1.188395), BigDecimal.valueOf(1e-5)),
                new InputData(BigDecimal.valueOf(0.5), BigDecimal.valueOf(2.085829), BigDecimal.valueOf(1e-5))
        );
    }

    @ParameterizedTest
    @MethodSource("setUpTestsData")
    void calculate_returnsCorrectAnswer(InputData inputData) {
        Csc csc = new Csc(new Sin());
        assertEquals(0, inputData.expected.setScale(3, RoundingMode.HALF_UP).compareTo(csc.calculate(inputData.x, inputData.precision).setScale(3, RoundingMode.HALF_UP)));
    }

    private record InputData(BigDecimal x, BigDecimal expected, BigDecimal precision) {}
}
