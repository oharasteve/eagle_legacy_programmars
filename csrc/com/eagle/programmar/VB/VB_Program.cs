// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

namespace com.eagle.programmar.VB
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using VB_Statement = com.eagle.programmar.VB.VB_Element.VB_Statement;
	using VB_Function = com.eagle.programmar.VB.Statements.VB_Function;
	using VB_Subroutine = com.eagle.programmar.VB.Statements.VB_Subroutine;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableFunction = com.eagle.transform.EagleTransformableFunction;
	using EagleTransformableProgram = com.eagle.transform.EagleTransformableProgram;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class VB_Program : AbstractLanguage, EagleRunnable, EagleTransformableProgram
	{
		public const string VB = "VB";

		public VB_Program() : base(VB, new VB_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "https://learn.microsoft.com/en-us/dotnet/visual-basic/language-reference/";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<VB_Element> statements;
		public  OPT;

		public override void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the Function and Sub definitions
			foreach (VB_Element stmt in statements._elements)
			{
				for (int i = 0; i < stmt.baseStatements.getPrimaryCount(); i++)
				{
					VB_Statement baseStatement = stmt.baseStatements.getPrimaryElement(i);
					AbstractToken which = baseStatement.getWhich();
					if (which is VB_Function)
					{
						VB_Function func = (VB_Function) which;
						interpreter.addFunction(func.id.getValue(), func);
					}
					if (which is VB_Subroutine)
					{
						VB_Subroutine sub = (VB_Subroutine) which;
						interpreter.addFunction(sub.id.getValue(), sub);
					}
				}
			}

			// Second pass, run any stuff in the outermost 'object'
			foreach (VB_Element stmt in statements._elements)
			{
				for (int i = 0; i < stmt.baseStatements.getPrimaryCount(); i++)
				{
					VB_Statement baseStatement = stmt.baseStatements.getPrimaryElement(i);
					interpreter.tryToInterpret(baseStatement);
				}
			}
		}

		public override AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// First pass, transform all the Function and Sub definitions
			foreach (VB_Element stmt in statements._elements)
			{
				for (int i = 0; i < stmt.baseStatements.getPrimaryCount(); i++)
				{
					VB_Statement baseStatement = stmt.baseStatements.getPrimaryElement(i);
					AbstractToken which = baseStatement.getWhich();
					if (which is EagleTransformableFunction)
					{
						EagleTransformableFunction transformable = (EagleTransformableFunction) which;
						transformable.transformFunction(transformer, generator);
					}
				}
			}

			// Second pass, transform all the data and logic
			foreach (VB_Element stmt in statements._elements)
			{
				for (int i = 0; i < stmt.baseStatements.getPrimaryCount(); i++)
				{
					VB_Statement baseStatement = stmt.baseStatements.getPrimaryElement(i);
					AbstractToken which = baseStatement.getWhich();
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
