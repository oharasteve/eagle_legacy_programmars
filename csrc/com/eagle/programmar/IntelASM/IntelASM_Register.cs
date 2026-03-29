// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 16, 2022

namespace com.eagle.programmar.IntelASM
{
	using IntelASM_KeywordChoice = com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class IntelASM_Register : TokenSequence
	{
		public static string[] _REGISTERS = new string[] {"EAX", "EBX", "ECX", "EDX", "ESI", "EDI", "EBP", "ESP", "RAX", "RBX", "RCX", "RDX", "R8", "R9", "R10", "CS", "DS", "SS", "ES", "FS", "GS", "AH", "AL", "BH", "BL", "CH", "CL", "DH", "DL", "AX", "BX", "CX", "DX", "BP", "SI", "DI", "SP", "RDI", "RSI"};

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice REG = new com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice(_REGISTERS);
		public IntelASM_KeywordChoice REG = new IntelASM_KeywordChoice(_REGISTERS);

		public virtual int getValue(IntelASM_StateMachine state)
		{
			switch (REG.ToString().ToUpper())
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
				throw new Exception("Can't get register " + REG + " yet");
			}
		}

		public virtual void setValue(IntelASM_StateMachine state, int value)
		{
			int val = value;
			switch (REG.ToString().ToUpper())
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
				state._RAX = (state._RAX & unchecked((int)0xFFFF0000)) | val;
				break;
			case "AH":
				val = value & 0xFF;
				state._RAX = (state._RAX & unchecked((int)0xFFFF00FF)) | val;
				break;
			case "AL":
				val = value & 0xFF;
				state._RAX = (state._RAX & unchecked((int)0xFFFFFF00)) | val;
				break;
			default:
				throw new Exception("Can't set register " + REG + " yet");
			}

			if (state._TRACE)
			{
				Console.WriteLine("     Setting " + REG + " = " + val);
			}
		}

		public virtual int compValue(IntelASM_StateMachine state, int value)
		{
			int val;
			switch (REG.ToString().ToUpper())
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
				Console.WriteLine("**** CMP " + myVal + " to " + val);
			}

			return myVal - val;
		}
	}

}
