package prob79passwordGuess;

import java.util.LinkedList;

public class CustomList<E> extends LinkedList<E>
{
	public void add(int pos, E a)
	{
		if (pos == size())
			super.add(a);
		else
			super.add(pos,a);
	}
	
	public void moveBefore(E replacee, E replaceElement)
	{
		moveRelative(replacee, replaceElement, true);
	}
	public void moveAfter(E replacee, E replaceElement)
	{
		moveRelative(replacee, replaceElement, false);
	}
	
	private void moveRelative(E replacee, E replaceElement, boolean beforeOrAfter)
	{
		if (replacee.equals(replaceElement) || !contains(replacee))
			return;
		// if element already behind, no change eg: moveBefore(2,5) {10,2,39,5} no change
		if (indexOf(replacee) > indexOf(replaceElement))
			return;
		set(indexOf(replaceElement),null);
		//insert element before/after
		add(indexOf(replacee)+(beforeOrAfter ? 0:1),replaceElement);
		remove(null);
	}
	
	
	public void move(int index, E element)
	{
		this.set(indexOf(element),null);
		if (index >= indexOf(null))
			add(index+1,element);
		else
			add(index,element);
		remove(null);
	}
	
	
}
