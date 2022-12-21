package com.learn.tvschedule;

import com.learn.tvschedule.model.TelevisionScheduleException;
import com.learn.tvschedule.service.TelevisionService;
import com.learn.tvschedule.service.TelevisionServiceImpl;

import java.util.Scanner;

public class Runner {

    private static TelevisionService televisionService = new TelevisionServiceImpl();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add television");
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
            } else if (mode == 0) {
                break;
            }
        }
    }
}