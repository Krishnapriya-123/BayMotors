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

    /**
     * Fetch the list of suppliers.
     *
     * @return List of suppliers.
     * @throws Exception If fetching suppliers fails.
     */
    public static List<Supplier> listSuppliers() throws Exception {
        try {
            return new ArrayList<>(suppliers);
        } catch (Exception e) {
            System.err.println("Error while fetching suppliers: " + e.getMessage());
            throw new Exception("Unable to fetch suppliers.", e);
        }
    }

    /**
     * Add a new supplier.
     *
     * @param supplier The supplier to add.
     * @throws Exception If adding the supplier fails.
     */
    public static void addSupplier(Supplier supplier) throws Exception {
        try {
            suppliers.add(supplier);
        } catch (Exception e) {
            System.err.println("Error while adding supplier: " + e.getMessage());
            throw new Exception("Unable to add supplier.", e);
        }
    }
}
