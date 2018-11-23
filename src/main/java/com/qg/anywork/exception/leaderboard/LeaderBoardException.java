package com.qg.anywork.exception.leaderboard;

import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.AbstractAnyWorkException;

/**
 * Create by ming on 18-11-4 下午8:57
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
public class LeaderBoardException extends AbstractAnyWorkException {
    public LeaderBoardException(StatEnum statEnum) {
        super(statEnum);
    }
}
