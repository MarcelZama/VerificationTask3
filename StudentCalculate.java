import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class StudentCalculate implements ICalculate{

    private final static BigDecimal studentDiscountLimit = new BigDecimal(5.5);
    private final static BigDecimal discountRate = new BigDecimal(0.33);
    public BigDecimal ParentCalculate(BigDecimal normalRate, BigDecimal reducedRate, ArrayList<Period> normalPeriods, ArrayList<Period> reducedPeriods, Period periodStay)
    {
        int normalRateHours = periodStay.occurences(normalPeriods);
        int reducedRateHours = periodStay.occurences(reducedPeriods);

        BigDecimal results = (normalRate.multiply(BigDecimal.valueOf(normalRateHours))).add(reducedRate.multiply(BigDecimal.valueOf(reducedRateHours)));
        if(results.compareTo(studentDiscountLimit) > 0)
        {
            BigDecimal remaining = results.subtract(studentDiscountLimit).multiply(discountRate);
            return results.subtract(remaining).setScale(2, RoundingMode.DOWN);
        }
        else
            return results.setScale(2,RoundingMode.DOWN);
    }
}
