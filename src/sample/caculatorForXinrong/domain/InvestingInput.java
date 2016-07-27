package sample.caculatorForXinrong.domain;

import java.math.BigDecimal;

public class InvestingInput {

    private BigDecimal money;

    private int month;

    private int day;

    private int vip;

    private double yearRate;

    private boolean isDoubleScore;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public double getYearRate() {
        return yearRate;
    }

    public void setYearRate(double yearRate) {
        this.yearRate = yearRate;
    }

    public boolean isDoubleScore() {
        return isDoubleScore;
    }

    public void setDoubleScore(boolean doubleScore) {
        isDoubleScore = doubleScore;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InvestingInput{");
        sb.append("money=").append(money);
        sb.append(", month=").append(month);
        sb.append(", day=").append(day);
        sb.append(", vip=").append(vip);
        sb.append(", yearRate=").append(yearRate);
        sb.append(", isDoubleScore=").append(isDoubleScore);
        sb.append('}');
        return sb.toString();
    }
}
