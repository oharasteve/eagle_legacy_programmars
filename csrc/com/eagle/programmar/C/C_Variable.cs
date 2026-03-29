// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

namespace com.eagle.programmar.C
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using C_Identifier_Reference = com.eagle.programmar.C.Symbols.C_Identifier_Reference;
	using C_Punctuation = com.eagle.programmar.C.Terminals.C_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationStar = com.eagle.tokens.punctuation.PunctuationStar;

	public class C_Variable : TokenSequence, EagleRunnable, AbstractVariable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<C_VariableStar> stars;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) C_VariableIdentifier firstId;
		public C_VariableIdentifier firstId;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<C_ExtendedIdentifier> moreIds;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<C_Subscript> subscript;
		public  OPT;

		public class C_VariableStar : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationStar star;
			public PunctuationStar star;
		}

		public class C_VariableIdentifier : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_CastedVariable XXcastedVariable;
			public C_CastedVariable XXcastedVariable;
	//		public @CHOICE C_SubscriptedVariable XXsubscriptedVariable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST C_Identifier_Reference XXid;
			public C_Identifier_Reference XXid;
		}

		public class C_CastedVariable : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen1;
			public PunctuationLeftParen leftParen1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen2;
			public PunctuationLeftParen leftParen2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) C_Type jtype;
			public C_Type jtype;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen1;
			public PunctuationRightParen rightParen1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.C.Symbols.C_Identifier_Reference id;
			public C_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightParen rightParen2;
			public PunctuationRightParen rightParen2;
		}

	//	public static class C_SubscriptedVariable extends TokenSequence
	//	{
	//		public @S(10) C_Identifier_Reference id;
	//		public @S(20) TokenList<C_Subscript> subscripts;
	//	}

		public class C_ExtendedIdentifier : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class C_DotIdentifier extends com.eagle.tokens.TokenSequence
			public class C_DotIdentifier : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationPeriod dot;
				public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Symbols.C_Identifier_Reference id;
				public C_Identifier_Reference id;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class C_ArrowIdentifier extends com.eagle.tokens.TokenSequence
			public class C_ArrowIdentifier : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_Punctuation arrow = new com.eagle.programmar.C.Terminals.C_Punctuation("->");
				public C_Punctuation arrow = new C_Punctuation("->");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Symbols.C_Identifier_Reference id;
				public C_Identifier_Reference id;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class C_ColonColonIdentifier extends com.eagle.tokens.TokenSequence
			public class C_ColonColonIdentifier : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_Punctuation colonColon = new com.eagle.programmar.C.Terminals.C_Punctuation("::");
				public C_Punctuation colonColon = new C_Punctuation("::");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT C_Punctuation tilde = new com.eagle.programmar.C.Terminals.C_Punctuation("~");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.Symbols.C_Identifier_Reference id;
				public C_Identifier_Reference id;
			}
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			AbstractToken which = firstId.getWhich();
			if (!(which is C_Identifier_Reference))
			{
				throw new Exception("Unable to handle " + which);
			}
			C_Identifier_Reference id = (C_Identifier_Reference) which;

			if (subscript != null && subscript.size() > 0)
			{
				EagleArray arry = (EagleArray) interpreter.findSymbol(id.getValue());
				C_Subscript subscr = subscript._elements.get(0);
				int sub = interpreter.getIntValue(subscr.expr);
				interpreter.pushEagleValue(arry.getValue(sub));
			}
			else
			{
				EagleValue value = interpreter.findSymbol(id.getValue());
				interpreter.pushEagleValue(value);
			}
		}
	}

}
