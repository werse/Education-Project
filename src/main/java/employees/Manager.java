package employees;

import java.util.List;

@SuppressWarnings("WeakerAccess")
public class Manager extends Employee {

  public Manager(Worker worker, List<Worker> subordinates, Worker superior) {
    super(worker, subordinates, superior);
  }
}
