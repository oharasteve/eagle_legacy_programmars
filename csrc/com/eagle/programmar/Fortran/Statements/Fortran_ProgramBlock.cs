// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

namespace com.eagle.programmar.Fortran.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Fortran_Statement = com.eagle.programmar.Fortran.Fortran_Statement;
	using Fortran_Syntax = com.eagle.programmar.Fortran.Fortran_Syntax;
	using Fortran_Function_Definition = com.eagle.programmar.Fortran.Symbols.Fortran_Function_Definition;
	using Fortran_Function_Reference = com.eagle.programmar.Fortran.Symbols.Fortran_Function_Reference;
	using Fortran_EOLN = com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
	using Fortran_Keyword = com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableFunction = com.eagle.transform.EagleTransformableFunction;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Fortran_ProgramBlock : TokenSequence, EagleRunnable, AbstractStatement, EagleScope.EagleScopeInterface, EagleTransformableFunction
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("6j4m0vnar/index.html") com.eagle.programmar.Fortran.Terminals.Fortran_Keyword PROGRAM1 = new com.eagle.programmar.Fortran.Terminals.Fortran_Keyword("PROGRAM");
		public @DOC("6j4m0vnar/index.html") Fortran_Keyword PROGRAM1 = new Fortran_Keyword("PROGRAM");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Fortran.Symbols.Fortran_Function_Definition id;
		public Fortran_Function_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Fortran.Terminals.Fortran_EOLN eoln1;
		public Fortran_EOLN eoln1;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<com.eagle.programmar.Fortran.Fortran_Statement> statements;
		public TokenList<Fortran_Statement> statements;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Fortran.Terminals.Fortran_Keyword END = new com.eagle.programmar.Fortran.Terminals.Fortran_Keyword("END");
		public Fortran_Keyword END = new Fortran_Keyword("END");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Fortran.Terminals.Fortran_Keyword PROGRAM2 = new com.eagle.programmar.Fortran.Terminals.Fortran_Keyword("PROGRAM");
		public Fortran_Keyword PROGRAM2 = new Fortran_Keyword("PROGRAM");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Fortran.Symbols.Fortran_Function_Reference fnName2;
		public Fortran_Function_Reference fnName2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.Fortran.Terminals.Fortran_EOLN eoln2;
		public Fortran_EOLN eoln2;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, com.eagle.programmar.Fortran.Fortran_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, Fortran_Syntax.IS_CASE_SENSITIVE);

		public EagleScope Scope
		{
			return _scope;
		}

		public void interpret(EagleInterpreter interpreter)
		{
			foreach (Fortran_Statement stmt in statements._elements)
			{
				interpreter.tryToInterpret(stmt);
			}
		}

		public void transformFunction(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			string fnName = id.getValue();
			if (VERBOSE)
			{
				Console.WriteLine("** Found Fortran program " + fnName);
			}

			// Set up the main program
			generator.addMethod(null, generator.mainName(), this);
			generator.addMainArgs();

			foreach (Fortran_Statement stmt in statements._elements)
			{
				ICollection<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt.getWhich());
				if (newStmts != null)
				{
					foreach (AbstractStatement newStmt in newStmts)
					{
						generator.addStatement(newStmt, stmt.getWhich());
					}
				}
			}

			generator.doneMethod();
		}
	}

}
