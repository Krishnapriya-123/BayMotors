package com.baymotors.services;

import com.baymotors.dao.SupplierDao;
import com.baymotors.models.Supplier;

import java.util.List;

public class SupplierService {

    /**
     * List all suppliers.
     *
     * @return List of suppliers.
     * @throws Exception If fetching suppliers fails.
     */
    public static List<Supplier> listSuppliers() throws Exception {
        try {
            return SupplierDao.listSuppliers();
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
            // Business logic: Check if the supplier email already exists
            boolean emailExists = SupplierDao.listSuppliers().stream()
                    .anyMatch(s -> s.getEmail() != null && s.getEmail().equalsIgnoreCase(supplier.getEmail()));

            if (emailExists) {
                throw new IllegalArgumentException("Supplier with this email already exists.");
            }

            SupplierDao.addSupplier(supplier);
        } catch (IllegalArgumentException e) {
            System.err.println("Validation error: " + e.getMessage());
            throw e; // Re-throw IllegalArgumentException for validation failures
        } catch (Exception e) {
            System.err.println("Error while adding supplier: " + e.getMessage());
            throw new Exception("Unable to add supplier.", e);
        }
    }
}
