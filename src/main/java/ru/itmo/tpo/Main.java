package ru.itmo.tpo;

import ru.itmo.tpo.csv.CsvWriter;
import ru.itmo.tpo.logariphmic.Ln;
import ru.itmo.tpo.logariphmic.Log;
import ru.itmo.tpo.trigonometry.*;

import java.io.IOException;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) throws IOException {

        Sin sin = new Sin();
        CsvWriter.write(
                "csv/sin.csv",
                sin,
                new BigDecimal(-1),
                new BigDecimal(1),
                new BigDecimal("0.1"),
                new BigDecimal("0.0000000001"));
        Cos cos = new Cos(sin);
        CsvWriter.write(
                "csv/cos.csv",
                cos,
                new BigDecimal(-1),
                new BigDecimal(1),
                new BigDecimal("0.1"),
                new BigDecimal("0.0000000001"));
        Tan tan = new Tan(sin, cos);
        CsvWriter.write(
                "csv/tan.csv",
                tan,
                new BigDecimal(-1),
                new BigDecimal(1),
                new BigDecimal("0.1"),
                new BigDecimal("0.0000000001"));
        Cot cot = new Cot(tan);
        CsvWriter.write(
                "csv/cot.csv",
                cot,
                new BigDecimal(-2),
                new BigDecimal(-1),
                new BigDecimal("0.1"),
                new BigDecimal("0.0000000001"));
        Csc csc = new Csc(sin);
        CsvWriter.write(
                "csv/csc.csv",
                csc,
                new BigDecimal(-2),
                new BigDecimal(-1),
                new BigDecimal("0.1"),
                new BigDecimal("0.0000000001"));
        Ln ln = new Ln();
        CsvWriter.write(
                "csv/ln.csv",
                ln,
                new BigDecimal(1),
                new BigDecimal(2),
                new BigDecimal("0.1"),
                new BigDecimal("0.0000000001"));
        Log log2 = new Log(ln, 2);
        CsvWriter.write(
                "csv/log2.csv",
                log2,
                new BigDecimal(1),
                new BigDecimal(2),
                new BigDecimal("0.1"),
                new BigDecimal("0.0000000001"));
        FunctionsSystem functionsSystem = new FunctionsSystem(tan, cot, csc, ln, new Log(ln, 3), new Log(ln, 10), new Log(ln, 5));
        CsvWriter.write(
                "csv/function_system.csv",
                functionsSystem,
                new BigDecimal(-3),
                new BigDecimal(-2),
                new BigDecimal("0.1"),
                new BigDecimal("0.0000000001"));
    }
}