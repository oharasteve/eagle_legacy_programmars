// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 7, 2025

package com.eagle.programmar.IntelASM;

import java.util.HashMap;
import java.util.Stack;

import com.eagle.interpret.EagleStateMachine;
import com.eagle.io.EaglePrinter;

public class IntelASM_StateMachine extends EagleStateMachine
{
	public int _nextInstruction = 0;	// Index into Instructions
	public int _currentLine = 0;		// Index into Data, etc
	public int _dollarLine;				// For the '$' variable
	public String _startLabel = null;	// Where to begin running code

	public EaglePrinter _prt = new EaglePrinter();
	public boolean _TRACE = false;
	
	public Stack<Integer> _calls = new Stack<Integer>(); // Call history so RET works
	
	public int _flag;					// From CMP, for JL etc.
	
	public HashMap<String, Integer> _labels = new HashMap<String, Integer>();
	
	public static enum IntelASM_Sections
	{
		NONE, RODATA, DATA, TEXT
	}
	public IntelASM_Sections _section = IntelASM_Sections.NONE;
	
	// Not sure how much space to allocate for these two
	// Byte is signed. Char works too but displays differently
	private short[] _memory = new short[2000];
	public int _memoryUsed = 0;
	
	public int _RAX, _RBX, _RCX, _RDX;
	public int _RDI, _RSI;
	public int _R8, _R9, _R10;
	
	public int getMemory1(int index)
	{
		return _memory[index];
	}

	public void setMemory1(int index, int value)
	{
		_memory[index] = (short) (value & 0xFF);
		if (_TRACE)
		{
			System.out.println("     Set memory[" + index + "] to " + _memory[index]);
		}
	}

	public int getMemory4(int index)
	{
		return (_memory[index] |
				(_memory[index+1] << 8) |
				(_memory[index+2] << 16) |
				(_memory[index+3] << 24));
	}

	public void setMemory4(int index, int value)
	{
		_memory[index] = (short) (value & 0xFF);
		_memory[index+1] = (short) ((value >> 8) & 0xFF);
		_memory[index+2] = (short) ((value >> 16) & 0xFF);
		_memory[index+3] = (short) ((value >> 24) & 0xFF);
		if (_TRACE)
		{
			System.out.println("     Set memory[" + index + "] to " + _memory[index]);
			System.out.println("     Set memory[" + (index+1) + "] to " + _memory[index+1]);
			System.out.println("     Set memory[" + (index+2) + "] to " + _memory[index+2]);
			System.out.println("     Set memory[" + (index+3) + "] to " + _memory[index+3]);
		}
	}
}
