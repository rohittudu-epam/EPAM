package Optional_Example;

import java.util.Optional;

public class User {
    private String name;
    private Optional<Address> address;

    public User(String name, Optional<Address> address) {
        setName(name);
        setAddress(address);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<Address> getAddress() {
        return address;
    }

    public void setAddress(Optional<Address> address) {
        this.address = address;
    }
}
