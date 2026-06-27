package FunctionalProgramming;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ToolBooth {

    public static void main(String[] args) {
        TollOps tollOps = new TollOps();
        List<Vehicle> vehicleList = tollOps.getAllVehicleDetails();
        tollOps.printVehicleDetails();
        tollOps.updateJourney("KA-15-3011", VehicleStatus.ENTRY, LocalDate.of(2026, 6, 27));
        tollOps.updateJourney("DL-05-3363", VehicleStatus.EXIT, LocalDate.of(2026, 6, 28));
    }
}

enum VehicleStatus {
    ENTRY,
    EXIT,
    MAIN_ROAD
}

class Vehicle {
    String regNumber;
    int balance;

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDate getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDate exitDate) {
        this.exitDate = exitDate;
    }

    LocalDate entryDate;
    LocalDate exitDate;
    VehicleStatus vehicleStatus;

    public Vehicle(String regNumber, int balance, VehicleStatus vehicleStatus) {
        this.regNumber = regNumber;
        this.balance = balance;
        this.vehicleStatus = vehicleStatus;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(VehicleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }
}

class TollOps {

    List<Vehicle> vehicleList = List.of(
            new Vehicle("MH-05-2022", 1000, VehicleStatus.ENTRY),
            new Vehicle("KA-15-3011", 150, VehicleStatus.EXIT),
            new Vehicle("DL-05-3363", 2000, VehicleStatus.MAIN_ROAD),
            new Vehicle("KA-05-4375", 500, VehicleStatus.MAIN_ROAD)
    );


    public List<Vehicle> getAllVehicleDetails() {
        return vehicleList;
    }

    public void printVehicleDetails() {
        System.out.println("Printing vehicle details -> ");
        vehicleList.forEach(vehicle -> {
            System.out.println("RegNumber -> " + vehicle.getRegNumber());
            System.out.println("Balance -> " + vehicle.getBalance());
            System.out.println("Running-Status -> " + vehicle.getVehicleStatus());
            System.out.println("====================================");
        });
    }

    public void updateJourney(String regNumber, VehicleStatus vehicleStatus, LocalDate date) {
        int entryJourneyCost = 120;
        int exitJourneyCost = 20;
        int exitJourneyCostNextDay = 40;

        if (vehicleStatus.equals(VehicleStatus.ENTRY)) {
            vehicleList.stream()
                    .filter(vehicle -> Objects.equals(vehicle.getRegNumber(), regNumber))
                    .forEach(vehicle -> {
                        vehicle.setVehicleStatus(VehicleStatus.MAIN_ROAD);
                        vehicle.setBalance((vehicle.getBalance() - entryJourneyCost));
                    });
        } else if (vehicleStatus.equals(VehicleStatus.EXIT)) {
            vehicleList.stream()
                    .filter(vehicle -> Objects.equals(vehicle.getRegNumber(), regNumber))
                    .forEach(vehicle -> {
                        vehicle.setVehicleStatus(vehicleStatus);

                        if (((vehicle.getBalance() - exitJourneyCost) < vehicle.getBalance()) && ((vehicle.getBalance() - exitJourneyCostNextDay) < vehicle.getBalance())) {
                            System.out.println("Error!! Balance not sufficient, Please recharge");
                        }

                        if (LocalDate.now().equals(date)) {
                            vehicle.setBalance((vehicle.getBalance() - exitJourneyCost));
                        } else {
                            vehicle.setBalance((vehicle.getBalance() - exitJourneyCostNextDay));
                        }

                    });
        }

        this.printDetailsForVehicle(regNumber);
    }

    public void printDetailsForVehicle(String regNumber) {
        vehicleList.stream().filter(vehicle -> Objects.equals(vehicle.getRegNumber(), regNumber))
                .forEach(vehicle -> {
                    System.out.println("RegNumber -> " + vehicle.getRegNumber());
                    System.out.println("Updated Balance -> " + vehicle.getBalance());
                    System.out.println("Running-Status -> " + vehicle.getVehicleStatus());
                    System.out.println("====================================");
                });
    }

}

