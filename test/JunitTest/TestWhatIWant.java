package JunitTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.TreeSet;

import org.junit.Test;

import index.DocScore;
import index.Index;
import index.SortedDocScore;
import io.FileDocSource;
import score.TFIDFScoringFun;
import tokenizer.IndexingTokenizer;
import tokenizer.Tokenizer;

public class TestWhatIWant {

	@Test
	public void testTokenize() {
		Tokenizer tok = new IndexingTokenizer();
		ArrayList<String> tokens = tok.tokenize("A state-of-the-art product.");
		assertEquals("Success lowercase", "a", tokens.get(0));
		assertEquals("Success hyphen test", "state-of-the-art", tokens.get(1));
		assertEquals("Success simple token", "product", tokens.get(2));
	}

	@Test
	public void testSortedDocScore() {
		SortedDocScore doc1 = new SortedDocScore(1.0,0,"That Arizona Sky, that's burning in your eyes!");
		SortedDocScore doc2 = new SortedDocScore(0.9,1,"You look at me, and baby wanna catch on fire.");
		SortedDocScore doc3 = new SortedDocScore(0.9,2,"It's buried in my soul, like california gold");
		SortedDocScore doc4 = new SortedDocScore(1.5,3,"You find the light in me that I couldn't find");
		TreeSet<SortedDocScore> _tsSortedDoc = new TreeSet<SortedDocScore>();
		_tsSortedDoc.add(doc1);
		_tsSortedDoc.add(doc2);
		_tsSortedDoc.add(doc3);
		_tsSortedDoc.add(doc4);
		ArrayList<DocScore> _alDoc = new ArrayList<DocScore>(_tsSortedDoc);
		assertEquals("doc4 rank first", doc4, _alDoc.get(0));
		assertEquals("doc3 is before doc2", doc2, _alDoc.get(3));
	}
	
	@Test
	public void testDocFreq() {
		Index index = new index.InvertedIndex(new FileDocSource("files/Part1/awards_1994"), new IndexingTokenizer(), new TFIDFScoringFun());
		assertEquals("Equipment has not written in those document, so it will return 1", "1", index.getDocumentFreq("Equipment").toString());
	}
	
	@Test
	public void testGetSortedSearchResults() {
		Index index = new index.InvertedIndex(new FileDocSource("files/Part1"), new IndexingTokenizer(), new TFIDFScoringFun());
		index.buildIndex();
		Index solnindex = new soln.index.InvertedIndex(new soln.io.FileDocSource("files/Part1"), 
				   new soln.tokenizer.IndexingTokenizer(), 
				   new soln.score.TFIDFScoringFun());
		solnindex.buildIndex();
		assertEquals("They are the same", solnindex.getSortedSearchResults("a"), index.getSortedSearchResults("a"));
	}
	
	@Test 
		public void testGetDoc() {
		FileDocSource fds = new FileDocSource("files/Part1");
		assertEquals("Their content is the same", new soln.io.FileDocSource("files/Part1").getDoc(1), fds.getDoc(1));
	}
}
