package services;

import entities.AccountInfo;
import enums.AppOrSites;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class ManagerFie {

    Scanner sc = new Scanner(System.in);

    public void CreateCli(Set<AccountInfo> cli, boolean operation,String path) {



        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, operation))) {

            for (AccountInfo a : cli) {
                bw.write(a.getAppOrSites() + "," + a.getLogin() + "," + a.getPassword());
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }


    public Set<AccountInfo> ReadFile(String path) {

        Set<AccountInfo> cli = new LinkedHashSet<>();


        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine();

            while (line != null) {


                String[] fields = line.split(",");

                if (fields.length == 3) {

                    String appOrSite = fields[0];
                    String login = fields[1];
                    String password = fields[2];

                    cli.add(new AccountInfo(AppOrSites.valueOf(appOrSite), login, password));
                }

                line = br.readLine();
            }

            cli.forEach(System.out::println);

        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        return cli;
    }

    public boolean toCheking(String path, String password) {

        try (BufferedReader br = new BufferedReader(new BufferedReader(new FileReader(path)))) {

            String line = br.readLine();

           if(line != null){
               String[] fields = line.split(",");

               if(fields[2].equals(password)){
                   return false;
               }

           }

        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return true;
    }
}
