package com.qg.anywork.domain;

import com.qg.anywork.model.po.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Create by ming on 18-8-11 下午2:09
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
public interface StudentRepository extends CrudRepository<Student, Integer> {

    /**
     * 根据学号查找
     *
     * @param studentId 学号
     * @return 如果有则返回实体类
     */
    Student findByStudentId(@Param("studentId") String studentId);

}
