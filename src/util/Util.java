package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Util {

	static long e;
	
	Util() {
		e = 1;
	}

	public static void main(String[] args) {
		float h=0,m=0,prime=1;
		for(m=0;m<60;m++){
			h=(2*prime+11*m)/60;
			if(h<=12&&h==(int)h){
				log(h+":"+m);
			}
		}
	}
	
	static void log(Object o) {
		System.out.println(o.toString());
	}

	private final static boolean gen() {
		System.out.println("GEN");
		return false;
	}

}

class B extends Util {
	Util[] utils = new B[9];
	Util util = new Util();
	B b = new B();

	private float test() {
		return e;
	}

}

class InstrumentedHashSet<E> extends HashSet<E> {
	private int addCount = 0;

	public InstrumentedHashSet() {
	}

	public InstrumentedHashSet(int initCap, float loadFactor) {
		super(initCap, loadFactor);
	}

	@Override
	public boolean add(E e) {
		addCount++;
		return super.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		addCount += c.size();
		return super.addAll(c);
	}

	public int getAddCount() {
		return addCount;
	}
}
