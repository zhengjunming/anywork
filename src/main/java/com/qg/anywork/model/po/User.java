package com.qg.anywork.model.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户实体
 *
 * @author FunriLy
 * @date 2017/7/10
 * From small beginnings comes great things.
 */
@Data
@NoArgsConstructor
@Table(name = "user")
@Entity
public class User implements Serializable {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    /**
     * 昵称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 手机
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 标志，区分是学生还是教师, 0学生，1老师
     */
    @Column(name = "mark")
    private int mark;

    /**
     * 学号
     */
    @Column(name = "student_id")
    private String studentId;

    /**
     * 头像路径
     */
    @Column(name = "image_path")
    private String imagePath;
}
