// Time Complexity : O(n), where n is the number of nodes in the tree
//   - Each node is visited exactly once during level-order traversal
// Space Complexity : O(n), for the queue used in BFS (worst case when tree is a complete binary tree)

// This solution performs a level-order traversal (BFS) of the tree.
// For each level, we add the last node's value to the result list (rightmost node at that level).
// If we wanted the left view instead, we would add the first node at each level (i == 0).

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();

                // Add the last node in the current level to the result (right view)
                if (i == size - 1) {
                    res.add(curr.val);
                }

                // Add left and right children to the queue
                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
            }
        }

        return res;
    }
}


// Time Complexity : O(n), where n is the number of nodes in the tree
//   - Each node is visited once during level-order traversal
// Space Complexity : O(n), for the queue used in BFS (in the worst case, when the tree is a complete binary tree)

// This solution uses level-order traversal (BFS) to find whether nodes x and y are cousins.
// It ensures x and y are at the same level but not siblings (i.e., do not share the same parent).
// We check sibling condition explicitly and set flags to detect if both nodes are found at the same level.

class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            boolean x_found = false, y_found = false;

            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();

                // Check if x and y are children of the same parent (i.e., siblings)
                if (curr.left != null && curr.right != null) {
                    if ((curr.left.val == x && curr.right.val == y) ||
                            (curr.left.val == y && curr.right.val == x)) {
                        return false; // x and y are siblings, not cousins
                    }
                }

                // Check if current node is x or y
                if (curr.val == x) x_found = true;
                if (curr.val == y) y_found = true;

                // Add children to the queue for the next level
                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
            }

            // If both x and y are found at the same level and not siblings
            if (x_found && y_found) return true;

            // If only one of x or y is found at the current level, return false
            if (x_found || y_found) return false;
        }

        return false; // x and y not found in the tree
    }
}

