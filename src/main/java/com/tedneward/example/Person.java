package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  private int count = 0;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    ssn = "";
    count++;
  }

  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }

  public String toString() {
    return "[Person name:" + name + " age:" + age + " salary:" + salary + "]";

  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    if (age < 0) {
      throw new IllegalArgumentException("The age you set is invalid. The age cannot be below 0");
    }
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    if (name == null){
      throw new IllegalArgumentException("The name entered is not valid.");
    }
    this.name = name;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public boolean equals(Object otherObject) {
    if (otherObject instanceof Person) {
      Person person = (Person) otherObject;
      return person.name == this.name && person.age == this.age;
    }
    return false;
  }

  public int compareTo(Person otherPerson){
    double difference = this.salary - otherPerson.salary;
    if (difference > 0) {
      return -1;
    } else if (difference < 0) {
      return 1;
    } else {
      return 0;
    }
  }

  public int count(){
    return count;
  }
  public static class AgeComparator implements Comparator<Person> {
    public int compare(Person one, Person two) {
      return one.age - two.age;
    }
  }

  public static ArrayList<Person> getNewardFamily(){
    ArrayList<Person> ans = new ArrayList<>();
    ans.add(new Person("Ted", 41, 250000));
    ans.add(new Person("Charlotte", 43, 150000));
    ans.add(new Person("Michael", 22, 10000));
    ans.add(new Person("Matthew", 15, 0));
    return ans;
  }
  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
