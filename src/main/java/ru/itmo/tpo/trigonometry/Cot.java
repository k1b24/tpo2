package ru.itmo.tpo.trigonometry;

import ru.itmo.tpo.function.FunctionInterface;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static java.lang.String.format;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.*;
import static java.math.RoundingMode.HALF_EVEN;

public class Cot implements FunctionInterface {

    private final Tan tan;

    public Cot(Tan tan) {
        this.tan = tan;
    }

    public BigDecimal calculate(BigDecimal x, BigDecimal precision) throws ArithmeticException {
        final BigDecimal tanValue = tan.calculate(x, precision);

        if (tanValue.compareTo(ZERO) == 0) {
            throw new ArithmeticException(format("Function value for argument %s doesn't exist", x));
        }

        final BigDecimal result = BigDecimal.ONE.divide(tanValue, DECIMAL128.getPrecision(), HALF_EVEN);
        return result.setScale(precision.scale(), HALF_EVEN);
    }
}
