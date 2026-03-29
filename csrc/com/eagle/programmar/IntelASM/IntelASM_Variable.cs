// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 9, 2025

namespace com.eagle.programmar.IntelASM
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using IntelASM_Identifier_Reference = com.eagle.programmar.IntelASM.Symbols.IntelASM_Identifier_Reference;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;

	public class IntelASM_Variable : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_Register XXreg;
		public IntelASM_Register XXreg;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST IntelASM_Identifier_Reference XXvar;
		public IntelASM_Identifier_Reference XXvar;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class IntelASM_Brackets_Register extends com.eagle.tokens.TokenSequence
		public class IntelASM_Brackets_Register : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
			public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE IntelASM_Register register;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE PunctuationRightBracket rightBracket;
			public  NOSPACE;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class IntelASM_Brackets_Address extends com.eagle.tokens.TokenSequence
		public class IntelASM_Brackets_Address : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
			public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE IntelASM_Identifier_Reference id;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE PunctuationRightBracket rightBracket;
			public  NOSPACE;
		}

		public virtual int getValue(EagleInterpreter interpreter)
		{
			IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;

			AbstractToken which = this.getWhich();
			if (which is IntelASM_Register)
			{
				IntelASM_Register reg = (IntelASM_Register) which;
				return reg.getValue(state);
			}
// JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getCanonicalName method:
			throw new Exception("Unexpected variable: " + which.GetType().FullName);
		}

		public virtual void setValue(EagleInterpreter interpreter, int value)
		{
			IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;

			AbstractToken which = this.getWhich();
			if (which is IntelASM_Register)
			{
				IntelASM_Register reg = (IntelASM_Register) which;
				reg.setValue(state, value);
			}
			else if (which is IntelASM_Brackets_Register)
			{
				IntelASM_Brackets_Register brack = (IntelASM_Brackets_Register) which;
				IntelASM_Register reg = brack.register;
				int index = reg.getValue(state);
				state.setMemory1(index, value);
			}
			else if (which is IntelASM_Brackets_Address)
			{
				IntelASM_Brackets_Address addr = (IntelASM_Brackets_Address) which;
				int index = interpreter.getIntValue(addr.id);
				state.setMemory4(index, value);
			}
			else
			{
// JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getCanonicalName method:
				throw new Exception("Unexpected variable: " + which.GetType().FullName);
			}
		}
	}

}
