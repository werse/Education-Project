package employees;

import java.util.List;

@SuppressWarnings("WeakerAccess")
public class Boss extends Manager {

  public Boss(Worker worker, List<Worker> subordinates, String org, int exp) {
    super(worker, subordinates, null, org, exp);
  }
}