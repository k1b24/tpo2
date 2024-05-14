package ru.itmo.tpo.trigonometry;

import ru.itmo.tpo.function.FunctionInterface;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;

public class Cos implements FunctionInterface {

    private final Sin sin;

    public Cos(final Sin sin) {
        super();
        this.sin = sin;
    }

    public BigDecimal calculate(BigDecimal x, BigDecimal precision)
            throws ArithmeticException {

        if (x.compareTo(ZERO) == 0) {
            return ONE;
        }

        final BigDecimal result =
                sin.calculate(
                        (new BigDecimal(Math.PI)).divide(new BigDecimal(2), DECIMAL128.getPrecision(), HALF_EVEN).subtract(x),
                        precision
                );
        return result.setScale(precision.scale(), HALF_EVEN);
    }
}
