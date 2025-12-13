import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        runApp(scanner);
        scanner.close();
    }

    static void runApp(Scanner scanner) {
        String userName = "unknown";

        while (true) {
            printMenu(userName);
            String choice = scanner.nextLine().trim().toLowerCase();

            switch (choice) {
                case "q":
                case "quit":
                case "exit":
                    System.out.println("Goodbye!");
                    return;
                case "1":
                    userName = greetUser(scanner);
                    break;
                case "2":
                    if (!portalCalculator(scanner)) {
                        System.out.println("Goodbye!");
                        return;
                    }
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    static boolean portalCalculator(Scanner scanner) {
        while (true) {
            System.out.println("=== Portal Calculators ===");
            System.out.println("1: Overworld to Nether coordinates");
            System.out.println("2: Nether to Overworld coordinates");
            System.out.println("3: Main menu");
            System.out.println("Q: Quit");
            String choice = scanner.nextLine().trim().toLowerCase();
            switch (choice) {
                case "q":
                case "quit":
                case "exit":
                    return false;
                case "1": {
                    System.out.println("Overworld to Nether conversion");
                    Coordinate overworldCoord = getCoordinates(scanner, "Overworld");
                    Coordinate netherCoord = overworldToNether(overworldCoord);
                    System.out.println("Your Overworld X coordinate " + overworldCoord.x + " links to " + netherCoord.x + " in the nether.");
                    System.out.println("Your Overworld Z coordinate " + overworldCoord.z + " links to " + netherCoord.z + " in the nether.");
                    break;
                }
                case "2": {
                    System.out.println("Nether to Overworld conversion");
                    Coordinate netherCoord = getCoordinates(scanner, "Nether");
                    Coordinate overworldCoord = netherToOverworld(netherCoord);
                    System.out.println("Your Nether X coordinate " + netherCoord.x + " links to " + overworldCoord.x + " in the overworld.");
                    System.out.println("Your Nether Z coordinate " + netherCoord.z + " links to " + overworldCoord.z + " in the overworld.");
                    break;
                }
                case "3":
                    return true;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    static Coordinate getCoordinates(Scanner scanner, String label) {
        double x = readDouble(scanner, label + " X coordinate: ");
        double z = readDouble(scanner, label + " Z coordinate: ");
        return new Coordinate(x, z);
    }

    static Coordinate netherToOverworld(Coordinate coord) {
        double overworldX = coord.x * 8;
        double overworldZ = coord.z * 8;
        return new Coordinate(overworldX, overworldZ);
    }

    static Coordinate overworldToNether(Coordinate coord) {
        double netherX = Math.floor(coord.x / 8);
        double netherZ = Math.floor(coord.z / 8);
        return new Coordinate(netherX, netherZ);
    }

    static String greetUser(Scanner scanner) {
        while (true) {
            System.out.println("What's your name? ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Try again: ");
            } else {
                System.out.println("Hello, " + name + "!");
                return name;
            }
        }
    }

    static void printMenu(String userName) {
        System.out.println("=== Minecraft Utility Box for " + userName + "===");
        System.out.println("1. Greet user");
        System.out.println("2: Minecraft Calculator");
        System.out.println("q. Quit");
        System.out.print("Choose an option: ");
    }

    static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);

            if (!scanner.hasNextDouble()) {
                String badInput = scanner.nextLine();
                System.out.println("'" + badInput + "' is not valid. Try again.");
                continue;
            }

            double value = scanner.nextDouble();
            scanner.nextLine();
            return value;
        }
    }

}