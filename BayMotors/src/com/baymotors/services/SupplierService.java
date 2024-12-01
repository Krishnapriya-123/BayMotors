package com.baymotors.services;

import com.baymotors.dao.SupplierDao;
import com.baymotors.models.Supplier;

import java.util.List;

public class SupplierService {
	/**
     * List all suppliers.
     *
     * @return List of suppliers.
     */
    public static List<Supplier> listSuppliers() {
        return SupplierDao.listSuppliers();
    }

    /**
     * Add a new supplier.
     *
     * @param supplier The supplier to add.
     */
    public static void addSupplier(Supplier supplier) {
        // Business logic: Check if the supplier email already exists
        boolean emailExists = SupplierDao.listSuppliers().stream()
                .anyMatch(s -> s.getEmail() != null && s.getEmail().equalsIgnoreCase(supplier.getEmail()));

        if (emailExists) {
            throw new IllegalArgumentException("Supplier with this email already exists.");
        }

        SupplierDao.addSupplier(supplier);
    }
}
