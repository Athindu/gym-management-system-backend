package com.coursework.gymmanager.manager;


import com.coursework.gymmanager.view.Console;
import com.coursework.gymmanager.model.DefaultMember;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;


public class MyGymManager implements GymManager {

    //ArrayList which stores Members from the repo
    private ArrayList<DefaultMember> membersArray = new ArrayList<>();

    public ArrayList<DefaultMember> getMembersArray() {
        return membersArray;
    }

    public final int MAX_MEMBERS = 100;
    public boolean addFlag;

    //ArrayList which stores all membership ids
    public ArrayList<String> membershipNumArray = new ArrayList<>();


    @Override
    public boolean addMember(DefaultMember member) {
        /*
        add members if the member number is less than 100
        */
        if (membersArray.size() < MAX_MEMBERS) {
            membersArray.add(member);
            int spaceLeft = membersArray.size();
            System.out.println(Console.ANSI_YELLOW + "\nMember added successfully..." + Console.ANSI_RESET);
            if ((MAX_MEMBERS - spaceLeft) == 0) {
                System.out.println(Console.ANSI_YELLOW + "All the member space is filled\nNo more members can be added to the system." + Console.ANSI_RESET);
            } else {
                System.out.println(Console.ANSI_YELLOW + "You can add " + (MAX_MEMBERS - spaceLeft) + " more members to the system." + Console.ANSI_RESET);
            }
            addFlag = true;
        }
        return true;
    }

    @Override
    public boolean deleteMember(String membershipID) {
        /*
        check if the input id is in the DB
        */
        checkID();
        if (membershipNumArray.contains(membershipID)) {
            for (DefaultMember member : membersArray) {

                if (member.getMembershipNumber().equals(membershipID)) {
                    membersArray.remove(member);
                    System.out.println(Console.ANSI_YELLOW + member.getClass().getSimpleName() + " removed." + Console.ANSI_RESET);
                    int spaceLeft = membersArray.size();
                    System.out.println(Console.ANSI_YELLOW + (MAX_MEMBERS - spaceLeft) + " more spots available." + Console.ANSI_RESET);
                    return true;
                }
            }
        } else {
            System.out.println(Console.ANSI_RED + "Member not found\nCheck and reenter the Membership ID" + Console.ANSI_RESET);
            return false;
        }
        return false;
    }


    @Override
    public boolean printMembers() {
        /*
        sorting the list using comparing method in Comparator interface
        */
        System.out.println(Console.ANSI_BLUE + "\n--------------Members List--------------\n" + Console.ANSI_RESET);
        Comparator<DefaultMember> comparator = Comparator.comparing(DefaultMember::getName);
        membersArray.sort(comparator);
        for (DefaultMember member : membersArray) {
            System.out.print((membersArray.indexOf(member) + 1) + ". ");
            System.out.println(member);
        }
        return true;
    }

    @Override
    public boolean save() {
        /*
        save to a txt file
        */
        try {
            FileWriter fileWriter = new FileWriter("C:\\Users\\Athindu\\Desktop\\Members_List.txt");
            StringBuilder row = new StringBuilder();
            for (DefaultMember aSavingList : membersArray) {
                row.append(aSavingList).append("\n");
            }
            fileWriter.write("-------Members-------\n\n" + row);
            fileWriter.close();
            System.out.println(Console.ANSI_YELLOW + "Data saved in the file." + Console.ANSI_RESET);
            return true;
        } catch (Exception e) {
            System.out.print(e);
            return false;
        }
    }


    @Override
    public boolean openGUI() {
        /*
        Open using google chrome
        */
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe",
                    "http://localhost:4200/");
            processBuilder.start();
            System.out.println("Browser opened.");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean searchMember(String searchID) {
        checkID();
        if (membershipNumArray.contains(searchID)) {
            for (DefaultMember member : membersArray) {
                if (member.getMembershipNumber().equals(searchID)) {
                    System.out.println("---> " + member);
                    return true;
                }
            }
        } else {
            System.out.println(Console.ANSI_RED + "Member not found\nCheck and reenter the Membership ID" + Console.ANSI_RESET);
            return false;
        }
        return false;
    }


    private void checkID() {
        /*
        insert all the membership ids to the arraylist
        */
        for (DefaultMember memberNw : membersArray) {
            String memberNum = memberNw.getMembershipNumber();
            membershipNumArray.add(memberNum);
        }
    }
}