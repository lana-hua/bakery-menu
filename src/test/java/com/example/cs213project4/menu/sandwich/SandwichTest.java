package com.example.cs213project4.menu.sandwich;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class SandwichTest {
    Sandwich beefLettuceTomatoCheeseSandwich;
    Sandwich chickenAllAddOnsSandwich;
    Sandwich salmonSandwichMultipleQuantities;

    @BeforeEach
    public void setUp() throws Exception {
        ArrayList<AddOns> addOns1 = new ArrayList<>(Arrays.asList(AddOns.Lettuce, AddOns.Tomato, AddOns.Cheese));
        beefLettuceTomatoCheeseSandwich = new Sandwich(1, Bread.Wheat, Protein.Beef, addOns1);

        ArrayList<AddOns> addOns2 = new ArrayList<>(Arrays.asList(AddOns.Cheese, AddOns.Lettuce, AddOns.Onion, AddOns.Tomato));
        chickenAllAddOnsSandwich = new Sandwich(1, Bread.Sourdough, Protein.Chicken, addOns2);

        ArrayList<AddOns> addOns3 = new ArrayList<>();
        salmonSandwichMultipleQuantities = new Sandwich(3, Bread.Bagel, Protein.Salmon, addOns3);
    }

    @Test
    public void testBeefSandwichWithCheeseAndLettuce() {
        // Beef ($12.99) + Lettuce ($0.30) + Tomato ($0.30) + Cheese ($1.00) = $14.59
        assertEquals(14.59, beefLettuceTomatoCheeseSandwich.price(), 0.01);
    }

    @Test
    public void testChickenAllAddOnsSandwich(){
        // Chicken ($10.99) + Cheese ($1.00) + Lettuce ($0.30) + Onion ($0.30) + Tomato ($0.30) = $12.89
        assertEquals(12.89, chickenAllAddOnsSandwich.price(), 0.01);
    }

    @Test
    public void testSalmonSandwichMultipleQuantities() {
        // Salmon ($14.99) * 3 Quantities = $44.97
        assertEquals(44.97, salmonSandwichMultipleQuantities.price(), 0.01);
    }
}