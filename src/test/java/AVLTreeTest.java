import org.junit.Test;

/**
 * Copyright (C) 2014 iQIYI.COM - All Rights Reserved
 * <p>
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * @description:
 * @author: Bangkura
 * @create: 2018-06-15 10:48
 **/

public class AVLTreeTest {
    @Test
    public void test() {
        AVLTree<Integer> tree = new AVLTree<Integer>(15);
        tree.insert(5);
        //System.out.println(tree);
        tree.insert(2);
        System.out.println(tree);
    }
}
