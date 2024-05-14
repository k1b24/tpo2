package ru.itmo.tpo.function;

import java.math.BigDecimal;

public interface FunctionInterface {
    BigDecimal calculate(final BigDecimal x, final BigDecimal precision);
}
