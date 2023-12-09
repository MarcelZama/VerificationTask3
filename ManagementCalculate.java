import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class ManagementCalculate implements ICalculate{
    private final static BigDecimal minimumPayment = new BigDecimal(5);
    public BigDecimal ParentCalculate(BigDecimal normalRate, BigDecimal reducedRate, ArrayList<Period> normalPeriods, ArrayList<Period> reducedPeriods, Period periodStay)
    {
        int normalRateHours = periodStay.occurences(normalPeriods);
        int reducedRateHours = periodStay.occurences(reducedPeriods);

        BigDecimal results = (normalRate.multiply(BigDecimal.valueOf(normalRateHours))).add(reducedRate.multiply(BigDecimal.valueOf(reducedRateHours)));
        if(results.compareTo(minimumPayment) <= 0)
        {
            return (minimumPayment).setScale(2, RoundingMode.UP);
        }
        else
            return results.setScale(2,RoundingMode.UP);
    }
}
