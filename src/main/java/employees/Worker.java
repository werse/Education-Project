package employees;

import java.time.LocalDate;

@SuppressWarnings("WeakerAccess")
public class Worker {

  private final String firstName;
  private final String lastName;
  private final String middleName;
  private final String email;
  private final LocalDate dateOfBirth;

  private Worker(WorkerBuilder builder) {
    firstName = builder.firstName;
    lastName = builder.lastName;
    middleName = builder.middleName;
    email = builder.email;
    dateOfBirth = builder.dateOfBirth;
  }
  public static class WorkerBuilder {
    private final String firstName;
      private final String lastName;
      private String middleName;
      private String email;

      private LocalDate dateOfBirth;

      public WorkerBuilder(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
      }

      public WorkerBuilder middleName(String middleName) {
        this.middleName = middleName;
        return this;
      }

      public WorkerBuilder email(Worker worker) {
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

}
