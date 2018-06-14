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
        BinaryTree<Integer> tree = new BinaryTree<Integer>(15);
        tree.insert(5);
        tree.insert(16);
        tree.insert(3);
        tree.insert(12);
        tree.insert(20);
        tree.insert(10);
        tree.insert(13);
        tree.insert(18);
        tree.insert(23);
        tree.insert(6);
        tree.insert(7);
        tree.delete(5);
        System.out.println(tree);

        tree.getTreeString();
    }

}
