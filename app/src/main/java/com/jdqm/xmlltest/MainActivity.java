package com.jdqm.xmlltest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.jdqm.xmlltest.bean.Student;
import com.jdqm.xmlltest.parser.PullStudentParser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*try {
            InputStream is = getAssets().open("students.xml");
            PullStudentParser studentParser = new PullStudentParser();
            List<Student> students = studentParser.parser(is);
            for (Student stu : students) {
                Log.i(TAG, stu.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        try {
            PullStudentParser studentParser = new PullStudentParser();
            List<Student> students = new ArrayList<>();
            students.add(new Student(4,"李四", 24));
            students.add(new Student(5,"王五", 25));
            students.add(new Student(6,"赵六", 26));
            String xml = studentParser.serialize(students);  //序列化
            Log.i(TAG, xml);
            FileOutputStream fos = openFileOutput("students.xml", Context.MODE_PRIVATE);
            fos.write(xml.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
