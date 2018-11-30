package test;
import index.SortedDocScore;;

public class TestSortedDocScore {
	public static void main(String[] args) {
		SortedDocScore doc1 = new SortedDocScore(1.0,0,"The quick brown fo jumps over the lazy dog");
		SortedDocScore doc1copy= new SortedDocScore(doc1);
		SortedDocScore doc2 = new SortedDocScore(0.9,1,"Amazingly few discotheques provide jukeboxes");
		System.out.println(("Does doc1 equal doc1Copy?" + (doc1.equals(doc1copy) ? "yes" : "no")));
		System.out.println(("Does doc1 equal doc2?" + (doc1.equals(doc2) ? "yes" : "no")));
		System.out.println(("Does doc1 come before doc2?" + (doc1.compareTo(doc2) == -1 ? "yes" : "no")));
		System.out.println(doc1.hashCode());
		System.out.println(doc2.hashCode());
		
	}
}
