package ru.itmo.tpo;

import ru.itmo.tpo.function.FunctionInterface;
import ru.itmo.tpo.logariphmic.Ln;
import ru.itmo.tpo.logariphmic.Log;
import ru.itmo.tpo.trigonometry.Cot;
import ru.itmo.tpo.trigonometry.Csc;
import ru.itmo.tpo.trigonometry.Tan;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;

public class FunctionsSystem implements FunctionInterface {

    private final Tan tan;
    private final Cot cot;
    private final Csc csc;
    private final Ln ln;
    private final Log log3;
    private final Log log10;
    private final Log log5;


    public FunctionsSystem(Tan tan, Cot cot, Csc csc, Ln ln, Log log3, Log log10, Log log5) {
        this.tan = tan;
        this.cot = cot;
        this.csc = csc;
        this.ln = ln;
        this.log3 = log3;
        this.log10 = log10;
        this.log5 = log5;
    }

    public BigDecimal calculate(BigDecimal x, BigDecimal precision) {
        if (x.compareTo(ZERO) <= 0) {
            return csc.calculate(x, precision)
                    .divide(cot.calculate(x, precision), HALF_UP)
                    .divide(csc.calculate(x, precision), HALF_UP)
                    .multiply(cot.calculate(x, precision))
                    .subtract(tan.calculate(x, precision))
                    .pow(2);
        } else {
            return ln.calculate(x, precision).pow(3)
                    .multiply(log10.calculate(x, precision))
                    .add(log5.calculate(x, precision))
                    .add(ln.calculate(x, precision))
                    .add(
                            log3.calculate(x, precision)
                                    .add(log3.calculate(x, precision))
                                    .pow(2)
                    );
        }
    }
}
