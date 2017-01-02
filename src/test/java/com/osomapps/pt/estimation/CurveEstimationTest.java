package com.osomapps.pt.estimation;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CurveEstimationTest {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {     
            { -100.00F,	-22.5F },
            { -90.00F,	-22.5F },
            { -80.00F,	-22.5F },
            { -70.00F,	-22.5F },
            { -60.00F,	-22.5F },
            { -50.00F,	-22.5F },
            { -40.00F,	-13.5F },
            { -30.00F,	-6.5F },
            { -20.00F,	-1.5F },
            { -10.00F,	1.5F },
            { 0.00F,	2.5F },
            { 10.00F,	3.5F },
            { 20.00F,	6.5F },
            { 30.00F,	11.5F },
            { 40.00F,	18.5F },
            { 50.00F,	27.5F },
            { 60.00F,	38.5F },
            { 70.00F,	51.5F },
            { 80.00F,	52.5F },
            { 90.00F,	52.5F },
            { 100.00F,	52.5F }
           });
    }
    
    private float fInput;

    private float fExpected;

    public CurveEstimationTest(float input, float expected) {
        fInput = input;
        fExpected = expected;
    }

    @Test
    public void calc() {
        assertEquals(fExpected, CurveEstimation.of(1, 0, 2.5F, 2, 50).calc(fInput), 2);
    }

}
