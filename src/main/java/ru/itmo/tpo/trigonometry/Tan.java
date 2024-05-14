package ru.itmo.tpo.trigonometry;

import ru.itmo.tpo.function.FunctionInterface;

import java.math.BigDecimal;

import static java.lang.String.format;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;

public class Tan implements FunctionInterface {

    private final Sin sin;
    private final Cos cos;

    public Tan(final Sin sin, final Cos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    public BigDecimal calculate(BigDecimal x, BigDecimal precision)
            throws ArithmeticException {

        final BigDecimal sinValue = sin.calculate(x, precision);
        final BigDecimal cosValue = cos.calculate(x, precision);

        if (cosValue.compareTo(ZERO) == 0) {
            throw new ArithmeticException(format("Function value for argument %s doesn't exist", x));
        }

        final BigDecimal result = sinValue.divide(cosValue, DECIMAL128.getPrecision(), HALF_EVEN);
        return result.setScale(precision.scale(), HALF_EVEN);
    }
}
