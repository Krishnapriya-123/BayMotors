package com.baymotors.models;

import java.util.List;

//import com.dao.TaskDao;

public class Mechanic extends Employee {
	public Mechanic() {
		super();
	}
	public Mechanic(int id, String username, String password, String firstName, String lastName, String email, String mobileNumber,
            String role, String address) {
		super(id, username, password, firstName, lastName, email, mobileNumber, role, address);
	}

	@Override
    public String performRoleSpecificTask() {
        return "Mechanic task: Repairing vehicles and completing assigned tasks.";
    }
	
	@Override
    public String displayMenu() {
        String border = "+---------------------------------------------------------------+";
        return "\n" + border + "\n" +
               "|                   Mechanic Operations Menu                    |\n" +
               border + "\n" +
               "| 1. List Tasks                                                |\n" +
               "| 2. Complete Task                                             |\n" +
               "| 3. List Manufacturers                                        |\n" +
               "| 4. Add Manufacturer                                          |\n" +
               "| 5. List Suppliers                                            |\n" +
               "| 6. Add Supplier                                              |\n" +
               "| 7. LogOut                                                    |\n" +
               border;
    }
		
}


