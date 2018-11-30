package index;


public class SortedDocScore extends DocScore implements Comparable<Object>{

	public SortedDocScore(double score, int doc_id, String content) {
		super(score, doc_id, content);
	}
	
	public SortedDocScore(DocScore ds) {
		super(ds);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if (o instanceof SortedDocScore) {
			SortedDocScore e = (SortedDocScore) o;
//			IndexingTokenizer it = this._content.
			if(this.equals(e))
				return 0;
			else if (_score == e._score && _content.compareTo(e._content) < 0) 
				return -1;
			else if (_score > e._score)
				return -1;
			else if (_score < e._score)
				return 1;
		}
			
		return 1;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof SortedDocScore) {
			SortedDocScore e = (SortedDocScore) o;
			return _score == e._score && _docID == e._docID && _content.equals(e._content);
		}
		return false;
	}
	
	//Optional 
	public int hashCode() {
		int hc;
		hc = (int)_score + _content.hashCode() * 37 + _docID * 37 * 37;
		return hc;
	}
}
