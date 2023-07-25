package org.example;

import java.util.*;
import java.util.stream.Collectors;

class Student{
    private int id;
    private String name;
    private String department;

    public Student(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "id : " + id + " name : " + name + " department : " + department;
    }
}

public class Main {

    
    public static void main(String[] args) {
        Student s1 = new Student(1,"abc","IT");
        Student s2 = new Student(2,"def","IT");
        Student s3 = new Student(3,"ghi","EC");

        List<Student> list = Arrays.asList(s1,s2,s3);
//        Map<String,List<Student>> map = new HashMap<>();

//        for(Student s: list){
//            String dept = s.getDepartment();
//            List<Student> getDetails = map.getOrDefault(dept,new ArrayList<>());
//            getDetails.add(s);
//            map.put(dept,getDetails);
//        }
//
//        for(Map.Entry<String,List<Student>> data:map.entrySet()){
//            System.out.println(data.getKey() + " " + data.getValue());
//        }

        Map<String, List<Student>> map1 = list.stream()
                .collect(Collectors.groupingBy(Student::getDepartment));

        for(Map.Entry<String,List<Student>> data:map1.entrySet()){
            System.out.println(data.getKey() + " " + data.getValue());
        }

    }
}