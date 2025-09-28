package entities;

import enums.AppOrSites;

import java.util.Objects;
import java.util.Scanner;

public class AccountInfo {

    Scanner sc = new Scanner(System.in);

    private AppOrSites appOrSites;
    private String login;
    private String password;

    public AccountInfo() {
    }

    public AccountInfo(AppOrSites appOrSites, String login, String password) {
        this.appOrSites = appOrSites;
        this.login = login;
        this.password = password;
    }

    public AppOrSites getAppOrSites() {
        return appOrSites;
    }

    public void setAppOrSites(AppOrSites appOrSites) {
        this.appOrSites = appOrSites;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void UpdateLogin(String login) {

        boolean loop = true;
        do {

            System.out.println("Create the password to login");
            String password = sc.nextLine();

            System.out.println("repeat the password again ");
            String repPassword = sc.nextLine();

            if (password.equals(repPassword)) {

                this.login = login;
                System.out.println("login changed successfully");
                loop = false;

            } else {
                System.out.println("passwords cannot be different, try againpasswords cannot be different, try again");
            }

        } while (loop);
    }

    public void UpdatePassword() {

        boolean loop = true;
        do {

            System.out.println("Create the password to login");
            String password = sc.nextLine();

            System.out.println("repeat the password again ");
            String repPassword = sc.nextLine();

            if (password.equals(repPassword)) {

                this.password = password;
                System.out.println("password changed successfully");
                loop = false;

            } else {
                System.out.println("passwords cannot be different, try againpasswords cannot be different, try again");
            }

        } while (loop);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AccountInfo that = (AccountInfo) o;
        return appOrSites == that.appOrSites;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(appOrSites);
    }

    @Override
    public String toString() {
        return "APP/SITE (" + appOrSites + ") login: " + login + ", password: " + password;
    }
}