package employees;

import java.util.List;

import static java.util.Collections.emptyList;

@SuppressWarnings("WeakerAccess")
public class Employee {

  private Worker worker;
  private List<Worker> subordinates;
  private Worker superior;

  public Employee(Worker worker, Worker superior) {
    this.worker = worker;
    this.subordinates = emptyList();
    this.superior = superior;
  }

  Employee(Worker worker, List<Worker> subordinates, Worker superior) {
    this.worker = worker;
    this.subordinates = subordinates;
    this.superior = superior;
  }

  public Worker getWorker() {
    return worker;
  }

  public void setWorker(Worker worker) {
    this.worker = worker;
  }

  public List<Worker> getSubordinates() {
    return subordinates;
  }

  public void setSubordinates(List<Worker> subordinates) {
    this.subordinates = subordinates;
  }

  public Worker getSuperior() {
    return superior;
  }

  public void setSuperior(Worker superior) {
    this.superior = superior;
  }

  @Override
  public String toString() {
    return "Employee{" +
      "worker=" + worker +
      ", subordinates=" + subordinates +
      ", superior=" + superior +
      '}';
  }

  // TODO: 21.09.2017 All employees should be part of organization and have work experience
}


