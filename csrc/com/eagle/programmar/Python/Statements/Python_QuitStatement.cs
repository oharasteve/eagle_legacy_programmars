// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 4, 2024

namespace com.eagle.programmar.Python.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using Python_ComplexStatement = com.eagle.programmar.Python.Python_ComplexStatement;
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Python_QuitStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NOSPACE Python_Keyword QUIT = new com.eagle.programmar.Python.Terminals.Python_Keyword("quit");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Python_Expression code;
		public  OPT;

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			interpreter._exitCode = interpreter.getIntValue(code);
			return Eagle_Statement_Result.BREAK;
		}

		public static Python_ComplexStatement newQuitStatement(AbstractExpression code, AbstractToken source)
		{
			Python_QuitStatement stmt = new Python_QuitStatement();
			stmt.code = (Python_Expression) code;
			stmt.setTransformationSource(source);
			return Python_Generator.wrapStatement(stmt);
		}
	}

}
