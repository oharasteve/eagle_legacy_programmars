// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

namespace com.eagle.programmar.Delphi
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using Delphi_Identifier_Reference = com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
	using Delphi_Punctuation = com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;

	public class Delphi_Variable : TokenSequence, EagleRunnable, AbstractVariable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference var;
		public Delphi_Identifier_Reference var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Delphi_Extended_Variable> extensions;
		public  OPT;

		public class Delphi_Extended_Variable : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_DotName XXdotName;
			public Delphi_DotName XXdotName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Subscript XXsubscript;
			public Delphi_Subscript XXsubscript;
		}

		public class Delphi_DotName : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Delphi_Punctuation caret = new com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation("^");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot;
			public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference var;
			public Delphi_Identifier_Reference var;
		}

		public class Delphi_Subscript : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
			public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<Delphi_Expression, com.eagle.tokens.punctuation.PunctuationComma> exprs;
			public SeparatedList<Delphi_Expression, PunctuationComma> exprs;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
			public PunctuationRightBracket rightBracket;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.findSymbol(var.getValue());

			if (extensions != null)
			{
				foreach (Delphi_Extended_Variable ext in extensions._elements)
				{
					if (ext.getWhich() is Delphi_Subscript)
					{
						EagleArray array = (EagleArray) value;
						Delphi_Subscript subscript = (Delphi_Subscript) ext.getWhich();
						int subscr = interpreter.getIntValue(subscript.exprs.first());
						EagleValue val = array.getValue(subscr);
						interpreter.pushEagleValue(val);
						return;
					}
				}
			}

			interpreter.pushEagleValue(value);
		}

		public static Delphi_Variable newVariable(string name)
		{
			Delphi_Variable var = new Delphi_Variable();
			Delphi_Extended_Variable extVar = new Delphi_Extended_Variable();
			Delphi_DotName dotName = new Delphi_DotName();
			dotName.dot = new PunctuationPeriod();
			Delphi_Identifier_Reference id = new Delphi_Identifier_Reference();
			id.setValue(name);
			dotName.var = id;
			extVar.setWhich(dotName);

			var.extensions = new TokenList<Delphi_Extended_Variable>();
			var.extensions.addToken(extVar);
			return var;
		}
	}

}
