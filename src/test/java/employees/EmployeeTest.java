package employees;

import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class EmployeeTest {

  @Test
  public void employeesWork() throws Exception {
    Worker johnSmith = worker("John", null, "Smith");
    Worker annaSmith = worker("Anna", null, "Smith");
    Worker jamesMaser = worker("James", "Alfred", "Maser");
    Worker mosesPollard = worker("Moses", "Carl", "Pollard");

    Employee jonh = new Employee(johnSmith, jamesMaser);
    Employee anna = new Employee(annaSmith, jamesMaser);
    Manager james = new Manager(jamesMaser, asList(johnSmith, annaSmith), mosesPollard);

    Boss moses = new Boss(mosesPollard, singletonList(jamesMaser));

    System.out.println(james.toString());
    System.out.println(james.getSubordinates());

    System.out.println(anna.getSubordinates());
    System.out.println(jonh.getSubordinates());

    System.out.println(moses.getSuperior());
  }

  private Worker worker(String firstName, String middleName, String lastName) {
    Worker worker = new Worker();
    worker.setFirstName(firstName);
    worker.setMiddleName(middleName);
    worker.setLastName(lastName);
    return worker;
  }

  // TODO: 21.09.2017 Create method. that generates email for worker with pattern {FirstName}_{LastName}@{@OrganizationDomain}
  private static Worker createAndSetEmail(Worker worker) {
    return null;
  }
}