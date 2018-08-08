package register;

import register.persist.RegisterDbStorage;
import register.persist.RegisterFileStorage;
import register.persist.RegisterStorage;

/**
 * Created by jaro on 3.2.2014.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        //Register register = new ArrayRegister(3);
    	RegisterStorage storage = new RegisterDbStorage();

    	Register register = storage.load();

    	if (register.getCount() == 0) {
			register.addPerson(new Person("Janko Hrasko", "0900123456"));
	    	register.addPerson(new Person("Jurko", "546548"));
	    	register.addPerson(new Person("Luki", "545844"));
    	}
    	
        ConsoleUI ui = new ConsoleUI(register);
        ui.run();
        
        storage.save(register);
    }
}