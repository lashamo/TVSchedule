package com.learn.tvschedule;

import com.learn.tvschedule.model.Television;
import com.learn.tvschedule.model.TelevisionScheduleException;
import com.learn.tvschedule.service.TelevisionService;
import com.learn.tvschedule.service.TelevisionServiceImpl;

import java.util.List;
import java.util.Scanner;

public class Runner {

    private static TelevisionService televisionService = new TelevisionServiceImpl();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add television");
            System.out.println("2. Show all television");
            System.out.println("3. Add program");
            System.out.println("0. Exit");
            int mode = Integer.parseInt(scanner.nextLine());

            if (mode == 1) {
                System.out.println("Please enter television name");
                String televisionName = scanner.nextLine();
                System.out.println("Please enter type (KIDS, POLITIC, SPORT, MUSIC)");
                String type = scanner.nextLine();
                System.out.println("Please enter founded year");
                int foundedYear = Integer.parseInt(scanner.nextLine());
                try {
                    televisionService.addTelevision(televisionName, type, foundedYear);
                } catch (TelevisionScheduleException ex) {
                    System.out.println("<<Error>> " + ex.getMessage());
                }
            } else if (mode == 2) {
                try {
                    List<Television> televisions = televisionService.getAllTelevision();
                    for (Television television : televisions) {
                        System.out.println(television);
                    }
                } catch (TelevisionScheduleException ex) {
                    System.out.println("<<Error>> " + ex.getMessage());
                }
            } else if (mode == 3) {
                System.out.println("Please enter program name");
                String programName = scanner.nextLine();
                System.out.println("Please enter date with format dd-MM-yyyy");
                String date = scanner.nextLine();
                System.out.println("Please enter start time with format HH:mm");
                String startTime = scanner.nextLine();
                System.out.println("Please enter end time with format HH:mm");
                String endTime = scanner.nextLine();
                System.out.println("please enter television ID");
                int televisionID = Integer.parseInt(scanner.nextLine());
                try {
                    televisionService.addProgram(programName, date, startTime, endTime, televisionID);
                } catch (TelevisionScheduleException ex) {
                    System.out.println("<<Error>> " + ex.getMessage());
                }
            } else if (mode == 0) {
                break;
            }
        }
    }
}