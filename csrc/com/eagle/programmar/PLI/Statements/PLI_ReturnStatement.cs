// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 19, 2011

namespace com.eagle.programmar.PLI.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleValue = com.eagle.math.EagleValue;
	using PLI_Expression = com.eagle.programmar.PLI.PLI_Expression;
	using PLI_Procedure = com.eagle.programmar.PLI.PLI_Procedure;
	using PLI_Keyword = com.eagle.programmar.PLI.Terminals.PLI_Keyword;
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

	public class PLI_ReturnStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("7.48") com.eagle.programmar.PLI.Terminals.PLI_Keyword RETURN = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("RETURN");
		public @DOC("7.48") PLI_Keyword RETURN = new PLI_Keyword("RETURN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PLI_Expression expression;
		public @OPT PLI_Expression expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			EagleValue val = interpreter.getEagleValue(expression);

			AbstractToken parent = this.getParent();
			while (parent != null)
			{
				if (parent is PLI_Procedure)
				{
					PLI_Procedure proc = (PLI_Procedure) parent;
					proc._returnMetrics.returned(val.getType());
					break;
				}
				parent = parent.getParent();
			}

			interpreter.pushEagleValue(val);
			return Eagle_Statement_Result.RETURN;
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression retExpr = null;
			if (expression != null && expression.isPresent())
			{
				retExpr = transformer.transformExpression(generator, expression);
			}
			return generator.newReturnStatement(retExpr, this);
		}
	}

}
