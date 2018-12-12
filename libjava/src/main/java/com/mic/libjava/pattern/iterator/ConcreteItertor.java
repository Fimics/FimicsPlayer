package com.mic.libjava.pattern.iterator;

import java.util.ArrayList;
import java.util.List;


/**

 */
public class ConcreteItertor<T>  implements  Iterator<T>{
	public ConcreteItertor(List<T> list) {
		this.list = list;
	}

	private List<T> list=new ArrayList<>();
	private int cursor=0;
	
	
	@Override
	public boolean hasNext() {
		return cursor!=list.size();
	}

	@Override
	public T next() {
		T obj=null;
		if(this.hasNext())
		{
			obj=list.get(cursor++);
		}
		
		
		return obj;
	}

}
