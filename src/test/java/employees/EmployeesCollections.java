package employees;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static employees.Worker.workerBuilder;
import static java.time.Instant.ofEpochMilli;
import static java.time.LocalDate.parse;
import static java.time.ZoneId.systemDefault;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class EmployeesCollections {

  public static final String DOMAIN = "@email.com";
  private static final List<String> FIRST_NAMES =
    asList("John", "Daniel", "Clara", "Anna", "Clark", "Ronald", "Catherine", "Roberta",
      "Edward", "Tammy", "Hector", "Leo");

  private static final List<String> LAST_NAMES =
    asList("Fuentes", "Randle", "Potter", "Haney", "McGee", "Anderson", "Grace", "White");

  private static final List<String> COMPANIY_NAMES = asList("Google", "Microsoft", "Apple");
  private static final Random RANDOM = new Random();
  public static final String GOOGLE = "Google";

  @Test
  public void employeesWork() throws Exception {
    Worker johnSmith = workerBuilder().firstName("John").lastName("Smith").dateOfBirth(parse("2000-06-03")).build();
    Worker annaSmith = workerBuilder().firstName("Anna").lastName("Smith").build();
    Worker jamesMaser = workerBuilder().firstName("James").lastName("Maser").middleName("Alfred").build();
    Worker mosesPollard = workerBuilder().firstName("Moses").lastName("Pollard").middleName("Carl").build();

    Employee john = new Employee(johnSmith, jamesMaser, "Google", 5);
    Employee anna = new Employee(annaSmith, jamesMaser, "Google", 2);
    Manager james = new Manager(jamesMaser, asList(johnSmith, annaSmith), mosesPollard, "Google", 15);

    Boss moses = new Boss(mosesPollard, singletonList(jamesMaser), "Microsoft", 40);

    List<Employee> employees = asList(james, anna, john, moses);

    String company = "Google";
    List<Employee> employeesByCompany = getEmployeesByCompany(employees, company);
    Map<String, List<Employee>> grouppedEmployees = groupEmployeesByCompany(employees);
  }

  @Test
  public void randomDataFiltering() throws Exception {
    List<Worker> googleWorkers = generateWorkers(1000);

    List<Worker> googleManagerWorkers = generateWorkers(10);

    Worker googleBossWorker = generateRandomWorker();

    AtomicInteger counter = new AtomicInteger(0);

    Boss googleBoss = new Boss(googleBossWorker, googleManagerWorkers, "Google", 50);

    List<Manager> googleManagers = googleManagerWorkers.stream()
      .map(worker -> new Manager(
        worker, googleWorkers.subList(counter.get() * 100, counter.incrementAndGet() * 100),
        googleBossWorker, GOOGLE, RANDOM.nextInt(25) + 5))
      .collect(toList());

    List<Employee> googleEmployees = googleManagers.stream()
      .map(manager -> manager.getSubordinates().stream()
        .map(e -> new Employee(e, manager.getWorker(), GOOGLE, RANDOM.nextInt(10) + 1))
        .collect(toList()))
      .flatMap(Collection::stream)
      .collect(toList());

    List<Employee> employees = Stream.of(singletonList(googleBoss), googleManagers, googleEmployees)
      .flatMap(Collection::stream)
      .collect(toList());

    System.out.println(employees);
    groupByAgeCategory(googleWorkers);
    groupByEmail(googleWorkers);
  }

  private List<Employee> getEmployeesByCompany(List<Employee> employees, String company) {
    // TODO: 25.09.2017 implement this method
    return emptyList();
  }

  private Map<String, List<Employee>> groupEmployeesByCompany(List<Employee> employees) {
    // TODO: 25.09.2017 implement this method
    return emptyMap();
  }

  private Map<String, List<Worker>> groupByAgeCategory(List<Worker> workers) {
    //Young = "young" from 20 to 35 [20; 35)
    //Old = "old" more that 55 [55; +inf)
    //Medium = "medium" from 35 to 55 [35; 55)
    return emptyMap();
  }

  private Map<String, List<Worker>> groupByEmail(List<Worker> workers) {
    return emptyMap();
  }

  private Map<Integer, List<Worker>> groupByWorkExperience(List<Worker> workers) {
    // Work experience 1 year -> 1 worker
    // Work experience 10 years -> 2 workers
    return emptyMap();
  }

  // TODO: 25.09.2017 Organization as class with name, phone and location

  private static Worker worker(String firstName, String lastName, String middleName) {
    return workerBuilder().firstName(firstName).lastName(lastName).middleName(middleName)
      .dateOfBirth(getRandomDate()).build();
  }

  private static List<Worker> generateWorkers(int count) {
    return range(0, count).mapToObj(index -> generateRandomWorker()).collect(toList());
  }

  private static Worker generateRandomWorker() {

    int firstNameIndex = RANDOM.nextInt(FIRST_NAMES.size());
    int middleNameIndex = RANDOM.nextInt(FIRST_NAMES.size());
    int lastNameIndex = RANDOM.nextInt(LAST_NAMES.size());

    return worker(FIRST_NAMES.get(firstNameIndex), LAST_NAMES.get(lastNameIndex), FIRST_NAMES.get(middleNameIndex));
  }

  private static LocalDate getRandomDate() {
    return ofEpochMilli(getRandomEpochSeconds()).atZone(systemDefault()).toLocalDate();
  }

  private static long getRandomEpochSeconds() {
    long max = 946_587_600_000L;
    long min = -946_782_000_000L;
    return min + ((long) (new Random().nextDouble() * (max + 1 - min)));
  }
}
