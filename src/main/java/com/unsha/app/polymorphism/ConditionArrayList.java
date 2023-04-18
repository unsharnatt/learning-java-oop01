package com.unsha.app.polymorphism;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class ConditionArrayList extends ArrayList<Integer> {

    private Predicate<Integer> condition;

    public Predicate<Integer> getCondition() {
      return condition;
    }
    public void setCondition(Predicate<Integer> condition) {
      this.condition = condition;
    }

    public ConditionArrayList(Predicate<Integer> predicate,//added
                              Integer... nums) {//* optional param ...
        // super(Arrays.stream(nums)
        //         .filter(predicate)//mod
        //         .collect(Collectors.toList()));
        // this.condition = predicate;
        // * calling, make it easy to maintain in single method
        super(new ConditionArrayList(predicate, Arrays.asList(nums)));
        this.condition = predicate;// XXX Have to have!!!
        // System.out.println("->condition:" + condition + " nums:" + nums);
    }

    public ConditionArrayList(Predicate<Integer> predicate,
        // ArrayList<Integer> arrayList) {
        List<Integer> arrayList) {//change to List, make it more handy
      super(arrayList.stream()
          .filter(predicate)
          .collect(Collectors.toList()));
      this.condition = predicate;
      // System.out.println("-->predicate:"+predicate+" arrayList:"+arrayList);
    }

    @Override
    public void add(int index, Integer element) {
        if (isEligible(element)) {//mod
            super.add(index, element);
        }
    }

    @Override
    public boolean add(Integer element) {
        if (isEligible(element)) {//mod
            return super.add(element);
        } else {
            return false;
        }
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        return super.addAll(c.stream()
                .filter(this::isEligible)//mod
                .collect(Collectors.toList()));
    }

    @Override
    public boolean addAll(int index,
                          Collection<? extends Integer> c) {
        return super.addAll(index, c.stream()
                .filter(this::isEligible)//mod
                .collect(Collectors.toList()));
    }

    @Override
    public Integer set(int index,
                       Integer element) {
        if (isEligible(element)) {//mod
            return super.set(index, element);
        } else {
            System.out.println(element + " is not odd. ");
            return Integer.MIN_VALUE;
        }
    }


    @Override
    public void replaceAll(UnaryOperator<Integer> operator) {
        super.replaceAll(operator);
        super.removeIf(n -> !isEligible(n));//mod
    }

    public boolean isEligible(Integer element) {// utils for filter just like odd works
      // System.out.println("->condition:" + condition + " element:" + element);
      return condition.test(element);
    }

}
