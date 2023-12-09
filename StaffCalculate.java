import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class StaffCalculate implements ICalculate{

    private final static BigDecimal staffMaxPay = new BigDecimal(10);
    public BigDecimal ParentCalculate(BigDecimal normalRate, BigDecimal reducedRate, ArrayList<Period> normalPeriods, ArrayList<Period> reducedPeriods, Period periodStay)
    {
        int normalRateHours = periodStay.occurences(normalPeriods);
        int reducedRateHours = periodStay.occurences(reducedPeriods);

        BigDecimal results = (normalRate.multiply(BigDecimal.valueOf(normalRateHours))).add(reducedRate.multiply(BigDecimal.valueOf(reducedRateHours)));
        if (results.compareTo(staffMaxPay) < 0) {
            return results.setScale(2, RoundingMode.DOWN);
        } else
            return staffMaxPay.setScale(2, RoundingMode.DOWN);
    }
}
