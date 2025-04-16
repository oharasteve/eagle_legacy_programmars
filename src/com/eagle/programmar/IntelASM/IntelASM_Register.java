// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 16, 2022

package com.eagle.programmar.IntelASM;

import com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class IntelASM_Register extends TokenSequence
{
	public static String[] _REGISTERS = new String[]
	{
		"EAX", "EBX", "ECX", "EDX", "ESI", "EDI", "EBP", "ESP",
		"RAX", "RBX", "RCX", "RDX", "R8", "R9", "R10",
		"CS", "DS", "SS", "ES", "FS", "GS",
		"AH", "AL", "BH", "BL", "CH", "CL", "DH", "DL", "AX",
		"BX", "CX", "DX", "BP", "SI", "DI", "SP", "RDI", "RSI"
	};
	
	public @S(10) IntelASM_KeywordChoice REG = new IntelASM_KeywordChoice(_REGISTERS);
	
	public int getValue(IntelASM_StateMachine state)
	{
		switch (REG.toString().toUpperCase())
		{
		case "R8":
			return state._R8;
		case "R9":
			return state._R9;
		case "R10":
			return state._R10;
		case "RAX":
			return state._RAX;
		case "RBX":
			return state._RBX;
		case "RCX":
			return state._RCX;
		case "RDX":
			return state._RDX;
		case "RDI":
			return state._RDI;
		case "RSI":
			return state._RSI;
		case "AX":
			return state._RAX & 0xFFFF;
		case "AH":
			return (state._RAX >> 8) & 0xFF;
		case "AL":
			return state._RAX & 0xFF;
		default:
			throw new RuntimeException("Can't get register " + REG + " yet");
		}
	}
	
	public void setValue(IntelASM_StateMachine state, int value)
	{
		int val = value;
		switch (REG.toString().toUpperCase())
		{
		case "R8":
			state._R8 = value;
			break;
		case "R9":
			state._R9 = value;
			break;
		case "R10":
			state._R10 = value;
			break;
		case "RAX":
			state._RAX = value;
			break;
		case "RBX":
			state._RBX = value;
			break;
		case "RCX":
			state._RCX = value;
			break;
		case "RDX":
			state._RDX = value;
			break;
		case "RDI":
			state._RDI = value;
			break;
		case "RSI":
			state._RSI = value;
			break;
		case "AX":
			val = value & 0xFFFF;
			state._RAX = (state._RAX & 0xFFFF0000) | val;
			break;
		case "AH":
			val = value & 0xFF;
			state._RAX = (state._RAX & 0xFFFF00FF) | val;
			break;
		case "AL":
			val = value & 0xFF;
			state._RAX = (state._RAX & 0xFFFFFF00) | val;
			break;
		default:
			throw new RuntimeException("Can't set register " + REG + " yet");
		}
		
		if (state._TRACE)
		{
			System.out.println("     Setting " + REG + " = " + val);
		}
	}
	
	public int compValue(IntelASM_StateMachine state, int value)
	{
		int val;
		switch (REG.toString().toUpperCase())
		{
		case "AX":
			val = value & 0xFFFF;
			break;
		case "AH":
		case "AL":
			val = value & 0xFF;
			break;
		default:
			val = value;
			break;
		}
		
		int myVal = getValue(state);
		if (state._TRACE)
		{
			System.out.println("**** CMP " + myVal + " to " + val);
		}
		
		return myVal - val;
	}
}
