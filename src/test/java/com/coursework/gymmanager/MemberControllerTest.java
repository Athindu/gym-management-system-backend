package com.coursework.gymmanager;

import com.coursework.gymmanager.controller.MemberController;
import com.coursework.gymmanager.model.Date;
import com.coursework.gymmanager.model.DefaultMember;
import com.coursework.gymmanager.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberControllerTest {

    @InjectMocks
    private MemberController controller;

    @Mock
    private MemberRepository repository;

    @Test
    void getMembersTest(){

        Date date = new Date(2,3,2019);
        DefaultMember member1 = new DefaultMember("12345","Athindu",date,"male",713654892);
        DefaultMember member2 = new DefaultMember("67890","Umayanga",date,"male",713698892);

        List<DefaultMember> memberList = new ArrayList<>();
        memberList.add(member1);
        memberList.add(member2);
        System.out.println(memberList);

        when(repository.findAll()).thenReturn(memberList);

        List<DefaultMember> newList = controller.getAllMembers();
        System.out.println(newList);

        /*
        checking if the list obtained from repository is equal to created list
        */
        assertEquals(memberList,newList);

    }



}