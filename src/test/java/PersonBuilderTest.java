import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.netology.Person;
import ru.netology.PersonBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonBuilderTest {

    @BeforeEach
    public void init() {
        System.out.println("Test begin");
    }

    @AfterEach
    public void finish() {
        System.out.println("End of test");
    }

    @CsvFileSource(files = "src/test/resources/builderArg.csv")
    @ParameterizedTest
    public void builderTest(String name, String surname, Integer age, String address) {

        System.out.println("======== Тест создания объекта Person");

        Person exmpl = new Person(name, surname);
        if (age != null) exmpl.setAge(age);
        exmpl.setAddress(address);

        PersonBuilder e = new PersonBuilder(name, surname).setAddress(address);
        if (age != null) e.setAge(age);
        Person expct = e.build();

        assertEquals(expct, exmpl);
    }

    @CsvFileSource(files = "src/test/resources/wrongAge.csv")
    @ParameterizedTest
    public void wrongAgeTest(String name, String surname, Integer age, String address) {

        System.out.println("======== Тест выброса сообщения о неверном возрасте");

        PersonBuilder exmpl = new PersonBuilder(name, surname);
        assertThrows(IllegalArgumentException.class, () -> exmpl.setAge(age), "Wrong age argument");
    }

    @Test
    public void missArguTest() {

        System.out.println("======== Тест выброса сообщения об отсутствии обязательных полей (имя и фамилия)");

        String name = "Ann";
        String surname = null;
        PersonBuilder exmpl = new PersonBuilder(name, surname);
        assertThrows(IllegalStateException.class, () -> exmpl.build(), "Missing required fields");
    }

    @Test
    public void happyBirthdayTest() {

        System.out.println("======== Тест правильности работы happyBirthday");

        Person mom = new PersonBuilder("Ann", "Smirnoff")
                .setAge(28)
                .build();
        mom.happyBirthday();

        assertEquals(29, mom.getAge());
    }

    @Test
    public void newChildTest() {

        System.out.println("======== Тест правильности работы newChildTest");

        PersonBuilder mom = new PersonBuilder("Ann", "Smirnoff")
                .setAge(28)
                .setAddress("Moscow");

        Person momChild = mom.newChildBuilder().build();
        Person child = new Person("name","Smirnoff");
        child.setAddress("Moscow");

        assertEquals(child, momChild);
    }
}
