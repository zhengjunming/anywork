package com.qg.anywork.domain;

import com.qg.anywork.model.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


/**
 * Create by ming on 18-8-11 下午5:08
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 根据学号查找用户
     *
     * @param studentId 学号
     * @return user
     */
    User findByStudentId(@Param("studentId") String studentId);

    /**
     * 根据邮箱查找用户
     *
     * @param email 邮箱
     * @return user
     */
    User findByEmail(@Param("email") String email);


    /**
     * 根据用户ID查找用户
     *
     * @param userId 用户ID
     * @return user
     */
    User findByUserId(@Param("userId") int userId);

    /**
     * 根据ID修改密码
     *
     * @param userId   ID
     * @param password 密码
     * @return 1为修改成功，0为修改失败
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query("UPDATE User user set user.password = :password where user.userId = :userId")
    int updatePasswordByUserId(@Param("userId") int userId, @Param("password") String password);

    /**
     * 根据ID修改路径
     *
     * @param userId    用户ID
     * @param imagePath 路径
     * @return 1为修改成功，0为修改失败
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query("UPDATE User user set user.imagePath = :imagePath where user.userId = :userId")
    int updateImagePathByUserId(@Param("userId") int userId, @Param("imagePath") String imagePath);

    /**
     * 根据学号删除记录
     *
     * @param studentId 学号
     * @return int
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    int deleteAllByStudentId(@Param("studentId") String studentId);


    /**
     * 根据邮箱修改密码
     *
     * @param email    邮箱
     * @param password 密码
     * @return 1为修改成功，0为修改失败
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query("UPDATE User user set user.password = :password where user.email = :email")
    int updatePasswordByEmail(@Param("email") String email, @Param("password") String password);

    /**
     * 根据用户ID修改邮箱和手机号码
     *
     * @param email  邮箱
     * @param phone  手机号码
     * @param userId 用户ID
     * @return 1为成功，0为失败
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query("UPDATE User user set user.email = :email, user.phone = :phone where user.userId = :userId")
    int updateEmailAndPhoneOrderByUserId(@Param("email") String email, @Param("phone") String phone, @Param("userId") int userId);
}
