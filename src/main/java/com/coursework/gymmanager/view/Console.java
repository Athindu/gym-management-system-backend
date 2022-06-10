package com.coursework.gymmanager.view;

import com.coursework.gymmanager.model.Date;
import com.coursework.gymmanager.model.DefaultMember;
import com.coursework.gymmanager.model.Over60Member;
import com.coursework.gymmanager.model.StudentMember;
import com.coursework.gymmanager.repository.MemberRepository;
import com.coursework.gymmanager.manager.MyGymManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

@Component
public class Console {

    private MyGymManager manager = new MyGymManager();
    private Scanner sc = new Scanner(System.in);

    /*
    ArrayList which stores membership ids to avoid duplicate entries when adding members
    */
    private ArrayList<String> duplicateEntry = new ArrayList<>();
    private Date date ;

    @Autowired
    private MemberRepository memberRepository;

    private Validation validation = new Validation();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_CYAN = "\u001B[36m";


    public void welcome(){
        /*
        Get all the members from DB and insert members and their ids to lists
        */
        for (DefaultMember member : memberRepository.findAll()) {
            manager.getMembersArray().add(member);
            String s = member.getMembershipNumber();
            duplicateEntry.add(s);
        }

        System.out.println(ANSI_BLUE+"\n===== Welcome to Gym Management System ====="+ANSI_RESET);
        menu();
    }

