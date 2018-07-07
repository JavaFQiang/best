package com.best.wechat.handler;

import com.best.wechat.ai.TxtMassage;
import com.best.wechat.builder.ImageBuilder;
import com.best.wechat.builder.TextBuilder;
import com.best.wechat.utils.JsonUtils;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MsgHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
        }

        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        try {
            if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
                    && weixinService.getKefuService().kfOnlineList()
                    .getKfOnlineList().size() > 0) {
                return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                        .fromUser(wxMessage.getToUser())
                        .toUser(wxMessage.getFromUser()).build();
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        String msgType = wxMessage.getMsgType();
        String toUser = wxMessage.getToUser();
        String msg = wxMessage.getMsg();
        String picUrl = wxMessage.getPicUrl();
        String response = "";
        //TODO 组装回复消息
        switch (msgType) {
            case "text":
                response = TxtMassage.scene(msg, toUser);
                return new TextBuilder().build(response, wxMessage, weixinService);
            case "image":
                return new ImageBuilder().build(response, wxMessage, weixinService);
            default:
                return new TextBuilder().build("这个需求我还没有做，过几天吧", wxMessage, weixinService);
        }


    }

}
