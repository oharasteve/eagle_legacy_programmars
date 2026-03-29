// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.COBOL
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class COBOL_Sentence : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<COBOL_StatementOrComment> statements;
		public TokenList<COBOL_StatementOrComment> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PunctuationPeriod dot1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @CURIOUS("SENTENCE: Extra dot") @OPT PunctuationPeriod dot2;
		public @CURIOUS("SENTENCE: Extra dot") PunctuationPeriod dot2;

		public void interpret(EagleInterpreter interpreter)
		{
			foreach (COBOL_StatementOrComment statement in statements._elements)
			{
				interpreter.tryToInterpret(statement);
			}
		}

		public void transform(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			foreach (COBOL_StatementOrComment stmtOrComm in statements._elements)
			{
				if (stmtOrComm.getWhich() is COBOL_Statement)
				{
					COBOL_Statement stmt = (COBOL_Statement) stmtOrComm.getWhich();
					if (stmt.getWhich() is EagleTransformableStatement)
					{
						EagleTransformableStatement trans = (EagleTransformableStatement) stmt.getWhich();
						AbstractStatement newStmt = trans.transformStatement(transformer, generator);
						generator.addStatement(newStmt, stmt);
					}
					else
					{
						throw new Exception("Unable to transform " + stmt.getWhich());
					}
				}
			}
		}
	}
}
