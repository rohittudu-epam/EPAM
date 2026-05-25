package Optional_Example;

import java.util.Optional;


public class OptionalExample {
    public static void main(String[] args){
        Address addrs = new Address("New York");
        User alice = new User("ALice", Optional.of(addrs));

        Optional<User> user = Optional.of(alice);

        String city = user.flatMap(User::getAddress).map(Address::getCity).orElse("Unknown City");

        System.out.println("City: " + city);
    }
}
