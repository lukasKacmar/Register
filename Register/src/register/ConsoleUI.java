package register;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * User interface of the application.
 */
public class ConsoleUI {
	/** register.Register of persons. */
	private Register register;

	/**
	 * In JDK 6 use Console class instead.
	 * 
	 * @see readLine()
	 */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Menu options.
	 */
	private enum Option {
		PRINT, ADD, UPDATE, REMOVE, FIND, EXIT
	};

	public ConsoleUI(Register register) {
		this.register = register;
	}

	public void run() {
		while (true) {
			switch (showMenu()) {
			case PRINT:
				printRegister();
				break;
			case ADD:
				if (register.getSize() == register.getCount())
					System.err.println("Can't add person, register is full!");
				else
					addToRegister();
				break;
			case UPDATE:
				updateRegister();
				break;
			case REMOVE:
				removeFromRegister();
				break;
			case FIND:
				findInRegister();
				break;
			case EXIT:
				return;
			}
		}
	}

	private String readLine() {
		// In JDK 6.0 and above Console class can be used
		// return System.console().readLine();

		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	private Option showMenu() {
		System.out.println("Menu.");
		for (Option option : Option.values()) {
			System.out.printf("%d. %s%n", option.ordinal() + 1, option);
		}
		System.out.println("-----------------------------------------------");

		int selection = -1;
		do {
			System.out.println("Option: ");
			try {
				selection = Integer.parseInt(readLine());
			} catch (NumberFormatException e) {
				System.err.println("Invalid index, enter only numbers you can see in menu!");
			}
		} while (selection <= 0 || selection > Option.values().length);

		return Option.values()[selection - 1];
	}

	private void printRegister() {
		int order = 1;
		for (int i = 0; i < register.getCount(); i++) {
			System.out.println(order + ". " + register.getPerson(i));
			order++;
		}
	}

	private void addToRegister() {
		System.out.println("Enter Name: ");
		String name = readLine();
		boolean nameNotFound = true;
		for (int i = 0; i < register.getCount(); i++) {
			if (register.getPerson(i).getName().equals(name)) {
				System.err.println("Entered name already exist, please add new person again with different name!");
				nameNotFound = false;
			}
		}

		if (nameNotFound) {
			System.out.println("Enter Phone Number: ");
			String phoneNumber = readLine();
			boolean phoneNumberNotFound = true;
			for (int i = 0; i < register.getCount(); i++) {
				if (register.getPerson(i).getPhoneNumber().equals(phoneNumber)) {
					System.err.println(
							"Entered phone number already exist, please add new person again with different phone number!");
					phoneNumberNotFound = false;
				}
			}
			if (nameNotFound && phoneNumberNotFound)
				try {
					register.addPerson(new Person(name, phoneNumber));
				} catch (InvalidPhoneNumberException e) {
					System.err.println("Invalid phone number, enter only digits!");
				}
		}

	}

	private void updateRegister() {
		try {
			System.out.println("Enter index: ");
			int index = Integer.parseInt(readLine());

			if (index > 0 && index <= register.getCount()) {
				System.out.println("Enter Name: ");
				String newName = readLine();
				System.out.println("Enter Phone Number: ");
				String newPhoneNumber = readLine();
				Person p = register.getPerson(index - 1);
				p.setName(newName);
				if (newPhoneNumber.matches("[0-9]+")) {
					p.setPhoneNumber(newPhoneNumber);
				} else
					System.err.println("Invalid phone number!");
			} else
				System.err.println("Invalid index!");
		} catch (NumberFormatException e) {
			System.err.println("Invalid index, enter digits only!");
		}
	}

	private void findInRegister() {
		try {
			System.out.println("Enter index:\n1. Find by name\n2. Find by phone number ");
			int index = Integer.parseInt(readLine());
			if (index > 0 && index <= register.getCount()) {
				switch (index) {
				case 1: {
					System.out.println("Enter name: ");
					String name = readLine();
					Person p = register.findPersonByName(name);
					if (p == null)
						System.err.println("Person does not exist!");
					else
						System.out.println(p);
				}
					break;
				case 2: {
					System.out.println("Enter phone number: ");
					String phoneNumber = readLine();
					Person p = register.findPersonByPhoneNumber(phoneNumber);
					if (p == null)
						System.err.println("Phone number does not exist!");
					else
						System.out.println(register.findPersonByPhoneNumber(phoneNumber));
				}
					break;
				}
			} else
				System.err.println("Invalid index!");
		} catch (NumberFormatException e) {
			System.err.println("Invalid index, enter digits only!");
		}
	}

	private void removeFromRegister() {
		try {
			System.out.println("Enter index: ");
			int index = Integer.parseInt(readLine());
			if (index > 0 && index <= register.getCount()) {
				Person person = register.getPerson(index - 1);
				register.removePerson(person);
			} else
				System.err.println("Invalid index!");
		} catch (NumberFormatException e) {
			System.err.println("Invalid index, enter digits only!");
		}
	}
}
