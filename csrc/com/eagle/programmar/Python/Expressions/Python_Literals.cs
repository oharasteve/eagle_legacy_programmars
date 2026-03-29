// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Text;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Python.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_Literal = com.eagle.programmar.Python.Terminals.Python_Literal;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using TokenList = com.eagle.tokens.TokenList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Python_Literals : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<com.eagle.programmar.Python.Terminals.Python_Literal> literals;
		public TokenList<Python_Literal> literals;

		public override void interpret(EagleInterpreter interpreter)
		{
			if (literals._elements.size() == 1)
			{
				Python_Literal literal = literals._elements.get(0);
				interpreter.pushStr(literal.removeQuotes());
			}
			else
			{
				StringBuilder sb = new StringBuilder();
				foreach (Python_Literal literal in literals._elements)
				{
					sb.Append(literal.removeQuotes());
				}
				interpreter.pushStr(sb.ToString());
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (literals._elements.size() != 1)
			{
				throw new Exception("Cannot handle multiple literals yet");
			}
			Python_Literal literal = literals._elements.get(0);
			return literal.transformExpression(transformer, generator);
		}

		public static Python_Literals generateLiterals(string txt, AbstractToken source)
		{
			string val = txt;
			Python_Literal lit1 = Python_Literal.generateLiteral(val, source);
			Python_Literals lits = new Python_Literals();
			lits.literals = new TokenList<Python_Literal>();
			lits.literals.addToken(lit1);
			lits.setTransformationSource(source);
			return lits;
		}

		public static Python_Expression generateLiteralsExpression(string txt, AbstractToken source)
		{
			Python_Literals lit = generateLiterals(txt, source);
			return Python_Generator.wrapExpression(lit);
		}
	}

}
