// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

namespace com.eagle.programmar.TCL
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using AssignMetrics = com.eagle.metrics.AssignMetrics;
	using TCL_Statement = com.eagle.programmar.TCL.TCL_Element.TCL_Statement;
	using TokenList = com.eagle.tokens.TokenList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableFunction = com.eagle.transform.EagleTransformableFunction;
	using EagleTransformableProgram = com.eagle.transform.EagleTransformableProgram;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class TCL_Program : AbstractLanguage, EagleRunnable, EagleTransformableProgram
	{
		public const string TCL = "TCL";

		public TCL_Program() : base(TCL, new TCL_Syntax())
		{
		}

		public override string booleanName(bool flag)
		{
			if (flag)
			{
				return "$true";
			}
			return "$false";
		}

		public override string DocRoot
		{
			get
			{
				return "https://www.tcl.tk/man/tcl8.7/";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<TCL_Element> statements;
		public TokenList<TCL_Element> statements;

		public override void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the method definitions
			foreach (TCL_Element stmt in statements._elements)
			{
				for (int i = 0; i < stmt.statements.getPrimaryCount(); i++)
				{
					TCL_Statement @base = stmt.statements.getPrimaryElement(i);
					if (@base.getWhich() is TCL_Procedure)
					{
						TCL_Procedure proc = (TCL_Procedure) @base.getWhich();
						interpreter.addFunction(proc.id.getValue(), proc);
					}
				}
			}

			// Second pass, run any stuff in the outermost 'object'
			foreach (TCL_Element stmt in statements._elements)
			{
				interpreter.tryToInterpret(stmt);
			}
		}

		public override AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// First pass, transform all the Procedure definitions
			foreach (TCL_Element element in statements._elements)
			{
				int nstmts = element.statements.getPrimaryCount();
				for (int i = 0; i < nstmts; i++)
				{
					TCL_Statement @base = element.statements.getPrimaryElement(i);
					if (@base.getWhich() is EagleTransformableFunction)
					{
						EagleTransformableFunction transformable = (EagleTransformableFunction) @base.getWhich();
						transformable.transformFunction(transformer, generator);
					}
				}
			}

			// Are there any global variables we need to declare?
			string scopeStr = this._currentLine + "-" + this._endLine;
			List<AssignMetrics> asgMetrics = transformer._metrics.findVarsInScope(scopeStr);
			foreach (AssignMetrics met in asgMetrics)
			{
				EagleGenerator.TypeEnum typ = met.uniqueType();
				if (typ != EagleGenerator.TypeEnum.VOID)
				{
					// System.err.println("****** Found var " + met._symbolName);
					AbstractType absType = generator.transformType(typ, null, this);
					AbstractStatement dataStmt = generator.newDataDeclaration(false, met._symbolName, null, absType, null, this);
					generator.addStatement(dataStmt, this);
				}
			}

			// Second pass, transform all the data and logic
			foreach (TCL_Element element in statements._elements)
			{
				int nstmts = element.statements.getPrimaryCount();
				for (int i = 0; i < nstmts; i++)
				{
					TCL_Statement stmt = element.statements.getPrimaryElement(i);
					ICollection<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt.getWhich());
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
