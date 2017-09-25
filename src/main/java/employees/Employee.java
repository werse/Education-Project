package employees;

import java.util.List;

import static java.util.Collections.emptyList;

@SuppressWarnings("WeakerAccess")
public class Employee {

  private Worker worker;
  private List<Worker> subordinates;
  private Worker superior;
  private String organization;
  private int experience;

  public Employee(Worker worker, Worker superior, String organization, int experience) {
    this.worker = worker;
    this.subordinates = emptyList();
    this.superior = superior;
    this.organization = organization;
    this.experience = experience;
  }

  Employee(Worker worker, List<Worker> subordinates, Worker superior, String organization, int experience) {
    this.worker = worker;
    this.subordinates = subordinates;
    this.superior = superior;
    this.organization = organization;
    this.experience = experience;
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

  public String getOrganization() {
    return organization;
  }

  public void setOrganization(String organization) {
    this.organization = organization;
  }

  public int getExperience() {
    return experience;
  }

  public void setExperience(int experience) {
    this.experience = experience;
  }

  @Override
  public String toString() {
    return "Employee{" +
            "worker=" + worker +
            ", subordinates=" + subordinates +
            ", superior=" + superior +
            ", organization='" + organization + '\'' +
            ", experience=" + experience +
            " months" +
            '}';
  }
}


