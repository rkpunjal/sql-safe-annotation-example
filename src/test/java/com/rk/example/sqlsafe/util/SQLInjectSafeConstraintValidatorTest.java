package com.rk.example.sqlsafe.util;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLInjectSafeConstraintValidatorTest {

    SQLInjectionSafeConstraintValidator safeConstraintValidator = new SQLInjectionSafeConstraintValidator();

    @Test
    public void testWithBadData(){
        String[] maliciousDataSamples = {
                "select adf from abc",
                "insert into abcd",
                "update abcd",
                "delete from abcd",
                "upsert abcd",
                "call abcd",
                "rollback ",
                "create table abc",
                "drop table",
                "drop view",
                "alter table abc",
                "truncate table abc",
                "desc abc",
        };

        for(String maliciousPart : maliciousDataSamples){
            testUnSafeWithAllVariations(maliciousPart);
        }


        String[] sqlDisruptiveDataSamples = {
                "--",
                "/*",
                "*/",
                ";",
                "someone -- abcd",
                "abcd /* adf */ adf",
        };


        for(String desruptivePart : sqlDisruptiveDataSamples){
            testForPurelyUnSafeDataWithAllVariations(desruptivePart);
        }



    }

    @Test
    public void testWithGoodData(){
        String[] safeDataSamples = {
                "12",
                "abcd123",
                "123abcd",
                "abcd",
        };

        for(String safeData : safeDataSamples){
            assertTrue("Failed to qualify this as SQL-injection safe data : " + safeData,
                    safeConstraintValidator.isValid(safeData, null)
            );
        }

    }


    private void testUnSafeWithAllVariations(String maliciousPart) {
        String prefix = "some-Data-prefix";
        String suffix = "some-Data-suffix";
        String space = " ";

        String maliciousData = "";
        String safeData = "";

        maliciousData = prefix + space + maliciousPart + space + suffix;

        assertFalse("Failed to detect SQL-unsafe data : " + maliciousData,
                safeConstraintValidator.isValid(maliciousData, null)
        );

        assertFalse("Failed to detect SQL-unsafe data : " + maliciousData.toUpperCase(),
                safeConstraintValidator.isValid(maliciousData.toUpperCase(), null)
        );

        safeData = prefix + maliciousPart + suffix;

        assertTrue("Failed to qualify this as SQL-injection safe data : " + safeData,
                safeConstraintValidator.isValid(safeData, null)
        );

        safeData = removeAllSpaces(maliciousData);
        assertTrue("Failed to qualify this as SQL-injection safe data : " + safeData,
                safeConstraintValidator.isValid(safeData, null)
        );

        prefix = "";
        suffix = "";
        maliciousData = prefix + maliciousPart + suffix;

        assertFalse("Failed to detect SQL-unsafe data : " + maliciousData,
                safeConstraintValidator.isValid(maliciousData, null)
        );


        safeData = removeAllSpaces(maliciousData);
        assertTrue("Failed to qualify this as SQL-injection safe data : " + safeData,
                safeConstraintValidator.isValid(safeData, null)
        );

    }
    private void testForPurelyUnSafeDataWithAllVariations(String maliciousPart) {
        String prefix = "some-Data-prefix";
        String suffix = "some-Data-suffix";
        String space = " ";

        String maliciousData = "";
        String safeData = "";

        maliciousData = prefix + space + maliciousPart + space + suffix;

        assertFalse("Failed to detect SQL-unsafe data : " + maliciousData,
                safeConstraintValidator.isValid(maliciousData, null)
        );

        assertFalse("Failed to detect SQL-unsafe data : " + maliciousData.toUpperCase(),
                safeConstraintValidator.isValid(maliciousData.toUpperCase(), null)
        );

        assertFalse("Failed to detect SQL-unsafe data : " + removeAllSpaces(maliciousData),
                safeConstraintValidator.isValid(removeAllSpaces(maliciousData), null)
        );

        prefix = "";
        suffix = "";
        maliciousData = prefix + maliciousPart + suffix;

        assertFalse("Failed to detect SQL-unsafe data : " + maliciousData,
                safeConstraintValidator.isValid(maliciousData, null)
        );

        assertFalse("Failed to detect SQL-unsafe data : " + removeAllSpaces(maliciousData),
                safeConstraintValidator.isValid(removeAllSpaces(maliciousData), null)
        );

    }

    private String removeAllSpaces(String str){
        String pattern="[\\s|\\r|\\n|\\t]";
        String replace="";

        Pattern p= Pattern.compile(pattern);
        Matcher m=p.matcher(str);

        return m.replaceAll(replace);
    }

}


