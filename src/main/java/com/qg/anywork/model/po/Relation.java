package com.qg.anywork.model.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ming
 */
@Data
@NoArgsConstructor
public class Relation implements Serializable {

    /**
     * ID
     */
    private int relationId;

    /**
     * 组织ID
     */
    private int organizationId;

    /**
     * 用户ID
     */
    private int userId;

    /**
     * 角色，预留字段
     */
    private int role;
}
