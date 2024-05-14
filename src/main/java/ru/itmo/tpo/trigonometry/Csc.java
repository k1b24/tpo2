package ru.itmo.tpo.trigonometry;

import ru.itmo.tpo.function.FunctionInterface;

import java.math.BigDecimal;

import static java.lang.String.format;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;

public class Csc implements FunctionInterface {

    private final Sin sin;

    public Csc(Sin sin) {
        this.sin = sin;
    }

    public BigDecimal calculate(BigDecimal x, BigDecimal precision) {
        final BigDecimal sinValue = sin.calculate(x, precision);

        if (sinValue.compareTo(ZERO) == 0) {
            throw new ArithmeticException(format("Function value for argument %s doesn't exist", x));
        }

        final BigDecimal result = BigDecimal.ONE.divide(sinValue, DECIMAL128.getPrecision(), HALF_EVEN);
        return result.setScale(precision.scale(), HALF_EVEN);
    }
}
