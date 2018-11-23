package com.qg.anywork.web.controller;

import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.common.ParamNotExistException;
import com.qg.anywork.model.dto.RequestResult;
import com.qg.anywork.model.po.User;
import com.qg.anywork.service.LeaderBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Create by ming on 18-9-12 下午2:16
 * <p>
 * 排行榜
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
@RestController
@RequestMapping("/leaderboard")
@CrossOrigin
@Slf4j
public class LeaderBoardController {

    @Autowired
    private LeaderBoardService leaderBoardService;

    /**
     * 显示总的排行榜
     */
    @PostMapping("/show")
    public RequestResult showLeaderBoard(HttpServletRequest request, @RequestBody Map<String, Integer> map) {
        if (!map.containsKey("leaderboardType")) {
            throw new ParamNotExistException(StatEnum.PARAM_IS_NOT_EXIST);
        }
        int leaderBoardType = map.get("leaderboardType");
        User user = (User) request.getSession().getAttribute("user");
        return leaderBoardService.showLeaderBoard(user.getUserId(), leaderBoardType);
    }

    /**
     * 显示每个测试的排行榜
     */
    @PostMapping("/paper/show")
    public RequestResult showPaperLeaderBoard(HttpServletRequest request, @RequestBody Map<String, Integer> map) {
        if (!map.containsKey("leaderboardType") || !map.containsKey("testpaperId")) {
            throw new ParamNotExistException(StatEnum.PARAM_IS_NOT_EXIST);
        }
        int leaderBoardType = map.get("leaderboardType");
        int testPaperId = map.get("testpaperId");
        User user = (User) request.getSession().getAttribute("user");
        return leaderBoardService.showPaperLeaderBoard(user.getUserId(), leaderBoardType, testPaperId);
    }

    @PostMapping("/teacher/show")
    public RequestResult teacherShowAllOrganizationsLeaderBoard(HttpServletRequest request, @RequestBody Map<String, Integer> map) {
        if (!map.containsKey("testpaperId")) {
            throw new ParamNotExistException(StatEnum.PARAM_IS_NOT_EXIST);
        }
        int testPaperId = map.get("testpaperId");
        User user = (User) request.getSession().getAttribute("user");
        return leaderBoardService.teacherShowAllOrganizationsLeaderBoard(user.getUserId(), testPaperId);
    }

    @PostMapping("/teacher/show/{organizationId}")
    public RequestResult teacherShowOneOrganizationsLeaderBoard(@PathVariable int organizationId,
                                                                @RequestBody Map<String, Integer> map) {
        if (!map.containsKey("testpaperId")) {
            throw new ParamNotExistException(StatEnum.PARAM_IS_NOT_EXIST);
        }
        int testPaperId = map.get("testpaperId");
        return leaderBoardService.teacherShowOneOrganizationsLeaderBoard(testPaperId, organizationId);
    }
}
