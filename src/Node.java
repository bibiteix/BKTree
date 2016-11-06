import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class Node
{
    public String word;
    public Hashtable Children;
    
 
    public Node() {
    	
    }
 
    public Hashtable getChildren() {
		return Children;
	}

	public void setChildren(Hashtable children) {
		Children = children;
	}

	public String getWord() {
		return word;
	}


	public void setWord(String word) {
		this.word = word;
	}


	public Node(String word)
    {
        this.word = word.toLowerCase();
    }
 
    public Node funcaoQueRetornaONode (int key)
    {
        return (Node)Children.get(key); 
    }
 
    public ArrayList Keys()
    {
            if(Children == null)
                return new ArrayList();
            return Collections.list(Children.keys());
    }
 
    public boolean ContainsKey(int key)
    {
        return Children != null && Children.contains(key);
    }
 
    public void AddChild(int key, String word)
    {
        if(this.Children == null)
            Children = new Hashtable();
        this.Children.put(key, new Node(word));
    }
}