package com.hwua.util;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created  on 2019-9-09
 */
public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2021000116694811"; //测试

    // 商户私钥，您的PKCS8格式RSA2私钥
   public static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCgYIZohTagM6LpiQpREF7RqHehzNGc4aUVkuEbU9irCIf6riQvHMVrFn5dAuraNP8P/h+Gj+qnMGqptpzD8EjLY6RzrxQ2MUWRSGQjeNvf0f6SiBN2RRExQIZ/kXJo9Taue/19DZzfzgbfWzcdGh7cMv1ONdUn4US52uA41JNhsHhw4nybAdLoCqXTnCV4DAtMEDYoxlUNfPh4XsJ2idBfNiTMl1GHbpfDHU1vcy83xAt9Ie2yU3Q10uT33ly3y8muZZGyGy84dBh0Sl1Oap1QQ9UbcUnU7Pv0qHzYWbpWJ396wFkFr25g5felNNK9+0EC/2TUm+22u7XoUgoagpETAgMBAAECggEAX3oXipID+0vCaRXUtn3wdRC+Z+zvvV34UeenbAV2sBvcGDkkAYg3X5F8mRLrjgyO7I51Zj63i+EfHvpAScX7EFq5xMUuWzTqHZvjQdrpy03RKJsqOvbTTWcEq12Dp0iUzTuzpYc9JWAaZ05+eSzbwmv4sV8LDzrlF2BerHahZz4dbR1n0Xbh1sjJEjb0h4skYAZEBXZ5oliUDIha+bWLpHA6PldYOn1YjZYASaj3RLL0pfGiS+BDBX1E6boQDb20F+R2hW/b9yEOknBiY3fsSfB/0TS59iUaOnQsuMQVGsD69SZEK5jV3IBIFiIH/zEC9jTnPdOT+y08GeseKGUzwQKBgQDgjX0DUFt7LbLNlAo/dRKrLvCKkTD5AUtRDBVOua83S4UBUmn0Rq6JdZqfXqJ+/E/0cb2dKUUimZiq5LyRvl+2xV1hZqgDD/LCoblIL6p2ArIdEFBaziCTxrlvVQn1gsve+1CX4/jJOrBOu0JB6jNpdwlFqH1COIa1PTJ07WIhxQKBgQC21kFGYel1FfnIAkKKNtZxakTBTI6YF59fDnTCuTqEVHDYbJJQfEqPdu+CIfi7sM80v6h/yzsMcQZ0Zi4Qa+udm6f7h0zzG1l0+rq6mZCmh5fZT89XabK8e7yCU3ycQerJI2lKEbkntmpUum3T2cINxn12Bwreau9Mhh9Ea+/M9wKBgQCIiyCoYjWuekROo6VzntHiS+GysMTxtSrCNrHCNEDqQ7615hJNmqlJJr0GCbqPswc/YeZl6ZGDj5h42iwcB4IWFn9I2e5yJkF8vnB3B50Es3DxIVlrTgobMezSWQh8RLOJfJIuOk6utkQ9K7sHb80hZWO6+tBn8w1eqb7DwRE57QKBgAqU8egx6IIXSIKT5uf67oRX4yzQ0nvo+hdVTdqrP8DC5lkQR5OS7fnJp2j/sFakkAl8gjMLPxqkO/xmoYnfxR41EQ7UsNZtIctMa5w7IQ0SUe6I2PY1LtwLyUg9rruYcQ89Fm3sAqxSuWq3831MYBfOAhrfo3hl9f2W0Z5zJMjXAoGAb4ed5Pl7yt7Y3csM7JjFstXsu3MgmdlRaf6uT9FAntmm3civokYccvDAJMTGIp9rTKVtP2Telf2eaSlQFgtRChfpwEYe5/ntI+4vMTIG1FE9IQRMQe3tv94G/Wj540Dkf3v/HuNFRilEEwOPTYtluBVyyFemNnK9+VDFW18Fr+I=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoglUGVR2XrORQ/rm6EIJuvDb7G1qI8OiySYUQJ+N+QWavH0vXD9NQ2tNj1hILOtKodjgBol/xv9aiyTmRRXhg/M7e116RI9LkMmKUHCv7tXV9DagJKhGw7VBMYhG9FyiQBiMVR9v/12i7C7Bt0w0LYzF9EDWbzS68GjnmRWulJXGBb/Sa/m/MP1MwRaAKpCKKV1HYwfHDW2iekC1aXHlM1+UcZLkFVXa5A4CfMAUh9pNqXnupwSSFzDQ9mbhizqrKjUuAstbVGK0HsUVYe3ZjfaCIrUN36hj9fuKZw9DJ8ArqqSBnqjlNy62sGXQWn1YmEWHs39qQ769tgC6uOypDQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/SchoolGO/notifyUrl.do";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://localhost:8080/SchoolGO/returnUrl.do";
    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "UTF-8";

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do"; //测试

    // 支付宝网关
    public static String log_path = "E:\\";

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}