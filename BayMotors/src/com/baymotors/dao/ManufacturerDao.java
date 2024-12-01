package com.baymotors.dao;

import com.baymotors.models.Manufacturer;

import java.util.ArrayList;
import java.util.List;

public class ManufacturerDao {
    private static final List<Manufacturer> manufacturers = new ArrayList<>();

    static {
        manufacturers.add(new Manufacturer(1, "Toyota", "Japan"));
        manufacturers.add(new Manufacturer(2, "Ford", "USA"));
    }

    public static List<Manufacturer> listManufacturers() {
        return new ArrayList<>(manufacturers);
    }

    public static void addManufacturer(Manufacturer manufacturer) {
        manufacturers.add(manufacturer);
    }
}
