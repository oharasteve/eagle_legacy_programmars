// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

namespace com.eagle.programmar.CSharp
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using CSharp_Identifier_Reference = com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference;
	using CSharp_KeywordChoice = com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class CSharp_Variable : TokenSequence, EagleRunnable, AbstractVariable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) CSharp_VariableIdentifier firstId;
		public CSharp_VariableIdentifier firstId;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<CSharp_MoreVariableIdentifiers> moreIds;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<CSharp_Subscript> subscript;
		public  OPT;

		public class CSharp_MoreVariableIdentifiers : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NOSPACE PunctuationPeriod dot;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE CSharp_VariableIdentifier nextId;
			public  NOSPACE;
		}

		public class CSharp_VariableIdentifier : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_KeywordChoice XXbuiltIn = new com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice("this", "base", "class");
			public CSharp_KeywordChoice XXbuiltIn = new CSharp_KeywordChoice("this", "base", "class");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_Identifier_Reference XXid;
			public CSharp_Identifier_Reference XXid;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CSharp_CastedVariable extends com.eagle.tokens.TokenSequence
			public class CSharp_CastedVariable : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen1;
				public PunctuationLeftParen leftParen1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationLeftParen leftParen2;
				public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE CSharp_Type cstype;
				public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationRightParen rightParen1;
				public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference id;
				public CSharp_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @NOSPACE PunctuationRightParen rightParen2;
				public  NOSPACE;
			}
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			CSharp_Identifier_Reference which = (CSharp_Identifier_Reference) firstId.getWhich();
			EagleValue value = interpreter.findSymbol(which.ToString());
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

		public static CSharp_Variable newVariable(string name)
		{
			CSharp_Variable var = new CSharp_Variable();
			var.firstId = new CSharp_VariableIdentifier();
			CSharp_Identifier_Reference id = new CSharp_Identifier_Reference();
			id.setValue(name);
			var.firstId.setWhich(id);
			return var;
		}
	}

}
