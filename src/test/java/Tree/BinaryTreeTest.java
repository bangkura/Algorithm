package Tree;

import Tree.BinaryTree;
import org.junit.Test;

/**
 * Copyright (C) 2014 iQIYI.COM - All Rights Reserved
 * <p>
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * @description: The test class of the binary tree
 * @author: Bangkura
 * @create: 2018-06-14 12:15
 **/

public class BinaryTreeTest {
    @Test
    public void Test() {
        BinaryTree<Integer> tree = new BinaryTree<Integer>(13);
        tree.insert(8);
        tree.insert(17);
        tree.insert(1);
        tree.insert(11);
        tree.insert(15);
        tree.insert(25);
        tree.insert(11);
        tree.insert(15);
        tree.insert(25);

        TreePrinter.printTree(tree.search(13));
    }

}
