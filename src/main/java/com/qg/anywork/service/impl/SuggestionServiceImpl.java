package com.qg.anywork.service.impl;

import com.qg.anywork.domain.SuggestionRepository;
import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.model.dto.RequestResult;
import com.qg.anywork.model.po.Suggestion;
import com.qg.anywork.service.SuggestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by ming on 18-8-5 下午10:22
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
@Service
@Slf4j
public class SuggestionServiceImpl implements SuggestionService {

    @Autowired
    private SuggestionRepository suggestionRepository;

    @Override
    public RequestResult addSuggestion(Suggestion suggestion) {
        if (suggestion.getDescription() == null || "".equals(suggestion.getDescription())) {
            return new RequestResult(0, "请填写你的建议");
        }
        suggestionRepository.save(suggestion);
        log.info("{}添加了一条建议", suggestion.getUser().getUserName());
        return new RequestResult(1, "成功");
    }

    @Override
    public RequestResult show() {
        List<Suggestion> suggestions = suggestionRepository.findAll();
        List<Object> list = new ArrayList<>();
        for (Suggestion suggestion : suggestions) {
            Map<String, String> map = new HashMap<>(3);
            map.put("userName", suggestion.getUser().getUserName());
            map.put("description", suggestion.getDescription());
            map.put("imagePath", suggestion.getImagePath());
            list.add(map);
        }
        return new RequestResult<>(StatEnum.GET_TEST_SUCCESS, list);
    }
}
