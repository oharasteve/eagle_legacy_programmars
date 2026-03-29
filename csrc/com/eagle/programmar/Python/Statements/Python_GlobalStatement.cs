// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2014

namespace com.eagle.programmar.Python.Statements
{
	using Python_ComplexStatement = com.eagle.programmar.Python.Python_ComplexStatement;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_Identifier_Reference = com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
	using Python_KeywordChoice = com.eagle.programmar.Python.Terminals.Python_KeywordChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;

	public class Python_GlobalStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("simple_stmts.html#the-global-statement") com.eagle.programmar.Python.Terminals.Python_KeywordChoice GLOBAL = new com.eagle.programmar.Python.Terminals.Python_KeywordChoice("global", "nonlocal");
		public @DOC("simple_stmts.html#the-global-statement") Python_KeywordChoice GLOBAL = new Python_KeywordChoice("global", "nonlocal");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.Python.Symbols.Python_Identifier_Reference, com.eagle.tokens.punctuation.PunctuationComma> vars;
		public SeparatedList<Python_Identifier_Reference, PunctuationComma> vars;

		public static Python_ComplexStatement generateGlobal(string variableName, AbstractToken source)
		{
			Python_GlobalStatement globStmt = new Python_GlobalStatement();
			globStmt.GLOBAL.setValue("global");
			globStmt.vars = new SeparatedList<Python_Identifier_Reference, PunctuationComma>();
			Python_Identifier_Reference id = new Python_Identifier_Reference();
			id.setValue(variableName);
			globStmt.vars.addPrimaryElement(id);

			globStmt.setTransformationSource(source);
			return Python_Generator.wrapStatement(globStmt);
		}
	}

}
