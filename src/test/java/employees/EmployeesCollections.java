package employees;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static employees.EmployeesCollections.AgeCategory.MEDIUM;
import static employees.EmployeesCollections.AgeCategory.OLD;
import static employees.EmployeesCollections.AgeCategory.YOUNG;
import static employees.Worker.workerBuilder;
import static java.time.Instant.ofEpochMilli;
import static java.time.LocalDate.parse;
import static java.time.ZoneId.systemDefault;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

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

    Map<AgeCategory, List<Worker>> stringListMap = groupByAgeCategory(googleWorkers);

    stringListMap.get(YOUNG).forEach(worker ->
      assertThat(getAge(worker), lessThan(35L)));

    stringListMap.get(MEDIUM).forEach(worker ->
      assertThat(getAge(worker), is(both(greaterThanOrEqualTo(35L)).and(lessThan(55L)))));

    stringListMap.get(OLD).forEach(worker -> assertThat(getAge(worker), greaterThanOrEqualTo(55L)));

    groupByWorkExperience(employees);
    System.out.println();
    groupByWorkExperienceFunctional(employees);

    Map<String, List<Worker>> groupedByEmail1 = groupByEmail(googleWorkers);
    assertNotNull(groupedByEmail1);
    assertFalse(groupedByEmail1.isEmpty());

    Map<String, List<Worker>> groupedByEmail2 = groupByEmailFunctional(googleWorkers);
    assertNotNull(groupedByEmail2);
    assertFalse(groupedByEmail2.isEmpty());
  }

  private static long getAge(Worker worker) {
    return ChronoUnit.YEARS.between(worker.getDateOfBirth(), LocalDate.now());
  }

  private List<Employee> getEmployeesByCompany(List<Employee> employees, String company) {
    // TODO: 25.09.2017 implement this method
    return emptyList();
  }

  private Map<String, List<Employee>> groupEmployeesByCompany(List<Employee> employees) {
    // TODO: 25.09.2017 implement this method
    return emptyMap();
  }

  private Map<AgeCategory, List<Worker>> groupByAgeCategory(List<Worker> workers) {
    List<Worker> youngWorkers = new ArrayList<>();
    List<Worker> middleAgedWorkers = new ArrayList<>();
    List<Worker> oldWorkers = new ArrayList<>();

    Map<AgeCategory, List<Worker>> group = new HashMap<>();

    workers.forEach(worker -> {
      long age = getAge(worker);

      if (age >= 20 && age < 35) youngWorkers.add(worker);
      if (age >= 35 && age < 55) middleAgedWorkers.add(worker);
      if (age >= 55) oldWorkers.add(worker);
    });

    group.put(YOUNG, youngWorkers);
    group.put(MEDIUM, middleAgedWorkers);
    group.put(OLD, oldWorkers);

    return group;
  }

  private Map<String, List<Worker>> groupByEmail(List<Worker> workers) {
    Map<String, List<Worker>> group = new HashMap<>();

    workers.forEach(worker -> {
      String email = worker.getEmail();
      if (group.containsKey(email)) {
        group.get(email).add(worker);
      } else {
        group.put(email, new ArrayList<>(singletonList(worker)));
      }
    });

    return group;
  }

  private Map<String, List<Worker>> groupByEmailFunctional(List<Worker> workers) {
    return workers.stream().collect(toMap(Worker::getEmail, Lists::newArrayList, this::addElementsToList));
  }

  private <T extends Worker> List<T> addElementsToList(List<T> initialList, List<T> addedValue) {
    initialList.addAll(addedValue);
    return initialList;
  }

  private Map<Integer, Integer> groupByWorkExperience(List<Employee> employees) {
    Map<Integer, Integer> group = new TreeMap<>();
    employees.forEach(worker -> {
      int value = group.containsKey(worker.getExperience()) ? (group.get(worker.getExperience()) + 1) : 1;
      group.put(worker.getExperience(), value);
    });

    printWorkers(group);
    return group;
  }

  private Map<Integer, Integer> groupByWorkExperienceFunctional(List<Employee> workers) {
    Map<Integer, Integer> group = workers.stream()
      .collect(toMap(Employee::getExperience, e -> 1, this::addValue, TreeMap::new));
    printWorkers(group);
    return group;
  }

  private Integer addValue(Integer oldVal, Integer newVal) {
    return oldVal + newVal;
  }

  private static void printWorkers(Map<Integer, Integer> group) {
    System.out.println("work exp - employee");
    group.forEach((key, value) -> {
      if (key == 1 && value == 1)
        System.out.println(key + " year - " + value + " worker");
      else if (key == 1)
        System.out.println(key + " year - " + value + " workers");
      else if (value == 1)
        System.out.println(key + " years - " + value + " worker");
      else
        System.out.println(key + " years - " + value + " workers");
    });
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

  enum AgeCategory {
    YOUNG, MEDIUM, OLD
  }
}
