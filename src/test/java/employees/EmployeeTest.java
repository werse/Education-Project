package employees;

import org.junit.Test;

import java.time.LocalDate;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class EmployeeTest {

  @Test
  public void employeesWork() throws Exception {
    Worker johnSmith = new Worker.WorkerBuilder("John", "Smith")
            .dateOfBirth(LocalDate.of(2000, 3, 6))
            .build();
    Worker annaSmith = new Worker.WorkerBuilder("Anna", "Smith")
            .build();
    Worker jamesMaser = new Worker.WorkerBuilder("James", "Maser")
            .middleName("Alfred")
            .build();
    Worker mosesPollard = new Worker.WorkerBuilder("Moses", "Pollard")
            .middleName("Carl")
            .build();

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


  // TODO: 21.09.2017 Create method. that generates email for worker with pattern {FirstName}_{LastName}@{@OrganizationDomain}
  private static String createEmail(Worker worker) {
    return worker.getFirstName() + "_"+ worker.getLastName()
  }
}