    void menu(){
        int option;

        do {
            System.out.println(ANSI_CYAN+"\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+ANSI_RESET);
            System.out.println("Please choose an option: ");
            System.out.println("\t1) Add Member");
            System.out.println("\t2) Delete Member");
            System.out.println("\t3) Print list of Members");
            System.out.println("\t4) Save in a file");
            System.out.println("\t5) Search Members");
            System.out.println("\t6) Open GUI");
            System.out.println("\t7) Exit");
            System.out.println(ANSI_CYAN+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+ANSI_RESET);
            System.out.print(">");

            option = validation.nonNumeric(sc);

            switch (option) {
                case 1:
                    if (manager.getMembersArray().size()<manager.MAX_MEMBERS) {
                        addMem();
                    }
                    else {
                        System.out.println(ANSI_RED+"Sorry can not add more members.\nMaximum number reached."+ANSI_RESET);
                    }
                    menu();
                    break;

                case 2:
                    deleteMem();
                    menu();
                    break;
                case 3:
                    printList();
                    menu();
                    break;
                case 4:
                    saveMem();
                    menu();
                    break;
                case 5:
                    searchMem();
                    menu();
                    break;
                case 6:
                    interfaceM();
                    menu();
                    break;
                case 7:
                    System.out.println(ANSI_BLUE+"\tBye, See you later!!!!\n"+ANSI_RESET);
                    System.exit(0);
                    break;
                default:
                    System.out.println(ANSI_RED+"\tInvalid number, Please try again..."+ANSI_RESET);
                    break;
            }
        }
        /*
        loop while option is in the range
        */
        while (option < 1 | option> 7);
    }


    private void addMem(){
        System.out.println("Select Member Category:");
        System.out.println("\t1. Default Member");
        System.out.println("\t2. Student Member");
        System.out.println("\t3. Over 60 Member");
        System.out.print(">");
        int category = validation.nonNumeric(sc);

        /*
        check if the option is in the range
        */
        if (category<1 | category>3){
            System.out.println(ANSI_RED+"\tInvalid number, Please try again...\n"+ANSI_RESET);
            addMem();
            menu();
        }

        System.out.println("Enter Membership no :");
        String membershipNum = sc.next();

        isAvailable(membershipNum);     //check for duplicate membership ids


        System.out.println("Enter name: ");
        String name = sc.next();
        System.out.println("Enter membership start date ");


        /*
        loop while date is valid
        */
        while (true) {
            System.out.println("Year: ");
            int year = validation.nonNumeric(sc);
            System.out.println("Month: ");
            int month = validation.nonNumeric(sc);
            System.out.println("Day: ");
            int day = validation.nonNumeric(sc);

            /*
            return true if the date is valid
            */
            if (validation.dateValidate(year, month, day)) {
                date = new Date(day, month, year);
                break;
            }
            else {
                System.out.println(ANSI_RED+"\tDate is not valid !!!\n\tPlease try again...\n"+ANSI_RESET);
            }
        }

        System.out.println("Enter member's gender: ");
        String genderM = sc.next();

        System.out.println("Enter the contact number: ");
        int contact = validation.nonNumeric(sc);
        duplicateEntry.add(membershipNum);
        if (category==1){

            DefaultMember member = new DefaultMember(membershipNum,name,date,genderM,contact);
            manager.addMember(member);
            if (manager.addFlag){
                memberRepository.save(member);  //save the member to the DB
            }

        }

        else if (category==2){

            System.out.println("Enter the School Name: ");
            String school = sc.next();
            System.out.println("Enter the Grade: ");
            String grade = sc.next();
            System.out.println("Enter the Guardian Name: ");
            String guardian = sc.next();

            DefaultMember member = new StudentMember(membershipNum,name,date,genderM,contact,school,grade,guardian);
            manager.addMember(member);
            ((StudentMember) member).setNameAAA("aa");

            if (manager.addFlag){
                memberRepository.save(member);
            }
        }

        else if (category==3){

            System.out.println("Enter the Age: ");
            int age = validation.nonNumeric(sc);
            System.out.println("Enter the NIC number: ");
            String nic = sc.next();
            System.out.println("Enter the occupation status: (Retired or Working)");
            String job = sc.next();

            DefaultMember member = new Over60Member(membershipNum,name,date,genderM,contact,age,nic,job);
            manager.addMember(member);
            if (manager.addFlag){
                memberRepository.save(member);
            }
        }
    }

    private void isAvailable(String membershipNum){
        /*
        check for duplicate entries
        */
        if (duplicateEntry.contains(membershipNum)){
            System.out.println(ANSI_RED+"\tDuplicate data entry...\n"+ANSI_RESET);
            menu();
        }
    }


    private void deleteMem(){
        /*
        Check if the DB is empty
        */
        isEmpty();
        System.out.println("Enter the member's Membership Number: ");
        String delMember = sc.next();

        for (DefaultMember member : manager.getMembersArray()){
            if (member.getMembershipNumber().equals(delMember)){
                memberRepository.delete(member);        //delete member from DB
                break;
            }
        }

        manager.deleteMember(delMember);
        duplicateEntry.remove(delMember);
    }


    private void saveMem(){
        isEmpty();
        System.out.println(ANSI_YELLOW+"Details are writing to the file...\\Desktop\\Members_List.txt"+ANSI_RESET);
        manager.save();

        /*
        open the saved txt file using notepad according to the user input
        */
        while (true) {
            System.out.println("Do you want to view the file ? ( Y / N )");
            String viewOption = sc.next();
            if (viewOption.toUpperCase().equals("Y")) {
                try {
                    System.out.println("Application opening...");
                    ProcessBuilder processBuilder = new ProcessBuilder(
                            "Notepad.exe",
                            "C:\\Users\\Athindu\\Desktop\\Members_List.txt");
                    processBuilder.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            } else if (viewOption.toUpperCase().equals("N")) {
                break;
            } else {
                System.out.println(ANSI_RED + "\tWrong Option!!!\n\tChoose again correctly." + ANSI_RESET);
            }
        }
    }


    private void printList(){
        isEmpty();
        manager.printMembers();
    }


    private void interfaceM(){
        System.out.println(ANSI_YELLOW+"Opening the application..."+ANSI_RESET);
        manager.openGUI();
    }

    private void searchMem(){
        isEmpty();
        System.out.println("Enter the membership ID to search: ");
        String searchID = sc.next();
        manager.searchMember(searchID);
    }

    /*
    Method which checks if the list is empty
    */
    private void isEmpty(){
        if (manager.getMembersArray().isEmpty()){
            System.out.println(ANSI_YELLOW+"\tNo members in the system.\n\tAdd members to the system from the menu..."+ANSI_RESET);
            menu();
        }
    }

}
