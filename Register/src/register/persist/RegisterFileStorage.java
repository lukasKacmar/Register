package register.persist;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import register.ListRegister;
import register.Person;
import register.Register;

public class RegisterFileStorage implements RegisterStorage {
	private static final String FILE_NAME = "register.out"; 
	
	@Override
	public void save(Register register) {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
			List<Person> persons = new ArrayList<>();
			for (int i=0; i<register.getCount(); i++) {
				persons.add(register.getPerson(i));
			}
			oos.writeObject(persons);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public Register load() {
		Register register = new ListRegister();
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
			List<Person> persons = (List<Person>)ois.readObject();
			for (Person person : persons) {
				register.addPerson(person);
			}
		} catch (IOException | ClassCastException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return register;
	}

}
