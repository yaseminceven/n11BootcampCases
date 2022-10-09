package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BaseData {
    private final String[] cartNum = {"5425","2334","3010","9904"};
    private final String[] cartInfo = {"yasemin ceven","4","2023","317"};
    private static Properties properties = new Properties();
    private static String projectPath = System.getProperty("user.dir");
    private static String configFilePath = "/config.properties";

    public Map<String, String> getPaymentInformation() {
        Map<String, String> paymentInformation=new HashMap<>();
        paymentInformation.put("email","yasemintest@gmail.com");
        paymentInformation.put("country","US(+1)");
        paymentInformation.put("number","5612237260");
        paymentInformation.put("name","yasemin ceven");
        paymentInformation.put("city","İstanbul");
        paymentInformation.put("district","Sarıyer");
        paymentInformation.put("neighbourhood","Ayazağa");
        paymentInformation.put("address","Büyükdere Caddesi Ayazağa Mahallesi");
        paymentInformation.put("tc","53733830338");
        paymentInformation.put("addressName","ev");
        return paymentInformation;
    }

    public String[] getCartNum(){
        return cartNum;
    }
    public String getCartInfo(int i){
        return cartInfo[i];
    }

    public String getApplicationUrl(String env){
        String url = "";
        try {
            InputStream input = new FileInputStream(projectPath + configFilePath);
            properties.load(input);
            if(env.equalsIgnoreCase("prod")){
                url = properties.getProperty("url");
                return url;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }
}
