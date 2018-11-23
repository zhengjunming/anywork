package com.qg.anywork.web.controller;

import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.common.ParamNotExistException;
import com.qg.anywork.model.bo.StudentPaper;
import com.qg.anywork.model.dto.RequestResult;
import com.qg.anywork.model.po.Chapter;
import com.qg.anywork.model.po.StudentTestResult;
import com.qg.anywork.model.po.User;
import com.qg.anywork.service.ChapterService;
import com.qg.anywork.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author logan
 * @date 2017/7/12
 */
@RestController
@RequestMapping("/test")
@CrossOrigin
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private ChapterService chapterService;

    @Resource(name = "defaultThreadPool")
    private ThreadPoolTaskExecutor executor;

    /***
     * 获取组织下的章节列表
     * @param map map
     *            organizationId 组织ID
     * @return 章节列表
     */
    @PostMapping("/chapter")
    public RequestResult<List<Chapter>> getChapter(@RequestBody Map<String, Integer> map) {
        if (!map.containsKey("organizationId")) {
            throw new ParamNotExistException(StatEnum.PARAM_IS_NOT_EXIST);
        }
        int organizationId = map.get("organizationId");
        return chapterService.getByOrganizationId(organizationId);
    }

    /**
     * 获取试卷概要列表，包括考试、预习题、复习题
     *
     * @param request request
     * @param map     map
     *                organizationId 组织ID
     *                chapter 组织ID 如果是请求考试，这个字段为0
     *                testPaperType 要哪种类型的试卷  1是考试，2是预习题，3是课后复习题
     * @return 试卷列表
     */
    @PostMapping("/list")
    public RequestResult listTest(HttpServletRequest request, @RequestBody Map<String, Object> map) {
        if (!map.containsKey("organizationId") || !map.containsKey("chapter") || !map.containsKey("testPaperType")) {
            throw new ParamNotExistException(StatEnum.PARAM_IS_NOT_EXIST);
        }
        User user = (User) request.getSession().getAttribute("user");
        int organizationId = (int) map.get("organizationId");
        int chapterId = (int) map.get("chapter");
        int testPaperType = (int) map.get("testPaperType");
        return testService.listTest(user.getUserId(), organizationId, chapterId, testPaperType);
    }

    /**
     * 获取已完成的试卷的结果
     *
     * @param request request
     * @param map     map
     *                testpaperId 试卷iD
     * @return 试卷结果
     */
    @PostMapping("/done/detail")
    public RequestResult getDoneTestDetail(HttpServletRequest request, @RequestBody Map<String, Integer> map) {
        if (!map.containsKey("testpaperId")) {
            throw new ParamNotExistException(StatEnum.PARAM_IS_NOT_EXIST);
        }
        User user = (User) request.getSession().getAttribute("user");
        return testService.getDoneTestDetail(user.getUserId(), map.get("testpaperId"));
    }

    /**
     * 获取详细的试题（完成一部分和还未做）
     *
     * @param request request
     * @param map     map
     *                testpaperId 试卷ID
     *                choice 对做一半的试卷的选择 1代表继续做，2代表重新做，如果是对于还未做的试卷，值为0
     * @return 试卷结果
     */
    @PostMapping("/none/detail")
    public RequestResult getNoneTestDetail(HttpServletRequest request, @RequestBody Map<String, Integer> map) {
        if (!map.containsKey("testpaperId") || !map.containsKey("choice")) {
            throw new ParamNotExistException(StatEnum.PARAM_IS_NOT_EXIST);
        }
        User user = (User) request.getSession().getAttribute("user");
        return testService.getNoneTestDetail(user.getUserId(), map.get("testpaperId"), map.get("choice"));
    }

    /***
     * 提交试卷
     * @param studentPaper 学生答卷
     * @return 测试结果
     */
    @PostMapping("/submit")
    public RequestResult submit(@RequestBody StudentPaper studentPaper) throws ExecutionException, InterruptedException {
        if (studentPaper == null) {
            return new RequestResult<>(StatEnum.REQUEST_ERROR);
        }
        Future<RequestResult<StudentTestResult>> future = executor.submit(() -> testService.submitTestPaper(studentPaper));
        return future.get();
    }

    /***
     * 具体查看学生某道题的答题情况
     * @param map map
     *            questionId 问题ID
     * @return 详细的答题情况
     */
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public RequestResult detail(@RequestBody Map map, HttpServletRequest request) {

        String questionId = String.valueOf(map.get("questionId"));
        if (questionId == null || "".equals(questionId)) {
            return new RequestResult<>(StatEnum.REQUEST_ERROR);
        }
        User user = (User) request.getSession().getAttribute("user");
        return testService.getQuestionDetail(user.getUserId(), Integer.parseInt(questionId));
    }


    /***
     * 添加章节
     * @param chapter 章节
     * @return 章节信息
     */
    @RequestMapping(value = "/addChapter", method = RequestMethod.POST)
    public RequestResult addChapter(@RequestBody Chapter chapter, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getMark() == 0) {
            return new RequestResult<>(0, "无此权限");
        }
        return chapterService.addChapter(user.getUserId(), chapter);
    }

    /***
     * 删除章节
     * @param map map
     *            chapterId 章节ID
     * @return 返回信息
     */
    @RequestMapping(value = "/deleteChapter", method = RequestMethod.POST)
    public RequestResult deleteChapter(@RequestBody Map map, HttpServletRequest request) {
        int chapterId = (int) map.get("chapterId");
        User user = (User) request.getSession().getAttribute("user");
        if (user.getMark() == 0) {
            return new RequestResult(0, "无此权限");
        }
        return chapterService.deleteChapter(chapterId);
    }

    @PostMapping("/chapter/update")
    public RequestResult updateChapter(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getMark() == 0) {
            return new RequestResult<>(0, "无此权限");
        }
        return chapterService.updateChapter((int) map.get("chapterId"), map.get("chapterName").toString());
    }
}
