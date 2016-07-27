package sample.caculatorForXinrong.domain;

public class RechargeInput extends InvestingInput{

    private int handingMonth;

    private int handingDay;

    public int getHandingMonth() {
        return handingMonth;
    }

    public void setHandingMonth(int handingMonth) {
        this.handingMonth = handingMonth;
    }

    public int getHandingDay() {
        return handingDay;
    }

    public void setHandingDay(int handingDay) {
        this.handingDay = handingDay;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RechargeInput{");
        sb.append("money=").append(super.getMoney());
        sb.append(", month=").append(super.getMonth());
        sb.append(", day=").append(super.getDay());
        sb.append(", vip=").append(super.getVip());
        sb.append(", yearRate=").append(super.getYearRate());
        sb.append(", isDoubleScore=").append(super.isDoubleScore());
        sb.append(", handingMonth=").append(handingMonth);
        sb.append(", handingDay=").append(handingDay);
        sb.append('}');
        return sb.toString();
    }
}
