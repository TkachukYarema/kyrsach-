package com.example.myapplication;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test

public void addTest (){
        CalcTest calc = new CalcTest(10,-5);
        try{
            calc.addNumbers(10,-5);
            if(calc.a<0){
                    throw new IllegalAccessException("cant be <0");
            }

        }catch (Exception e){
           System.out.println(e);
        }
    }

}