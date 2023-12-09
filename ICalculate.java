import java.math.BigDecimal;
import java.util.ArrayList;

public interface ICalculate {
    public abstract BigDecimal ParentCalculate(BigDecimal normalRate, BigDecimal reducedRate, ArrayList<Period> normalPeriods, ArrayList<Period> reducedPeriods,Period stay);
}
