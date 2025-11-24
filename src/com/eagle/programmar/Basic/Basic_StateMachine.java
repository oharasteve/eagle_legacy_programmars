// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic;

import java.util.ArrayList;
import java.util.HashMap;

import com.eagle.interpret.EagleStateMachine;

public class Basic_StateMachine extends EagleStateMachine
{
	// ============================ GoSub ============================

	// ============================ Goto ============================

	// Hash maps Label to index in _statements array
	private HashMap<Integer, Integer> _numbers = new HashMap<Integer, Integer>();
	// Collect all the statements so GOTO and GOSUB work
	private ArrayList<Basic_Statement> _statements = new ArrayList<Basic_Statement>();

	private int _currentStatement = 0;

	// Basic_Program collects all the statements using this
	public void addStatement(Basic_Statement stmt)
	{
		int indx = _statements.size();
		_statements.add(stmt);
		int label = Integer.parseInt(stmt.label.getValue());
		_numbers.put(Integer.valueOf(label), Integer.valueOf(indx));
	}

	// Set next instruction to be performed
	public void gotoStatement(int label)
	{
		Integer index = _numbers.get(Integer.valueOf(label));
		_currentStatement = index.intValue();
	}

	// Used by GoSub / Return
	public int getCurrentStatement()
	{
		return _currentStatement;
	}

	public void setCurrentStatement(int curr)
	{
		_currentStatement = curr;
	}

	// In normal flow, this is the next instruction to be performed
	public Basic_Statement nextStatement()
	{
		if (_currentStatement >= _statements.size())
		{
			// Fell off the end
			return null;
		}
		Basic_Statement stmt = _statements.get(_currentStatement);
		_currentStatement++;
		return stmt;
	}

	// ============================ Data ============================

	// Collect all the DATA statements
	private ArrayList<Integer> _dataValues = new ArrayList<Integer>();
	// They are always read sequentially
	private int _nextDataValue = 0;

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
		int val = _dataValues.get(_nextDataValue).intValue();
		_nextDataValue++;
		return val;
	}
}
