package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Test {

    public static void testMailWithM() throws ClientProtocolException, IOException {
        // final String url = "http://sendcloud.sohu.com/webapi/mail.send.json";
        final String url = "http://sendcloud.sohu.com/webapi/mail.send_template.json";
        final String apiUser = "folway_test_LABbpR";
        final String apiKey = "2iuqUS8Z98EJj9im";

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(url);

        List params = new ArrayList();
        // 不同于登录SendCloud站点的帐号，您需要登录后台创建发信子帐号，使用子帐号和密码才可以进行邮件的发送。
        params.add(new BasicNameValuePair("api_user", apiUser));
        params.add(new BasicNameValuePair("api_key", apiKey));
        params.add(new BasicNameValuePair("from", "service@sendcloud.im"));
        params.add(new BasicNameValuePair("fromname", "测试"));
        params.add(new BasicNameValuePair("template_invoke_name", "test_template_active"));

        params.add(new BasicNameValuePair("substitution_vars",
                "{\"to\": [\"folway@qq.com\", \"347348493@qq.com\"],\"sub\":{\"%name%\": [\"Run\", \"Ma\"],\"%url%\":[\"www.baidu.com\", \"www.baidu.com\"]}}"));

        // params.add(new BasicNameValuePair("to", "folway@qq.com"));
        params.add(new BasicNameValuePair("subject", "来自SendCloud的第一封邮件！"));
        // params.add(new BasicNameValuePair("html",
        // "你太棒了！你已成功的从SendCloud发送了一封测试邮件，接下来快登录前台去完善账户信息吧！"));
        params.add(new BasicNameValuePair("resp_email_id", "true"));

        httpost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        // 请求
        HttpResponse response = httpclient.execute(httpost);
        // 处理响应
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
            // 读取xml文档
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
        } else {
            System.err.println("error");
        }
        httpost.releaseConnection();
    }

    public static void testMail() throws ClientProtocolException, IOException {

        final String url = "http://sendcloud.sohu.com/webapi/mail.send.json";
        final String apiUser = "folway_test_LABbpR";
        final String apiKey = "2iuqUS8Z98EJj9im";

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(url);

        String content = "<style type=\"text/css\">html,body{margin:0;padding:0}</style><center><table><tbody><tr><td><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600px\"><tbody><tr><td><table style=\"background:url(http://7xi9bi.com1.z0.glb.clouddn.com/35069/2015/07/20/a9179f3bf9714c08b6b8e02c2296aa42.jpg) no-repeat top center;width:100%;font-size:14px\"><tbody><tr><td align=\"center\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"background:url(http://7xi9bi.com1.z0.glb.clouddn.com/35069/2015/07/20/e8b87c314d4c4b9d9dbbe63c6c2b0632.jpg) no-repeat center center;margin-top:53px;width:500px;height:449px;box-shadow:3px 3px 3px #eee\"><tbody><tr><td align=\"center\" colspan=\"2\" valign=\"top\"><div style=\"margin-top:115px;color:#1271be;line-height:1.5\">%name%，欢迎加入我们!<br>为了保证您正常使用我们的功能，请激活账号。</div><div style=\"margin-top:60px\"><a style=\"text-decoration:none;display:inline-block;color:#fff;width:144px;height:38px;line-height:38px;background:url(http://7xi9bi.com1.z0.glb.clouddn.com/35069/2015/07/20/2f7e0a9789e642eeb3ccdcfcbf759086.jpg) no-repeat center\" href=\"%url%\">立即激活</a></div></td></tr><tr><td valign=\"bottom\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"background:url(http://7xi9bi.com1.z0.glb.clouddn.com/35069/2015/07/20/0f5667bc43d94ae49fed78ad3e412e14.png) #f5f5f5 no-repeat top right\" width=\"214px\"><tbody><tr><td align=\"center\" height=\"130px\" valign=\"middle\"><div style=\"width:148px;color:#fff;font-size:12px;text-align:left;padding-left:10px;line-height:2\">如果以上按钮无法打开，<br>请把右侧的链接复制到浏览<br>器地址栏中打开：</div></td></tr></tbody></table></td><td valign=\"bottom\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"background:#f5f5f5\" width=\"287px\"><tbody><tr><td align=\"center\" height=\"130px\" valign=\"middle\"><p style=\"padding:0 20px;word-wrap:break-word;word-break:break-word;line-height:1.75\">%url%</p></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></center>";
        content = content.replaceAll("%name%", "Run").replaceAll("%url%", "https://www.baidu.com");

        // System.out.println(content);

        List params = new ArrayList();
        // 不同于登录SendCloud站点的帐号，您需要登录后台创建发信子帐号，使用子帐号和密码才可以进行邮件的发送。
        params.add(new BasicNameValuePair("api_user", apiUser));
        params.add(new BasicNameValuePair("api_key", apiKey));
        params.add(new BasicNameValuePair("from", "service@sendcloud.im"));
        params.add(new BasicNameValuePair("fromname", "加加"));
        params.add(new BasicNameValuePair("to", "folway@qq.com"));
        params.add(new BasicNameValuePair("subject", "来自SendCloud的第一封邮件！"));
        params.add(new BasicNameValuePair("html", content));
        params.add(new BasicNameValuePair("resp_email_id", "true"));

        httpost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        // 请求
        HttpResponse response = httpclient.execute(httpost);
        // 处理响应
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
            // 读取xml文档
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
        } else {
            System.err.println("error");
        }
        httpost.releaseConnection();
    }

    public static void main(String[] args) throws ClientProtocolException, IOException {

        Test.testMail();

    }

}
