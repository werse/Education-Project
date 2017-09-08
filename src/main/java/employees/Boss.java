package employees;

import java.util.List;

@SuppressWarnings("WeakerAccess")
public class Boss extends Manager {

  public Boss(Worker worker, List<Worker> subordinates) {
    super(worker, subordinates, null);
  }
}

