// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

namespace com.eagle.programmar.Powershell
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using AssignMetrics = com.eagle.metrics.AssignMetrics;
	using Powershell_Function = com.eagle.programmar.Powershell.Statements.Powershell_Function;
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

	public class Powershell_Program : AbstractLanguage, EagleRunnable, EagleTransformableProgram
	{
		public const string POWERHSELL = "Powershell";

		public Powershell_Program() : base(POWERHSELL, new Powershell_Syntax())
		{
		}

		public override string booleanName(bool flag)
		{
			if (flag)
			{
				return "$True";
			}
			return "$False";
		}

		public override string DocRoot
		{
			get
			{
				return "https://docs.microsoft.com/en-us/powershell/scripting/lang-spec/";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<Powershell_CommentEoln> comments1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Powershell_Directive> directives;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Powershell_CmdletBinding cmdletBinding;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Powershell_Parameters parameters;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<Powershell_CommentEoln> comments2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT TokenList<Powershell_Element> statements;
		public  OPT;

		public override void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the FUNCTION definitions
			foreach (Powershell_Element stmt in statements._elements)
			{
				if (stmt.element.getWhich() is Powershell_Function)
				{
					Powershell_Function fn = (Powershell_Function) stmt.element.getWhich();
					interpreter.addFunction(fn.id.getValue(), fn);
				}
			}

			// Second pass, execute the program
			foreach (Powershell_Element stmt in statements._elements)
			{
				interpreter.tryToInterpret(stmt.element);
			}
		}

		public override AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// Transform all the Function definitions first
			foreach (Powershell_Element stmt in statements._elements)
			{
				AbstractToken whichStmt = stmt.element.getWhich();
				if (whichStmt is Powershell_Function)
				{
					Powershell_Function func = (Powershell_Function) whichStmt;
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
					AbstractExpression initExpr = null;
					AbstractStatement dataStmt = generator.newDataDeclaration(false, met._symbolName, null, abstrType, initExpr, this);
					generator.addStatement(dataStmt, this);
				}
			}

			// Transform all the global data and logic, etc.
			foreach (Powershell_Element stmt in statements._elements)
			{
				AbstractToken whichStmt = stmt.element.getWhich();
				ICollection<AbstractStatement> newStmts = transformer.transformStatement(generator, whichStmt);
				if (newStmts != null)
				{
					foreach (AbstractStatement newStmt in newStmts)
					{
						generator.addStatement(newStmt, whichStmt);
					}
				}
			}

			return generator.getTransfomedProgram();
		}
	}
}
