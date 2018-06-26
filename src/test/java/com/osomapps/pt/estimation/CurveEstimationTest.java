package com.osomapps.pt.estimation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CurveEstimationTest {

    @ParameterizedTest
    @CsvSource({
            "-100.00F,	-22.5F",
            "-90.00F,	-22.5F",
            "-80.00F,	-22.5F",
            "-70.00F,	-22.5F",
            "-60.00F,	-22.5F",
            "-50.00F,	-22.5F",
            "-40.00F,	-13.5F",
            "-30.00F,	-6.5F",
            "-20.00F,	-1.5F",
            "-10.00F,	1.5F",
            "0.00F,	2.5F",
            "10.00F,	3.5F",
            "20.00F,	6.5F",
            "30.00F,	11.5F",
            "40.00F,	18.5F",
            "50.00F,	27.5F",
            "60.00F,	38.5F",
            "70.00F,	51.5F",
            "80.00F,	52.5F",
            "90.00F,	52.5F",
            "100.00F,	52.5F"
    })
    public void calc(float fInput, float fExpected) {
        assertEquals(fExpected, CurveEstimation.of(1, 0, 2.5F, 2, 50).calc(fInput), 2);
    }
}
