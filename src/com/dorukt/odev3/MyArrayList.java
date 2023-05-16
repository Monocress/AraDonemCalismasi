package com.dorukt.odev3;

import java.util.Arrays;

public class MyArrayList<T> {
	int capacity;
	Object[] dizi;
	int size;

	public MyArrayList() {
		super();
		dizi = new Object[capacity];
	}

	public void add(T element) {
		increaseCapacity();
			dizi[size++] = element;
	}

	public void add(int index, T element) {
		if (index >= capacity)
			throw new IndexOutOfBoundsException();
		else if (index < 0) {
			throw new NegativeArraySizeException();
		}
		increaseCapacity();
		size++;
		Object[] temp = Arrays.copyOf(dizi, capacity);
		for (int i = 0, j = 0; i < dizi.length;) {
			if (i == index) {

				i++;
				continue;
			} else {
				dizi[i] = temp[j];
				i++;
				j++;
			}
		}
		dizi[index] = element;
	}

	@SuppressWarnings("unchecked")
	public T get(int index) {
		if (index >= capacity)
			throw new IndexOutOfBoundsException();
		else if (index < 0) {
			throw new NegativeArraySizeException();
		}
		return (T) dizi[index];
	}

	public void set(int index, T element) {
		if (index >= capacity)
			throw new IndexOutOfBoundsException();
		else if (index < 0) {
			throw new NegativeArraySizeException();
		}
		dizi[index] = element;
	}

	public void remove(int index) {
		if (index >= capacity)
			throw new IndexOutOfBoundsException();
		else if (index < 0) {
			throw new NegativeArraySizeException();
		}
		dizi[index] = null;
		Object[] temp = Arrays.copyOf(dizi, capacity);
		for (int i = 0, j = 0; j < dizi.length;) {
			if (j == index) {
				j++;
				continue;
			} else {
				dizi[i] = temp[j];
				i++;
				j++;
			}
		}
		size--;
		decreaseCapacity();

	}

	public boolean contains(T element) {
		boolean varMi = false;
		for (Object object : dizi) {
			if (object.equals(element))
			return varMi = true;
		}

		return varMi;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;

	}

	private void increaseCapacity() {
		if (capacity == 0) {
			capacity++;
			dizi = new Object[capacity];
		} else {
			dizi = Arrays.copyOf(dizi, capacity + 1);
			capacity++;
		}

	}

	private void decreaseCapacity() {
			capacity--;
			dizi = Arrays.copyOf(dizi, capacity);

	}

	@Override
	public String toString() {
		return Arrays.toString(dizi);
	}
}
