package com.qg.anywork.web.controller;

import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.question.ExcelReadException;
import com.qg.anywork.exception.testpaper.NotPowerException;
import com.qg.anywork.model.bo.TeacherSubmit;
import com.qg.anywork.model.dto.RequestResult;
import com.qg.anywork.model.po.User;
import com.qg.anywork.service.PaperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Map;

/**
 * Create by ming on 18-10-5 下午8:43
 * 试卷发布与分析控制器
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/paper")
public class PaperController {

    private final PaperService paperService;

    @Autowired
    public PaperController(PaperService paperService) {
        this.paperService = paperService;
    }

    /**
     * 发布试卷
     *
     * @param file           文件
     * @param testpaperTitle 试卷标题
     * @param chapterId      章节ID
     * @param createTime     开始时间
     * @param endingTime     结束时间
     * @param testpaperType  试卷类型
     * @param request        请求
     */
    @PostMapping("/publish")
    public RequestResult publishPaper(@RequestPart("file") MultipartFile file,
                                      @RequestParam("testpaperTitle") String testpaperTitle,
                                      @RequestParam("chapterId") Integer chapterId,
                                      @RequestParam("createTime") String createTime,
                                      @RequestParam("endingTime") String endingTime,
                                      @RequestParam("testpaperType") Integer testpaperType,
                                      HttpServletRequest request) throws Exception {
        if (null != file && !file.isEmpty()) {
            String filename = file.getOriginalFilename();
            assert filename != null;
            if (!(filename.endsWith(".xlsx") || filename.endsWith(".xls"))) {
                throw new ExcelReadException(StatEnum.FILE_FORMAT_ERROR);
            }
        } else {
            return new RequestResult<>(0, "excel文件为空");
        }
        if (testpaperTitle == null || "".equals(testpaperTitle)) {
            return new RequestResult<>(0, "试卷标题为空");
        }
        if (chapterId == null) {
            return new RequestResult<>(0, "章节号为空");
        }
        if (createTime == null) {
            return new RequestResult(0, "开始时间为空");
        }
        if (endingTime == null) {
            return new RequestResult<>(0, "结束时间为空");
        }
        if (testpaperType == null) {
            return new RequestResult(0, "试卷类型为空");
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user.getMark() == 0) {
            throw new NotPowerException(StatEnum.NOT_HAVE_POWER);
        }
//        User user = new User();
//        user.setUserId(1);
//        user.setUserName("name");
        return paperService.addTestPaper(file.getInputStream(), testpaperTitle, chapterId, createTime, endingTime, testpaperType, user);
    }

    /**
     * 更新试卷信息
     *
     * @param request 请求
     * @param map     map
     *                testpaperId 试卷ID
     *                testpaperTitle 试卷标题
     *                testpaperType 试卷类型，int，1是考试，2是预习题，3是课后复习题
     *                createTime 开始时间
     *                endingTime 结束时间
     */
    @PostMapping("/update")
    public RequestResult updatePaperInfo(HttpServletRequest request, @RequestBody Map<String, Object> map) throws ParseException {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getMark() != 1) {
            throw new NotPowerException(StatEnum.NOT_HAVE_POWER);
        }
        return paperService.updateTestPaperInfo((int) map.get("testpaperId"), map.get("testpaperTitle").toString(),
                (int) map.get("testpaperType"), map.get("createTime").toString(), map.get("endingTime").toString());
    }

    /**
     * 删除已发布的试卷
     *
     * @param request request
     * @param map     map
     */
    @PostMapping("delete")
    public RequestResult deleteTestPaper(HttpServletRequest request, @RequestBody Map<String, Integer> map) {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getMark() != 1) {
            throw new NotPowerException(StatEnum.NOT_HAVE_POWER);
        }
        return paperService.deleteTestPaper(map.get("testpaperId"));
    }

    /**
     * 查看试卷
     *
     * @param request 请求
     * @param map     map
     *                testpaperId 试卷ID
     */
    @PostMapping("/show")
    public RequestResult showTestPaper(HttpServletRequest request, @RequestBody Map<String, Integer> map) {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getMark() != 1) {
            throw new NotPowerException(StatEnum.NOT_HAVE_POWER);
        }
        return paperService.showTestPaper(map.get("testpaperId"));
    }

    /**
     * 试卷分析
     *
     * @param request 请求
     * @param map     map
     *                testpaperId 试卷ID
     *                organizationId 组织ID，分析以组织为单位，如果想看全部班的情况，此字段为0
     */
    @PostMapping("/analyse")
    public RequestResult analyseTestPaper(HttpServletRequest request, @RequestBody Map<String, Integer> map) {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getMark() != 1) {
            throw new NotPowerException(StatEnum.NOT_HAVE_POWER);
        }
        return paperService.analyseTestPaper(map.get("testpaperId"), map.get("organizationId"), user.getUserId());
    }

    /**
     * 查看一个组织里面自己发布过的试卷列表
     *
     * @param request        请求
     * @param organizationId 组织ID
     */
    @PostMapping("/{organizationId}/list")
    public RequestResult listTestPaper(HttpServletRequest request, @PathVariable("organizationId") int organizationId) {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getMark() != 1) {
            throw new NotPowerException(StatEnum.NOT_HAVE_POWER);
        }
        return paperService.listTestPaper(organizationId);
    }

    /**
     * 老师查看某套试题中的学生完成情况列表
     *
     * @param request 请求
     * @param map     map
     *                testpaperId 试卷ID
     *                organizationId 组织ID，如果要查看自己创建的全部组织的情况，该字段为0
     */
    @PostMapping("/student/list")
    public RequestResult listStudentDoneDetail(HttpServletRequest request, @RequestBody Map<String, Integer> map) {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getMark() != 1) {
            throw new NotPowerException(StatEnum.NOT_HAVE_POWER);
        }
        return paperService.listStudentDoneDetail(map.get("testpaperId"), map.get("organizationId"), user);
    }

    /**
     * 具体查看某学生完成过的某套试题
     *
     * @param request 请求
     * @param map     map
     *                testpaperId 试卷id
     *                studentId 学生id
     */
    @PostMapping("/student/testDetail")
    public RequestResult showStudentTestDetail(HttpServletRequest request, @RequestBody Map<String, Integer> map) {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getMark() != 1) {
            throw new NotPowerException(StatEnum.NOT_HAVE_POWER);
        }
        return paperService.showStudentTestDetail(map.get("testpaperId"), map.get("studentId"));
    }

    /**
     * 获取学生简答题答案进行评卷
     *
     * @param request 请求
     * @param map     map
     *                testpaperId 试卷id
     *                studentId 学生id
     */
    @PostMapping("/student/subject")
    public RequestResult getStudentSubjectAnswer(HttpServletRequest request, @RequestBody Map<String, Integer> map) {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getMark() != 1) {
            throw new NotPowerException(StatEnum.NOT_HAVE_POWER);
        }
        return paperService.getStudentSubjectAnswer(map.get("testpaperId"), map.get("studentId"));
    }

    /**
     * 教师评卷
     */
    @PostMapping("/teacher/judge")
    public RequestResult teacherJudgeSubjectAnswer(HttpServletRequest request, @RequestBody TeacherSubmit teacherSubmit) {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getMark() != 1) {
            throw new NotPowerException(StatEnum.NOT_HAVE_POWER);
        }
        return paperService.teacherJudgeSubjectAnswer(teacherSubmit);
    }
}
