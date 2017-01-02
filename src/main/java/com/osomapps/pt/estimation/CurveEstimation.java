package com.osomapps.pt.estimation;

public class CurveEstimation {
    private float impact = 1F;
    private float zeroAdjustmentBefore = 0;
    private float zeroAdjustmentAfter = 2.5F;
    private float baseNumber = 2F;
    private float limit = 50F;

    private CurveEstimation(float impact, float zeroAdjustmentBefore,
            float zeroAdjustmentAfter, float baseNumber, float limit) {
        this.impact = impact; // B1
        this.zeroAdjustmentBefore = zeroAdjustmentBefore; // B2
        this.zeroAdjustmentAfter = zeroAdjustmentAfter; // B3
        this.baseNumber = baseNumber; // B4
        this.limit = limit; // B5
    }

    public static CurveEstimation of(float impact, float zeroAdjustmentBefore,
            float zeroAdjustmentAfter, float baseNumber, float limit) {
        return new CurveEstimation(impact, zeroAdjustmentBefore,
            zeroAdjustmentAfter, baseNumber, limit);
    }

    public float calc(float value) {
        if (value < 0) {
            return (float) (Math.abs(Math.min(Math.max((Math.pow(value / 100, baseNumber) * 100 / impact), -limit), limit *0.5F))
                    * Math.signum(value) + zeroAdjustmentAfter);
        }
        return (float) (Math.abs(Math.min(Math.max((Math.pow(value / 100, baseNumber) * 100 / impact), -limit), limit))
                    * Math.signum(value) + zeroAdjustmentAfter);
    }
}
