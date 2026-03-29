// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

namespace com.eagle.programmar.Powershell.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Powershell_Expression = com.eagle.programmar.Powershell.Powershell_Expression;
	using Powershell_Keyword = com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
	using Powershell_KeywordChoice = com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
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

	public class Powershell_WriteStatement : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatementList
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice WRITE = new com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice("Write-Error", "Write-Host", "Write-Output");
		public Powershell_KeywordChoice WRITE = new Powershell_KeywordChoice("Write-Error", "Write-Host", "Write-Output");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Powershell_WriteOption> options1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.SeparatedList<com.eagle.programmar.Powershell.Powershell_Expression, com.eagle.tokens.punctuation.PunctuationComma> exprs;
		public SeparatedList<Powershell_Expression, PunctuationComma> exprs;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<Powershell_WriteOption> options2;
		public  OPT;

		public class Powershell_WriteOption : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_KeywordChoice XXNONEWLINE = new com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice("-NoNewLine");
			public Powershell_KeywordChoice XXNONEWLINE = new Powershell_KeywordChoice("-NoNewLine");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Powershell_WriteOptionColor extends com.eagle.tokens.TokenSequence
			public class Powershell_WriteOptionColor : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Terminals.Powershell_Keyword FGColor = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("-ForegroundColor");
				public Powershell_Keyword FGColor = new Powershell_Keyword("-ForegroundColor");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice COLOR = new com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice("Green", "Yellow");
				public Powershell_KeywordChoice COLOR = new Powershell_KeywordChoice("Green", "Yellow");
			}
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			for (int i = 0; i < exprs.getPrimaryCount(); i++)
			{
				string result = interpreter.getStrValue(exprs.getPrimaryElement(i));
				Console.WriteLine(result);
			}
		}

		public override List<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractStatement> result = new List<AbstractStatement>();
			for (int i = 0; i < exprs.getPrimaryCount(); i++)
			{
				Powershell_Expression expr = exprs.getPrimaryElement(i);
				AbstractExpression line = transformer.transformExpression(generator, expr);
				result.Add(generator.newPrintStatement(line, EagleGenerator.TypeEnum.STRING, true, false, this));
			}
			return result;
		}
	}

}
