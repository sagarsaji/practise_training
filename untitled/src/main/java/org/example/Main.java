package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Student {
    private int id;
    private String name;
    private String department;

    public Student(int id,String name, String department) {
        this.id=id;
        this.name = name;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student(1,"Alice", "Math"));
        list.add(new Student(2,"Bob", "Computer Science"));
        list.add(new Student(3,"Charlie", "Physics"));
        list.add(new Student(4,"David", "Math"));

        // Create a map to store students grouped by department
        Map<String, List<Student>> map = new HashMap<>();
        for (Student s : list) {
            String dept = s.getDepartment();
            List<Student> deptStudents = map.getOrDefault(dept, new ArrayList<>());
            deptStudents.add(s);
            map.put(dept, deptStudents);
        }

        // Print the students grouped by department
        for (Map.Entry<String, List<Student>> entry : map.entrySet()) {
            String department = entry.getKey();
            List<Student> studentsInDepartment = entry.getValue();

            System.out.println("Department: " + department);
            for (Student student : studentsInDepartment) {
                System.out.println("   " + student.getId()+student.getName()+student.getDepartment());
            }
        }
    }
}
