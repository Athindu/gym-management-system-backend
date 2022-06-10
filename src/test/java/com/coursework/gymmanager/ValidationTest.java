package com.coursework.gymmanager;

import com.coursework.gymmanager.view.Validation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {
    private Validation validation = new Validation();


    @Test
    void dateValidateTrue(){
        /*
        normal date test
        */
        assertTrue(validation.dateValidate(2019,3,2));
    }

    @Test
    void yearTest(){
        /*
        test if the year is valid
        */
        assertFalse(validation.dateValidate(2029,3,2));
    }

    @Test
    void monthTest(){
        /*
        test if the month is valid
        */
        assertFalse(validation.dateValidate(2019,13,2));
    }

    @Test
    void dayTest1(){
        /*
        test if the day is valid
        */
        assertFalse(validation.dateValidate(2019,10,35));
    }

    @Test
    void dayTest2(){
        /*
        test if the day is valid according to month
        */
        assertFalse(validation.dateValidate(2019,4,31));
    }

    @Test
    void dayTest3(){
        /*
        test if the day is valid in month February in a non leap year
        */
        assertFalse(validation.dateValidate(2019,2,29));
    }

    @Test
    void dayTest4(){
        /*
        test if the day is valid in month February in a leap year
        */
        assertTrue(validation.dateValidate(2020,2,29));
    }


}