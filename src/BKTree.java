import java.util.ArrayList;
import java.util.List;
 
public class BKTree
{
    private static Node _Root;
 
    public static void Add(String word)
    {
        word = word.toLowerCase();
        if (_Root == null)
        {
            _Root = new Node(word);
            return;
        }
 
        Node curNode = _Root;
 
        int dist = LevenshteinDistance(curNode.word, word);
        while (curNode.ContainsKey(dist))
        {
            if (dist == 0) return;
 
            curNode = curNode.funcaoQueRetornaONo(dist);
            dist = LevenshteinDistance(curNode.word, word);
        }
 
        curNode.AddChild(dist,word);
    }
 
    public static List<String> Search(String word, int d)
    {
        List<String> rtn = new ArrayList<String>();
        word = word.toLowerCase();
 
        RecursiveSearch(_Root, rtn, word, d);
 
        return rtn;
    }
 
    private static void RecursiveSearch(Node node, List<String> rtn, String word, int d )
    {
        int curDist = LevenshteinDistance(node.word, word);
        int minDist = curDist - d;
        int maxDist = curDist + d;
 
        if (curDist <= d)
            rtn.add(node.word);
 
 //       foreach (int key in node.Keys.Cast<int>().Where(key => minDist <= key && key <= maxDist))
 //       {
 //           RecursiveSearch(node[key], rtn, word, d);
 //       }
        
        //for each key in node
        int size = node.Keys().size();
        for (int i = 0; i < size; i++) {
        	int currentKey = node.Keys().indexOf(i);
        	if ((currentKey >= minDist) && (minDist <= currentKey) && (currentKey <= maxDist)) {
                RecursiveSearch(node.funcaoQueRetornaONo(i), rtn, word, d);
        	}
        }
    }
 
    //monta a matriz que gera a distancia minima para que uma string se torne outra 
    public static int LevenshteinDistance(String first, String second)
    {
        if (first.length() == 0) 
        	return second.length();
        if (second.length() == 0) 
        	return first.length();
 
        int lenFirst = first.length();
        int lenSecond = second.length();
 
        int[][] d = new int[lenFirst + 1][lenSecond + 1];
        
 
        for (int i = 0; i <= lenFirst; i++)
            d[i][0] = i;
 
        for (int i = 0; i <= lenSecond; i++)
            d[0][i] = i;
 
        for (int i = 1; i <= lenFirst; i++)
        {
            for (int j = 1; j <= lenSecond; j++)
            {
            	int match;
            	if (first.charAt(i - 1) == second.charAt(j - 1)) {
            		match = 0;
            	}
            	else {
            		match = 1;
            	}
            	
                d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1), d[i - 1][j - 1] + match);
            }
        }
        
        //o ultimo elemento da matriz é a distancia minima
        return d[lenFirst][lenSecond];
    }
}
 




