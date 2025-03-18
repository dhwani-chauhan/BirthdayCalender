package com;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Scanner;

public class BirthdayCalender {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        do{
            LocalDate birthDate;
            DateTimeFormatter fullFormat = DateTimeFormatter.ofPattern("MMM d,yyyy");
            DateTimeFormatter monthFormat = DateTimeFormatter.ofPattern("MMM d");
            System.out.println("Today is" + LocalDate.now().format(fullFormat) + ".");
            System.out.println();
            System.out.print("Please enter your birthdate" + "(yyyy-mm-dd):");
            String input = scanner.nextLine();
            try{
                birthDate = LocalDate.parse(input);
                if (birthDate.isAfter(LocalDate.now())){
                    System.out.println("You haven't been born yet!");
                    continue;
                }
                System.out.println(birthDate.format(fullFormat) + " was a good day!");

                DayOfWeek dayWeek = birthDate.getDayOfWeek();
                System.out.println("You were born on a " + dayWeek + ".");

                long years = birthDate.until(LocalDate.now(), ChronoUnit.YEARS);
                System.out.println("You are " + years + "years young.");

                LocalDate nextBirthday = birthDate.plusYears(years + 1);
                System.out.println("Your next birthday is " + nextBirthday.format(fullFormat) + ".");

                long wait = LocalDate.now().until(nextBirthday,ChronoUnit.DAYS);
                System.out.println("That's just " + wait + " days from now!");

                LocalDate halfBirthday = birthDate.plusMonths(6);
                System.out.println("Your half birthday is " + halfBirthday.format(monthFormat) + ".");

            }catch (DateTimeParseException e){
                System.out.println("Sorry, not a valid date.");
            }
        }while (askAgain());
    }

    private static boolean askAgain(){
        System.out.println();
        System.out.print("Another? (Y or N)");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("Y");
    }
}