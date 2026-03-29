// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 7, 2025

namespace com.eagle.programmar.IntelASM
{

	using EagleStateMachine = com.eagle.interpret.EagleStateMachine;
	using EaglePrinter = com.eagle.io.EaglePrinter;

	public class IntelASM_StateMachine : EagleStateMachine
	{
		public int _nextInstruction = 0; // Index into Instructions
		public int _currentLine = 0; // Index into Data, etc
		public int _dollarLine; // For the '$' variable
		public string _startLabel = null; // Where to begin running code

		public EaglePrinter _prt = new EaglePrinter();
		public bool _TRACE = false;

		public Stack<int> _calls = new Stack<int>(); // Call history so RET works

		public int _flag; // From CMP, for JL etc.

		public Dictionary<string, int> _labels = new Dictionary<string, int>();

		public enum IntelASM_Sections
		{
			NONE,
			RODATA,
			DATA,
			TEXT
		}

		public IntelASM_Sections _section = IntelASM_Sections.NONE;

		// Not sure how much space to allocate for these two
		// Byte is signed. Char works too but displays differently
		private short[] _memory = new short[2000];
		public int _memoryUsed = 0;

		public int _RAX, _RBX, _RCX, _RDX;
		public int _RDI, _RSI;
		public int _R8, _R9, _R10;

		public virtual int getMemory1(int index)
		{
			return _memory[index];
		}

		public virtual void setMemory1(int index, int value)
		{
			_memory[index] = (short)(value & 0xFF);
			if (_TRACE)
			{
				Console.WriteLine("     Set memory[" + index + "] to " + _memory[index]);
			}
		}

		public virtual int getMemory4(int index)
		{
			return (_memory[index] | (_memory[index + 1] << 8) | (_memory[index + 2] << 16) | (_memory[index + 3] << 24));
		}

		public virtual void setMemory4(int index, int value)
		{
			_memory[index] = (short)(value & 0xFF);
			_memory[index + 1] = (short)((value >> 8) & 0xFF);
			_memory[index + 2] = (short)((value >> 16) & 0xFF);
			_memory[index + 3] = (short)((value >> 24) & 0xFF);
			if (_TRACE)
			{
				Console.WriteLine("     Set memory[" + index + "] to " + _memory[index]);
				Console.WriteLine("     Set memory[" + (index + 1) + "] to " + _memory[index + 1]);
				Console.WriteLine("     Set memory[" + (index + 2) + "] to " + _memory[index + 2]);
				Console.WriteLine("     Set memory[" + (index + 3) + "] to " + _memory[index + 3]);
			}
		}
	}

}
