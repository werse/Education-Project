package employees;

import java.time.LocalDate;

@SuppressWarnings("WeakerAccess")
public class Worker {

  private final String firstName;
  private final String lastName;
  private final String middleName;
  private final String email;
  private final LocalDate dateOfBirth;

  private static final String DOMAIN = "@email.com";

  private Worker(WorkerBuilder builder) {
    firstName = builder.firstName;
    lastName = builder.lastName;
    middleName = builder.middleName;
    email = createEmail(firstName, lastName);
    dateOfBirth = builder.dateOfBirth;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getEmail() {
    return email;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  @Override
  public String toString() {
    return "Worker{" +
      "firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", middleName='" + middleName + '\'' +
      ", email='" + email + '\'' +
      ", dateOfBirth=" + dateOfBirth +
      '}';
  }

  public static WorkerBuilder workerBuilder() {
    return new WorkerBuilder();
  }

  private String createEmail(String firstName, String lastName) {
    if (firstName == null && lastName == null) {
      return null;
    }
    return (firstName + "_" + lastName + DOMAIN).toLowerCase();
  }

  public static class WorkerBuilder {
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private LocalDate dateOfBirth;

    public WorkerBuilder() {
    }

    public WorkerBuilder firstName(String firstName) {
      this.firstName = firstName;
      return this;
    }

    public WorkerBuilder lastName(String lastName) {
      this.lastName = lastName;
      return this;
    }

    public WorkerBuilder middleName(String middleName) {
      this.middleName = middleName;
      return this;
    }

    public WorkerBuilder email(String email) {
      this.email = email;
      return this;
    }

    public WorkerBuilder dateOfBirth(LocalDate dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
      return this;
    }

    public Worker build() {
      return new Worker(this);
    }
  }
}
