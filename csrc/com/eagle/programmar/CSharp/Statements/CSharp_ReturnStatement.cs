// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

namespace com.eagle.programmar.CSharp.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleValue = com.eagle.math.EagleValue;
	using CSharp_Expression = com.eagle.programmar.CSharp.CSharp_Expression;
	using CSharp_Generator = com.eagle.programmar.CSharp.CSharp_Generator;
	using CSharp_Method = com.eagle.programmar.CSharp.CSharp_Method;
	using CSharp_Statement = com.eagle.programmar.CSharp.CSharp_Statement;
	using CSharp_Keyword = com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
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

	public class CSharp_ReturnStatement : TokenSequence, EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NEWLINE @OPT CSharp_Keyword YIELD = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("yield");
		public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("statements/jump-statements#the-return-statement") com.eagle.programmar.CSharp.Terminals.CSharp_Keyword RETURN = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("return");
		public @DOC("statements/jump-statements#the-return-statement") CSharp_Keyword RETURN = new CSharp_Keyword("return");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT CSharp_Expression expression;
		public @OPT CSharp_Expression expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationSemicolon semicolon;
		public @NOSPACE PunctuationSemicolon semicolon;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			EagleValue val = interpreter.getEagleValue(expression);

			AbstractToken parent = this.getParent();
			while (parent != null)
			{
				if (parent is CSharp_Method)
				{
					CSharp_Method meth = (CSharp_Method) parent;
					meth._returnMetrics.returned(val.getType());
					break;
				}
				parent = parent.getParent();
			}

			interpreter.pushEagleValue(val);
			return Eagle_Statement_Result.RETURN;
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression expr = transformer.transformExpression(generator, expression);
			return generator.newReturnStatement(expr, this);
		}

		public static CSharp_Statement generateReturn(CSharp_Expression ret, AbstractToken source)
		{
			CSharp_ReturnStatement retStmt = new CSharp_ReturnStatement();
			if (ret != null)
			{
				retStmt.expression = ret;
				retStmt.expression.setPresent(true);
			}
			retStmt.semicolon = new PunctuationSemicolon();
			retStmt.setTransformationSource(source);
			return CSharp_Generator.wrapStatement(retStmt);
		}
	}

}
