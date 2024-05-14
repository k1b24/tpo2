package ru.itmo.tpo.logariphmic;

import ru.itmo.tpo.function.FunctionInterface;

import java.math.BigDecimal;

import static java.lang.String.format;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;

public class Log implements FunctionInterface {

    private final Ln ln;
    private final int base;


    public Log(Ln ln, int base) {
        this.ln = ln;
        this.base = base;
    }

    public BigDecimal calculate(final BigDecimal x, final BigDecimal precision)
            throws ArithmeticException {

        if (x.compareTo(ZERO) <= 0) {
            throw new ArithmeticException(format("Function value for argument %s doesn't exist", x));
        }

        final BigDecimal result =
                ln.calculate(x, precision)
                        .divide(
                                ln.calculate(new BigDecimal(base), precision),
                                DECIMAL128.getPrecision(),
                                HALF_EVEN);
        return result.setScale(precision.scale(), HALF_EVEN);
    }
}
