// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2024

namespace com.eagle.programmar.PHP
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using PHP_EndTag = com.eagle.programmar.PHP.PHP_Program.PHP_EndTag;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatementList = com.eagle.transform.EagleTransformableStatementList;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class PHP_Body : TokenChooser
	{
		// Really wasteful ... frequently parses twice
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PHP_MissingEnd extends com.eagle.tokens.TokenSequence
		public class PHP_MissingEnd : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<PHP_Element> elements;
			public TokenList<PHP_Element> elements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) PHP_EndOfFile eof;
			public PHP_EndOfFile eof; // Can't be inside another class ...
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PHP_NormalBlock extends com.eagle.tokens.TokenSequence implements com.eagle.interpret.EagleRunnable, com.eagle.transform.EagleTransformableStatementList
		public class PHP_NormalBlock : TokenSequence, EagleRunnable, EagleTransformableStatementList
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<PHP_Element> elements;
			public TokenList<PHP_Element> elements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.PHP.PHP_Program.PHP_EndTag endTag;
			public PHP_EndTag endTag;

			public override void interpret(EagleInterpreter interpreter)
			{
				foreach (PHP_Element entry in elements._elements)
				{
					interpreter.tryToInterpret(entry);
				}
			}

			public override List<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
			{
				List<AbstractStatement> result = new List<AbstractStatement>();
				foreach (PHP_Element entry in elements._elements)
				{
					List<AbstractStatement> stmts = transformer.transformStatement(generator, entry.getWhich());
					if (stmts != null)
					{
						foreach (AbstractStatement stmt in stmts)
						{
							result.Add(stmt);
						}
					}
				}
				return result;
			}
		}
	}

}
