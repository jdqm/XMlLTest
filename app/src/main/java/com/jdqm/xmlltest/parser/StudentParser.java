package com.jdqm.xmlltest.parser;

import com.jdqm.xmlltest.bean.Student;

import java.io.InputStream;
import java.util.List;

/**
 * Created by jdqm on 2017-7-3.
 */

public interface StudentParser {

    /**
     * 解析xml
     * @param inputStream
     * @return 解析得到的对象集合
     * @throws Exception
     */
    List<Student> parser(InputStream inputStream) throws Exception;

    /**
     * 序列化
     * @param students
     * @return
     * @throws Exception
     */
    String serialize(List<Student> students) throws Exception;

}
