package com.qg.anywork.domain;

import com.qg.anywork.model.po.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Create by ming on 18-8-10 下午9:47
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, Integer> {


}
