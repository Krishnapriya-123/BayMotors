package com.baymotors.dao;

import com.baymotors.models.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierDao {
    private static final List<Supplier> suppliers = new ArrayList<>();

    static {
        suppliers.add(new Supplier(1, "ABC Supplies", "John Doe", "1234567890", "abc@example.com", 1, "123 Main Street"));
        suppliers.add(new Supplier(2, "XYZ Parts", "Jane Smith", "9876543210", "xyz@example.com", 2, "456 Elm Street"));
    }

    public static List<Supplier> listSuppliers() {
        return new ArrayList<>(suppliers);
    }

    public static void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
    }
}