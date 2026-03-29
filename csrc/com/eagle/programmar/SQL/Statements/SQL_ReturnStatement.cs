// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

namespace com.eagle.programmar.SQL.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleValue = com.eagle.math.EagleValue;
	using SQL_Expression = com.eagle.programmar.SQL.SQL_Expression;
	using SQL_Keyword = com.eagle.programmar.SQL.Terminals.SQL_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class SQL_ReturnStatement : TokenSequence, EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NEWLINE @DOC("statements.html#14.17") com.eagle.programmar.SQL.Terminals.SQL_Keyword RETURN = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("return");
		public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT SQL_Expression expression;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE PunctuationSemicolon semicolon;
		public  NOSPACE;

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			if (expression != null && expression.isPresent())
			{
				EagleValue val = interpreter.getEagleValue(expression);

				AbstractToken parent = this.getParent();
				while (parent != null)
				{
					if (parent is SQL_CreateFunctionStatement)
					{
						SQL_CreateFunctionStatement func = (SQL_CreateFunctionStatement) parent;
						func._returnMetrics.returned(val.getType());
						break;
					}
					parent = parent.getParent();
				}

				interpreter.pushEagleValue(val);
			}
			return Eagle_Statement_Result.RETURN;
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression expr = transformer.transformExpression(generator, expression);
			return generator.newReturnStatement(expr, this);
		}
	}

}
