package test;
import java.util.ArrayList;
import java.util.HashMap;

import io.DocSource;
import soln.io.FileDocSource;
import tokenizer.IndexingTokenizer;

public class TestFileDocSource {
	public static void main(String[] args) {
		FileDocSource fds = new FileDocSource("files");
		System.out.println(fds.getNumDocs());
		System.out.println("----------");
		System.out.println(fds.getDoc(1));
		System.out.println("----------");
		IndexingTokenizer it= new IndexingTokenizer();
		System.out.println(it.tokenize(fds.getDoc(1)));
//		ArrayList<String[]> alal = new ArrayList<String[]>();
//		alal.add(new String[] {"A","B","C","D","E","F","A","B","A"});
//		alal.add(new String[] {"A","B","C","D","E","F","A","B","AD"});
//		alal.add(new String[] {"A","B","C","D","E","F","A","B"});
//		alal.add(new String[] {"A","B","C","E","F","A","B"});
//		HashMap<String,Integer> _docFreq = new HashMap<String,Integer>();
////		HashMap<Integer,Integer> _temp = new HashMap<Integer,Integer>();
//		HashMap<String,HashMap<Integer,Integer>> _hm = new HashMap<String,HashMap<Integer,Integer>>();
//		for (Integer i=0; i<alal.size();i++) {
//			for (String j : alal.get(i)) {
//				if (!_hm.containsKey(j)) {
//					_hm.put(j,new HashMap<Integer,Integer>());
//					_hm.get(j).put(i, 1);
//					_docFreq.put(j, 1);
//				}
//				else if (_hm.containsKey(j) && _hm.get(j).containsKey(i))
//					_hm.get(j).replace(i, _hm.get(j).get(i)+1);
//				else {
//					_hm.get(j).put(i, 1);
//					_docFreq.replace(j, _docFreq.get(j)+1);
//				}
//			}
//		}
//		System.out.println(_docFreq);
	}
}