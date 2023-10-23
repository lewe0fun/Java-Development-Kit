package seminar4.hw;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PhoneBook {
    private final List<Person> personList;

    public PhoneBook(List<Person> personList) {
        this.personList = personList;
    }

    public Person findPersonById(int id) {
        return personList.stream().filter(p -> p.getId() == id).findAny().orElse(null);
    }

    public List<Person> findPersonByExp(int exp) {
        return personList.stream().filter(p -> p.getExp() == exp).collect(Collectors.toList());
    }

    public List<String> getPhoneByName(String name) {
        return personList.stream().filter(p -> Objects.equals(p.getName(), name)) .map(Person::getPhone).toList();
    }

    public void add(Person person) {
        personList.add(person);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Person p : personList)
            result.append(p.getId()).append(" ").append(p.getName()).append(" ").append(p.getPhone()).append(" ").append(p.getExp()).append("\n");
        return result.toString();
    }
}
