package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private Entry<K, V>[] table;
	private int capacity;
	private double loadfactor;
	private int size;

	public SimpleHashMap() {
		capacity = 16;
		loadfactor = 0.75;
		size = 0;
		table = (Entry<K, V>[]) new Entry[capacity];

	}

	public SimpleHashMap(int capacity) {
		this.capacity = capacity;
		loadfactor = 0.75;
		size = 0;
		table = (Entry<K, V>[]) new Entry[capacity];

	}

	public String show() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < table.length; i++) {
			sb.append(i + "   ");
			Entry<K, V> current = table[i];
			while (current != null) {
				sb.append("   " + current.toString());
				current = current.next;
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public V get(Object obj) {
		K key = (K) obj;
		Entry<K, V> e = find(index(key), key);
		if (e == null) {
			return null;
		} else {
			return e.getValue();
		}
	}

	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public V put(K key, V value) {
		double load = (double) size() / capacity;
		if (load > loadfactor) {
			rehash();
		}
		Entry<K, V> a = new Entry<K, V>(key, value);
		if (table[index(key)] == null) { // Index empty, put new entry.
			table[index(key)] = a;
			size++;
			return null;
		}

		Entry<K, V> e = find(index(key), key);
		if (e != null) { // if key already exists, update value;
			return e.setValue(value);
		} else { // Index not empty, key doesn't exist, put at end
			Entry<K, V> current = table[index(key)];
			while (current.next != null) {
				current = current.next;
			}
			current.next = a;
			size++;
			return null;

		}
	}

	@Override
	public V remove(Object obj) {
		K key = (K) obj;
		Entry<K, V> e = table[index(key)];

		if (e == null) {
			return null;
		} else if (e.next == null && e.getKey().equals(key)) {
			Entry<K, V> tmp = e;
			table[index(key)] = null;
			size--;
			return tmp.getValue();

		} else if (e.getKey().equals(key)) {
			Entry<K, V> tmp = e;
			table[index(key)] = e.next;
			size--;
			return tmp.getValue();
		} else {
			if (find(index(key),key) != null) {
				while (e.next != null) {
					if (e.next.getKey().equals(key)) {
						Entry<K,V> tmp = e.next;
						e.next = e.next.next;
						size--;
						return tmp.getValue();
					}
				}
			}
		}
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	private void rehash() {
		capacity = capacity * 2;
		Entry<K, V>[] tempTable = table;
		table = (Entry<K, V>[]) new Entry[capacity];
		size = 0;
		for (Entry<K, V> e : tempTable) {
			while (e != null) {
				put(e.getKey(), e.getValue());
				e = e.next;
			}
		}
	}

	private int index(K key) {
		return (key.hashCode() & 0x7fffffff) % capacity;
	}

	private Entry<K, V> find(int index, K key) {
		Entry<K, V> entry = table[index];
		if (entry == null)
			return null;

		if (entry.getKey().equals(key))
			return entry;

		else {
			Entry<K, V> next = entry.next;
			while (next != null) {
				if (next.getKey().equals(key)) {
					return next;
				}
				next = next.next;
			}
			return null;
		}

	}

	private static class Entry<K, V> implements Map.Entry<K, V> {
		private K key;
		private V value;
		private Entry<K, V> next;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		/**
		 * Returns old value corresponding to the entry
		 */
		@Override
		public V setValue(V value) {
			V tmp = this.value;
			this.value = value;
			return tmp;
		}

		@Override
		public String toString() {
			return getKey() + "=" + getValue();
		}
	}

	public static void main(String[] args) {
		SimpleHashMap<Integer, Integer> m = new SimpleHashMap<Integer, Integer>(5);
		m.put(142, 142);
		m.put(9, 9);
		m.put(13, 13);
		m.put(1117, 1117);
		m.put(96, 96);
		m.put(153, 153);
		m.put(90, 90);
		m.put(3, 3);
		m.put(0, 0);
		m.put(2, 2);
		m.put(655, 655);
		System.out.println(new Integer(13).hashCode());
		System.out.println(new String("hej").hashCode());

		System.out.println(m.show());

	}
}
