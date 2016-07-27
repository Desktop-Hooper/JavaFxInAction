package sample.caculatorForXinrong.domain;

/**
 * Created by 47123 on 2016/7/27.
 */
public class FilePathConfig {

    private static final String YEAR_RATIO = "../resource/yearRatio.properties";

    private static final String GLOBAL = "../resource/global.properties";

    private static final String SERVER_RATIO = "../resource/investRatio.properties";

    private static final String JIPINHUI_RATIO = "../resource/jipinhui.properties";

    private static final String VIP_RATIO = "../resource/vipScore.properties";

    public static String getYearRatio() {
        return YEAR_RATIO;
    }

    public static String getGLOBAL() {
        return GLOBAL;
    }

    public static String getServerRatio() {
        return SERVER_RATIO;
    }

    public static String getJipinhuiRatio() {
        return JIPINHUI_RATIO;
    }

    public static String getVipRatio() {
        return VIP_RATIO;
    }
}
