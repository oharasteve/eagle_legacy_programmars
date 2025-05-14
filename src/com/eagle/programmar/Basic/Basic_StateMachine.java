// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic;

import java.util.ArrayList;
import java.util.Stack;

import com.eagle.interpret.EagleStateMachine;

public class Basic_StateMachine extends EagleStateMachine
{
	private ArrayList<Integer> _dataValues = new ArrayList<Integer>();
	private int _nextDataValue = 0;
	
	public Stack<Integer> _goSubs = new Stack<Integer>(); // GoSub history so RETURN works
	// public Stack<Integer> _forLoops = new Stack<Integer>(); // FOR history so NEXT works
	
	public void addDataValue(int k)
	{
		_dataValues.add(Integer.valueOf(k));
	}
	
	public int getDataValue()
	{
		if (_nextDataValue >= _dataValues.size())
		{
			throw new RuntimeException("Data value out of bounds: " + _nextDataValue);
		}
		int val = _dataValues.get(_nextDataValue);
		_nextDataValue++;
		return val;
	}
}
