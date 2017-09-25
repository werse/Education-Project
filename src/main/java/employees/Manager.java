package employees;

import java.util.List;

@SuppressWarnings("WeakerAccess")
public class Manager extends Employee {

  public Manager(Worker worker, List<Worker> subordinates, Worker superior, String org, int exp) {
    super(worker, subordinates, superior, org, exp);
  }
}
