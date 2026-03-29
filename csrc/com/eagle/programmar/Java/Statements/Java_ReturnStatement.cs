// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

namespace com.eagle.programmar.Java.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleValue = com.eagle.math.EagleValue;
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using Java_Method = com.eagle.programmar.Java.Java_Method;
	using Java_Statement = com.eagle.programmar.Java.Java_Statement;
	using Java_Keyword = com.eagle.programmar.Java.Terminals.Java_Keyword;
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

	public class Java_ReturnStatement : TokenSequence, EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NEWLINE @DOC("statements.html#14.17") com.eagle.programmar.Java.Terminals.Java_Keyword RETURN = new com.eagle.programmar.Java.Terminals.Java_Keyword("return");
		public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Java_Expression expression;
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
					if (parent is Java_Method)
					{
						Java_Method meth = (Java_Method) parent;
						meth._returnMetrics.returned(val.getType());
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

		public static Java_Statement generateReturn(Java_Expression ret, AbstractToken source)
		{
			Java_ReturnStatement retStmt = new Java_ReturnStatement();
			if (ret != null)
			{
				retStmt.expression = ret;
				retStmt.expression.setPresent(true);
			}
			retStmt.semicolon = new PunctuationSemicolon();
			retStmt.setTransformationSource(source);
			return Java_Generator.wrapStatement(retStmt);
		}
	}

}
