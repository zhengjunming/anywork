package com.qg.anywork.web.controller;

import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.common.ParamNotExistException;
import com.qg.anywork.model.dto.RequestResult;
import com.qg.anywork.model.po.User;
import com.qg.anywork.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author FunriLy
 * @date 2017/7/13
 * From small beginnings comes great things.
 */
@Controller
@RequestMapping("/quest")
@CrossOrigin
@Slf4j
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * 收藏题目
     */
    @PostMapping("/collect")
    @ResponseBody
    public RequestResult collectQuestion(HttpServletRequest request, @RequestBody Map<String, Integer> map) {
        if (!map.containsKey("questionId")) {
            throw new ParamNotExistException(StatEnum.PARAM_IS_NOT_EXIST);
        }
        User user = (User) request.getSession().getAttribute("user");
        return questionService.collectQuestion(user.getUserId(), map.get("questionId"));
    }

    /**
     * 删除已收藏的题目
     */
    @PostMapping("/collect/delete")
    @ResponseBody
    public RequestResult deleteCollectionQuestion(HttpServletRequest request, @RequestBody Map<String, Integer> map) {
        if (!map.containsKey("questionId")) {
            throw new ParamNotExistException(StatEnum.PARAM_IS_NOT_EXIST);
        }
        User user = (User) request.getSession().getAttribute("user");
        return questionService.deleteCollection(user.getUserId(), map.get("questionId"));
    }

    @PostMapping("/collect/list")
    @ResponseBody
    public RequestResult listCollectionQuestion(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return questionService.listCollectionQuestion(user.getUserId());
    }
}
