package register.persist;

import register.Register;

public interface RegisterStorage {
	void save(Register register);
	
	Register load();
}
