package register;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Person person = new Person("Janko", "123");
		String file = "person.bin";
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(person);
		}
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			person = (Person)ois.readObject();
			System.out.println(person);
		}
	}
}
