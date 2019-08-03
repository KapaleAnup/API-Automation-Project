package Utils;

import CommanClass.BaseClass;
import org.apache.commons.lang3.RandomStringUtils;

public class RandomDataGenerator extends BaseClass {

    //RandomStringUtils class will help to generate random deatails such in Alphabetic, numeric, alpha numeric details.

    public static String getName(){
        String Employeename = RandomStringUtils.randomAlphabetic(4);
        return Employeename;

    }

    public static String getAge(){
        String EmployeeAge = RandomStringUtils.randomNumeric(2);
        return EmployeeAge;
    }

    public static String getSalary(){
      String Employeesalary =  RandomStringUtils.randomNumeric(5);
        return Employeesalary;
    }
}
