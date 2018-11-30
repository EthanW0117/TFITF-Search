package index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import io.DocSource;
import io.FileDocSource;
import score.TermScoringFun;
import tokenizer.IndexingTokenizer;
import tokenizer.Tokenizer;

public class InvertedIndex extends Index{
	
	private HashMap<String,HashMap<Integer,Integer>> _index;
	private HashMap<String,Integer> _docFreq;

	public InvertedIndex(DocSource doc_source, Tokenizer tokenizer, TermScoringFun scoring) {
		super(doc_source, tokenizer, scoring);
		
		_index = new HashMap<String,HashMap<Integer,Integer>>();
		_docFreq = new HashMap<String,Integer>();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void buildIndex() {

		ArrayList<String> al = new ArrayList<String>();
		for (int i = 0; i < _docSource.getNumDocs(); i++) {
			al = _tokenizer.tokenize(_docSource.getDoc(i));
			for(String s : al) {
				if (!_index.containsKey(s)) {
					_index.put(s,new HashMap<Integer,Integer>());
					_index.get(s).put(i, 1);
					_docFreq.put(s, 1);
				}
				else if (_index.containsKey(s) && _index.get(s).containsKey(i)){
					_index.get(s).replace(i, _index.get(s).get(i) + 1);
				}
				else {
					_index.get(s).put(i,1);
					_docFreq.replace(s, _docFreq.get(s) + 1);
				}
			}
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getDocumentFreq(String term) {
		// TODO Auto-generated method stub
		if (!_docFreq.containsKey(term))
			return 1;
		return _docFreq.get(term);
	}

	@Override
	public ArrayList<DocScore> getSortedSearchResults(String query) {
		// TODO Auto-generated method stub
		double score = 0;
		ArrayList<String> al = _tokenizer.tokenize(query);
		TreeSet<SortedDocScore> _tsSorted = new TreeSet<SortedDocScore>();
		for (int i = 0; i < _docSource.getNumDocs(); i++) {
			boolean inEveryDoc = false;
			for (String s : al) {
				if (_index.containsKey(s) && _index.get(s).containsKey(i))
				score += _scoring.scoreToken(s, (_index.get(s).get(i)));
				if (_docSource.getNumDocs() == this.getDocumentFreq(s))
					inEveryDoc = true;
			}
			if (score != 0 || inEveryDoc)
			_tsSorted.add(new SortedDocScore(score,i,_docSource.getDoc(i)));
			score = 0;
		}
		ArrayList<DocScore> _alDocScore = new ArrayList<DocScore>(_tsSorted);
		
		return _alDocScore;
	}

}
