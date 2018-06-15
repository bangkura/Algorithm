/**
 * Copyright (C) 2014 iQIYI.COM - All Rights Reserved
 * <p>
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * @description:
 * @author: Bangkura
 * @create: 2018-06-15 13:30
 **/

package Tree;

import static Tree.TreeNodeColor.BLACK;

public class RedBlackTreeNode<T extends Comparable<T>> extends TreeNode<T> {
    public static RedBlackTreeNode NIL = new RedBlackTreeNode(0).setColor(BLACK);
    private TreeNodeColor color = TreeNodeColor.RED;

    public RedBlackTreeNode(T val) {
        super(val);
        setLeft(NIL);
        setRight(NIL);
        setParent(NIL);
    }

    public TreeNodeColor getColor() {
        return color;
    }

    public RedBlackTreeNode setColor(TreeNodeColor color) {
        this.color = color;
        return this;
    }
}
