package singleLinkedList;

public class lltest {
		
	public static void main(String[] args){
		SingleLinkedList<Integer> linkedlist = new SingleLinkedList<Integer>();
		for(int i =3; i>0; i--){
			linkedlist.addFirst(i);
		}
		System.out.println(linkedlist.toString());
	}
	

}
