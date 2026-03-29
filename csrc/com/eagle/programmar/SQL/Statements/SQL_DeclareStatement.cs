// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 6, 2014

namespace com.eagle.programmar.SQL.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleString = com.eagle.math.EagleString;
	using EagleValue = com.eagle.math.EagleValue;
	using SQL_Expression = com.eagle.programmar.SQL.SQL_Expression;
	using SQL_Type = com.eagle.programmar.SQL.SQL_Type;
	using SQL_Declare_Definition = com.eagle.programmar.SQL.Symbols.SQL_Declare_Definition;
	using SQL_Keyword = com.eagle.programmar.SQL.Terminals.SQL_Keyword;
	using SQL_Punctuation = com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableStatementList = com.eagle.transform.EagleTransformableStatementList;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class SQL_DeclareStatement : TokenSequence, EagleRunnable, EagleTransformableStatementList
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword DECLARE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("DECLARE");
		public SQL_Keyword DECLARE = new SQL_Keyword("DECLARE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<SQL_Declaration> declarations;
		public TokenList<SQL_Declaration> declarations;

		public class SQL_Declaration : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Symbols.SQL_Declare_Definition id;
			public SQL_Declare_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.SQL_Type type;
			public SQL_Type type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT SQL_Punctuation colonEquals = new com.eagle.programmar.SQL.Terminals.SQL_Punctuation(":=");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT SQL_Expression initialValue;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
			public PunctuationSemicolon semicolon;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			foreach (SQL_Declaration decl in declarations._elements)
			{
				EagleValue value = new EagleString(""); // Create it with a bogus value
				if (decl.initialValue != null && decl.initialValue.isPresent())
				{
					value = interpreter.getEagleValue(decl.initialValue);
				}
				interpreter.setSymbol(decl.id, decl.id.ToString(), value);
			}
		}

		public override List<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractStatement> result = new List<AbstractStatement>();

			foreach (SQL_Declaration decl in declarations._elements)
			{
				string varName = decl.id.getValue();
				AbstractExpression newVal;
				if (decl.initialValue != null && decl.initialValue.isPresent())
				{
					newVal = transformer.transformExpression(generator, decl.initialValue);
					AbstractExpression asgExpr = generator.newAssignmentExpression(varName, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, EagleGenerator.AssignmentEnum.EQUALS, newVal, decl.initialValue);
					result.Add(generator.newExpressionStatement(asgExpr, decl.initialValue));
				}
				else
				{
					AbstractType varType = SQL_Type.findAbstractType(generator, decl.type);
					AbstractStatement varDecl = generator.newDataDeclaration(false, varName, null, varType, null, null);
					generator.addStatement(varDecl, null);
				}
			}

			return result;
		}
	}

}
