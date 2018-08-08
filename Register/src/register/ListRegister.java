package register;

import java.util.ArrayList;
import java.util.List;

public class ListRegister implements Register {

	private List<Person> persons = new ArrayList<>();

	@Override
	public int getCount() {
		return persons.size();
	}

	@Override
	public int getSize() {
		return Integer.MAX_VALUE;
	}

	@Override
	public Person getPerson(int index) {
		return persons.get(index);
	}

	@Override
	public void addPerson(Person person) {
		persons.add(person);
	}

	@Override
	public Person findPersonByName(String name) {
		for (Person person : persons) {
			if (person.getName().equals(name))
				return person;
		}
		return null;
	}

	@Override
	public Person findPersonByPhoneNumber(String phoneNumber) {
		for (Person person : persons) {
			if (person.getPhoneNumber().equals(phoneNumber))
				return person;
		}
		return null;
	}

	@Override
	public void removePerson(Person person) {
		persons.remove(person);
	}

}
