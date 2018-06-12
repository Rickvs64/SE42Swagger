package io.swagger.client;

import io.swagger.client.api.PetApi;
import io.swagger.client.api.StoreApi;
import io.swagger.client.api.UserApi;
import io.swagger.client.model.Pet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Testerdetest {

    private static PetApi petApi;
    private static StoreApi storeApi;
    private static UserApi userApi;

    private static void createPet() {
        show("Let's create a new pet.");
        show("What'll be its name?");
        String newPetName = input();

        showBr();

        show("Creating a new pet called " + newPetName);
        Pet newPet = new Pet();
        newPet.setName(newPetName);
        try {
            petApi.addPet(newPet);
        }
        catch (ApiException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void findAll() {
        show("Now, let's retrieve every single pet.");
        List<String> statuses = new ArrayList<String>();
        statuses.add("available");
        statuses.add("pending");
        statuses.add("sold");

        List<Pet> pets = new ArrayList<Pet>();

        try {
            pets = petApi.findPetsByStatus(statuses);
        }
        catch (ApiException e) {
            e.printStackTrace();
            System.exit(1);
        }

        for (Pet currPet: pets) {
            show(currPet.getName());
        }

    }

    public static void main(String[] args) {
        show("Launching Swagger example application...");
        petApi = new PetApi();
        storeApi = new StoreApi();
        userApi = new UserApi();

        showBr();

        createPet();
        show("New pet has been created.");

        showBr();

        findAll();

    }

    private static void show(String message) {
        System.out.println(message);
    }

    private static void showBr() {
        System.out.println();
        System.out.println();
    }

    private static String input() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
