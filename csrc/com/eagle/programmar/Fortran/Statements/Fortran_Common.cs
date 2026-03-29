// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

namespace com.eagle.programmar.Fortran.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Fortran_Statement = com.eagle.programmar.Fortran.Fortran_Statement;
	using Fortran_Common_Reference = com.eagle.programmar.Fortran.Symbols.Fortran_Common_Reference;
	using Fortran_Variable_Reference = com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference;
	using Fortran_EOLN = com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
	using Fortran_Keyword = com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationSlash = com.eagle.tokens.punctuation.PunctuationSlash;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatementList = com.eagle.transform.EagleTransformableStatementList;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Fortran_Common : TokenSequence, EagleRunnable, EagleTransformableStatementList
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("6j4m0vn7v/index.html") com.eagle.programmar.Fortran.Terminals.Fortran_Keyword COMMON = new com.eagle.programmar.Fortran.Terminals.Fortran_Keyword("COMMON");
		public @DOC("6j4m0vn7v/index.html") Fortran_Keyword COMMON = new Fortran_Keyword("COMMON");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationSlash slash1;
		public PunctuationSlash slash1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Fortran.Symbols.Fortran_Common_Reference common;
		public Fortran_Common_Reference common;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationSlash slash2;
		public PunctuationSlash slash2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.SeparatedList<com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference, com.eagle.tokens.punctuation.PunctuationComma> variables;
		public SeparatedList<Fortran_Variable_Reference, PunctuationComma> variables;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Fortran.Terminals.Fortran_EOLN eoln;
		public Fortran_EOLN eoln;

		// Used to see if a variable is declared inside a COMMON block
		public static HashSet<string> collectCommons(List<Fortran_Statement> statements)
		{
			HashSet<string> commons = new HashSet<string>();
			foreach (Fortran_Statement stmt in statements)
			{
				if (stmt.getWhich() is Fortran_Common)
				{
					Fortran_Common common = (Fortran_Common) stmt.getWhich();
					int numCommons = common.variables.getPrimaryCount();
					for (int i = 0; i < numCommons; i++)
					{
						Fortran_Variable_Reference @ref = common.variables.getPrimaryElement(i);
						commons.Add(@ref.getValue());
					}
				}
			}
			return commons;
		}

		public void interpret(EagleInterpreter interpreter)
		{
			// Nothing to do here
		}

		public List<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractStatement> newStmts = new List<AbstractStatement>();
			int numCommons = variables.getPrimaryCount();
			for (int i = 0; i < numCommons; i++)
			{
				Fortran_Variable_Reference @ref = variables.getPrimaryElement(i);
				AbstractStatement newStmt = generator.newGlobalVariable(@ref.getValue(), this);
				newStmts.Add(newStmt);
			}
			return newStmts;
		}
	}

}
