package model;
public class Dictionary<K, V> {
    public Dictionary() {
		super();
		// TODO Auto-generated constructor stub
	}

	private K key;
    private V value;

    public Dictionary(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
 }
