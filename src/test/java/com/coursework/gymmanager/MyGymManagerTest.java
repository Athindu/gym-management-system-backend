package com.coursework.gymmanager;

import com.coursework.gymmanager.model.Date;
import com.coursework.gymmanager.model.DefaultMember;
import com.coursework.gymmanager.manager.MyGymManager;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class MyGymManagerTest {

    private MyGymManager manager = new MyGymManager();

    @Test
    void addMemberTest(){
        /*
        test add member method
        */
        Date date = new Date(2,3,2019);
        DefaultMember member = new DefaultMember("12345","James",date,"male",713654892);
        assertTrue(manager.addMember(member));
    }

    @Test
    void deleteMemberFalseTest(){
        /*
        test if false returns when the member is not found
        */
        String memID = "2018580";
        assertFalse(manager.deleteMember(memID));
    }

    @Test
    void deleteMemberTrueTest(){
        /*
        test if a member is deleted
        */
        String memID = "2018580";
        manager.membershipNumArray.add(memID);
        Date date = new Date(2,3,2019);
        DefaultMember member = new DefaultMember(memID,"James",date,"male",713654892);
        manager.getMembersArray().add(member);
        assertTrue(manager.deleteMember(memID));
    }

    @Test
    void searchMemberFalseTest(){
        /*
        test if false returns when the member is not found
        */
        String memID = "2018580";
        assertFalse(manager.searchMember(memID));
    }

    @Test
    void searchMemberTrueTest(){
        /*
        test if a member is found when searched
        */
        String memID = "2018580";
        manager.membershipNumArray.add(memID);
        Date date = new Date(2,3,2019);
        DefaultMember member = new DefaultMember(memID,"James",date,"male",713654892);
        manager.getMembersArray().add(member);
        assertTrue(manager.searchMember(memID));
    }

    @Test
    void printTest(){
        assertTrue(manager.printMembers());
    }

    @Test
    void printAllTest(){
        /*
        test for printing the members
        */
        Date date = new Date(2,3,2019);
        DefaultMember member = new DefaultMember("12345","James",date,"male",713654892);
        DefaultMember member1 = new DefaultMember("67890","Athindu",date,"male",713698892);
        manager.addMember(member);
        manager.addMember(member1);
        assertTrue(manager.printMembers());
    }

    @Test
    void saveTest(){
        assertTrue(manager.save());
    }

    @Test
    void guiTest(){
        assertTrue(manager.openGUI());
    }

}