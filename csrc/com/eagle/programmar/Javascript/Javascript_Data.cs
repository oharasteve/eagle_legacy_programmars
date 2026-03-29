// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

namespace com.eagle.programmar.Javascript
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Javascript_Variable_Definition = com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
	using Javascript_Comment = com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatementList = com.eagle.transform.EagleTransformableStatementList;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Javascript_Data : TokenSequence, EagleRunnable, EagleTransformableStatementList
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Javascript_Type type;
		public Javascript_Type type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition var;
		public Javascript_Variable_Definition var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Javascript_InitData init;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<Javascript_More_Variables> moreVars;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT PunctuationSemicolon semicolon;
		public  OPT;

		public class Javascript_InitData : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Javascript_Expression expr;
			public Javascript_Expression expr;
		}

		public class Javascript_More_Variables : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
			public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<com.eagle.programmar.Javascript.Terminals.Javascript_Comment> comments;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition var;
			public Javascript_Variable_Definition var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Javascript_InitData init;
			public  OPT;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			if (init != null && init.isPresent())
			{
				EagleValue value = interpreter.getEagleValue(init.expr);
				interpreter.setSymbol(var, var.ToString(), value);
			}

			if (moreVars != null && moreVars.size() > 0)
			{
				foreach (Javascript_More_Variables more in moreVars._elements)
				{
					if (more.init != null && more.init.isPresent())
					{
						EagleValue value = interpreter.getEagleValue(more.init.expr);
						interpreter.setSymbol(more.var, more.var.ToString(), value);
					}
				}
			}
		}

		public override List<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			return transformStaticData(false, transformer, generator);
		}

		// Called directly from Javascript_Program for static class-level data
		public virtual List<AbstractStatement> transformStaticData(bool isStatic, EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractStatement> result = new List<AbstractStatement>();

			// See if the Declaration has some assignments in the metrics file
			EagleGenerator.TypeEnum typeEnum = transformer.findAssignMetric(var);
			AbstractType newType = generator.transformType(typeEnum, null, this);

			string name1 = var.getValue();
			AbstractExpression initial1 = null;
			if (init != null && init.isPresent())
			{
				initial1 = transformer.transformExpression(generator, init.expr);
			}
			AbstractStatement newData = generator.newDataDeclaration(isStatic, name1, null, newType, initial1, this);
			result.Add(newData);

			if (moreVars != null && moreVars.size() > 0)
			{
				foreach (Javascript_More_Variables more in moreVars._elements)
				{
					string name2 = more.var.getValue();
					AbstractExpression initial2 = null;
					if (more.init != null && more.init.isPresent())
					{
						initial2 = transformer.transformExpression(generator, more.init.expr);
					}
					AbstractStatement newData2 = generator.newDataDeclaration(isStatic, name2, null, newType, initial2, this);
					result.Add(newData2);
				}
			}

			return result;
		}
	}

}
