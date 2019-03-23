package com.cssl.util;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092700605485";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC4p1oeVjlCoHbHB7O0DBmMxczYMu73NTxnAo+iQBzrPpqrl2iTIqpVGFthqAX/bwiBjHymLFOjHGo1y1+LBf054O8QTQsS2crj8DGCesYz9O5z5loYipfYha4r0LOlvbr5Sgkx7bJCeWlL0YFhejaq3zpCTomCuUssJiBUNoV+mq5EYp3PBXtbLy2C4IIhR8Ikf94yHF1qBLySirX97Fv8Rh+bqhvVoezPdWJyfegrv84vYR799LrbJLwTeJYCBYSAeHJtORF5HhvuOavt+8KQ5H9q+ccSZ2iV//qxtbs9oRTY9GUY2zzZ2m7unOvgdqD2DhsxhPtpuYAKztuQvY2JAgMBAAECggEBAKCRBZAzsf75wc84J0985gq/RW0KfTmBlVCNrZCM55i4C48CTfEDx9HPKEc28GA5kM/19b4Z9XB0mPbGLgKZ5qSZr8gg9mwh7odeqe6oDAnKBj/KIrYMbhXdyKJZDJ4m6Zs0geJkqwmawbEoMLUurLLRAbv+xxxhO7jcPmh8MIDxTeGWlFxl07JCMFh5aW0E7vAL6bFRLXNprG5FpwhtHyKmlDSIX/lq8dATmDe0iAHm5e2mReFA4y4YURcftd9tGhVm6BTJIAY8BuFdvtMqdllDdLKNG8wB1u9J78Irjh5TPlUT+iir8t4STUamiIHpMdGaPz2JxmB9g7I9JKhDpjECgYEA5Bi0kZoECiuem2vtJnk59DfjS7M3qIprG17emnGfj9yR/HbeNanLWMfWMoxA2zMTGJBmN2Kn9HGVhCwMoV+zn/Ao0azxN+4RlcSUZJaxMslC5gf8t1wi4rC1X/QwvGlEPdD/KnfKAYMV/w5SiJUXDmD9M9PgyewmZP553ah4j+8CgYEAzz4mTavuO7FO5r8Fmw6bz6mu8kgBDHfaUVTMFgW2uYuUe0i/zcwyGKSk2I6DDtjf/i0X9lM7piCODdd/cnkDtEnei1Vzi8cL0XsvS50KOhadD+9jRlTxHfpGYtbXKjvEMWTq5oPJblM0S4oGEx25pp9vBR93EwX+UvMsC88KQgcCgYBwiTfddk1z7x1rtQ3mI31SXpiH6r4VK2W4tU/ChzigM8Ta+JKdi05GWSH3AnelLkfHoMatoMEYu0l3UnxQyfTHfifFezvvykdmyvwjvK1kTyPwdhRbKtwTV/fEhafd7Vm73fdmrn89y7z4f8p8lIIxl+yUu352YUp4HY1mrQ3+hQKBgQC5PXEZTguUeYjwAWBuV2F3/dCtKSUe0uZmeigtqw/Hy3Z9t+oRdmp00n2yP7fvhnS4ie/4Mi+H72NqBewaEwb3mGt+GXQ9RT5QEQ6WRJlGkL3052HSFMysoe9WG3iZfHCCQhUg1D0xCtgciROi9Jfj0nB2oMhbTakk1czSBU7C8wKBgCAiODKolQaJbnDQyIq/HJkkCWfZRfn9dkmufGbM3Gp2TyPYPWvQSCzuxDvYxSqSbzFIGPTh9WwldXcdZcqJN/iMGPAYhPS+k1vYKvWt6Gz68Hjl6DDvzMY2ds5PibjR6EyvWc8NaJVp/dGtgA/aETHAQ8Hhp0vPVxE6vTCXEnI3";	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA45m5se+25yfSQl3FWHq5s+fGV0OhCk7qmmRH5gT7MiC/JLQ6B2Xu45oUH0FRbS2PPLvsRBQCjOU6zZv1LCzF/I08NgVN96Q1nAwX8TJDqpts9EIjifB2mRJ7xkIyTX5TGHuUYi4Cy4sWi+eVadAuIk1YD4OgODXc5cnXiXPCeriHXT+FPchTRL21BwxzudFHLzd+96FPlqs23geDqtb0GA0eXUf3Tx47GR5a59YCccDdnxD6TXaxIf0ybNCh46+qVEkgdN4lFiv4tW+/mx6dKdD5SbnJVwMXSVC/6cOHNx1umAY/pIvsvfgIUb/fiRGLjuIFGg2Cvh31G3/ls8WqJwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://127.0.0.1:8080/maven/notify.do";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://127.0.0.1:8080/return.do";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
 

//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

 
}

