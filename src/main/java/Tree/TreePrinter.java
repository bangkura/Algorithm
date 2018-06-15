/**
 * Copyright (C) 2014 iQIYI.COM - All Rights Reserved
 * <p>
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * @description:
 * @author: Bangkura
 * @create: 2018-06-15 14:14
 **/

package Tree;

import java.util.ArrayList;
import java.util.List;

public class TreePrinter {
    private static int maxValLength;

    private static int getMaxTreeLevel(TreeNode node) {
        if(node == null)
            return 0;
        return Math.max(getMaxTreeLevel(node.getLeft()) + 1, getMaxTreeLevel(node.getRight()) + 1);
    }

    public static void printTree(TreeNode node) {
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(node);
        maxValLength = getMaxValLength(node);
        printTree(nodes, 1, getMaxTreeLevel(node));
    }

    private static void printTree(List<TreeNode> nodes, int currentLevel, int maxLevel) {
        if(currentLevel > maxLevel)
            return;
        //Firstly we calculate the space between two nodes
        //The number of the nodes in this level
        int nSize = (int)Math.pow(2, currentLevel - 1);
        //The total lenghth of every line
        int lineWidth = (int)Math.pow(2, maxLevel - 1) * (maxValLength + 2);
        //The space between two nodes
        int spaceWidth = (lineWidth - nSize * (maxValLength + 2))/(nSize + 1);

        //Then every time we print the space firstly
        printNSpaces(spaceWidth);
        //Then we print the node's value
        List<TreeNode> newNodes = new ArrayList<>();
        for(TreeNode node : nodes) {
            if(node == null) {
                printNSpaces(maxValLength + 2);
                printNSpaces(spaceWidth);
                newNodes.add(null);
                newNodes.add(null);
            }
            else {
                printNSpaces((int) Math.ceil((maxValLength + 2 - node.getVal().toString().length())/2.0));
                System.out.print(node.getVal().toString());
                printNSpaces((int) Math.floor((maxValLength + 2 - node.getVal().toString().length())/2.0));
                printNSpaces(spaceWidth);
                newNodes.add(node.getLeft());
                newNodes.add(node.getRight());
            }
        }
        System.out.println("");

        //Then we print the slash
        //We first calculate the level of the slashes which equals to the distance between the first node of these two level which means that it equals to the difference between the space
        if(currentLevel < maxLevel) {
            int nextNSize = (int)Math.pow(2, currentLevel);
            int nextSpaceWidth = (lineWidth - nextNSize * (maxValLength + 2))/(nextNSize + 1);
            int slashLevel = spaceWidth - nextSpaceWidth;
            for(int i = 0; i < slashLevel; ++i) {
                printNSpaces(spaceWidth - i);
                for(TreeNode node : nodes) {
                    if(node == null || node.getLeft() == null)
                        System.out.print(' ');
                    else
                        System.out.print('/');
                    printNSpaces(maxValLength + 2 + 2 * i - 2);
                    if(node == null || node.getRight() == null)
                        System.out.print(' ');
                    else
                        System.out.print('\\');
                    printNSpaces(spaceWidth - 2 * i);
                }
                System.out.println("");
            }
        }

        printTree(newNodes, currentLevel + 1, maxLevel);
    }

    private static int getMaxValLength(TreeNode node) {
        if(node == null)
            return Integer.MIN_VALUE;
        if(node.getLeft() == null && node.getRight() == null)
            return node.getVal().toString().length();
        return Math.max(Math.max(getMaxValLength(node.getLeft()), getMaxValLength(node.getRight())), node.getVal().toString().length());
    }

    private static void printNSpaces(int n) {
        for(int i = 0; i < n; ++i)
            System.out.print(" ");
        return;
    }

    public static int testgetmaxValLength(TreeNode node) {
        return getMaxValLength(node);
    }
}
