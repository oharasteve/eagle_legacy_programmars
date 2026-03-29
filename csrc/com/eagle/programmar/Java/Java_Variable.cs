// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

namespace com.eagle.programmar.Java
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using Java_Identifier_Reference = com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
	using Java_KeywordChoice = com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Java_Variable : TokenSequence, EagleRunnable, AbstractVariable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Java_VariableIdentifier firstId;
		public Java_VariableIdentifier firstId;
	//	public S() @OPT TokenList<Java_DotVariable> moreIds;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Java_Subscript> subscript;
		public  OPT;

	//	public static class Java_DotVariable extends TokenSequence
	//	{
	//		public @S(10) @NOSPACE PunctuationPeriod dot;
	//		public @S(20) @NOSPACE Java_VariableIdentifier nextId;
	//	}

		public class Java_VariableIdentifier : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_KeywordChoice XXbuiltIn = new com.eagle.programmar.Java.Terminals.Java_KeywordChoice("this", "class", "super");
			public Java_KeywordChoice XXbuiltIn = new Java_KeywordChoice("this", "class", "super");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_Identifier_Reference XXid;
			public Java_Identifier_Reference XXid;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Java_CastedVariable extends com.eagle.tokens.TokenSequence
			public class Java_CastedVariable : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen1;
				public PunctuationLeftParen leftParen1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen2;
				public PunctuationLeftParen leftParen2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Java_Type jtype;
				public Java_Type jtype;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen1;
				public PunctuationRightParen rightParen1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Java.Symbols.Java_Identifier_Reference id;
				public Java_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightParen rightParen2;
				public PunctuationRightParen rightParen2;
			}
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			Java_Identifier_Reference which = (Java_Identifier_Reference) firstId.getWhich();
			EagleValue value = interpreter.findSymbol(which.ToString());
			if (value == null)
			{
				throw new Exception("Unable to find a value for " + which.ToString());
			}
			if (subscript != null && subscript.size() > 0)
			{
				EagleArray array = (EagleArray) value;
				int sub = interpreter.getIntValue(subscript.first().expr);
				EagleValue val = array.getValue(sub);
				interpreter.pushEagleValue(val);
			}
			else
			{
				interpreter.pushEagleValue(value);
			}
		}

		public static Java_Variable newVariable(string name)
		{
			Java_Variable var = new Java_Variable();
			var.firstId = new Java_VariableIdentifier();
			Java_Identifier_Reference id = new Java_Identifier_Reference();
			id.setValue(name);
			var.firstId.setWhich(id);
			return var;
		}
	}

}
