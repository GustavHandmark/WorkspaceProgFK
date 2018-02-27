package exprtree;

public class ExprTree {
	private ExprNode root;

	public ExprTree() {
		root = null;
	}

	/**
	 * Skapar ett binärt träd med innehållet data i roten och med leftTree som
	 * vänster underträd och rightTree som höger underträd.
	 */
	public ExprTree(String element, ExprTree leftTree, ExprTree rightTree) {
		root = new ExprNode(element);
		if (leftTree != null) {
			root.left = leftTree.root;
		} else {
			root.left = null;
		}
		if (rightTree != null) {
			root.right = rightTree.root;
		} else {
			root.right = null;
		}
	}

	/**
	 * Returnerar en teckensträng som representerar uttrycket. Teckensträngen
	 * innehåller parenteser runt alla deluttryck, utom runt talen.
	 */
	public String fullParen() {
		if(root==null){
			return "";
		} else{
			StringBuilder sb = new StringBuilder();
			recFullParen(root, sb);
			return sb.toString();
		}
	}
	private void recFullParen(ExprNode n, StringBuilder sb){
		if(n.left==null && n.right == null){
			sb.append(n.element);
		}
		else {
			sb.append("(");
			recFullParen(n.left,sb);
			sb.append(n.element);
			recFullParen(n.right,sb);
			sb.append(")");
			
		}
		
	}

	private static class ExprNode {
		public ExprNode(String element) {
			this.element = element;
		}

		String element;
		ExprNode left;
		ExprNode right;
	}

	public static void main(String[] args) {
		ExprTree empty = new ExprTree();
		System.out.println(empty.fullParen());

		ExprTree leftOp = new ExprTree("9", null, null);
		ExprTree rightOp = new ExprTree("2", null, null);
		ExprTree minus = new ExprTree("-", leftOp, rightOp);

		leftOp = new ExprTree("5", null, null);
		ExprTree mult = new ExprTree("*", leftOp, minus);

		rightOp = new ExprTree("7", null, null);
		ExprTree tree = new ExprTree("+", mult, rightOp);
		System.out.println(tree.fullParen());
	}
	
	
/*	private class catalogues {
		public catalogues(){
			//osv osv
		}
		
		boolean isFile();
		boolean isDirectory();
		long length();
		File[] listFiles();
		
		public static List<File> biggerThan(File file, int size){
			List<File> list = new List<File>();
			recBiggerThan(file,size,list);
			return list;
		}
		private static void recBiggerThan(File file, int size, List<File> list){
			if(file.isFile() && file.length()> size){
				list.add(file);
				}
			else if(file.isDirectory()){
				for(File file:file.listFiles()){
					recBiggerThan(file,size,list);
				}			
			}
		}
	}
	
	while(!queue.isEmpty()){
		element<E> e = queue.getFirst();
		if(e.left !=null){
			queue.add(e.left);
		} else if(e.right!=null){
			queue.add(e.right);
		}
		e.dostuff();
	
*/
}

