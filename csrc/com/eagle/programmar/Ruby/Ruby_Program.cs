// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

namespace com.eagle.programmar.Ruby
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using AssignMetrics = com.eagle.metrics.AssignMetrics;
	using Ruby_Function = com.eagle.programmar.Ruby.Statements.Ruby_Function;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableProgram = com.eagle.transform.EagleTransformableProgram;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Ruby_Program : AbstractLanguage, EagleRunnable, EagleTransformableProgram
	{
		public const string RUBY = "Ruby";

		public Ruby_Program() : base(RUBY, new Ruby_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "https://docs.ruby-lang.org/en/2.4.0/syntax/";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<Ruby_Statement> elements;
		public TokenList<Ruby_Statement> elements;

		public override void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the FUNCTION definitions
			foreach (Ruby_Statement stmt in elements._elements)
			{
				AbstractToken which = stmt.getWhich();
				if (which is Ruby_Function)
				{
					Ruby_Function fn = (Ruby_Function) which;
					interpreter.addFunction(fn.id.getValue(), fn);
				}
			}

			// Second pass, execute the program
			foreach (Ruby_Statement stmt in elements._elements)
			{
				interpreter.tryToInterpret(stmt);
			}
		}

		public override AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// First pass, transform all the Function definitions
			foreach (Ruby_Statement stmt in elements._elements)
			{
				AbstractToken which = stmt.getWhich();
				if (which is Ruby_Function)
				{
					Ruby_Function func = (Ruby_Function) which;
					func.transformFunction(transformer, generator);
				}
			}

			// Are there any global variables we need to declare?
			string scopeStr = this._currentLine + "-" + this._endLine;
			List<AssignMetrics> asgMetrics = transformer._metrics.findVarsInScope(scopeStr);
			foreach (AssignMetrics met in asgMetrics)
			{
				EagleGenerator.TypeEnum typE = met.uniqueType();
				if (typE != EagleGenerator.TypeEnum.VOID)
				{
					AbstractType abstrType = generator.transformType(typE, null, this);

					// System.err.println("****** Found var " + met._symbolName);
					AbstractStatement dataStmt = generator.newDataDeclaration(false, met._symbolName, null, abstrType, null, this);
					generator.addStatement(dataStmt, this);
				}
			}

			// Second pass, transform all the data and logic
			foreach (Ruby_Statement stmt in elements._elements)
			{
				AbstractToken which = stmt.getWhich();
				if (!(which is Ruby_Function))
				{
					ICollection<AbstractStatement> newStmts = transformer.transformStatement(generator, which);
					if (newStmts != null)
					{
						foreach (AbstractStatement newStmt in newStmts)
						{
							generator.addStatement(newStmt, stmt);
						}
					}
				}
			}

			return generator.getTransfomedProgram();
		}
	}
}
