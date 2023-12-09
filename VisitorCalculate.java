import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class VisitorCalculate implements ICalculate{

    private final static BigDecimal freeFee = new BigDecimal(10);
    private final static BigDecimal procentageReduction = new BigDecimal(0.5);
    public BigDecimal ParentCalculate(BigDecimal normalRate, BigDecimal reducedRate, ArrayList<Period> normalPeriods, ArrayList<Period> reducedPeriods, Period periodStay)
    {
        int normalRateHours = periodStay.occurences(normalPeriods);
        int reducedRateHours = periodStay.occurences(reducedPeriods);

        BigDecimal results = (normalRate.multiply(BigDecimal.valueOf(normalRateHours))).add(reducedRate.multiply(BigDecimal.valueOf(reducedRateHours)));
        if( results.compareTo(freeFee)>= 0)
        {
            results = results.subtract(freeFee);
            return results.multiply(procentageReduction).setScale(2, RoundingMode.UP);
        }
        else
        {
            return BigDecimal.ZERO.setScale(2, RoundingMode.UP);
        }
    }
}
