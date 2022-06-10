package com.coursework.gymmanager.view;



import java.util.Scanner;

public class Validation {

    private boolean returnVal=false;

    /*
    Method which causes an error if the input is non numeric
    */
    int nonNumeric( Scanner sc ) {
        while(!sc.hasNextInt()){
            System.out.println(Console.ANSI_RED+"\tYou entered an non numerical character!!! \n\tPlease try again... "+Console.ANSI_RESET);
            sc.nextLine();
        }
        return sc.nextInt();
    }


    /*
    check if the input date is valid
    */
    public boolean dateValidate(int year, int month, int day){

        /*
        checking for year, month and day range
        */
        //chose year range from 2012 - 2021
        if ( (year<=2012 || year>=2021) || (month<=0 || month>=13) || (day<=0 || day>=32) ){
            returnVal= false;
        }else {
            /*
            checking for leap years
            */
            if (year % 4 == 0) {
                if (month == 2) {
                    if (day < 30) {
                        returnVal = true;
                    }
                }
                else if (month==4 | month==6 | month==9 | month==11){
                    if (day < 31) {
                        returnVal = true;
                    }
                }
                else {
                    returnVal = true;
                }
            }
            /*
            non leap years
            */
            else{
                if (month ==2){
                    if (day < 29) {
                        returnVal = true;
                    }
                }
                else if (month==4 | month==6 | month==9 | month==11){
                    if (day < 31) {
                        returnVal = true;
                    }
                }
                else {
                    returnVal = true;
                }
            }
        }

        return returnVal;

    }




}