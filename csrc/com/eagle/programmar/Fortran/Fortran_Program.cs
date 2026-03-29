// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 23, 2022

namespace com.eagle.programmar.Fortran
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Fortran_Function = com.eagle.programmar.Fortran.Statements.Fortran_Function;
	using Fortran_Subroutine = com.eagle.programmar.Fortran.Statements.Fortran_Subroutine;
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

	public class Fortran_Program : AbstractLanguage, EagleRunnable, EagleTransformableProgram
	{
		public const string FORTRAN = "Fortran";

		public Fortran_Program() : base(FORTRAN, new Fortran_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "https://docs.oracle.com/cd/E19957-01/805-4939/";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<Fortran_Statement> statements;
		public TokenList<Fortran_Statement> statements;

		public override void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the FUNCTION definitions
			foreach (Fortran_Statement stmt in statements._elements)
			{
				AbstractToken which = stmt.getWhich();
				if (which is Fortran_Function)
				{
					Fortran_Function fn = (Fortran_Function) which;
					interpreter.addFunction(fn.id.getValue(), fn);
				}
				else if (which is Fortran_Subroutine)
				{
					Fortran_Subroutine sub = (Fortran_Subroutine) which;
					interpreter.addFunction(sub.id.getValue(), sub);
				}
			}

			// Second pass, execute the program
			foreach (Fortran_Statement stmt in statements._elements)
			{
				interpreter.tryToInterpret(stmt);
			}
		}

		public override AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// First pass, just collect all the FUNCTION, SUBROUTINE and PROGRAM definitions
			foreach (Fortran_Statement stmt in statements._elements)
			{
				AbstractToken which = stmt.getWhich();
				if (which is EagleTransformableFunction)
				{
					EagleTransformableFunction transformable = (EagleTransformableFunction) which;
					transformable.transformFunction(transformer, generator);
				}
			}

			// Only needed by Python
			generator.addCallToMain();

			return generator.getTransfomedProgram();
		}
	}

}
