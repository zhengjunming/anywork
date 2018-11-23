package com.qg.anywork.model.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Create by ming on 18-8-10 下午9:42
 * 建议实体类
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "suggestion")
public class Suggestion implements Serializable {

    @Id
    @Column(name = "suggestion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer suggestionId;

    @Column(name = "description")
    private String description;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "user_id")
    private User user;
}
