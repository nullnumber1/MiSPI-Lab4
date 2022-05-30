package nullnumber1.service.impl;

import nullnumber1.service.MissPercentageMBean;

public class MissPercentage implements MissPercentageMBean {
    private static final MissPercentage missPercentage = new MissPercentage();

    private double missRate;

    public static MissPercentage getInstance() {
        return missPercentage;
    }

    @Override
    public double getPercentage(int count, int hit) {
        missRate = hit / count * 100;
        return missRate;
    }

    public void setMissRate(double missRate) {
        this.missRate = missRate;
    }
}
