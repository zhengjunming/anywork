package com.qg.anywork.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author logan
 * @date 2017/7/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chapter")
public class Chapter  implements Serializable {

    /**
     * 章节ID
     */
    @Id
    @Column(name = "chapter_id", nullable = false)
    private int chapterId;


    /**
     * 组织ID
     */
    @Column(name = "organization_id")
    private int organizationId;

    /**
     * 章节名称
     */
    @Column(name = "chapter_name")
    private String chapterName;

    @Column(name = "user_id")
    private Integer userId;
}
