package com.qg.anywork;

import com.qg.anywork.dao.OrganizationDao;
import com.qg.anywork.domain.UserRepository;
import com.qg.anywork.model.po.User;
import com.qg.anywork.util.Encryption;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class AnyworkApplicationTests {

    //    @Autowired
//    private StudentRepository studentRepository;
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private OrganizationDao organizationDao;
//
//    @Test
//    public void contextLoads() {
//    }
//
//    @Test
//    public void addUser() {
//        List<Student> students = (List<Student>) studentRepository.findAll();
//        List<User> users = new ArrayList<>();
//        int email = 15949988;
//        int i = 1;
//        for (Student student : students) {
//            User user = new User();
//            user.setStudentId(student.getStudentId());
//            user.setUserName(student.getStudentName());
//            user.setPassword(Encryption.getMD5("123456"));
//            user.setMark(0);
//            user.setEmail(String.valueOf(email + i) + "@qq.com");
//            users.add(user);
//            i++;
//        }
//        userRepository.saveAll(users);
//    }
//
//    @Test
//    public void getStudentId() {
//        List<Student> students = (List<Student>) studentRepository.findAll();
//        for (Student student : students) {
//            System.out.println(student.getStudentId());
//        }
//    }
//
//    @Test
//    public void deleteStudentId() {
//        List<Student> students = (List<Student>) studentRepository.findAll();
//        for (Student student : students) {
//            try {
//                userRepository.findByStudentId(student.getStudentId());
//            } catch (IncorrectResultSizeDataAccessException e) {
//                System.out.println("Test");
//                userRepository.deleteAllByStudentId(student.getStudentId());
//            }
//        }
//    }
//
//    private static List<User> read(InputStream inputStream) throws IOException, InvalidFormatException {
//        List<User> users = new ArrayList<>();
//        Workbook workbook = WorkbookFactory.create(inputStream);
//        Sheet sheet = workbook.getSheetAt(0);
//        for (Iterator rowIterator = sheet.rowIterator(); rowIterator.hasNext(); ) {
//            XSSFRow row = (XSSFRow) rowIterator.next();
//            List<Object> list = new ArrayList<>();
//            User user = new User();
//            for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
//                list.add(row.getCell(j));
//            }
//            user.setUserName(String.valueOf(list.get(0)));
//            user.setMark(0);
//            user.setStudentId(String.valueOf(list.get(1)));
//            user.setEmail(String.valueOf(list.get(2)));
//            user.setPhone(String.valueOf(list.get(3)));
//            user.setPassword(Encryption.getMD5("123456"));
//            list.clear();
//            users.add(user);
//        }
//        return users;
//    }
//
//    @Test
//    public void addUser() throws IOException, InvalidFormatException {
//        File file = new File("/home/ming/桌面/anywork/18级信息/工作簿2.xlsx");
//        InputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        List<User> users = read(inputStream);
//        userRepository.saveAll(users);
//        for (User user : users) {
//            organizationDao.joinOrganization(1, user.getUserId());
//        }
//    }
}
