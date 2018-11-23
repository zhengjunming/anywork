package com.qg.anywork.web.controller;

import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.common.ParamNotExistException;
import com.qg.anywork.exception.testpaper.NotPowerException;
import com.qg.anywork.model.dto.RequestResult;
import com.qg.anywork.model.po.Message;
import com.qg.anywork.model.po.User;
import com.qg.anywork.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Create by ming on 18-8-17 下午5:16
 * 公告模块控制器
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/publish")
    public RequestResult publishMessage(HttpServletRequest request, @RequestBody Message message) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getMark() != 1) {
            throw new NotPowerException(StatEnum.NOT_HAVE_POWER);
        }
//        User user = new User();
//        user.setUserId(1996);
//        user.setMark(1);
//        user.setUserName("伍峻贤");
        message.setUserId(user.getUserId());
        return messageService.publishMessage(message, user.getUserName());
    }

    @PostMapping("/list")
    public RequestResult listMessage(HttpServletRequest request, @RequestBody Map<String, Integer> map) {
        User user = (User) request.getSession().getAttribute("user");
        if (!map.containsKey("pageNum") || !map.containsKey("pageSize")) {
            throw new ParamNotExistException(StatEnum.PARAM_IS_NOT_EXIST);
        }
        return messageService.listMessage(user.getUserId(), map.get("pageNum"), map.get("pageSize"));
    }

    @PostMapping("/delete")
    public RequestResult deleteMessage(HttpServletRequest request, @RequestBody Map<String, Integer> map) {
        User user = (User) request.getSession().getAttribute("user");
        if (!map.containsKey("messageId")) {
            throw new ParamNotExistException(StatEnum.PARAM_IS_NOT_EXIST);
        }
        if (user.getMark() != 1) {
            throw new NotPowerException(StatEnum.NOT_HAVE_POWER);
        }
        log.info("{}删除公告", user.getUserName());
        return messageService.deleteMessage(map.get("messageId"), user.getUserId());
    }

    @PostMapping("/show")
    public RequestResult studentShowMessage(HttpServletRequest request, @RequestBody Map<String, Integer> map) {
        User user = (User) request.getSession().getAttribute("user");
//        User user = new User();
//        user.setUserId(1988);
        if (!map.containsKey("pageNum") || !map.containsKey("pageSize") || !map.containsKey("status")) {
            throw new ParamNotExistException(StatEnum.PARAM_IS_NOT_EXIST);
        }
        return messageService.studentShowMessage(user.getUserId(), map.get("pageNum"), map.get("pageSize"), map.get("status"));
    }

    @PostMapping("/change")
    public RequestResult changeMessageStatus(HttpServletRequest request, @RequestBody Map<String, Integer> map) {
        User user = (User) request.getSession().getAttribute("user");
        if (!map.containsKey("messageId")) {
            throw new ParamNotExistException(StatEnum.PARAM_IS_NOT_EXIST);
        }
        return messageService.changeMessageStatus(user.getUserId(), map.get("messageId"));
    }
}
