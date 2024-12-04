package com.baymotors.models;

import java.util.List;

//import com.dao.TaskDao;

public class Manager extends Employee{
	public Manager() {
		super();
	}
	public Manager(int id, String username, String password, String firstName, String lastName, String email, String mobileNumber,
            String role, String address) {
		super(id, username, password, firstName, lastName, email, mobileNumber, role, address);
	}
	
	@Override
    public String performRoleSpecificTask() {
        return "Manager task: Overseeing operations and assigning tasks.";
    }
	
	@Override
    public String displayManagerMenu() {
        String border = "+---------------------------------------------------------------+";
        return "\n" + border + "\n" +
               "|                   Manager Operations Menu                    |\n" +
               border + "\n" +
               "| 1. List Mechanics                                             |\n" +
               "| 2. Add Mechanic                                               |\n" +
               "| 3. List Customers                                             |\n" +
               "| 4. Add Customer                                               |\n" +
               "| 5. Register Customer                                          |\n" +
               "| 6. List Vehicles                                              |\n" +
               "| 7. Log Vehicle                                                |\n" +
               "| 8. List Tasks                                                 |\n" +
               "| 9. Add Task                                                   |\n" +
               "| 10. Notify Customers                                          |\n" +
               "| 11. List Manufacturers                                        |\n" +
               "| 12. Add Manufacturer                                          |\n" +
               "| 13. List Suppliers                                            |\n" +
               "| 14. Add Supplier                                              |\n" +
               "| 15. LogOut                                                    |\n" +
               border;
    }

    @Override
    public String displayMechanicMenu() {
        return "Managers cannot access the Mechanic menu.";
    }
	
}

