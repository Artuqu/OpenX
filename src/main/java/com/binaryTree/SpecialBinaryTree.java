package com.binaryTree;

public class SpecialBinaryTree {


    private static SpecialBinaryTree specialBinaryTree;
    private int value;

    private SpecialBinaryTree left;
    private SpecialBinaryTree right;

    public SpecialBinaryTree() {
    }

    public SpecialBinaryTree(int root) {
        this.value = root;
        this.left = null;
        this.right = null;
    }

    public static SpecialBinaryTree buildTree(int valueToAdd, SpecialBinaryTree currentNode) {
        if (currentNode == null) {
            return new SpecialBinaryTree(valueToAdd);
        }
        if (valueToAdd >= currentNode.value || valueToAdd == 0) {
            currentNode.right = buildTree(valueToAdd, currentNode.right);
        } else if (valueToAdd < currentNode.value) {
            currentNode.left = buildTree(valueToAdd, currentNode.left);
        } else {
            return currentNode;
        }
        return currentNode;
    }

    public static SpecialBinaryTree add(int valueToAdd) {
        specialBinaryTree = buildTree(valueToAdd, specialBinaryTree);
        return specialBinaryTree;
    }


}
