package com.coursework.gymmanager.controller;

import com.coursework.gymmanager.model.Count;
import com.coursework.gymmanager.model.DefaultMember;
import com.coursework.gymmanager.model.Over60Member;
import com.coursework.gymmanager.model.StudentMember;
import com.coursework.gymmanager.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    private ArrayList<DefaultMember> arrayList = new ArrayList<>();

    private ArrayList<DefaultMember> seniorList = new ArrayList<>();
    private ArrayList<DefaultMember> studentList = new ArrayList<>();

    /*
        Get all the members from the repository and pass as a list
    */
    @GetMapping("/members")
    public List<DefaultMember> getAllMembers(){
        return memberRepository.findAll();
    }

    /*
        Calculate the count of different member types and pass it as a Count object
    */
    @GetMapping("/members/all")
    public Count findAllCount(){
        arrayList.clear();
        arrayList.addAll(memberRepository.findAll());
        int fCount = arrayList.size();
        int countSenior=0;
        int countStudent=0;
        int countDefault = 0;
        for (DefaultMember m : arrayList){
            if (m instanceof StudentMember){
                countStudent++;
            }
        }
        for (DefaultMember m : arrayList){
            if (m instanceof Over60Member){
                countSenior++;
            }
        }
        countDefault = fCount - (countSenior+countStudent);
        Count count = new Count(fCount,countStudent,countSenior,countDefault);
        return count;
    }

    /*
        Identify members according to their types (Default,Over60,Student) and pass separately
    */

    @GetMapping("/members/senior")
    public ArrayList<DefaultMember> getSenior(){

        seniorList.clear();
        arrayList.clear();
        arrayList.addAll(memberRepository.findAll());
        for (DefaultMember m : arrayList){
                if (m instanceof Over60Member) {
                    seniorList.add(m);
                }
        }
        return seniorList;
    }

    @GetMapping("/members/student")
    public ArrayList<DefaultMember> getStudent(){

        studentList.clear();
        arrayList.clear();
        arrayList.addAll(memberRepository.findAll());
        for (DefaultMember m : arrayList){
            if (m instanceof StudentMember){
                studentList.add(m);
            }
        }
        return studentList;
    }

    @GetMapping("/members/default")
    public ArrayList<DefaultMember> getDefault(){
        arrayList.addAll(memberRepository.findAll());
        seniorList.clear();
        studentList.clear();
        arrayList.clear();
        arrayList.addAll(memberRepository.findAll());
        for (DefaultMember m : arrayList){
            if (m instanceof Over60Member) {
                seniorList.add(m);
            }
            else if (m instanceof StudentMember){
                studentList.add(m);
            }
        }

        arrayList.removeAll(seniorList);
        arrayList.removeAll(studentList);
        return arrayList;
    }

}

