package score;

import index.Index;

public class TFIDFScoringFun implements TermScoringFun {
	
	private Index _index;
	@Override
	public void init(Index s) {
		// TODO Auto-generated method stub
		_index = s;
	}

	@Override
	public double scoreToken(String term, int freq) {
		// TODO Auto-generated method stub
		int N = _index.getDocSource().getNumDocs();
//		double _idf = Math.log10((double)N/(double)_index.getDocumentFreq(term));
		double score = (1+Math.log10(freq))* Math.log10((double)N/(double)_index.getDocumentFreq(term));;
		return score;
	}

}
