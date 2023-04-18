package com.unsha.app.emp;

public class ProEmployee {

  private String name;
  private String location;
  private double salary;
  private int age;

  public ProEmployee(String name, String location, double salary, int age) {
    this.name = name;
    this.location = location;
    this.salary = salary;
    this.age = age;
  }

  public void raiseSalary() {
    this.salary = this.salary * 1.2;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }
  public void setLocation(String location) {
    this.location = location;
  }

  public double getSalary() {
    return salary;
  }
  public void setSalary(double salary) {
    this.salary = salary;
  }

  public int getAge() {
    return age;
  }
  public void setAge(int age) {
    this.age = age;
  }
  
}
