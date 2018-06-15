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

    private List<Integer> indexs;
    private List<T> vals;

    public abstract boolean insert(T val);

    public abstract TreeNode<T> delete(T val);


    private void getLeftMargin(TreeNode node, int margin) {
        leftMargin = Math.max(leftMargin, margin);
        if(node.getLeft() != null)
            getLeftMargin(node.getLeft(), margin + node.getLeft().getVal().toString().length());
        if(node.getRight() != null)
            getLeftMargin(node.getRight(), margin - node.getRight().getVal().toString().length());
        return;
    }

    public int getLeftMargin() {
        getLeftMargin(root, 0);
        return leftMargin;
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

    private String populateCell(String val, int length) {
        for(int i = 0; i < length - val.length(); ++i)
            val += " ";
        return val;
    }

}
