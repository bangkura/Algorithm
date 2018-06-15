/**
 * Copyright (C) 2014 iQIYI.COM - All Rights Reserved
 * <p>
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * @description:
 * @author: Bangkura
 * @create: 2018-06-15 13:29
 **/

package Tree;

import static Tree.TreeNodeColor.*;
import static Tree.RedBlackTreeNode.*;

public class RedBlackTree<T extends Comparable<T>> extends AbstractTree<T> {

    @Override
    public boolean insert(T val) {
        return false;
    }

    @Override
    public TreeNode<T> delete(T val) {
        return null;
    }
}
