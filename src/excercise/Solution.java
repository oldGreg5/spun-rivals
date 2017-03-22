package excercise;

import java.util.ArrayList;
import java.util.Scanner;

enum Color {
	RED, GREEN
}

abstract class Tree {

	private int value;
	private Color color;
	private int depth;

	public Tree(int value, Color color, int depth) {
		this.value = value;
		this.color = color;
		this.depth = depth;
	}

	public int getValue() {
		return value;
	}

	public Color getColor() {
		return color;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {

	private ArrayList<Tree> children = new ArrayList<>();

	public TreeNode(int value, Color color, int depth) {
		super(value, color, depth);
	}

	public void accept(TreeVis visitor) {
		visitor.visitNode(this);

		for (Tree child : children) {
			child.accept(visitor);
		}
	}

	public void addChild(Tree child) {
		children.add(child);
	}

	public ArrayList<Tree> getChildren() {
		return children;
	}
}

class TreeLeaf extends Tree {

	public TreeLeaf(int value, Color color, int depth) {
		super(value, color, depth);
	}

	public void accept(TreeVis visitor) {
		visitor.visitLeaf(this);
	}
}

abstract class TreeVis {
	public abstract int getResult();

	public abstract void visitNode(TreeNode node);

	public abstract void visitLeaf(TreeLeaf leaf);

}

class SumInLeavesVisitor extends TreeVis {
	public int getResult() {
		// implement this
		return 0;
	}

	public void visitNode(TreeNode node) {
		// implement this
	}

	public void visitLeaf(TreeLeaf leaf) {
		// implement this
	}
}

class ProductOfRedNodesVisitor extends TreeVis {
	public int getResult() {
		// implement this
		return 1;
	}

	public void visitNode(TreeNode node) {
		// implement this
	}

	public void visitLeaf(TreeLeaf leaf) {
		// implement this
	}
}

class FancyVisitor extends TreeVis {
	public int getResult() {
		// implement this
		return 0;
	}

	public void visitNode(TreeNode node) {
		// implement this
	}

	public void visitLeaf(TreeLeaf leaf) {
		// implement this
	}
}

class Edge {
	int from;
	int to;
}

public class Solution {

	public static Tree solve() {
		Scanner in = new Scanner(System.in);
		int elements = in.nextInt();
		int values[] = new int[elements];
		Color colors[] = new Color[elements];
		Edge edges[] = new Edge[elements - 1];
		int depth[] = new int[elements];

		for (int i = 0; i < elements; i++) {
			values[i] = in.nextInt();
		}

		for (int i = 0; i < elements; i++) {
			if (in.nextInt() == 0) {
				colors[i] = Color.RED;
			} else {
				colors[i] = Color.GREEN;
			}
		}

		for (int i = 0; i < elements - 1; i++) {
			edges[i] = new Edge();
			edges[i].from = in.nextInt();
			edges[i].to = in.nextInt();
		}

		// initialize tree with nodes or leafs
		Tree myTree[] = new Tree[elements];
		for (int i = 0; i < elements; i++) {
			boolean isNode = false;
			for (int j = 0; j < edges.length; j++) {
				if (edges[j].from - 1 == i) {
					isNode = true;
				}
			}
			if (isNode) {
				myTree[i] = new TreeNode(values[i], colors[i], 0);

			} else {
				myTree[i] = new TreeLeaf(values[i], colors[i], 0);
			}
		}

		// populate nodes with children with depth
		int level = 1;
		for (int i = 0; i < elements; i++) {
			if (myTree[i] instanceof TreeNode) {
				for (int j = 0; j < edges.length; j++) {
					if (edges[j].from - 1 == i) {
						myTree[edges[j].to - 1].setDepth(level);
						// System.out.println("depth:" + myTree[edges[j].to -
						// 1].getDepth());
						// System.out.println("adding child:" +
						// myTree[edges[j].to - 1].getValue());
						((TreeNode) myTree[i]).addChild(myTree[edges[j].to - 1]);
					}
				}
				level++;
				// System.out.println("node: " + myTree[i].getValue() + "/" +
				// myTree[i].getColor().toString() + "/"
				// + myTree[i].getDepth() + "/" + ((TreeNode)
				// myTree[i]).getChildren().size());
				// for (int k = 0; k < ((TreeNode)
				// myTree[i]).getChildren().size(); k++) {
				// System.out.println(" child " + k + ":" + ((TreeNode)
				// myTree[i]).getChildren().get(k).getValue());
				// }

			}
			// else {
			// System.out.println("leaf: " + myTree[i].getValue() + "/" +
			// myTree[i].getColor().toString() + "/"
			// + myTree[i].getDepth());
			// }
		}

		return myTree[0];
		// read the tree from STDIN and return its root as a return value of
		// this function
	}

	public static void main(String[] args) {
		Tree root = solve();
		System.exit(0);
		SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
		ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
		FancyVisitor vis3 = new FancyVisitor();

		root.accept(vis1);
		root.accept(vis2);
		root.accept(vis3);

		int res1 = vis1.getResult();
		int res2 = vis2.getResult();
		int res3 = vis3.getResult();

		System.out.println(res1);
		System.out.println(res2);
		System.out.println(res3);
	}
}