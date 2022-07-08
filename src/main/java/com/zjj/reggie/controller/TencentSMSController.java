package com.zjj.reggie.controller;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

public class TencentSMSController {
    @RequestMapping("testTenccent" )
    @ResponseBody
    public void ta() {
// 短信应用 SDK AppID
        int appid = 1400703195; // SDK AppID 以1400开头
// 短信应用 SDK AppKey
        String appkey = "8e2f08aee6733f12f5bbf6477e1336bc";
// 需要发送短信的手机号码
        String[] phoneNumbers = {"15670340373"};
// 短信模板 ID，需要在短信应用中申请
        int templateId = 1464219; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
// 签名
        String smsSign = "瑞吉外卖"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请

        try {
            String[] params = {"5678"};
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers[0],
                    templateId, params, smsSign, "", "");
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        }
    }

}
