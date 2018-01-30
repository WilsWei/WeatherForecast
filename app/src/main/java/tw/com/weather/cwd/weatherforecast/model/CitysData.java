package tw.com.weather.cwd.weatherforecast.model;

/**
 * Created by Yu-D-Siang on 2018/1/29.
 */

public class CitysData {

    private static String[] sCitys = new String[] { "臺北市","基隆市","新北市","連江縣","宜蘭縣","新竹市","新竹縣","桃園市","苗栗縣","臺中市",
            "彰化縣","南投縣","嘉義市","嘉義縣","雲林縣","臺南市","高雄市","澎湖縣","金門縣","屏東縣","臺東縣","花蓮縣" };

    public static String[] getCitys() {
        return sCitys;
    }

    public static String getCityName(int i) {
        return sCitys[i];
    }
}
