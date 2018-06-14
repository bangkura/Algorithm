import java.util.*;

/**
 * Copyright (C) 2014 iQIYI.COM - All Rights Reserved
 * <p>
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * @description:
 * @author: Bangkura
 * @create: 2018-06-14 13:21
 **/

public abstract class AbstractTree<T extends Comparable<T>> {
    protected TreeNode<T> root;
    private int leftMargin;
    private int rightMargin;

    private List<Integer> indexs;
    private List<T> vals;

    public abstract void insert(T val);

    public abstract TreeNode<T> delete(T val);


    private void getLeftMargin(TreeNode node, int margin) {
        leftMargin = Math.max(leftMargin, margin);
        if(node.getLeft() != null)
            getLeftMargin(node.getLeft(), margin + node.getLeft().getVal().toString().length());
        if(node.getRight() != null)
            getLeftMargin(node.getRight(), margin - node.getRight().getVal().toString().length());
        return;
    }

    private void getRightMargin(TreeNode node, int margin) {
        rightMargin = Math.max(rightMargin, margin);
        if(node.getRight() != null)
            getLeftMargin(node.getRight(), margin + node.getRight().getVal().toString().length());
        if(node.getLeft() != null)
            getLeftMargin(node.getLeft(), margin - node.getLeft().getVal().toString().length());
        return;
    }

    public int getLeftMargin() {
        getLeftMargin(root, 0);
        return leftMargin;
    }

    public int getRightMargin() {
        getRightMargin(root, 0);
        return rightMargin;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        StringBuilder builder = new StringBuilder();
        int leftMargin = getLeftMargin();
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        Queue<Integer> lineQueue = new LinkedList();
        Queue<Integer> marginQueue = new LinkedList();

        int currentLine = 0;
        nodeQueue.offer(root);
        lineQueue.offer(0);
        marginQueue.offer(leftMargin);

        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int line = lineQueue.poll();
            int margin = marginQueue.poll();
            if(currentLine != line) {
                result = result.append(builder.toString() + '\n');
                builder = new StringBuilder();
                currentLine = line;
            }
            int size = margin - builder.length();
            for(int i = 0; i < size; ++i)
                builder.append(" ");
            builder.append(node.getVal());
            if(node.getLeft() != null) {
                nodeQueue.offer(node.getLeft());
                lineQueue.offer(line + 1);
                marginQueue.offer(margin - 1);
            }
            if(node.getRight() != null) {
                nodeQueue.offer(node.getRight());
                lineQueue.offer(line + 1);
                marginQueue.offer(margin + 1);
            }
        }
        result.append(builder.toString());
        return result.toString();
    }

    private int getMaxNodeLength(TreeNode node) {
        if(node== null)
            return Integer.MIN_VALUE;
        int nodeLength = node.getVal().toString().length();
        return Math.max(nodeLength, Math.max(getMaxNodeLength(node.getRight()), getMaxNodeLength(node.getLeft())));
    }

    private int getMaxNodeLength() {
        return getMaxNodeLength(root);
    }

    private int getTreeDepth(TreeNode node, int depth) {
        if(node == null)
            return depth;
        depth++;
        return Math.max(getTreeDepth(node.getLeft(), depth), getTreeDepth(node.getRight(), depth));
    }

    private int getTreeDepth() {
        return getTreeDepth(root, 0);
    }

    private void populateTreeList(TreeNode node, int index) {
        if(node == null)
            return;
        indexs.add(index);
        vals.add((T)node.getVal());

        populateTreeList(node.getLeft(), index * 2 + 1);
        populateTreeList(node.getRight(), index * 2 + 2);
    }

    private void populateTreeList() {
        indexs = new ArrayList<Integer>();
        vals = new ArrayList<T>();
        populateTreeList(root, 0);
    }

    public void getTreeString() {
        populateTreeList();
        StringBuilder emptyLine = new StringBuilder();
        int depth = getTreeDepth();
        int cell = getMaxNodeLength();
        for(int i = 0; i < Math.pow(2, depth - 1) * cell; ++i)
            emptyLine.append(" ");
        for(int i = 0; i < depth; ++i) {
            StringBuilder line = new StringBuilder(emptyLine);
            //for the line i, we only need the index between 2^(i) - 1 to 2^(i + 1) - 2
            for(int j = 0; j < indexs.size(); ++j) {
                int index = indexs.get(j);
                if(index >= Math.pow(2, i) - 1 && index <= Math.pow(2, i + 1) - 2) {
                    //Here we get the index and the val
                    T val = vals.get(j);
                    //We calculate the actuall index in this line we should subtract it by 2^(i) - 1;
                    int start = (index - (int)Math.pow(2, i) + 1)*cell + (index - (int)Math.pow(2, i + 1) - 2);
                    line.replace(start, start + cell, populateCell(val.toString(), cell));
                }
            }
            System.out.println(line);
            line = new StringBuilder(emptyLine);
        }
    }

    private String populateCell(String val, int length) {
        for(int i = 0; i < length - val.length(); ++i)
            val += " ";
        return val;
    }

    public void getTest() {
        System.out.print(getTreeDepth());
    }
}
