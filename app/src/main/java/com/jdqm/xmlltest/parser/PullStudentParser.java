package com.jdqm.xmlltest.parser;

import android.util.Xml;

import com.jdqm.xmlltest.bean.Student;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 2017-7-3.
 */

public class PullStudentParser implements StudentParser {

    @Override
    public List<Student> parser(InputStream inputStream) throws Exception {

        List<Student> students = null;
        Student student = null;

        //XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        //XmlPullParser parser = factory.newPullParser();

        XmlPullParser parser = Xml.newPullParser(); //由android.util.Xml创建一个XmlPullParser实例
        parser.setInput(inputStream, "UTF-8");               //设置输入流 并指明编码方式

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    students = new ArrayList<>();
                    break;
                case XmlPullParser.START_TAG:
                    if (parser.getName().equals("student")) {
                        student = new Student();
                    } else if (parser.getName().equals("no")) {
                        parser.next();
                        student.setNo(Integer.parseInt(parser.getText()));
                    } else if (parser.getName().equals("name")) {
                        parser.next();
                        student.setName(parser.getText());
                    } else if (parser.getName().equals("age")) {
                        parser.next();
                        student.setAge(Integer.parseInt(parser.getText()));
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (parser.getName().equals("student")) {
                        students.add(student);
                        student = null;
                    }
                    break;
            }
            eventType = parser.next();
        }
        return students;
    }

    @Override
    public String serialize(List<Student> students) throws Exception {

        // XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        // XmlSerializer serializer = factory.newSerializer();

        XmlSerializer serializer = Xml.newSerializer(); //由android.util.Xml创建一个XmlSerializer实例
        StringWriter writer = new StringWriter();
        serializer.setOutput(writer);   //设置输出方向为writer
        serializer.startDocument("UTF-8", true);
        serializer.startTag("", "students");
        for (Student student : students) {
            serializer.startTag("", "student");

            serializer.startTag("", "no");
            serializer.text(String.valueOf(student.getNo()));
            serializer.endTag("", "no");

            serializer.startTag("", "name");
            serializer.text(student.getName());
            serializer.endTag("", "name");

            serializer.startTag("", "age");
            serializer.text(student.getAge() + "");
            serializer.endTag("", "age");

            serializer.endTag("", "student");
        }
        serializer.endTag("", "students");
        serializer.endDocument();

        return writer.toString();
    }

}
