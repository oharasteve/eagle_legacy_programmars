// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

namespace com.eagle.programmar.Gupta.Declarations
{
	using Gupta_Declaration = com.eagle.programmar.Gupta.Gupta_Declaration;
	using Gupta_Statement = com.eagle.programmar.Gupta.Gupta_Statement;
	using Gupta_Type = com.eagle.programmar.Gupta.Gupta_Type;
	using Gupta_Variable_Declaration = com.eagle.programmar.Gupta.Gupta_Variable_Declaration;
	using Gupta_Function_Definition = com.eagle.programmar.Gupta.Symbols.Gupta_Function_Definition;
	using Gupta_CommentToEndOfLine = com.eagle.programmar.Gupta.Terminals.Gupta_CommentToEndOfLine;
	using Gupta_Keyword = com.eagle.programmar.Gupta.Terminals.Gupta_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;

	public class Gupta_Function : Gupta_Declaration
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword Function = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("Function");
		public Gupta_Keyword Function = new Gupta_Keyword("Function");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
		public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Gupta.Symbols.Gupta_Function_Definition functionName;
		public Gupta_Function_Definition functionName;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) Gupta_Function_Description description;
		public Gupta_Function_Description description;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) Gupta_Function_Returns returns;
		public Gupta_Function_Returns returns;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) Gupta_Function_Parameters parameters;
		public Gupta_Function_Parameters parameters;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) Gupta_Function_Static_Variables staticVariables;
		public Gupta_Function_Static_Variables staticVariables;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) Gupta_Function_Local_Variables localVariables;
		public Gupta_Function_Local_Variables localVariables;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) Gupta_Function_Actions actions;
		public Gupta_Function_Actions actions;

		public class Gupta_Function_Description : Gupta_Declaration
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword Description = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("Description");
			public Gupta_Keyword Description = new Gupta_Keyword("Description");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Gupta.Terminals.Gupta_CommentToEndOfLine description;
			public Gupta_CommentToEndOfLine description;
		}

		public class Gupta_Function_Returns : Gupta_Declaration
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword Returns = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("Returns");
			public Gupta_Keyword Returns = new Gupta_Keyword("Returns");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Gupta_Function_Return_Type returnType;
			public  OPT;

			public class Gupta_Function_Return_Type : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Gupta.Gupta_Type returnType;
				public Gupta_Type returnType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
				public PunctuationColon colon;
			}
		}

		public class Gupta_Function_Parameters : Gupta_Declaration
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword Parameters = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("Parameters");
			public Gupta_Keyword Parameters = new Gupta_Keyword("Parameters");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<com.eagle.programmar.Gupta.Gupta_Variable_Declaration> variables;
			public  OPT;
		}

		public class Gupta_Function_Static_Variables : Gupta_Declaration
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword Static = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("Static");
			public Gupta_Keyword Static = new Gupta_Keyword("Static");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword Variables = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("Variables");
			public Gupta_Keyword Variables = new Gupta_Keyword("Variables");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.Gupta.Gupta_Variable_Declaration> variables;
			public  OPT;
		}

		public class Gupta_Function_Local_Variables : Gupta_Declaration
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword Local = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("Local");
			public Gupta_Keyword Local = new Gupta_Keyword("Local");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword Variables = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("Variables");
			public Gupta_Keyword Variables = new Gupta_Keyword("Variables");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.Gupta.Gupta_Variable_Declaration> variables;
			public  OPT;
		}

		public class Gupta_Function_Actions : Gupta_Declaration
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword Actions = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("Actions");
			public Gupta_Keyword Actions = new Gupta_Keyword("Actions");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<com.eagle.programmar.Gupta.Gupta_Statement> statements;
			public TokenList<Gupta_Statement> statements;
		}
	}

}
