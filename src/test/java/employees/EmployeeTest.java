package employees;

import org.junit.Test;

import static employees.Worker.workerBuilder;
import static java.time.LocalDate.parse;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class EmployeeTest {

  @Test
  public void employeesWork() throws Exception {
    Worker johnSmith = workerBuilder().firstName("John").lastName("Smith").dateOfBirth(parse("2000-06-03")).build();
    Worker annaSmith = workerBuilder().firstName("Anna").lastName("Smith").build();
    Worker jamesMaser = workerBuilder().firstName("James").lastName("Maser").middleName("Alfred").build();
    Worker mosesPollard = workerBuilder().firstName("Moses").lastName("Pollard").middleName("Carl").build();

    Employee john = new Employee(johnSmith, jamesMaser, "Google", 13);
    Employee anna = new Employee(annaSmith, jamesMaser, "Google", 12);
    Manager james = new Manager(jamesMaser, asList(johnSmith, annaSmith), mosesPollard, "Google", 15);

    Boss moses = new Boss(mosesPollard, singletonList(jamesMaser), "Microsoft", 40);

    System.out.println(james.toString());
    System.out.println(james.getSubordinates());

    System.out.println(anna.getSubordinates());
    System.out.println(john.getSubordinates());

    System.out.println(moses.getSuperior());
  }
}