package application;

import entities.AccountInfo;
import enums.AppOrSites;
import exception.AccException;
import services.ManagerFie;

import java.util.Scanner;
import java.util.Set;

public class Program {

    public static void main(String[] args) {

        ManagerFie managerFie = new ManagerFie();
        Scanner sc = new Scanner(System.in);

        System.out.println("CREATE PATH(N) OR OPEN PATH EXIST(E) ");
        char choice = sc.next().charAt(0);
        sc.nextLine();

        managerChoice(sc, choice, managerFie);
    }

    public static boolean toCheck(Scanner sc, ManagerFie managerFie) {

        boolean loop = true;
        int attempts = 0;

        do {
            try {

                System.out.println("the password and the file:");
                System.out.print("path: ");
                String path = sc.nextLine();
                System.out.println("password: ");
                String password = sc.nextLine();

                loop = managerFie.toCheking(path, password);

                if (loop != true) {
                    openFile(path, managerFie, sc);
                }
                if (attempts == 5) {
                    return false;
                }
                attempts++;
            } catch (AccException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } while (loop);

        return loop;
    }

    public static void managerChoice(Scanner sc, char choice, ManagerFie managerFie) {

        if (choice == 'E') {
            boolean check = toCheck(sc, managerFie);

        }
    }

    public static void openFile(String path, ManagerFie managerFie, Scanner sc) {

        boolean loop = true;

        do {

            AccountInfo accountInfo = new AccountInfo();
            Set<AccountInfo> list = managerFie.ReadFile(path);

            System.out.println();
            System.out.println("Adicionar conta(1)");
            System.out.println("listar contas(2)");
            System.out.println("update senha(3)");
            System.out.println("login(4)");
            System.out.println("sair(5)");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    for (AppOrSites app : AppOrSites.values()) {
                        System.out.print(app + " ");
                    }

                    String appOrsite = sc.nextLine();
                    accountInfo.setAppOrSites(AppOrSites.valueOf(appOrsite));

                    if (list.contains(accountInfo)) {
                        System.out.println("dados a esse app ou site ja existentes");
                        break;
                    } else {

                        System.out.print("Login: ");
                        String login = sc.nextLine();
                        accountInfo.UpdateLogin(login);

                        list.add(accountInfo);
                    }

                    System.out.println();
                    break;

                case 2:

                    list.forEach(System.out::println);

                    break;

                case 3:

                    System.out.println("qual app vc quer alterar a senha");
                    String app = sc.nextLine();

                    list.forEach(a -> {
                        if (a.getAppOrSites() == AppOrSites.valueOf(app.toUpperCase())) {
                            a.UpdatePassword();
                        }
                    });

                    break;

                case 4:

                    System.out.println("qual app vc quer alterar o login");
                    String apps = sc.nextLine();
                    System.out.print("New login: ");
                    String login = sc.nextLine();

                    list.forEach(a -> {
                        if (a.getAppOrSites() == AppOrSites.valueOf(apps)) {
                            a.UpdateLogin(login);
                        }
                    });

                    break;

                case 5:

                    loop = false;
                    break;
                default:

                    System.out.println("OPCAO INEXISTENTE");
                    break;

            }

            managerFie.CreateCli(list, false, path);

        } while (loop);

    }

}