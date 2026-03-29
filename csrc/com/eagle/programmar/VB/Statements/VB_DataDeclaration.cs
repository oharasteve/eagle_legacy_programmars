// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 16, 2011

namespace com.eagle.programmar.VB.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using VB_Expression = com.eagle.programmar.VB.VB_Expression;
	using VB_Subscript = com.eagle.programmar.VB.VB_Subscript;
	using VB_Type = com.eagle.programmar.VB.VB_Type;
	using VB_Variable_Definition = com.eagle.programmar.VB.Symbols.VB_Variable_Definition;
	using VB_Keyword = com.eagle.programmar.VB.Terminals.VB_Keyword;
	using VB_KeywordChoice = com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatementList = com.eagle.transform.EagleTransformableStatementList;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class VB_DataDeclaration : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatementList
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.VB.Terminals.VB_KeywordChoice modifier = new com.eagle.programmar.VB.Terminals.VB_KeywordChoice("private", "public", "dim", "const");
		public VB_KeywordChoice modifier = new VB_KeywordChoice("private", "public", "dim", "const");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT VB_Keyword CONST = new com.eagle.programmar.VB.Terminals.VB_Keyword("const");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.VB.Symbols.VB_Variable_Definition var;
		public VB_Variable_Definition var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT VB_Subscript subscript;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<VB_MoreVariables> moreVariables;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT VB_DataType dataType;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT VB_DataInitialization initializer;
		public  OPT;

		public class VB_DataType : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.VB.Terminals.VB_Keyword AS = new com.eagle.programmar.VB.Terminals.VB_Keyword("as");
			public VB_Keyword AS = new VB_Keyword("as");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.VB.VB_Type type;
			public VB_Type type;
		}

		public class VB_DataInitialization : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.VB.VB_Expression expr;
			public VB_Expression expr;
		}

		public class VB_MoreVariables : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
			public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.VB.Symbols.VB_Variable_Definition var;
			public VB_Variable_Definition var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT VB_Subscript subscript;
			public  OPT;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			if (initializer != null && initializer.isPresent())
			{
				EagleValue value = interpreter.getEagleValue(initializer.expr);
				interpreter.setSymbol(var, var.getValue(), value);
			}
			else
			{
				interpreter.setSymbol(var, var.ToString(), null);
				if (moreVariables != null && moreVariables.isPresent())
				{
					foreach (VB_MoreVariables more in moreVariables._elements)
					{
						interpreter.setSymbol(more.var, more.var.getValue(), null);
					}
				}
			}
		}

		public override List<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractStatement> result = new List<AbstractStatement>();

			EagleGenerator.TypeEnum givenType = null;
			if (dataType != null && dataType.isPresent())
			{
				string typeName = dataType.type.getWhich().ToString();
				switch (typeName)
				{
				case "boolean":
					givenType = EagleGenerator.TypeEnum.BOOLEAN;
					break;
				case "integer":
					givenType = EagleGenerator.TypeEnum.INTEGER;
					break;
				case "double":
					givenType = EagleGenerator.TypeEnum.DOUBLE;
					break;
				case "string":
					givenType = EagleGenerator.TypeEnum.STRING;
					break;
				}
			}

			EagleGenerator.TypeEnum type = givenType; // Usually not given
			if (type == null)
			{
				// See if the Definition has some assignments in the metrics file
				type = transformer.findAssignMetric(var);
			}
			AbstractType newType = generator.transformType(type, null, dataType);

			AbstractExpression size = null;
			if (subscript != null && subscript.isPresent())
			{
				size = transformer.transformExpression(generator, subscript.exprs.first());
			}

			AbstractExpression initial = null;
			if (initializer != null && initializer.isPresent())
			{
				initial = transformer.transformExpression(generator, initializer.expr);
			}

			string name = var.getValue();
			AbstractStatement stmt = generator.newDataDeclaration(false, name, size, newType, initial, this);
			result.Add(stmt);

			if (moreVariables != null && moreVariables.isPresent())
			{
				foreach (VB_MoreVariables more in moreVariables._elements)
				{
					size = null;
					if (more.subscript != null && more.subscript.isPresent())
					{
						size = transformer.transformExpression(generator, more.subscript.exprs.first());
					}
					name = more.var.getValue();

					type = givenType; // Usually not given
					if (type == null)
					{
						// See if the Definition has some assignments in the metrics file
						type = transformer.findAssignMetric(more.var);
					}
					newType = generator.transformType(type, null, dataType);

					stmt = generator.newDataDeclaration(false, name, size, newType, initial, more);
					result.Add(stmt);
				}
			}

			return result;
		}
	}

}
