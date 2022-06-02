package nullnumber1.monitoring.impl;

import lombok.extern.slf4j.Slf4j;
import nullnumber1.monitoring.MissPercentageMXBean;

@Slf4j
public class MissPercentage implements MissPercentageMXBean {

    private double missRate;

    private static final MissPercentage missPercentage = new MissPercentage();

    public static MissPercentage getInstance() {
        return missPercentage;
    }

    @Override
    public void calculatePercentage(int count, int hit) {
        missRate = (double) (count - hit) / count * 100;
    }

    public void setMissRate(double missRate) {
        this.missRate = missRate;
    }

    @Override
    public double getMissPercentage() {
        return missRate;
    }
}
