package com.unsha.app;

import java.awt.*;

public class Tree {
  double heightFt;
  double trunkDiameterInches;

  TreeType treeType;// --> ENUM
  
  static Color TRUNK_COLOR = new Color(102, 51, 0);// static attribute -> behavier

  public Tree(double heightFt, double trunkDiameterInches, TreeType treeType) {
    this.heightFt = heightFt;
    this.trunkDiameterInches = trunkDiameterInches;
    this.treeType = treeType;
  }

  void grow() {
    this.heightFt = this.heightFt + 10;
    this.trunkDiameterInches = this.trunkDiameterInches + 1;
  }

  void alertTall() {
    if (this.heightFt > 10) {
      System.out.println("That's a tall " + this.treeType + " tree!");
    }
  }

  static void announceTree() { // XXX static method!!!
    // System.out.println("Look out for that " + this.TRUNK_COLOR + "tree!"); not "this." !!!
    System.out.println("Look out for that " + TRUNK_COLOR + "tree!");
  }

}
