// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

namespace com.eagle.programmar.Perl.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Perl_Expression = com.eagle.programmar.Perl.Perl_Expression;
	using Perl_Variable_Definition = com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition;
	using Perl_KeywordChoice = com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
	using Perl_Literal = com.eagle.programmar.Perl.Terminals.Perl_Literal;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatementList = com.eagle.transform.EagleTransformableStatementList;
	using EagleTransformer = com.eagle.transform.EagleTransformer;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class Perl_PrintStatement : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatementList
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice PRINT = new com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice("print", "printf");
		public Perl_KeywordChoice PRINT = new Perl_KeywordChoice("print", "printf");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Perl_PrintWhat what;
		public Perl_PrintWhat what;

		public class Perl_PrintWhat : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST Perl_PrintRedirectInput XXredirectInput;
			public Perl_PrintRedirectInput XXredirectInput;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_PrintNormal XXprintNormal;
			public Perl_PrintNormal XXprintNormal;
		}

		public class Perl_PrintRedirectInput : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition id;
			public Perl_Variable_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Terminals.Perl_Literal multiline;
			public Perl_Literal multiline; // With << or <<< to redirect stdin
		}

		public class Perl_PrintNormal : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.SeparatedList<com.eagle.programmar.Perl.Perl_Expression, com.eagle.tokens.punctuation.PunctuationComma> strings;
			public SeparatedList<Perl_Expression, PunctuationComma> strings;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT @CURIOUS("Extra comma") com.eagle.tokens.punctuation.PunctuationComma comma;
			public  OPT;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			if (what.getWhich() is Perl_PrintNormal)
			{
				Perl_PrintNormal prt = (Perl_PrintNormal) what.getWhich();
				for (int i = 0; i < prt.strings.getPrimaryCount(); i++)
				{
					Perl_Expression expr = prt.strings.getPrimaryElement(i);
					string item = interpreter.getStrValue(expr);
					Console.Write(item.Replace("\\n", "\n"));
				}
			}
		}

		public override List<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractStatement> result = new List<AbstractStatement>();
			if (what.getWhich() is Perl_PrintNormal)
			{
				Perl_PrintNormal prt = (Perl_PrintNormal) what.getWhich();
				for (int i = 0; i < prt.strings.getPrimaryCount(); i++)
				{
					Perl_Expression expr = prt.strings.getPrimaryElement(i);
					AbstractExpression line = transformer.transformExpression(generator, expr);
					AbstractStatement stmt = generator.newPrintStatement(line, EagleGenerator.TypeEnum.STRING, false, false, this);
					result.Add(stmt);
				}
			}
			return result;
		}
	}

}
