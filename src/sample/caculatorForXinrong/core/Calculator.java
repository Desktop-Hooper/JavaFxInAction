package sample.caculatorForXinrong.core;


import sample.caculatorForXinrong.domain.InvestingResult;
import sample.caculatorForXinrong.domain.RechargeResult;
import sample.caculatorForXinrong.util.PropertiesUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Calculator {


	private final static String VIPSCORE_NAME = "vipScore";
	private final static String VIPSCORE_PATH = "../resource/vipScore.properties";
	private final static String JIPINHUI_NAME = "jipinhui";
	private final static String JIPINHUI_PATH = "../resource/jipinhui.properties";
	private final static String SERVER_NAME = "investRatio";
	private final static String SERVER_PATH = "../resource/investRatio.properties";
	private final static String GLOBAL_VAR_PATH = "../resource/global.properties";
	private final static String ACCURACY_NAME = "baseAccuracy";

	// 投资服务费率
	static BigDecimal[] SERVER_CHARGER = null;

	// VIP等级系数
	static BigDecimal[] VIP_RATIO = null;

	// 积品汇折扣率
	static BigDecimal[] JI_PIN_HUI_OFF = null;

	// 结果精度
	static int BASE_ACCURACY = 3;

	// 算术精度
	static int RATIO_ACCURACY = BASE_ACCURACY + 1;

	// 一个月天数
	static int DAY_OF_MONTH = 30;


	static{
		loadJipinhuiProperties();
		loadServerChargeProperties();
		loadVipRatioProperties();
		loadAccuracy();
	}

	public static void loadVipRatioProperties(){
		Map<String, String> vipscore = PropertiesUtil.getPropertiesValueToMap(VIPSCORE_PATH);
		VIP_RATIO = new BigDecimal[vipscore.size()];
		for(int i=0;i<vipscore.size();i++){
			VIP_RATIO[i] = new BigDecimal(vipscore.get(VIPSCORE_NAME+i));
		}
	}

	public static void loadServerChargeProperties(){
		Map<String, String> serverCharge = PropertiesUtil.getPropertiesValueToMap(SERVER_PATH);
		SERVER_CHARGER = new BigDecimal[serverCharge.size()];
		for(int i=0;i<serverCharge.size();i++){
			SERVER_CHARGER[i] = new BigDecimal(serverCharge.get(SERVER_NAME+i));
			System.out.println("服务费 "+SERVER_CHARGER[i]);
		}
	}

	public static void loadJipinhuiProperties(){
		Map<String, String> jipinhuiOff = PropertiesUtil.getPropertiesValueToMap(JIPINHUI_PATH);
		JI_PIN_HUI_OFF = new BigDecimal[jipinhuiOff.size()];
		for(int i=0;i<jipinhuiOff.size();i++){
			JI_PIN_HUI_OFF[i] = new BigDecimal(jipinhuiOff.get(JIPINHUI_NAME+i));
			System.out.println("极品会 "+JI_PIN_HUI_OFF[i]);
		}
	}

	public static void loadAccuracy(){
		Map<String, String> globalMap = PropertiesUtil.getPropertiesValueToMap(GLOBAL_VAR_PATH);
		for(String key:globalMap.keySet()){
			if (key.equals(ACCURACY_NAME)) {
				BASE_ACCURACY = Integer.valueOf(globalMap.get(key));
				RATIO_ACCURACY = BASE_ACCURACY + 1;
			}
		}
	}

	public static void setAccuracy(int accuracy){
		Map<String, String> map = new HashMap<String, String>();
		map.put(ACCURACY_NAME, String.valueOf(accuracy));
		try {
			PropertiesUtil.modifyPropertiesValue(GLOBAL_VAR_PATH, map);
			loadAccuracy();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int getAccuracy(){
		return BASE_ACCURACY;
	}

	/**
	 * 投资金额 * 年化收益率 * 项目月数 / 12 + 投资金额 * 年化收益率 / 365天 * 项目天数
	 *
	 * @param investMoney
	 * @param yearIncome
	 * @param month
	 * @param day
	 * @return
	 */
	public static BigDecimal getIncome(BigDecimal investMoney,
									   double yearIncome, int month, int day) {
		BigDecimal monthMoney = investMoney
				.multiply(new BigDecimal(yearIncome));
		monthMoney = monthMoney.multiply(new BigDecimal(month));
		monthMoney = monthMoney.divide(new BigDecimal(12), RATIO_ACCURACY,
				BigDecimal.ROUND_HALF_UP);

		BigDecimal dayMoney = investMoney.multiply(new BigDecimal(yearIncome));
		dayMoney = dayMoney.divide(new BigDecimal(365), RATIO_ACCURACY,
				BigDecimal.ROUND_HALF_UP);
		dayMoney = dayMoney.multiply(new BigDecimal(day));

		BigDecimal income = monthMoney.add(dayMoney);
		income = income.setScale(BASE_ACCURACY, BigDecimal.ROUND_HALF_UP);

		return income;
	}

	/**
	 * 投资收益 * 投资服务费率
	 *
	 * @param income
	 * @param vip
	 * @return
	 */
	public static BigDecimal getInvestServerCharge(BigDecimal income, int vip) {
		income = income.multiply(SERVER_CHARGER[vip]);
		income = income.setScale(BASE_ACCURACY, BigDecimal.ROUND_HALF_UP);
		return income;
	}

	/**
	 * 投资收益 - 投资服务费
	 *
	 * @param income
	 * @param serverCharge
	 * @return
	 */
	public static BigDecimal getActualIncome(BigDecimal income,
											 BigDecimal serverCharge) {
		income = income.setScale(BASE_ACCURACY, BigDecimal.ROUND_HALF_UP);
		serverCharge = serverCharge.setScale(BASE_ACCURACY,
				BigDecimal.ROUND_HALF_UP);
		return income.subtract(serverCharge);
	}

	/**
	 * 投资金额 * 0.25 * 项目月数 * VIP系数
	 *
	 * @param investMoney
	 * @param month
	 * @param vip
	 * @return
	 */
	public static BigDecimal getScore(BigDecimal investMoney, int month, int vip,boolean isDoubleScore) {
		investMoney = investMoney.multiply(new BigDecimal(0.25));
		investMoney = investMoney.multiply(new BigDecimal(month));
		investMoney = investMoney.multiply(VIP_RATIO[vip]);
		investMoney = investMoney.setScale(BASE_ACCURACY,
				BigDecimal.ROUND_HALF_UP);
		if(isDoubleScore){
			investMoney = investMoney.multiply(new BigDecimal(2));
		}
		return investMoney;
	}

	/**
	 * 投资金额 * vip等级系数 / (120 * (30天 * 项目月数  + 项目天数) )
	 *
	 * @param income
	 * @param month
	 * @param vip
	 * @return
	 */
	public static BigDecimal getGrowScore(BigDecimal investMoney, int month,int day,
										  int vip) {
		investMoney = investMoney.multiply(VIP_RATIO[vip]);
		BigDecimal dayElement = new BigDecimal(30).multiply(new BigDecimal(month));
		dayElement = dayElement.add(new BigDecimal(day));
		investMoney = investMoney.divide(new BigDecimal(120), RATIO_ACCURACY,
				BigDecimal.ROUND_HALF_UP);
		investMoney = investMoney.multiply(dayElement);
		investMoney = investMoney.setScale(BASE_ACCURACY,
				BigDecimal.ROUND_HALF_UP);
		return investMoney;
	}

	/**
	 * 实际投资收益 * 12/ (投资月数 + 投资天数/30) /投资本金
	 *
	 * @param actualIncome
	 * @param investMoney
	 * @param month
	 * @param day
	 * @return
	 */
	public static BigDecimal getActualYearRatio(BigDecimal actualIncome,
												BigDecimal investMoney, int month, int day) {
		BigDecimal timeElement = new BigDecimal(month);
		BigDecimal dayElement = new BigDecimal(day).divide(new BigDecimal(
				DAY_OF_MONTH), 5, BigDecimal.ROUND_HALF_UP);
		timeElement = timeElement.add(dayElement);
		BigDecimal radio = actualIncome.multiply(new BigDecimal(12))
				.divide(timeElement, RATIO_ACCURACY, BigDecimal.ROUND_HALF_UP)
				.divide(investMoney, RATIO_ACCURACY, BigDecimal.ROUND_HALF_UP);
		return radio.setScale(RATIO_ACCURACY, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 【（ 积分/ (40000 * 积品汇折扣 )* 100 ） + 实际投资收益 】*12 / ( 月 + 天 /30 ) /投资金额
	 *
	 * @param score
	 * @param actualIncome
	 * @param investMoney
	 * @param month
	 * @param day
	 * @param vip
	 * @return
	 */
	public static BigDecimal getActualYearRatioWithReward(BigDecimal score,
														  BigDecimal actualIncome, BigDecimal investMoney, int month,
														  int day, int vip) {
		BigDecimal jiPinHuiElement = new BigDecimal(40000);
		jiPinHuiElement = score
				.divide(jiPinHuiElement)
				.divide(JI_PIN_HUI_OFF[vip], RATIO_ACCURACY,
						BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
		BigDecimal newIncome = actualIncome.add(jiPinHuiElement);

		BigDecimal timeElement = new BigDecimal(month);
		BigDecimal dayElement = new BigDecimal(day).divide(new BigDecimal(
						DAY_OF_MONTH),RATIO_ACCURACY,
				BigDecimal.ROUND_HALF_UP);
		timeElement = timeElement.add(dayElement);
		BigDecimal radio = newIncome.multiply(new BigDecimal(12))
				.divide(timeElement, RATIO_ACCURACY, BigDecimal.ROUND_HALF_UP)
				.divide(investMoney, RATIO_ACCURACY, BigDecimal.ROUND_HALF_UP);
		return radio.setScale(RATIO_ACCURACY, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal getActualYearRatioWithoutServerCharge(
			BigDecimal actualIncome, BigDecimal score, BigDecimal serverCharge,
			BigDecimal investMoney, int month, int day, int vip) {
		return getActualYearRatioWithReward(score,
				actualIncome.add(serverCharge), investMoney, month, day, vip);
	}

	public static BigDecimal getRechargeServer(BigDecimal income) {
		income = income.multiply(new BigDecimal(0.1));
		return income.setScale(BASE_ACCURACY, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * （项目月 + 项目天/30 - 持有月 - 持有天 / 30） * 投资金额 * vip系数 *0.25 /1000*3
	 *
	 * @param month
	 * @param day
	 * @param handingMonth
	 * @param hangdingDay
	 * @param vip
	 * @param investMoney
	 * @return
	 */
	public static BigDecimal getScoreRefund(int month, int day,
											int handingMonth, int hangdingDay, int vip, BigDecimal investMoney) {
		BigDecimal projectDayElemnt = new BigDecimal(day).divide(
				new BigDecimal(DAY_OF_MONTH), RATIO_ACCURACY,
				BigDecimal.ROUND_HALF_UP);
		BigDecimal projectTimeElemnt = new BigDecimal(month)
				.add(projectDayElemnt);

		BigDecimal handingDayElement = new BigDecimal(hangdingDay).divide(
				new BigDecimal(DAY_OF_MONTH), RATIO_ACCURACY,
				BigDecimal.ROUND_HALF_UP);
		BigDecimal handingTimeElemnt = new BigDecimal(handingMonth)
				.add(handingDayElement);

		BigDecimal time = projectTimeElemnt.subtract(handingTimeElemnt);

		BigDecimal score = time.multiply(investMoney).multiply(VIP_RATIO[vip])
				.multiply(new BigDecimal(0.25));

		return score.divide(new BigDecimal(1000), BASE_ACCURACY,
				BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(3));
	}

	/**
	 * 转让投资收益 - 转让服务费 - 投资服务费 - 积分补偿金
	 *
	 * @param income
	 * @param rechargeCost
	 * @param scoreRefund
	 * @param investServerCost
	 * @return
	 */
	public static BigDecimal getRechargeFinalIncome(BigDecimal income,
													BigDecimal rechargeCost, BigDecimal scoreRefund,
													BigDecimal investServerCost) {
		return income.subtract(rechargeCost).subtract(scoreRefund)
				.subtract(investServerCost);
	}

	public static InvestingResult caculateInvest(BigDecimal investMoney,
												 int month, int day, int vip, double yearIncome, boolean isDoubleScore) {

		InvestingResult result = new InvestingResult();
		BigDecimal income = Calculator.getIncome(investMoney, yearIncome, month, day);
		BigDecimal serverCharge = Calculator.getInvestServerCharge(income, vip);
		BigDecimal actualIncome = Calculator.getActualIncome(income, serverCharge);
		BigDecimal score = Calculator.getScore(investMoney, month, vip,isDoubleScore);
		BigDecimal growScore = Calculator.getGrowScore(investMoney, month,day, vip);
		BigDecimal actualYearIncome = Calculator.getActualYearRatio(actualIncome, investMoney, month, day);
		BigDecimal actualYearIncomeWithReward = Calculator.getActualYearRatioWithReward(score, actualIncome, investMoney, month, day, vip);

		result.setActualIncome(actualIncome.toString());
		result.setActualYearIncome(actualYearIncome.multiply(new BigDecimal(100)).toString() + "%");
		result.setActualYearIncomeWithReward(actualYearIncomeWithReward.multiply(new BigDecimal(100)).toString() + "%");
		result.setGrowScore(growScore.toString());
		result.setIncome(income.toString());
		result.setScore(score.toString());
		result.setServerCharge(serverCharge.toString());

		result.setActualIncomeInfo("投资收益["+income.toString()+"] - 投资服务费["+serverCharge.toString()+"] = "+actualIncome.toString());

		result.setActualYearIncomeInfo("<html>实际投资收益["+actualIncome.toString()+"] × 12 "
				+ "÷ (投资月数["+month+"] + 投资天数["+day+"] ÷ 30) <p>÷ 投资本金["+investMoney.toString()+"] = "+actualYearIncome.toString()+"<p></html>");

		result.setActualYearIncomeWithRewardInfo("<html>( ( 积分["+score.toString()+"] ÷ (40000 × 积品汇折扣["+JI_PIN_HUI_OFF[vip].toString()+"] )× 100 ) "
				+ "<p>+ 实际投资收益["+actualIncome.toString()+"] ) × 12 "
				+ "÷ ( 项目月数["+month+"] + 项目天数["+day+"] ÷ 30 )</p><p> "
				+ "÷ 投资本金["+investMoney.toString()+"] = "+actualYearIncomeWithReward.toString()+"</p></html>");

		result.setGrowScoreInfo("<html>投资本金["+investMoney.toString()+"] × vip等级系数["+VIP_RATIO[vip].toString()+"]  "
				+ "÷ 120 <p>× (30天 × 项目月数["+month+"] + 项目天数["+day+"])  = "+growScore.toString()+"</p></html>");

		result.setIncomeInfo("<html>投资本金["+investMoney.toString()+"] × 年化收益率["+yearIncome+"] × "
				+ "项目月数["+month+"] ÷ 12 <p>+ 投资本金["+investMoney.toString()+"]  "
				+ "× 年化收益率["+yearIncome+"]</p><p> ÷ 365天 × 项目天数["+day+"] = "+income.toString()+"</p></html>");

		result.setScoreInfo("投资本金["+investMoney.toString()+"] × 0.25 × 项目月数["+month+"]  × VIP系数["+VIP_RATIO[vip].toString()+"] = "+score.toString());

		result.setServerChargerInfo("投资收益["+income.toString()+"]  × 投资服务费率["+SERVER_CHARGER[vip].toString()+"] = "+serverCharge.toString());

		return result;
	}

	public static RechargeResult caculateRecharge(BigDecimal investMoney,
												  int month, int day, int handingMonth, int handingDay, int vip, double yearIncome, boolean isDoubleScore){
		RechargeResult result = new RechargeResult();

		BigDecimal income = Calculator.getIncome(investMoney, yearIncome, handingMonth, handingDay);
		BigDecimal investServerCost = Calculator.getInvestServerCharge(income, vip);
		BigDecimal investIncome = Calculator.getActualIncome(income, investServerCost);
		BigDecimal score = Calculator.getScore(investMoney, month, vip,isDoubleScore);
		BigDecimal growScore = Calculator.getGrowScore(investMoney, handingMonth, handingDay,vip);

		BigDecimal rechargeServer = null;
		if( month == handingMonth && handingDay == day){
			rechargeServer = new BigDecimal(0);
		}else{
			rechargeServer  = Calculator.getRechargeServer(income);
		}

		BigDecimal scoreRefund = Calculator.getScoreRefund(month, day, handingMonth, handingDay, vip, investMoney);
		BigDecimal rechargeFinalIncome = Calculator.getRechargeFinalIncome(income, rechargeServer, scoreRefund, investServerCost);
		BigDecimal actualYearRatio = Calculator.getActualYearRatio(rechargeFinalIncome, investMoney, handingMonth, handingDay);
		BigDecimal actualYearRatioWithReward = Calculator.getActualYearRatioWithReward(score, rechargeFinalIncome, investMoney, handingMonth, handingDay, vip);
		BigDecimal actualYearRatioWithoutServer = Calculator.getActualYearRatioWithoutServerCharge(rechargeFinalIncome, score,
				investServerCost, investMoney, handingMonth, handingDay, vip);



		result.setIncome(income.toString());
		result.setScore(score.toString());
		result.setGrowScore(growScore.toString());
		result.setInvestServerCharge(investServerCost.toString());
		result.setInvestIncome(investIncome.toString());
		result.setActualIncomeRatio(actualYearRatio.multiply(new BigDecimal(100)).toString() + "%");
		result.setScoreRefund(scoreRefund.toString());
		result.setRechargeServerCharge(rechargeServer.toString());
		result.setActualYearIncomeWithRewar(actualYearRatioWithReward.multiply(new BigDecimal(100)).toString() + "%");
		result.setActualYearIncomeWithoutServer(actualYearRatioWithoutServer.multiply(new BigDecimal(100)).toString() + "%");
		result.setIncomeAfterRecharge(rechargeFinalIncome.toString());

		result.setIncomeInfo("<html>投资本金["+investMoney.toString()+"] × 年化收益率["+yearIncome+"] × "
				+ "持有月数["+handingMonth+"] ÷ 12 <p>+ 投资本金["+investMoney.toString()+"]  "
				+ "× 年化收益率["+yearIncome+"]</p><p> ÷ 365天 × 持有天数["+handingDay+"] = "+income.toString()+"</p></html>");

		result.setScoreInfo("投资本金["+investMoney.toString()+"] × 0.25 × 项目月数["+month+"]  × VIP系数["+VIP_RATIO[vip].toString()+"] = "+score.toString());

		result.setGrowScoreInfo("<html>投资本金["+investMoney.toString()+"] × vip等级系数["+VIP_RATIO[vip].toString()+"]  "
				+ "÷ 120 <p>× (30天 × 项目月数["+month+"] + 项目天数["+day+"]) = "+growScore.toString()+"</p></html>");

		result.setInvestServerChargeInfo("投资收益["+investIncome.toString()+"]  × 投资服务费率["+SERVER_CHARGER[vip].toString()+"] = "+investServerCost.toString());

		result.setInvestIncomeInfo("投资收益["+income.toString()+"] - 投资服务费["+investMoney.toString()+"] = "+investIncome.toString());

		result.setActualIncomeRatioInfo("<html>实际收益["+rechargeFinalIncome.toString()+"] × 12 "
				+ "÷ (持有月数["+handingMonth+"] + 持有天数["+handingDay+"] ÷ 30) <p>÷ 投资本金["+investMoney.toString()+"] = "+actualYearRatio.toString()+"</p></html>");

		result.setScoreRefundInfo("<html>(项目月数["+month+"] + 项目天数["+day+"] ÷30 - 持有月["+handingMonth+"] <p>- 持有天["+handingDay+"]  ÷ 30)"
				+ " × 投资金额["+investMoney+"]  </p><p>× vip系数["+VIP_RATIO[vip]+"]  × 0.25 ÷ 1000 × 3 = "+scoreRefund.toString()+"</p></html>");

		result.setRechargeServerChargeInfo("投资收益["+income.toString()+"] × 0.1 = "+rechargeServer.toString());

		result.setActualYearIncomeWithRewarInfo("<html>( ( 积分["+score.toString()+"] ÷ (40000 × 积品汇折扣["+JI_PIN_HUI_OFF[vip].toString()+"] )× 100 ) "
				+ "<p>+ 实际投资收益["+rechargeFinalIncome.toString()+"] ) × 12 "
				+ "÷ ( 项目月数["+handingMonth+"] + 项目天数["+handingDay+"] ÷ 30 )</p><p> "
				+ "÷ 投资本金["+investMoney.toString()+"] = "+actualYearRatioWithReward.toString()+"</p></html>");

		result.setActualYearIncomeWithoutServerInfo("<html>( ( 积分["+score.toString()+"] ÷ (40000 × 积品汇折扣["+JI_PIN_HUI_OFF[vip].toString()+"] )× 100 ) "
				+ "<p>+ 实际投资收益["+rechargeFinalIncome.toString()+"] + 投资服务费["+investServerCost.toString()+"] )</p><p> × 12 "
				+ "÷ ( 项目月数["+handingMonth+"] + 项目天数["+handingDay+"] ÷ 30 )</p><p> "
				+ "÷ 投资本金["+investMoney.toString()+"] = "+actualYearRatioWithoutServer.toString()+"</p></html>");

		result.setIncomeAfterRechargeInfo("<html>投资收益["+investMoney.toString()+"] - 转让服务费["+rechargeServer.toString()+"]"
				+ "<p>- 投资服务费["+investServerCost.toString()+"] - 积分补偿金["+scoreRefund.toString()+"] = "+rechargeFinalIncome.toString()+"</p></html>");

		return result;
	}

	public static void main(String[] args) {
		BigDecimal income = Calculator.getIncome(new BigDecimal(720000), 0.168,
				9, 0);
		System.out.println(income);

		BigDecimal serverCharge = Calculator.getInvestServerCharge(income, 1);
		System.out.println(serverCharge);

		BigDecimal actualIncome = Calculator.getActualIncome(income,
				serverCharge);
		System.out.println(actualIncome);

		BigDecimal score = Calculator.getScore(new BigDecimal(720000), 9, 1,false);
		System.out.println(score);

		BigDecimal growScore = Calculator.getGrowScore(new BigDecimal(720000),
				9, 0,1);
		System.out.println(growScore);

		BigDecimal actualRatio = Calculator.getActualYearRatio(actualIncome,
				new BigDecimal(720000), 9, 0);
		System.out.println(actualRatio);

		BigDecimal acutalRatioWithReward = Calculator
				.getActualYearRatioWithReward(score, actualIncome,
						new BigDecimal(720000), 9, 10, 1);
		System.out.println(acutalRatioWithReward);

		BigDecimal newIncome = Calculator.getIncome(new BigDecimal(720000),
				0.168, 1, 0);
		System.out.println(newIncome);

		BigDecimal rechargeServer = Calculator.getRechargeServer(newIncome);
		System.out.println(rechargeServer);

		BigDecimal scoreRefund = Calculator.getScoreRefund(9, 0, 1, 0, 1,
				new BigDecimal(720000));
		System.out.println(scoreRefund);

		BigDecimal newInvestServer = Calculator.getInvestServerCharge(newIncome,
				1);
		System.out.println(newInvestServer);

		BigDecimal rechargeFinalIncome = Calculator.getRechargeFinalIncome(
				newIncome, rechargeServer, scoreRefund, newInvestServer);
		System.out.println(rechargeFinalIncome);

		BigDecimal rechargeYearRatio = Calculator.getActualYearRatio(
				rechargeFinalIncome, new BigDecimal(720000), 1, 0);
		System.out.println(rechargeYearRatio);

		BigDecimal shouldHaveScore = Calculator.getScore(new BigDecimal(720000),
				1, 1,false);

		BigDecimal rechargeYearRatioWithReward = Calculator
				.getActualYearRatioWithReward(score.subtract(shouldHaveScore),
						rechargeFinalIncome, new BigDecimal(720000), 1, 0, 1);
		System.out.println(rechargeYearRatioWithReward);

		BigDecimal rechargeYearRatioWithoutServer = Calculator
				.getActualYearRatioWithoutServerCharge(rechargeFinalIncome,
						score, newInvestServer, new BigDecimal(720000), 1, 0, 1);
		System.out.println(rechargeYearRatioWithoutServer);
	}
}

