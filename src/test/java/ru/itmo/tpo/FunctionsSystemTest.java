package ru.itmo.tpo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itmo.tpo.logariphmic.Ln;
import ru.itmo.tpo.logariphmic.Log;
import ru.itmo.tpo.logariphmic.LogTest;
import ru.itmo.tpo.trigonometry.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FunctionsSystemTest {

    @Mock
    private Tan tanMock;
    @Mock
    private Cot cotMock;
    @Mock
    private Csc cscMock;
    @Mock
    private Ln lnMock;
    @Mock
    private Log log3Mock;
    @Mock
    private Log log10Mock;
    @Mock
    private Log log5Mock;

    @Test
    void calculate_correctlyCallsMocks_whenArgumentIsZero() {
        FunctionsSystem system = new FunctionsSystem(tanMock, cotMock, cscMock, lnMock, log3Mock, log10Mock, log5Mock);
        when(tanMock.calculate(any(), any())).thenReturn(BigDecimal.ONE);
        when(cotMock.calculate(any(), any())).thenReturn(BigDecimal.ONE);
        when(cscMock.calculate(any(), any())).thenReturn(BigDecimal.ONE);

        system.calculate(BigDecimal.ZERO, BigDecimal.valueOf(0.001));

        verify(tanMock, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
        verify(cotMock, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
        verify(cscMock, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
    }

    @Test
    void calculate_correctlyCallsMocks_whenArgumentIsOne() {
        FunctionsSystem system = new FunctionsSystem(tanMock, cotMock, cscMock, lnMock, log3Mock, log10Mock, log5Mock);
        when(lnMock.calculate(any(), any())).thenReturn(BigDecimal.ONE);
        when(log3Mock.calculate(any(), any())).thenReturn(BigDecimal.ONE);
        when(log10Mock.calculate(any(), any())).thenReturn(BigDecimal.ONE);
        when(log5Mock.calculate(any(), any())).thenReturn(BigDecimal.ONE);

        system.calculate(BigDecimal.ONE, BigDecimal.valueOf(0.001));

        verify(lnMock, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
        verify(log3Mock, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
        verify(log10Mock, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
        verify(log5Mock, atLeastOnce()).calculate(any(BigDecimal.class), any(BigDecimal.class));
    }

    private static Stream<InputData> setUpTestsData() {
        return Stream.of(
                new InputData(BigDecimal.valueOf(-2), BigDecimal.valueOf(1.404319), BigDecimal.valueOf(1e-5)),
                new InputData(BigDecimal.valueOf(-1), BigDecimal.valueOf(6.540334), BigDecimal.valueOf(1e-5)),
                new InputData(BigDecimal.valueOf(1), BigDecimal.valueOf(0), BigDecimal.valueOf(1e-5)),
                new InputData(BigDecimal.valueOf(2), BigDecimal.valueOf(2.816363), BigDecimal.valueOf(1e-5))
        );
    }

    @ParameterizedTest
    @MethodSource("setUpTestsData")
    void calculate_returnsCorrectAnswer(InputData inputData) {
        Sin sin = new Sin();
        Cos cos = new Cos(sin);
        Tan tan = new Tan(sin, cos);
        Csc csc = new Csc(sin);
        Cot cot = new Cot(tan);
        Ln ln = new Ln();
        Log log3 = new Log(ln, 3);
        Log log5 = new Log(ln, 5);
        Log log10 = new Log(ln, 10);
        FunctionsSystem functionsSystem = new FunctionsSystem(tan, cot, csc, ln, log3, log10, log5);
        assertEquals(0, inputData.expected.setScale(1, RoundingMode.HALF_UP).compareTo(functionsSystem.calculate(inputData.x, inputData.precision).setScale(1, RoundingMode.HALF_UP)));
    }

    private record InputData(BigDecimal x, BigDecimal expected, BigDecimal precision) {}
}
