// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

namespace com.eagle.programmar.Fortran.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Fortran_EOLN = com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
	using Fortran_Keyword = com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Fortran_Implicit : TokenSequence, EagleRunnable, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("6j4m0vn9v/index.html") com.eagle.programmar.Fortran.Terminals.Fortran_Keyword IMPLICIT = new com.eagle.programmar.Fortran.Terminals.Fortran_Keyword("IMPLICIT");
		public @DOC("6j4m0vn9v/index.html") Fortran_Keyword IMPLICIT = new Fortran_Keyword("IMPLICIT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Fortran.Terminals.Fortran_Keyword NONE = new com.eagle.programmar.Fortran.Terminals.Fortran_Keyword("NONE");
		public Fortran_Keyword NONE = new Fortran_Keyword("NONE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Fortran.Terminals.Fortran_EOLN eoln;
		public Fortran_EOLN eoln;

		public void interpret(EagleInterpreter interpreter)
		{
			// Nothing to do here
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			return null; // Nothing to do here
		}
	}

}
