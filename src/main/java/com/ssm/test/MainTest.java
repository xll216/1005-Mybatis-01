package com.ssm.test;

import com.ssm.domain.Student;
import com.ssm.mapper.StudentMapper;
import com.ssm.page.PageBean;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by 蓝鸥科技有限公司  www.lanou3g.com.
 */
public class MainTest {

    private ApplicationContext context;

    @Before
    public void init() {

        context = new ClassPathXmlApplicationContext(
                "classpath*:spring-*.xml");

    }

    @Test
    public void testMapper() {
        /*获得mybatis的Student表的代理对象*/
        StudentMapper studentMapper =
                context.getBean(StudentMapper.class);

        /*调用接口中的查询所有*/
        List<Student> students = studentMapper
                .selectAll();

        System.out.println(students);

        Student student=new Student();
        student.setSname("王");

        PageBean<Student> pg = new
                PageBean<>(
                1, 3, 3);
        pg.setParameter(student);

        /*获取总条数*/
        int count = studentMapper.getTotalRecord(pg);

        System.out.println(count);

        /*分页查询*/
        PageBean<Student> pageBean =
                new PageBean<>(
                        1, 3, count);

        System.out.println(
                studentMapper.pageSelect(pageBean));


    }


    @Test
    public void testPageSelect() {
        StudentMapper studentMapper = context
                .getBean(StudentMapper.class);

        Student student=new Student();
        student.setSname("王");

        PageBean<Student> pg = new
                PageBean<>(
                1, 3, 3);
        pg.setParameter(student);

        int total = studentMapper.getTotalRecord(pg);

        PageBean<Student> pageBean = new
                PageBean<>(1, 3, total);
        Student param = new Student();
        param.setSname("王");
        pageBean.setParameter(param);

        List<Student> students =
                studentMapper.pageSelect(pageBean);
        System.out.println(students);

    }
}
