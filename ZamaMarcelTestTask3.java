import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ZamaMarcelTestTask3 {
    // 			Verification Project Task 3
    //							By Marcel Zama (C00260146)
    //		Date :23/11/2023

    // Branch Coverage :  Rate - 100% (36/36)
    //                    Period - 96% (25/26)

    /* -------------------------------------------------------------------------- */
    /*                     Period Class Test Cases                                */
    /* -------------------------------------------------------------------------- */

    //Test the Period constructor with valid parameters.
    @Test
    public void testValidPeriod() {
        Period period = new Period(4, 5);
        Assertions.assertInstanceOf(Period.class, period);
    }

    //Test the Period constructor with zero start and end times.
    @Test
    public void testZeroStartEnd() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Period(0, 0));
    }


    //Test the Period constructor with negative start and end times.
    @Test
    public void testNegativeStartEnd() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Period(-5, -3));
    }

    //Test the Period constructor with equal start and end times.
    @Test
    public void testEqualStartEnd() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Period(8, 8));
    }

    //Test the duration method of the Period class with valid parameters.
    @Test
    public void testValidDuration() {
        Period period = new Period(4, 5);
        assertEquals(1, period.duration());
    }

    //Test the duration method of the Period class with zero duration.
    @Test
    public void testZeroDuration() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Period(7, 7));
    }

    //Test the duration method of the Period class with negative duration.
    @Test
    public void testNegativeDuration() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Period(5, 3));
    }

    //Test the duration method of the Period class with large duration.
    @Test
    public void testLargeDuration() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Period(1, 1000));
    }

    // Test case to check if the occurences method correctly counts the number of occurrences of a period in a list
    @Test
    public void testOccurencesInList() {
        Period p1 = new Period(8, 10);
        Period p2 = new Period(9, 11);
        Period p3 = new Period(14, 16);
        ArrayList<Period> periodList = new ArrayList<>();
        periodList.add(p1);
        periodList.add(p2);
        periodList.add(p3);


        assertEquals(2, p1.occurences(periodList));
    }


    //Test the Rate constructor with overlapping normal periods.
    @Test
    public void RateTestCaseoverlappingnormalperiods() {
        assertThrows(IllegalArgumentException.class, () -> {
            CarParkKind kind = CarParkKind.STAFF;
            BigDecimal normalRate = BigDecimal.valueOf(7.45);
            BigDecimal reducedRate = BigDecimal.valueOf(6.25);
            ArrayList<Period> normalPeriods = new ArrayList<>();
            ArrayList<Period> reducedPeriods = new ArrayList<>();

            normalPeriods.add(new Period(9, 17));
            normalPeriods.add(new Period(20, 23));
            reducedPeriods.add(new Period(15, 19));

            Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);
        });
    }

    //Test the Rate constructor with overlapping normal and reduced periods.
    @Test
    public void RateTestCaseoverlappingnormalandreducedperiods() {
        assertThrows(IllegalArgumentException.class, () -> {
            CarParkKind kind = CarParkKind.STAFF;
            BigDecimal normalRate = BigDecimal.valueOf(7.25);
            BigDecimal reducedRate = BigDecimal.valueOf(6.25);
            ArrayList<Period> normalPeriods = new ArrayList<>();
            ArrayList<Period> reducedPeriods = new ArrayList<>();

            reducedPeriods.add(new Period(8, 12));
            reducedPeriods.add(new Period(10, 14));
            normalPeriods.add(new Period(8, 12));
            normalPeriods.add(new Period(14, 18));

            Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);
        });
    }

    // Test case to ensure that constructing a Rate object with negative rates throws an IllegalArgumentException
    @Test
    public void testInvalidRateConstruction_NegativeRates() {
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();

        // Verify that constructing a Rate with negative rates results in an exception
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Rate(CarParkKind.STUDENT, BigDecimal.valueOf(-5), BigDecimal.TEN, normalPeriods, reducedPeriods);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Rate(CarParkKind.STUDENT, BigDecimal.TEN, BigDecimal.valueOf(-5), normalPeriods, reducedPeriods);
        });
    }

    // Test case to ensure that constructing a Rate object with overlapping periods throws an IllegalArgumentException
    @Test
    public void testInvalidRateConstruction_OverlappingPeriods() {
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(8, 12));
        normalPeriods.add(new Period(10, 18)); // Overlapping period

        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(18, 22));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Rate(CarParkKind.STUDENT, BigDecimal.TEN, BigDecimal.valueOf(5), normalPeriods, reducedPeriods);
        });
    }

    // Test case to ensure that constructing a Rate with overlapping periods throws an IllegalArgumentException
    @Test
    public void testInvalidRateConstruction_OverlappingPeriods2() {
        assertThrows(IllegalArgumentException.class, () -> {
            ArrayList<Period> normalPeriods = new ArrayList<>();
            normalPeriods.add(new Period(0,1));
            normalPeriods.add(new Period(1,2));

            ArrayList<Period> reducedPeriods = new ArrayList<>();
            reducedPeriods.add(new Period(2,3));
            reducedPeriods.add(new Period(3,4));
            reducedPeriods.add(new Period(0,3));

            new Rate(CarParkKind.STUDENT, BigDecimal.TEN, BigDecimal.valueOf(5), normalPeriods, reducedPeriods);
        });
    }

    // Test case to check if constructing a Rate with a null normal rate throws an IllegalArgumentException
    @Test
    public void RateTestCasenullnormalRate() {
        assertThrows(IllegalArgumentException.class, () -> {
            CarParkKind kind = CarParkKind.STAFF;
            BigDecimal normalRate = null;
            BigDecimal reducedRate = BigDecimal.valueOf(6.25);
            ArrayList<Period> normalPeriods = new ArrayList<>();
            ArrayList<Period> reducedPeriods = new ArrayList<>();

            reducedPeriods.add(new Period(8, 12));
            reducedPeriods.add(new Period(10, 14));
            normalPeriods.add(new Period(8, 12));
            normalPeriods.add(new Period(14, 18));

            Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);
        });
    }

    // Test case to check if constructing a Rate with a null reduced rate throws an IllegalArgumentException
    @Test
    public void RateTestCasenullreducedRate() {
        assertThrows(IllegalArgumentException.class, () -> {
            CarParkKind kind = CarParkKind.STAFF;
            BigDecimal normalRate = BigDecimal.valueOf(6.25);
            BigDecimal reducedRate = null;
            ArrayList<Period> normalPeriods = new ArrayList<>();
            ArrayList<Period> reducedPeriods = new ArrayList<>();

            reducedPeriods.add(new Period(8, 12));
            reducedPeriods.add(new Period(10, 14));
            normalPeriods.add(new Period(8, 12));
            normalPeriods.add(new Period(14, 18));

            Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);
        });
    }

    // Test case to check if constructing a Rate with null normal periods throws an IllegalArgumentException
    @Test
    public void RateTestCaseNULLNormalPeriods() {
        assertThrows(IllegalArgumentException.class, () -> {
            CarParkKind kind = CarParkKind.STAFF;
            BigDecimal normalRate = BigDecimal.valueOf(7.25);
            BigDecimal reducedRate = BigDecimal.valueOf(6.25);
            ArrayList<Period> normalPeriods = null;
            ArrayList<Period> reducedPeriods = new ArrayList<>();

            reducedPeriods.add(new Period(8, 12));
            reducedPeriods.add(new Period(10, 14));

            Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);
        });
    }

    // Test case to check if the calculate method returns the correct total for a VISITOR within normal rate hours
    @Test
    public void testCalculateNormalRateVISITOR() {
        CarParkKind kind = CarParkKind.VISITOR;
        BigDecimal normalRate = new BigDecimal("10.00");
        BigDecimal reducedRate = new BigDecimal("5.00");
        ArrayList<Period> normalPeriods = new ArrayList<Period>();
        normalPeriods.add(new Period(1,5));
        normalPeriods.add(new Period(8,12));
        ArrayList<Period> reducedPeriods = new ArrayList<Period>();
        reducedPeriods.add(new Period(5,8));
        reducedPeriods.add(new Period(12,23));
        Rate rate = new Rate(kind, normalRate, reducedRate,normalPeriods,reducedPeriods);
        Period periodStay = new Period(8, 11); // Within normal rate hours

        BigDecimal expectedTotal = new BigDecimal("0");
        BigDecimal calculatedTotal = rate.calculate(periodStay);

        assertEquals(expectedTotal, calculatedTotal);
    }

    // Test case to check if constructing a Rate with a period start time over 24 hours throws an IllegalArgumentException
    @Test
    public void TestPeriodStartOVER24() {
        assertThrows(IllegalArgumentException.class, () -> {
            CarParkKind kind = CarParkKind.STAFF;
            BigDecimal normalRate = BigDecimal.valueOf(7.25);
            BigDecimal reducedRate = BigDecimal.valueOf(6.25);
            ArrayList<Period> normalPeriods = null;
            ArrayList<Period> reducedPeriods = new ArrayList<>();

            reducedPeriods.add(new Period(26, 28));

            Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);
        });
    }

    // Test case to check if constructing a Rate with overlapping periods throws an IllegalArgumentException
    @Test
    public void RateisValidPeriodOverlap() {
        assertThrows(IllegalArgumentException.class, () -> {
            CarParkKind kind = CarParkKind.VISITOR;
            BigDecimal normalRate = new BigDecimal("10.00");
            BigDecimal reducedRate = new BigDecimal("5.00");
            ArrayList<Period> normalPeriods = new ArrayList<Period>();
            normalPeriods.add(new Period(1, 7));
            normalPeriods.add(new Period(8, 14));
            ArrayList<Period> reducedPeriods = new ArrayList<Period>();
            reducedPeriods.add(new Period(5, 8));
            reducedPeriods.add(new Period(12, 23));
            Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);
            Period periodStay = new Period(8, 11); // Within normal rate hours
        });
    }


    /* -------------------------------------------------------------------------- */
    /*                           Rate Class Test Cases                            */
    /* -------------------------------------------------------------------------- */


    // Test case to check if constructing a Rate with valid input parameters does not throw any exceptions
    @Test
    public void testValidInput() {
        CarParkKind kind = CarParkKind.MANAGEMENT;
        BigDecimal normalRate = new BigDecimal("10.00");
        BigDecimal reducedRate = new BigDecimal("5.00");
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();

        Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);

        // Check that the constructor does not throw any exceptions
        assertNotNull(rate);
    }

    // Test case to check if constructing a Rate with a null CarParkKind does not throw any exceptions
    @Test
    public void testNullKind() {
        CarParkKind kind = null;
        BigDecimal normalRate = new BigDecimal("10.00");
        BigDecimal reducedRate = new BigDecimal("5.00");
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();

        Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);

        // Check that the constructor does not throw any exceptions
        assertNotNull(rate);
    }

    // Test case to check if constructing a Rate with negative rates throws an IllegalArgumentException
    @Test
    public void testNegativeRates() {
        CarParkKind kind = CarParkKind.STUDENT;
        BigDecimal normalRate = new BigDecimal("-10.00");
        BigDecimal reducedRate = new BigDecimal("-5.00");
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();

        // Check that the constructor does not throw any exceptions
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Rate(kind,normalRate,reducedRate,normalPeriods,reducedPeriods));
    }

    // Test case to check if constructing a Rate with null ArrayLists throws an IllegalArgumentException
    @Test
    public void testNullArrayLists() {
        CarParkKind kind = CarParkKind.VISITOR;
        BigDecimal normalRate = new BigDecimal("10.00");
        BigDecimal reducedRate = new BigDecimal("5.00");
        ArrayList<Period> normalPeriods = null;
        ArrayList<Period> reducedPeriods = null;

        // Chatch the exeption if the contructor does throw an exeption
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Rate(kind,normalRate,reducedRate,normalPeriods,reducedPeriods));
    }

    // Test case to check if constructing a Rate with zero rates throws an IllegalArgumentException
    @Test
    public void testZeroRates() {
        CarParkKind kind = CarParkKind.STUDENT;
        BigDecimal normalRate = BigDecimal.ZERO;
        BigDecimal reducedRate = BigDecimal.ZERO;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();

        // Check that the constructor does throw an exceptions as it should
        assertThrows(IllegalArgumentException.class, () -> new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods));
    }

    // Test case to check if constructing a Rate with valid normalRate does not throw any exceptions
    @Test
    void testValidnormalRate() { //Testing the Rate constructor
        BigDecimal minrate = new BigDecimal("1");
        BigDecimal maxrate = new BigDecimal("5");
        ArrayList<Period> normalPeriods = new ArrayList<Period>();
        ArrayList<Period> reducedPeriods = new ArrayList<Period>();
        normalPeriods.add(new Period(10,17));
        reducedPeriods.add(new Period(17,24));
        Rate test = new Rate(CarParkKind.STUDENT,maxrate,minrate,normalPeriods,reducedPeriods);

        assertNotNull(test);
    }

    // Test case to check if constructing a Rate with valid reducedRate does not throw any exceptions
    @Test
    void testValidreducedRate() { //Testing the Rate constructor
        CarParkKind kind = CarParkKind.STUDENT;
        BigDecimal minrate = new BigDecimal("1");
        BigDecimal maxrate = new BigDecimal("5");
        ArrayList<Period> normalPeriods = new ArrayList<Period>();
        ArrayList<Period> reducedPeriods = new ArrayList<Period>();
        normalPeriods.add(new Period(10,17));
        reducedPeriods.add(new Period(17,24));

        //Check if does not throw any errors , it should not.
        assertDoesNotThrow(() -> new Rate(kind, maxrate, minrate, normalPeriods, reducedPeriods));
    }

    // Test case to check if constructing a Rate with normalRate equal to zero returns a Rate instance
    @Test
    void testnormalRateequalzero() { //Testing the Rate constructor
        BigDecimal minrate = new BigDecimal("1");
        BigDecimal maxrate = new BigDecimal("5");
        ArrayList<Period> normalPeriods = new ArrayList<Period>();
        ArrayList<Period> reducedPeriods = new ArrayList<Period>();
        normalPeriods.add(new Period(10,17));
        reducedPeriods.add(new Period(17,24));
        Rate test = new Rate(CarParkKind.STUDENT,maxrate,minrate,normalPeriods,reducedPeriods);

        Assertions.assertInstanceOf(Rate.class, test);
    }


    // Test case to check if constructing a Rate with normalRate and reducedRate in incorrect order throws an exception
    @Test
    void test5() { //Testing the Rate constructor // normalRate and reducedRate >= 0
        CarParkKind kind = CarParkKind.STUDENT;
        BigDecimal minrate = new BigDecimal("1");
        BigDecimal maxrate = new BigDecimal("5");
        ArrayList<Period> normalPeriods = new ArrayList<Period>();
        ArrayList<Period> reducedPeriods = new ArrayList<Period>();
        normalPeriods.add(new Period(10,17));
        reducedPeriods.add(new Period(17,24));

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Rate(kind,minrate,maxrate,normalPeriods,reducedPeriods));
    }

    // Test case to calculate the total cost for a period of stay within normal rate hours
    @Test
    public void testCalculateNormalRate() {
        CarParkKind kind = CarParkKind.STAFF;
        BigDecimal normalRate = new BigDecimal("10.00");
        BigDecimal reducedRate = new BigDecimal("5.00");
        ArrayList<Period> normalPeriods = new ArrayList<Period>();
        normalPeriods.add(new Period(1,5));
        normalPeriods.add(new Period(8,12));
        ArrayList<Period> reducedPeriods = new ArrayList<Period>();
        reducedPeriods.add(new Period(5,8));
        reducedPeriods.add(new Period(12,23));
        Rate rate = new Rate(kind, normalRate, reducedRate,normalPeriods,reducedPeriods);
        Period periodStay = new Period(8, 11); // Within normal rate hours

        BigDecimal expectedTotal = new BigDecimal("30.00");
        BigDecimal calculatedTotal = rate.calculate(periodStay);

        assertEquals(expectedTotal, calculatedTotal);
    }

    // Test case to calculate the total cost for a period of stay within reduced rate hours
    @Test
    public void testCalculateReducedRate() {
        CarParkKind kind = CarParkKind.MANAGEMENT;
        BigDecimal normalRate = new BigDecimal("10.00");
        BigDecimal reducedRate = new BigDecimal("5.00");
        ArrayList<Period> normalPeriods = new ArrayList<Period>();
        normalPeriods.add(new Period(1,5));
        normalPeriods.add(new Period(8,12));
        ArrayList<Period> reducedPeriods = new ArrayList<Period>();
        reducedPeriods.add(new Period(5,8));
        reducedPeriods.add(new Period(12,23));

        Rate rate = new Rate(kind,normalRate,reducedRate,normalPeriods,reducedPeriods);

        Period periodStay = new Period(18, 22); // Within reduced rate hours

        BigDecimal expectedTotal = new BigDecimal("20.00");
        BigDecimal calculatedTotal = rate.calculate(periodStay);

        assertEquals(expectedTotal, calculatedTotal);
    }

    // Test case to calculate the total cost for a period of stay with mixed rate hours
    @Test
    public void testCalculateMixedRate() {
        CarParkKind kind = CarParkKind.STAFF;
        BigDecimal normalRate = new BigDecimal("10.00");
        BigDecimal reducedRate = new BigDecimal("5.00");
        ArrayList<Period> normalPeriods = new ArrayList<Period>();
        normalPeriods.add(new Period(1,5));
        normalPeriods.add(new Period(8,12));
        ArrayList<Period> reducedPeriods = new ArrayList<Period>();
        reducedPeriods.add(new Period(5,8));
        reducedPeriods.add(new Period(12,16));
        Rate rate = new Rate(kind, normalRate,reducedRate,normalPeriods,reducedPeriods);

        Period periodStay = new Period(15, 18); // Part of the period within normal rate hours and part within reduced rate hours

        BigDecimal expectedTotal = new BigDecimal("5.00");
        BigDecimal calculatedTotal = rate.calculate(periodStay);

        assertEquals(expectedTotal, calculatedTotal);
    }

    // Test case to ensure that creating a Rate with zero normal and reduced rates throws an exception
    @Test
    public void testCalculateZeroRates() {
        ArrayList<Period> normalPeriods = new ArrayList<Period>();
        ArrayList<Period> reducedPeriods = new ArrayList<Period>();


        Assertions.assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STUDENT, BigDecimal.ZERO, BigDecimal.ZERO,normalPeriods,reducedPeriods));
    }

    // Test case to ensure that Visitor.calculate() does not return 0
    @Test
    public void testVistorEqualNotZero() {
        CarParkKind kind = CarParkKind.VISITOR;
        BigDecimal normalRate = BigDecimal.ONE;
        BigDecimal reducedRate = BigDecimal.ZERO;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(1,5));
        normalPeriods.add(new Period(8,12));

        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(5,8));
        reducedPeriods.add(new Period(12,20));

        Period periodStay = new Period(8, 10);

        // Check that the constructor does throw an exceptions as it should
         Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);

        assertNotEquals(rate.calculate(periodStay), BigDecimal.ZERO);
    }

    // Test case to ensure that Normal Rate can be equal to the Reduced Rate
    @Test
    public void testNormalRateCanEqualReducedRate() {
        CarParkKind kind = CarParkKind.VISITOR;
        BigDecimal normalRate = BigDecimal.ZERO;
        BigDecimal reducedRate = BigDecimal.ZERO;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(1,5));
        normalPeriods.add(new Period(8,12));

        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(5,8));
        reducedPeriods.add(new Period(12,20));

        Period periodStay = new Period(8, 10);

        assertDoesNotThrow(() -> new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods));
    }

    // Test case to ensure that CarParkKind can not be Null
    @Test
    public void testCarParkKindNull() {
        CarParkKind kind = null;
        BigDecimal normalRate = BigDecimal.ZERO;
        BigDecimal reducedRate = BigDecimal.ZERO;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(1,5));
        normalPeriods.add(new Period(8,12));

        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(5,8));
        reducedPeriods.add(new Period(12,20));

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods));
    }

    // Test case to ensure that Visitor.calculate returns 1 if the number of normalPeriods hours is equal to 10 plus 2 hours from reducedPeriods witch are calculated as (each multiply by 0.5)
    @Test
    public void testVisitorDataMoreTen() {
        CarParkKind kind = CarParkKind.VISITOR;
        BigDecimal normalRate = BigDecimal.ONE;
        BigDecimal reducedRate = BigDecimal.ONE;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(0,12));

        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(12,23));

        Period periodStay = new Period(2, 14);

        Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);

        assertEquals(rate.calculate(periodStay), new BigDecimal(1).setScale(2, RoundingMode.DOWN));
    }

    // Test case to ensure that Visitor.calculate returns 0 if the number of normalPeriods hours is equal to 10 , as expected by following the specification.
    @Test
    public void testVisitorDataEqualTen() {
        CarParkKind kind = CarParkKind.VISITOR;
        BigDecimal normalRate = BigDecimal.ONE;
        BigDecimal reducedRate = BigDecimal.ONE;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(0,12));

        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(12,23));

        Period periodStay = new Period(0, 10);

        Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);

        assertEquals(rate.calculate(periodStay), new BigDecimal(0).setScale(2, RoundingMode.DOWN));
    }

    // Test case to ensure that Visitor.calculate returns 0 if the number of normalPeriods hours is equal to 8 , as expected by following the specification.
    @Test
    public void testVisitorDataLessTen() {
        CarParkKind kind = CarParkKind.VISITOR;
        BigDecimal normalRate = BigDecimal.ONE;
        BigDecimal reducedRate = BigDecimal.ONE;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(0,12));

        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(12,23));

        Period periodStay = new Period(0, 8);

        Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);

        assertEquals(rate.calculate(periodStay), new BigDecimal(0).setScale(2, RoundingMode.DOWN));
    }

    // Test case to ensure that Management.calculate returns at least 5 even if the number returned by calculate is less than five
    @Test
    public void testManagementDataLessThanFive() {
        CarParkKind kind = CarParkKind.MANAGEMENT;
        BigDecimal normalRate = BigDecimal.ONE;
        BigDecimal reducedRate = BigDecimal.ONE;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(0,12));

        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(12,23));

        Period periodStay = new Period(0, 4);

        Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);

        assertEquals(rate.calculate(periodStay), new BigDecimal(5).setScale(2, RoundingMode.DOWN));
    }

    // Test case to ensure that Management calculate returns 5 if the calculate.return value is 5
    @Test
    public void testManagementDataEqualFive() {
        CarParkKind kind = CarParkKind.MANAGEMENT;
        BigDecimal normalRate = BigDecimal.ONE;
        BigDecimal reducedRate = BigDecimal.ONE;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(0,12));

        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(12,23));

        Period periodStay = new Period(0, 5);

        Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);

        assertEquals(rate.calculate(periodStay), new BigDecimal(5).setScale(2, RoundingMode.DOWN));
    }

    // Test case to ensure that Management calculate is correct when value is grater than minimum payment of 5
    @Test
    public void testManagementDataBiggerThanFive() {
        CarParkKind kind = CarParkKind.MANAGEMENT;
        BigDecimal normalRate = BigDecimal.ONE;
        BigDecimal reducedRate = BigDecimal.ONE;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(0,12));

        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(12,23));

        Period periodStay = new Period(0,6);

        Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);

        assertEquals(rate.calculate(periodStay), new BigDecimal(6).setScale(2, RoundingMode.DOWN));
    }

    // Test case to ensure that Student calculate will return correct value when above 5.5 as by following the new changes in the specification
    @Test
    public void testStudentDataBiggerThanFivePointFive() {
        CarParkKind kind = CarParkKind.STUDENT;
        BigDecimal normalRate = BigDecimal.ONE;
        BigDecimal reducedRate = new BigDecimal(0.5);
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(0,12));

        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(12,23));

        Period periodStay = new Period(4,13);

        Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);

        assertEquals(rate.calculate(periodStay), new BigDecimal(7.5).setScale(2, RoundingMode.DOWN));
    }

    // Test case to ensure that Student calculate will return 5.5 when the value of rate.calculate(periodStay) is equal to 5.5
    @Test
    public void testStudentDataEqualFivePointFive() {
        CarParkKind kind = CarParkKind.STUDENT;
        BigDecimal normalRate = BigDecimal.ONE;
        BigDecimal reducedRate = new BigDecimal(0.5);
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(0,12));

        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(12,23));

        Period periodStay = new Period(7,13);

        Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);

        assertEquals(rate.calculate(periodStay), new BigDecimal(5.5).setScale(2, RoundingMode.DOWN));
    }

    // Test case to ensure that Student calculate will return correct value when the value of rate.calculate(periodStay) is less than 5.5
    @Test
    public void testStudentDataLessFivePointFive() {
        CarParkKind kind = CarParkKind.STUDENT;
        BigDecimal normalRate = BigDecimal.ONE;
        BigDecimal reducedRate = new BigDecimal(0.5);
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(0,12));

        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(12,23));

        Period periodStay = new Period(8,13);

        Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);

        assertEquals(rate.calculate(periodStay), new BigDecimal(4.5).setScale(2, RoundingMode.DOWN));
    }

    // Test case to ensure that Staff calculate will return correct value when above 10 as by following the new changes in the specification
    @Test
    public void testStaffDataBiggerThanTen() {
        CarParkKind kind = CarParkKind.STAFF;
        BigDecimal normalRate = BigDecimal.ONE;
        BigDecimal reducedRate = new BigDecimal(0.5);
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(0,12));

        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(12,23));

        Period periodStay = new Period(2,15);

        Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);

        assertEquals(rate.calculate(periodStay), new BigDecimal(10).setScale(2, RoundingMode.DOWN));
    }

    // Test case to ensure that Staff calculate will return correct value when below 10 as by following the new changes in the specification
    @Test
    public void testStaffDataSmallerThanTen() {
        CarParkKind kind = CarParkKind.STAFF;
        BigDecimal normalRate = BigDecimal.ONE;
        BigDecimal reducedRate = new BigDecimal(0.5);
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(0,12));

        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(12,23));

        Period periodStay = new Period(2,8);

        Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);

        assertEquals(rate.calculate(periodStay), new BigDecimal(6).setScale(2, RoundingMode.DOWN));
    }

    // Test case to ensure that Staff calculate will return correct value when equal to 10 as by following the new changes in the specification
    @Test
    public void testStaffDataEqualToTen() {
        CarParkKind kind = CarParkKind.STAFF;
        BigDecimal normalRate = BigDecimal.ONE;
        BigDecimal reducedRate = new BigDecimal(0.5);
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(0,12));

        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(12,23));

        Period periodStay = new Period(2,12);

        Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);

        assertEquals(rate.calculate(periodStay), new BigDecimal(10).setScale(2, RoundingMode.DOWN));
    }

    // Test case to ensure that the interface is implemented corresponding to the Specification
    @Test
    public void testNewStrategyPatternInterface() {
        CarParkKind kind = CarParkKind.STAFF;
        BigDecimal normalRate = BigDecimal.ONE;
        BigDecimal reducedRate = new BigDecimal(0.5);
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(0,12));

        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(12,23));

        Period periodStay = new Period(3,7);

        Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);

        assertEquals(rate.calculate(periodStay), new BigDecimal(4).setScale(2, RoundingMode.DOWN));
    }

    // Test case to ensure that the new Student Behavioural Implementation returns the correct value as stated in the Specification
    @Test
    public void testStudentStrategyPatternImplementationWithLargeValue() {
        CarParkKind kind = CarParkKind.STUDENT;
        BigDecimal normalRate = BigDecimal.ONE;
        BigDecimal reducedRate = new BigDecimal(0.5);
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(0,12));

        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(12,23));

        Period periodStay = new Period(0,23);

        Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);

        assertEquals(rate.calculate(periodStay), new BigDecimal(13.54).setScale(2, RoundingMode.DOWN));
    }

    // Test case to ensure that the new Management Behavioural Implementation using Strategy Pattern returns the correct value as stated in the Specification
    @Test
    public void testManagementStrategyPatternImplementationWithZeroValue() {
        CarParkKind kind = CarParkKind.MANAGEMENT;
        BigDecimal normalRate = BigDecimal.ONE;
        BigDecimal reducedRate = new BigDecimal(0);
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(0,12));

        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(12,23));

        Period periodStay = new Period(14,16);

        Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);

        assertEquals(rate.calculate(periodStay), new BigDecimal(5).setScale(2, RoundingMode.DOWN));
    }

    // Test case to ensure that the new Visitor Behavioural Implementation using Strategy Pattern returns the correct value as stated in the Specification
    @Test
    public void testVisitorStrategyPatternImplementationWithLargeValue() {
        CarParkKind kind = CarParkKind.VISITOR;
        BigDecimal normalRate = BigDecimal.ONE;
        BigDecimal reducedRate = BigDecimal.ONE;
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(0,12));

        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(12,23));

        Period periodStay = new Period(0,23);

        Rate rate = new Rate(kind, normalRate, reducedRate, normalPeriods, reducedPeriods);

        assertEquals(rate.calculate(periodStay), new BigDecimal(6.5).setScale(2, RoundingMode.DOWN));
    }

}