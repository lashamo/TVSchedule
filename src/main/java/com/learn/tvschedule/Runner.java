package com.learn.tvschedule;

import com.learn.tvschedule.model.Program;
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
            System.out.println("4. Show programs television name and date");
            System.out.println("5. Show next week television programs");
            System.out.println("6. Delete program");
            System.out.println("7. Delete television");
            System.out.println("8. Show television by type");
            System.out.println("9. Show television all programs by date");
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
            } else if (mode == 4) {
                System.out.println("Please enter television name");
                String name = scanner.nextLine();
                System.out.println("Please enter date with format dd-MM-yyyy");
                String date = scanner.nextLine();
                try {
                    List<Program> programs = televisionService.getProgramsByDateAndTelevisionName(name, date);
                    for (Program k : programs) {
                        System.out.println(k);
                    }
                } catch (TelevisionScheduleException ex) {
                    System.out.println("<<Error>>" + ex.getMessage());
                }

            } else if (mode == 5) {
                System.out.println("Please enter television name");
                String name = scanner.nextLine();
                try {
                    List<Program> programs = televisionService.getAllNextWeekProgramsByTvName(name);
                    for (Program program : programs) {
                        System.out.println(program);
                    }
                } catch (TelevisionScheduleException ex) {
                    System.out.println("<<Error>>" + ex.getMessage());
                }

            } else if (mode == 6) {
                System.out.println("Please enter program ID");
                String programId = scanner.nextLine();
                try {
                    televisionService.deleteProgram(programId);
                } catch (TelevisionScheduleException ex) {
                    System.out.println("<<Error>>" + ex.getMessage());
                }

            } else if (mode == 7) {
                System.out.println("Please enter television ID");
                String id = scanner.nextLine();
                try {
                    televisionService.deleteTelevision(id);
                } catch (TelevisionScheduleException ex) {
                    System.out.println("<<Error>>" + ex.getMessage());
                }

            } else if (mode == 8) {
                System.out.println("Please enter type (KIDS, POLITIC, SPORT, MUSIC)");
                String type = scanner.nextLine();
                try {
                    List<Television> televisions = televisionService.getAllTelevisionByType(type);
                    for (Television television : televisions) {
                        System.out.println(television);
                    }
                } catch (TelevisionScheduleException ex) {
                    System.out.println("<<Error>>" + ex.getMessage());
                }

            } else if (mode == 9) {
                try {
                    List<Program> programs = televisionService.getAllTelevisionProgramByDate();
                    for (Program program : programs) {
                        System.out.println(program);
                    }
                } catch (TelevisionScheduleException ex) {
                    System.out.println("<<Error>>" + ex.getMessage());
                }

            } else if (mode == 0) {
                break;
            }
        }
    }
}