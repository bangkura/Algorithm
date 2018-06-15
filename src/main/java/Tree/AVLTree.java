package Tree;

/**
 * Copyright (C) 2014 iQIYI.COM - All Rights Reserved
 * <p>
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * @description: AVL tree
 * @author: Bangkura
 * @create: 2018-06-15 10:22
 **/

public class AVLTree<T extends Comparable<T>> extends BinaryTree<T>{

    public AVLTree(T val) {
        root = new AVLTreeNode<T>(val);
    }

    public boolean insert(T val) {
        AVLTreeNode<T> node = new AVLTreeNode<T>(val);
        if(insert(node, root)) {
            maintainHeight();
            maintainTree((AVLTreeNode) root);
            return true;
        } else
            return false;

    }

    private void maintainTree(AVLTreeNode node) {
        if(node == null)
            return;
        int leftHeight = node.getLeft() == null ? 0 : ((AVLTreeNode)node.getLeft()).getHeight();
        int rightHeight = node.getRight() == null ? 0 : ((AVLTreeNode)node.getRight()).getHeight();
        if(Math.abs(leftHeight - rightHeight) <= 1) {
            maintainTree((AVLTreeNode) node.getLeft());
            maintainTree((AVLTreeNode) node.getRight());
            return;
        }

        //This node's left child and right child height different larger than two
        if(leftHeight - rightHeight > 1) {
            //We can ensure that the left node will never be null, because if left node is null, then right height will be less than 0. We can safely get the left child of left
            int childLeftHeight = node.getLeft().getLeft() == null ? 0 : ((AVLTreeNode) node.getLeft().getLeft()).getHeight();
            int childRightHeight = node.getLeft().getRight() == null ? 0 : ((AVLTreeNode) node.getLeft().getRight()).getHeight();
            //left left, just simply rotate the node right
            if(childLeftHeight - childRightHeight > 0) {
                rotateRight(node);
                return;
            }
            else {
                rotateLeft(node.getLeft());
                rotateRight(node);
                return;
            }
        } else {
            int childLeftHeight = node.getRight().getLeft() == null ? 0 : ((AVLTreeNode) node.getRight().getLeft()).getHeight();
            int childRightHeight = node.getRight().getRight() == null ? 0 : ((AVLTreeNode) node.getRight().getRight()).getHeight();
            if(childRightHeight - childLeftHeight > 0) {
                rotateLeft(node);
                return;
            } else {
                rotateRight(node.getRight());
                rotateLeft(node);
                return;
            }
        }
    }
    //To use this method you have to make sure that the left child of this node is not null
    private void rotateRight(TreeNode node) {
        TreeNode parent = node.getParent();
        TreeNode left = node.getLeft();
        if(parent == null)
            root = left;
        else {
            if(parent.getLeft() == node)
                parent.setLeft(left);
            if(parent.getRight() == node)
                parent.setRight(left);
        }
        left.setParent(parent);
        node.setLeft(left.getRight());
        node.setParent(left);
        left.setRight(node);
    }

    private void rotateLeft(TreeNode node) {
        TreeNode parent = node.getParent();
        TreeNode right = node.getRight();
        if(parent == null)
            right.setParent(parent);
        else {
            if(parent.getLeft() == node)
                parent.setLeft(right);
            if(parent.getRight() == node)
                parent.setRight(right);
        }
        node.setRight(right.getLeft());
        node.setParent(right);
        right.setLeft(node);
    }

    private void maintainHeight() {
        maintainHeight((AVLTreeNode) root);
    }

    private int maintainHeight(AVLTreeNode node) {
        if(node == null)
            return 0;
        node.setHeight(Math.max(maintainHeight((AVLTreeNode) node.getLeft()) + 1, maintainHeight((AVLTreeNode) node.getRight()) + 1));
        return node.getHeight();
    }

    @Override
    public TreeNode<T> delete(T val) {
        TreeNode node = super.delete(val);
        if(node != null) {
            maintainHeight();
            maintainTree((AVLTreeNode) root);
        }
        return node;
    }

    public void test(TreeNode node) {
        rotateLeft(node);
    }

}
