import java.util.LinkedList;
import java.util.Queue;

/**
 * Copyright (C) 2014 iQIYI.COM - All Rights Reserved
 * <p>
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * @description: The binary tree algorithm test
 * @author: Bangkura
 * @create: 2018-06-14 11:42
 **/

public class BinaryTree<T extends Comparable<T>> extends AbstractTree<T>{

    public BinaryTree(T val) {
        root = new TreeNode<T>(val);
    }

    public BinaryTree() {}

    @Override
    public boolean insert(T val) {
        TreeNode<T> treeNode = new TreeNode<T>(val);
        return insert(treeNode, root);
    }


    protected boolean insert(TreeNode<T> node2Insert, TreeNode<T> node) {
        if(node2Insert.getVal().compareTo(node.getVal()) > 0) {
            if(node.getRight() == null) {
                node.setRight(node2Insert);
                node2Insert.setParent(node);
                return true;
            }
            else
                return insert(node2Insert, node.getRight());
        } else if (node2Insert.getVal().compareTo(node.getVal()) < 0){
            if(node.getLeft() == null) {
                node.setLeft(node2Insert);
                node2Insert.setParent(node);
                return true;
            }
            else
                return insert(node2Insert, node.getLeft());
        }
        else
            return false;
    }


    @Override
    public TreeNode<T> delete(T val) {
        TreeNode targetNode = getTreeNodeByVal(val, root);
        if(targetNode == null)
            return null;
        //The node has only one child or does not have child
        if(targetNode.getRight() == null || targetNode.getLeft() == null) {
            TreeNode subTree = targetNode.getLeft() == null ? targetNode.getRight() : targetNode.getLeft();
            if(targetNode.getParent().getLeft() == targetNode)
                targetNode.getParent().setLeft(subTree);
            else
                targetNode.getParent().setRight(subTree);
            return targetNode;
        }
        //The node has both left and right child, then we will find the successor of this node
        TreeNode successor = getSuccessor(targetNode.getRight());
        targetNode.setVal(successor.getVal());
        successor.setVal(val);
        return delete(val);
    }

    private TreeNode getSuccessor(TreeNode node) {
        if(node.getLeft() == null)
            return node;
        return getSuccessor(node.getLeft());
    }

    public TreeNode search(T val) {
        return getTreeNodeByVal(val, root);
    }

    private TreeNode getTreeNodeByVal(T val, TreeNode node) {
        if(node == null)
            return null;
        if(node.getVal().compareTo(val) == 0)
            return node;
        if(node.getLeft() != null && node.getVal().compareTo(val) > 0)
            return getTreeNodeByVal(val, node.getLeft());
        if(node.getRight() != null && node.getVal().compareTo(val) < 0)
            return getTreeNodeByVal(val, node.getRight());
        return null;
    }
}
