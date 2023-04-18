package com.unsha.app;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.function.Predicate;

import com.unsha.app.bank.BankAcct;
import com.unsha.app.company.*;
import com.unsha.app.emp.ProEmployee;
import com.unsha.app.inheritance.ModArrayList;
import com.unsha.app.abstraction.*;
import com.unsha.app.polymorphism.*;

/**
 * Hello world!
 */
public class App {
  public static void main(String[] args) throws IOException {
    System.out.println("Hello World!");

    System.out.println("#Class and Object...");

    Tree treeMaple = new Tree(20, 2, TreeType.MAPLE);
    System.out.println("tree1:" + treeMaple.heightFt + "|" + treeMaple.trunkDiameterInches);
    treeMaple.grow();
    System.out.println("tree1.1:" + treeMaple.heightFt + "|" + treeMaple.trunkDiameterInches);
    treeMaple.grow();
    treeMaple.grow();
    System.out.println("tree1.2:" + treeMaple.heightFt + "|" + treeMaple.trunkDiameterInches);

    Tree treeOak = new Tree(20, 2, TreeType.OAK);

    // if (treeMaPle.heightFt > 10) {
    // System.out.println("That's a tall " + treeMaPle.treeType + " tree!");
    // }

    // if (treeOak.heightFt > 10) {
    // System.out.println("That's a tall " + treeOak.treeType + " tree!");
    // }

    // XXX copy paste is not a good programming -> create new method for print tall
    // alert -> alertTall
    treeMaple.alertTall();
    treeOak.alertTall();

    // * "static" does not rely on any instance member!!!
    System.out.println("->" + Tree.TRUNK_COLOR);// calling static member
    Tree.announceTree();// calling static method

    // * using build-in java class
    Color defaultBlue = Color.BLUE;
    Color brighterBlue = defaultBlue.brighter();
    System.out.println("default blue:" + defaultBlue.getRGB());
    System.out.println("bright  blue:" + brighterBlue.getRGB());

    // * trying to use my own crafted class
    Employee emp01 = new Employee("Foo", "LA", 2000, 35);
    Employee emp02 = new Employee("Bar", "KL", 1500, 31);

    emp02.raiseSalary();

    System.out.println("emp01:" + emp01.salary);
    System.out.println("emp02:" + emp02.salary);

    // # Implementing encapsulation with access modifiers #
    System.out.println("#Encapsulation...");
    ProEmployee proemp01 = new ProEmployee("Foo", "LA", 2000, 35);
    ProEmployee proemp02 = new ProEmployee("Bar", "KL", 1500, 31);

    proemp02.raiseSalary();

    System.out.println("pro emp01:" + proemp01.getSalary());
    System.out.println("pro emp02:" + proemp02.getSalary());

    // * learning encapsulation from String
    String myString = "ABC";
    System.out.println("string length:" + myString.length());// public method

    // # try encapsulation by building Bank Account class #
    // + constructure
    // + getOwner getBalance
    // + deposit(+)
    // + withdraw(-)
    BankAcct acc01 = new BankAcct("Foo Bar", 2000);
    acc01.withdraw(500);
    acc01.deposit(2000);
    acc01.withdraw(2000);

    System.out.println("acct:" + acc01.getOwner() + " Balance:" + acc01.getBalance());

    // ### Inheritance Class ###
    System.out.println("#Inheritance...");
    //         Person             - name
    //            |               extends
    //         Employee           - id, salary
    //           /  \             extends
    //     Analyst  SalesPerson
    //     - bonus(sal * 1.5)  - commission
    //* a class can only have one super class, but multiple subclasses
    //* if you need multiple super classes, use multi-Level inheritance

    com.unsha.app.company.Employee emp001 = new Analyst("Foo", 20000, 32);
    com.unsha.app.company.Employee emp002 = 
              new Salesperson("Foo", 20000, 32, 1.4);

    System.out.println("emp-01 bonus:" + ((Analyst) emp001).getAnnualBonus());
    System.out.println("emp-02 comission:" + ((Salesperson) emp002).getCommissionPercentage());

    // * see a good example from build-in foudational java class ***
    // [Vector] +addElement() +removeElementAt(i)
    //    |
    // [Stack] +push{addElement()} +pop{removeElementAt(len-1)} 
    Stack<String> wordStack = new Stack<>();

    wordStack.push("We're Friend.");
    wordStack.push("I'm Bar.");
    wordStack.push("I'm Foo.");

    System.out.println(wordStack.pop());
    System.out.println(wordStack.pop());
    System.out.println(wordStack.pop());

    // * Try to inherite ArrayList class
    //     to get index without error ('index out of bound') with the solution: mod index with len 
    ModArrayList<Integer> listy = new ModArrayList<>();
    listy.add(0);
    listy.add(10);
    listy.add(20);
    listy.add(30);

    System.out.println(listy.getUsingMod(1)); // -> 10
    System.out.println(listy.getUsingMod(-2));      // -> 20
    System.out.println(listy.getUsingMod(40));// |mod| with len result = 0 -> 0
    System.out.println(listy.getUsingMod(35));// |mod| with len result = 3 -> 30

    // ### Polymorphism ###
    System.out.println("#Polymorphism...");
    // - runtime 
    // - compile time
    // listy.add(10); <- add(i) is come from ArrayList Class

    // # runtime polymorphism
    // *** same input for OddArrayList and ArrayList let see the output results
    System.out.println("#run time polymorphism...");
    // * all
    ArrayList<Integer> listy1 = new ArrayList<>(
                        Arrays.asList(new Integer[] {1, 2, 3, 4}));
    System.out.println(listy1);
    // * odd
    OddArrayList oddListy = new OddArrayList(1, 2, 3, 4);
    System.out.println(oddListy);

    //addRandomNumber() 
    addRandomNumber(listy1);// using the same method -> addRandomNumber()
    System.out.println("- full list size:" + listy1.size());
    System.out.println("- full list :" + listy1);
    addRandomNumber(oddListy);// using the same method -> addRandomNumber()
    System.out.println("- odd list size:" + oddListy.size());
    System.out.println("- odd list :" + oddListy);

    // new ConditionArrayList class, more common use by inout a condition to filter the member added
    Predicate<Integer> isDivisibleByTwo = n -> Math.abs(n) % 2 == 0;
    ConditionArrayList evenListy = new ConditionArrayList(isDivisibleByTwo, 1,2,3,4);
    // evenListy.add(1);
    // evenListy.add(2);
    // evenListy.add(3);
    // evenListy.add(4);
    System.out.println(evenListy+ " even condition:" + evenListy.getCondition());

    addRandomNumber(evenListy);
    System.out.println("- even list size:" + evenListy.size());
    System.out.println("- even list :" + evenListy);

    // # compile time polymorphism
    System.out.println("#compile time polymorphism...");
    Predicate<Integer> isDivisibleByThree = n -> Math.abs(n) % 3 == 0;

    ArrayList<Integer> numsList = new ArrayList<>();
    numsList.add(1);
    numsList.add(2);
    numsList.add(3);
    numsList.add(4);

    // *** new constructure
    ConditionArrayList divisibleBy3Listy = new ConditionArrayList(isDivisibleByThree, numsList);
    System.out.println(divisibleBy3Listy);

    addRandomNumber(divisibleBy3Listy);
    System.out.println("- divisibleBy3 list size:" + divisibleBy3Listy.size());
    System.out.println("- divisibleBy3 list :" + divisibleBy3Listy);


    // build-in Java class use polymorphism 
    ArrayList<Integer> testList = new ArrayList<>();
    testList.addAll(divisibleBy3Listy);
    testList.addAll(0, divisibleBy3Listy);

    // *** Source code compile time polymorphism (overload)
    /*
    public boolean addAll(Collection<? extends E> c) {
      return addAll(this.size, c);
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);
        int cSize = c.size();
        if (cSize==0)
            return false;
        checkForComodification();
        root.addAll(offset + index, c);
        updateSizeAndModCount(cSize);
        return true;
    }
    */

    testList.add(0, 555);
    // *** runtime polymophism (override)
    /* 
      @ArrayList
      public void add(int index, E element) {
        rangeCheckForAdd(index);
        modCount++;
        final int s;
        Object[] elementData;
        if ((s = size) == (elementData = this.elementData).length)
            elementData = grow();
        System.arraycopy(elementData, index,
                         elementData, index + 1,
                         s - index);
        elementData[index] = element;
        size = s + 1;
    }

    @AbstracList
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }
    */

    Contact contactOne = new Contact("Sally",
                            new PhoneNumber("2637263737"));
    Contact contactTwo = new Contact("Maggie Smith",
                            new PhoneNumber(41, "9384713401"));
    Contact contactThree = new Contact("Roger Williams",
                            new PhoneNumber("448474734929"));
    Contact contactFour = new Contact("David Jones",
              "david_jones@bluewire.com");
    Contact contactFive = new Contact("Sarah Brown",
                            new PhoneNumber("2029384982"), "sarahb@tech.com");
    // * note: 3 diferent constructures in Contact
    //         2 different constructures in PhoneNumber

    System.out.println(contactOne);
    System.out.println(contactTwo);
    System.out.println(contactThree);
    System.out.println(contactFour);
    System.out.println(contactFive);

    // ### Abstraction ###
    System.out.println("#Abstraction...");
    // not implement yet
    // - abstrac class : some method is a abstract. 
    //   e.g. In AbstractFileReader, readFile() has operation to call readLine() but readLine() is a abstrac.
    // - interface : all method members are abstract.
    // # Implementation of abstrac class/method 
    System.out.println("#abstrac...");
    DigitsOnlyFileReader digitsOnlyFileReader = new DigitsOnlyFileReader("/Users/unsharnatty/Labs/Java_Object-Oriented/my-app/message.txt");

    System.out.println(digitsOnlyFileReader.readFile());
    System.out.println(digitsOnlyFileReader.getPath());

    // # Implementation of Interface
    System.out.println("#interface...");
    PasswordChangeEvent eventOne = new PasswordChangeEvent("321928399");
    AccountTransferEvent eventTwo = new AccountTransferEvent("1936428194");
    MissedPaymentEvent eventThree = new MissedPaymentEvent("93827451");

    Event[] events = { eventOne, eventTwo, eventThree };

    for (Event e : events) {
      System.out.println(e.getTimeStamp());
      e.process();
      System.out.println();
    }
    // # see an example in build-in java class
    // AbstactList <- ArrayList : can extends only one class
    // [List],[RandomAccess],[Clonable], ... <- ArrayList : many implements
    // [List] <- AbstactList : abstrac class implements an nterfaces

    // # update PasswordChangeEvent to use AbstractEvent
    //   so there are not get any chage to be effected here. 
    PasswordChangeEvent eventNewOne = new PasswordChangeEvent("321928399");
    eventNewOne.process();

  }

  public static void addRandomNumber(List<Integer> list) {
    int originalSize = list.size();
    Random random = new Random();
    while (originalSize + 1 != list.size()) { // loop to make sure the oddList has one more member added
      int n = random.nextInt(1000);
      // System.out.println("n:" + n + " condition:" + list.getCondition());
      list.add(n);// run time Polymorphism
    }
  }
}
