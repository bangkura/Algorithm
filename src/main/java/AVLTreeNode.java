/**
 * Copyright (C) 2014 iQIYI.COM - All Rights Reserved
 * <p>
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * @description: The tree node for the AVL Tree
 * @author: Bangkura
 * @create: 2018-06-15 10:17
 **/

public class AVLTreeNode<T extends Comparable<T>> extends TreeNode<T>{
    private int height = 0;

    public AVLTreeNode(T val) {
        super(val);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
