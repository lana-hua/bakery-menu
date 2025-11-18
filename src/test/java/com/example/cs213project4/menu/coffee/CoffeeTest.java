package com.example.cs213project4.menu.coffee;

import com.example.cs213project4.menu.sandwich.AddOns;
import com.example.cs213project4.menu.sandwich.Bread;
import com.example.cs213project4.menu.sandwich.Protein;
import com.example.cs213project4.menu.sandwich.Sandwich;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeTest {
    Coffee shortCoffeeNoAddIns;
    Coffee ventiCoffeeAllAddIns;
    Coffee tallCoffeeMultipleQuantities;

    @BeforeEach
    public void setUp() throws Exception {
        ArrayList<AddIns> addIns1 = new ArrayList<>();
        shortCoffeeNoAddIns = new Coffee(1, CupSize.Short, addIns1);

        ArrayList<AddIns> addIns2 = new ArrayList<>(Arrays.asList(AddIns.Mocha, AddIns.Caramel, AddIns.Vanilla, AddIns.Cream, AddIns.Milk));
        ventiCoffeeAllAddIns = new Coffee(1, CupSize.Venti, addIns2);

        ArrayList<AddIns> addIns3 = new ArrayList<>();
        tallCoffeeMultipleQuantities = new Coffee(3, CupSize.Tall, addIns3);
    }

    @Test
    public void testShortCoffeeNoAddIns() {
        // Short ($2.39) = $2.39
        assertEquals(2.39, shortCoffeeNoAddIns.price(), 0.01);
    }

    @Test
    public void testVentiCoffeeAllAddIns(){
        // Venti ($4.19) + Whipped Cream ($0.25) + Milk ($0.25) + Vanilla ($0.25) + Caramel ($0.25) + Mocha ($0.25) = $5.44
        assertEquals(5.44, ventiCoffeeAllAddIns.price(), 0.01);
    }

    @Test
    public void testTallCoffeeMultipleQuantities() {
        // Tall ($2.99) * 3 Quantities = $8.97
        assertEquals(8.97, tallCoffeeMultipleQuantities.price(), 0.01);
    }

}