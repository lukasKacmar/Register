package register;

/**
 * register.Person register.
 */
public class ArrayRegister implements Register {
	/** register.Person array. */
	private Person[] persons;

	/** Number of persons in this register. */
	private int count;

	/**
	 * Constructor creates an empty register with maximum size specified.
	 * 
	 * @param size
	 *            maximum size of the register
	 */
	public ArrayRegister(int size) {
		persons = new Person[size];
		count = 0;
	}

	/* (non-Javadoc)
	 * @see register.Register#getCount()
	 */
	@Override
	public int getCount() {
		return count;
	}

	/* (non-Javadoc)
	 * @see register.Register#getSize()
	 */
	@Override
	public int getSize() {
		return persons.length;
	}

	/* (non-Javadoc)
	 * @see register.Register#getPerson(int)
	 */
	@Override
	public Person getPerson(int index) {
		return persons[index];
	}

	/* (non-Javadoc)
	 * @see register.Register#addPerson(register.Person)
	 */
	@Override
	public void addPerson(Person person) {
		persons[count] = person;
		count++;
	}

	// TODO: Implement the method findPersonByName
	/* (non-Javadoc)
	 * @see register.Register#findPersonByName(java.lang.String)
	 */
	@Override
	public Person findPersonByName(String name) {
		Person person = null;
		for (int i = 0; i < count; i++) {
			if (persons[i].getName().equals(name)) {
				person = persons[i];
			}
		}
		return person;
	}

	// TODO: Implement the method findPersonByPhoneNumber
	/* (non-Javadoc)
	 * @see register.Register#findPersonByPhoneNumber(java.lang.String)
	 */
	@Override
	public Person findPersonByPhoneNumber(String phoneNumber) {
		Person person = null;
		for (int i = 0; i < count; i++) {
			if (persons[i].getPhoneNumber().equals(phoneNumber)) {
				person = persons[i];
			}
		}
		return person;
	}

	/* (non-Javadoc)
	 * @see register.Register#removePerson(register.Person)
	 */
	@Override
	public void removePerson(Person person) {
		for (int i = 0; i < count; i++) {
			if (persons[i].equals(person)) {
				persons[i] = persons[i + 1];
				for (int j = i + 1; j < count; j++) {
					persons[j] = persons[j + 1];
				}
				count--;
				return;
			}
		}
	}
}
