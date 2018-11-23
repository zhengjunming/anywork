package com.qg.anywork.service.impl;

import com.qg.anywork.dao.RedisDao;
import com.qg.anywork.domain.StudentRepository;
import com.qg.anywork.domain.UserRepository;
import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.user.*;
import com.qg.anywork.model.dto.RequestResult;
import com.qg.anywork.model.po.Student;
import com.qg.anywork.model.po.User;
import com.qg.anywork.service.UserService;
import com.qg.anywork.util.Encryption;
import com.qg.anywork.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

/**
 * Create by ming on 18-8-5 上午11:20
 * <p>
 * 用户服务实现类
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
@Slf4j
@Service
@Scope("prototype")
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisDao redisDao;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void userMessageCheck(User user) {
        if (null == user) {
            throw new EmptyUserException(StatEnum.REGISTER_EMPTY_USER);
        } else if (userRepository.findByStudentId(user.getStudentId()) != null) {
            throw new UserException(StatEnum.STUDENT_ID_HAS_BEEN_REGISTERED);
        } else if (!user.getEmail().matches("\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}") ||
                !user.getPassword().matches("\\w{6,15}")) {
            throw new FormatterFaultException(StatEnum.REGISTER_FORMAT_FAULT);
        } else if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserException(StatEnum.MAIL_HAS_BEEN_REGISTERED);
        }
    }

    @Override
    public void register(String email) {
        User user;
        user = redisDao.getUserMessage(email);
        if (null == user) {
            throw new EmptyUserException(StatEnum.REGISTER_EMPTY_USER);
        } else if (!user.getEmail().matches("\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}") ||
                !user.getUserName().matches("[a-z0-9A-Z\\u4e00-\\u9fa5]{1,15}") ||
                !user.getPassword().matches("\\w{6,15}")) {
            throw new FormatterFaultException(StatEnum.REGISTER_FORMAT_FAULT);
        } else {
            if (null != userRepository.findByEmail(user.getEmail())) {
                throw new UserException(StatEnum.REGISTER_ALREADY_EXIST);
            }
            //格式正确，加密密码并存入数据库
            user.setPassword(Encryption.getMD5(user.getPassword()));
            userRepository.save(user);
            int userId = user.getUserId();
            if (userId <= 0) {
                throw new UserException(StatEnum.DEFAULT_WRONG);
            }
        }
    }

    @Override
    public RequestResult<User> login(String studentId, String password) {
        if (studentId == null || password == null) {
            throw new FormatterFaultException(StatEnum.REGISTER_EMPTY_USER);
        }
        User user = userRepository.findByStudentId(studentId);
        if (user == null) {
            throw new UserNotExitException(StatEnum.LOGIN_NOT_EXIT_USER);
        } else if (!user.getPassword().equals(Encryption.getMD5(password))) {
            throw new UserLoginFailException(StatEnum.LOGIN_USER_MISMATCH);
        } else {
            //登录成功
            // TODO: 2017/7/10 用户登录成功，检索出所有的组织
            user.setPassword("");
            return new RequestResult<>(StatEnum.LOGIN_SUCCESS, user);
        }
    }

    @Override
    public RequestResult<User> updateUser(User user, String email, String phone) {
        if (user == null) {
            throw new FormatterFaultException(StatEnum.REGISTER_EMPTY_USER);
        }
        if (!email.matches("\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}")) {
            throw new FormatterFaultException(StatEnum.EMAIL_FORMAT_ERROR);
        }
        user.setEmail(email);
        if (!"".equals(phone)) {
            if (!phone.matches("^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$")) {
                throw new FormatterFaultException(StatEnum.PHONE_FORMAT_ERROR);
            }
            user.setPhone(phone);
        }
        int flag = userRepository.updateEmailAndPhoneOrderByUserId(user.getEmail(), user.getPhone(), user.getUserId());
        if (flag == 0) {
            throw new UserException(StatEnum.DEFAULT_WRONG);
        }
        log.info(user.getUserName() + "修改了个人信息: " + user.getPhone() + "  " + user.getEmail());
        // 查找新的用户实体
        User realUser = userRepository.findByUserId(user.getUserId());
        realUser.setPassword("");
        return new RequestResult<>(StatEnum.INFORMATION_CHANGE_SUCCESS, realUser);
    }

    @Override
    public boolean modifyPassword(int userId, String oldPassword, String newPassword) {
        User user = userRepository.findByUserId(userId);
        if (!user.getPassword().equals(Encryption.getMD5(oldPassword))) {
            throw new PasswordException(StatEnum.OLD_PASSWORD_ERROR);
        }
        if (!newPassword.matches("\\w{6,15}")) {
            throw new PasswordException(StatEnum.NEW_PASSWORD_FORMAT_ERROR);
        } else {
            int flag = userRepository.updatePasswordByUserId(userId, Encryption.getMD5(newPassword));
            return flag == 1;
        }
    }

    @Override
    public RequestResult resetPassword(String email, String password, String repeatPassword) {
        if ("".equals(email) || email == null) {
            throw new UserException(StatEnum.ERROR_PARAM);
        }
        if (!password.equals(repeatPassword)) {
            throw new PasswordException(StatEnum.INCONSISTENT_PASSWORD);
        }
        if (!password.matches("[a-z0-9A-Z\\u4e00-\\u9fa5]{6,15}")) {
            throw new PasswordException(StatEnum.NEW_PASSWORD_FORMAT_ERROR);
        }
        int flag = userRepository.updatePasswordByEmail(email, Encryption.getMD5(password));
        if (flag == 1) {
            return new RequestResult(StatEnum.PASSWORD_RESET_SUCCESS);
        } else {
            return new RequestResult(StatEnum.PASSWORD_RESET_FAIL);
        }

    }

    @Override
    public RequestResult<User> findUserInfo(int userId) {
        User user = userRepository.findByUserId(userId);
        if (null == user) {
            throw new UserNotExitException(StatEnum.LOGIN_NOT_EXIT_USER);
        }
        user.setPassword("");
        return new RequestResult<>(StatEnum.INFORMATION_GET_MYSELF, user);
    }

    @Override
    public RequestResult addStudent() throws IOException, InvalidFormatException {
        String path = System.getProperty("user.dir") + "/src/main/resources";
        File file = new File(path + "/static/名单.xlsx");
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Student> students;
        students = ExcelUtil.readStudentExcel(inputStream);
        studentRepository.saveAll(students);
        return new RequestResult(1, "添加成功");
    }
}


