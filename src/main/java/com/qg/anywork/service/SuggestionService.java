package com.qg.anywork.service;

import com.qg.anywork.model.dto.RequestResult;
import com.qg.anywork.model.po.Suggestion;


/**
 * @author logan
 * @date 2017/8/18
 */
public interface SuggestionService {
    /**
     * 添加建议
     *
     * @param suggestion suggestion
     * @return request result
     */
    RequestResult addSuggestion(Suggestion suggestion);

    /**
     * 显示建议
     *
     * @return 建议
     */
    RequestResult show();
}
