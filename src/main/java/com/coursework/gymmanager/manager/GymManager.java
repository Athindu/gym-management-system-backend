package com.coursework.gymmanager.manager;

import com.coursework.gymmanager.model.DefaultMember;

public interface GymManager {

    boolean addMember(DefaultMember member);
    boolean deleteMember(String membershipID);
    boolean printMembers();
    boolean save();
    boolean openGUI();
    boolean searchMember(String searchID);
}
