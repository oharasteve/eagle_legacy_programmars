// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

namespace com.eagle.programmar.Basic
{

	using EagleStateMachine = com.eagle.interpret.EagleStateMachine;

	public class Basic_StateMachine : EagleStateMachine
	{
		// ============================ GoSub ============================

		// ============================ Goto ============================

		// Hash maps Label to index in _statements array
		private Dictionary<int, int> _numbers = new Dictionary<int, int>();
		// Collect all the statements so GOTO and GOSUB work
		private List<Basic_Statement> _statements = new List<Basic_Statement>();

		private int _currentStatement = 0;

		// Basic_Program collects all the statements using this
		public virtual void addStatement(Basic_Statement stmt)
		{
			int indx = _statements.Count;
			_statements.Add(stmt);
			int label = int.Parse(stmt.label.getValue());
			_numbers[Convert.ToInt32(label)] = Convert.ToInt32(indx);
		}

		// Set next instruction to be performed
		public virtual void gotoStatement(int label)
		{
			int? index = _numbers[Convert.ToInt32(label)];
			_currentStatement = index.Value;
		}

		// Used by GoSub / Return
		public virtual int CurrentStatement
		{
			get
			{
				return _currentStatement;
			}
			set
			{
				_currentStatement = value;
			}
		}


		// In normal flow, this is the next instruction to be performed
		public virtual Basic_Statement nextStatement()
		{
			if (_currentStatement >= _statements.Count)
			{
				// Fell off the end
				return null;
			}
			Basic_Statement stmt = _statements[_currentStatement];
			_currentStatement++;
			return stmt;
		}

		// ============================ Data ============================

		// Collect all the DATA statements
		private List<int> _dataValues = new List<int>();
		// They are always read sequentially
		private int _nextDataValue = 0;

		public virtual void addDataValue(int k)
		{
			_dataValues.Add(Convert.ToInt32(k));
		}

		public virtual int DataValue
		{
			get
			{
				if (_nextDataValue >= _dataValues.Count)
				{
					throw new Exception("Data value out of bounds: " + _nextDataValue);
				}
				int val = _dataValues[_nextDataValue];
				_nextDataValue++;
				return val;
			}
		}
	}

}
