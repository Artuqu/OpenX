package com.binaryTree;

public class CreateTree extends SpecialBinaryTree {


    public static void main(String[] args) {

        int[] tree = new int[]{5, 3, 7, 2, 5, 1, 0, 2, 8, 5};
        for (int i = 0; i < tree.length; i++) {
            CreateTree.add(tree[i]);
        }
    }
}
