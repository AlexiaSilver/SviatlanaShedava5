package com.epam.javacourse.homework4;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GreenhouseManagement greenhouse = new GreenhouseManagement();
        Scanner scanner = new Scanner(System.in);
        GreenhouseManagement.TemperatureSensor sensor = new GreenhouseManagement.TemperatureSensor(25.0);
        double currentTemperature = sensor.getTemperature();
        System.out.println("Current temperature: " + currentTemperature + " degrees Celsius");
        GreenhouseManagement myGreenhouse = new GreenhouseManagement();
        Plant.useGardenTool(myGreenhouse, "Watering can");

        boolean exit = false;
        do {
            System.out.println("Enter 1 to create a new plant, 2 to buy a new plant, 3 to update a plant, 4 to remove a plant, 5 to water the plants, 6 to set the temperature, 7 to display all plants, 8 to exit:");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter the name of the plant:");
                    String name = scanner.nextLine();

                    System.out.println("Enter the type of the plant (Flowering Plant, House Plant, or Shrub):");
                    String type = scanner.nextLine();

                    System.out.println("Enter the native region of the plant:");
                    String nativeRegion = scanner.nextLine();

                    switch (type) {
                        case "Flowering Plant" -> {
                            System.out.println("Please enter the flowering time (in days):");
                            int floweringTime = scanner.nextInt();
                            FloweringPlant newPlant = new FloweringPlant(name, type, nativeRegion, floweringTime);
                            greenhouse.addPlant(newPlant);
                            System.out.println("New flowering plant added successfully.");
                        }
                        case "House Plant" -> {
                            System.out.println("Does the plant need sunlight? (true/false):");
                            boolean needsSunlight = scanner.nextBoolean();
                            scanner.nextLine();
                            HousePlant newPlant = new HousePlant(name, type, nativeRegion, needsSunlight);
                            greenhouse.addPlant(newPlant);
                            System.out.println("New house plant added successfully.");
                        }
                        case "Shrub" -> {
                            System.out.println("Please enter the height (in cm):");
                            int height = scanner.nextInt();
                            scanner.nextLine();
                            Shrub newPlant = new Shrub(name, type, nativeRegion, height);
                            greenhouse.addPlant(newPlant);
                            System.out.println("New shrub added successfully.");
                        }
                        default -> System.out.println("Invalid plant type entered.");
                    }
                }
                case 2 -> {
                    System.out.println("Enter the name of the plant to buy:");
                    String name = scanner.nextLine();

                    greenhouse.buyPlant(name);
                }
                case 3 -> {
                    System.out.println("Enter the name of the plant to update:");
                    String name = scanner.nextLine();

                    Plant plantToUpdate = null;
                    for (Plant plant : greenhouse.getPlants()) {
                        if (plant.getPlantName().equals(name)) {
                            plantToUpdate = plant;
                            break;
                        }
                    }

                    if (plantToUpdate == null) {
                        System.out.println("No plant with the given name found.");
                    } else {
                        System.out.println("Enter the new name for the plant:");
                        String newName = scanner.nextLine();
                        greenhouse.updatePlant(plantToUpdate, newName);
                    }
                }
                case 4 -> {
                    System.out.println("Enter the name of the plant to remove:");
                    String name = scanner.nextLine();

                    Plant plantToRemove = null;
                    for (Plant plant : greenhouse.getPlants()) {
                        if (plant.getPlantName().equals(name)) {
                            plantToRemove = plant;
                            break;
                        }
                    }

                    if (plantToRemove == null) {
                        System.out.println("No plant with the given name found.");
                    } else {
                        greenhouse.removePlant(plantToRemove);
                        System.out.println("Plant removed successfully.");
                    }
                }
                case 5 -> {
                    greenhouse.waterPlants();
                    System.out.println("All plants have been watered.");
                }
                case 6 -> {
                    System.out.println("Enter the temperature to set:");
                    double temperature = scanner.nextDouble();
                    scanner.nextLine();

                    greenhouse.setTemperature(temperature);
                }
                case 7 -> {
                    List<Plant> allPlants = greenhouse.getPlants();
                    if (allPlants.isEmpty()) {
                        System.out.println("No plants found.");
                    } else {
                        System.out.println("All plants:");
                        for (Plant plant : allPlants) {
                            System.out.println(plant);
                        }
                    }
                }
                case 8 -> {
                    exit = true;
                    System.out.println("Exiting the program...");
                }
                default -> System.out.println("Invalid choice.");
            }
        } while (!exit);
        scanner.close();
    }
}
