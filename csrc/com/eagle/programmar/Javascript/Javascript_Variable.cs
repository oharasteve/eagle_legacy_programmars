// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

namespace com.eagle.programmar.Javascript
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using Javascript_Identifier_Reference = com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
	using Javascript_KeywordChoice = com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
	using Javascript_PunctuationChoice = com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Javascript_Variable : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Javascript_VariableIdentifier firstId;
		public Javascript_VariableIdentifier firstId;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Javascript_VariableQualifier> qualifiers;
		public  OPT;

		public class Javascript_VariableIdentifier : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_Identifier_Reference XXid;
			public Javascript_Identifier_Reference XXid;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_KeywordChoice XXTHIS = new com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice("this");
			public Javascript_KeywordChoice XXTHIS = new Javascript_KeywordChoice("this");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Javascript_PunctuationChoice XXdollar = new com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice("$", "_");
			public Javascript_PunctuationChoice XXdollar = new Javascript_PunctuationChoice("$", "_");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Javascript_CastedVariable extends com.eagle.tokens.TokenSequence
			public class Javascript_CastedVariable : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen1;
				public PunctuationLeftParen leftParen1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen2;
				public PunctuationLeftParen leftParen2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Javascript_Type jstype;
				public Javascript_Type jstype;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen1;
				public PunctuationRightParen rightParen1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference id;
				public Javascript_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightParen rightParen2;
				public PunctuationRightParen rightParen2;
			}
		}

		public class Javascript_VariableQualifier : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_Subscript XXsubscript;
			public Javascript_Subscript XXsubscript;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Javascript_VarField extends com.eagle.tokens.TokenSequence
			public class Javascript_VarField : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationPeriod dot;
				public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference id;
				public Javascript_Identifier_Reference id;
			}
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.findSymbol(firstId.getWhich().ToString());

			if (qualifiers != null && qualifiers.isPresent() && qualifiers.size() == 1)
			{
				AbstractToken which = qualifiers._elements.get(0).getWhich();
				if (which is Javascript_Subscript)
				{
					Javascript_Subscript subscript = (Javascript_Subscript) which;
					EagleArray array = (EagleArray) value;
					int sub = interpreter.getIntValue(subscript.expr);
					EagleValue val = array.getValue(sub);
					interpreter.pushEagleValue(val);
					return;
				}
			}

			interpreter.pushEagleValue(value);
		}
	}

}
