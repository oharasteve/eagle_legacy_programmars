// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 16, 2013

namespace com.eagle.programmar.Delphi
{
	using Delphi_FunctionForward = com.eagle.programmar.Delphi.Delphi_Function.Delphi_FunctionForward;
	using Delphi_ProcedureForward = com.eagle.programmar.Delphi.Delphi_Procedure.Delphi_ProcedureForward;
	using Delphi_Variable_Definition = com.eagle.programmar.Delphi.Symbols.Delphi_Variable_Definition;
	using Delphi_Comment = com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
	using Delphi_Keyword = com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractClass = com.eagle.tokens.interfaces.AbstractClass;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class Delphi_Class : TokenSequence, AbstractClass
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Delphi.Terminals.Delphi_Keyword CLASS = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("Class");
		public Delphi_Keyword CLASS = new Delphi_Keyword("Class");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Delphi_SuperClass superClass;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<Delphi_Class_Entry> classEntries;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Delphi_PrivateEntries privateEntries;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Delphi_ProtectedEntries protectedEntries;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Delphi_PublicEntries publicEntries;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Delphi.Terminals.Delphi_Keyword END = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("End");
		public Delphi_Keyword END = new Delphi_Keyword("End");

		public class Delphi_SuperClass : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Delphi_Type parentType;
			public Delphi_Type parentType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public class Delphi_Class_Entry : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Comment XXcomment;
			public Delphi_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_ProcedureForward XXprocedure;
			public Delphi_ProcedureForward XXprocedure;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_FunctionForward XXfunction;
			public Delphi_FunctionForward XXfunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Property XXproperty;
			public Delphi_Property XXproperty;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Delphi_Field extends com.eagle.tokens.TokenSequence
			public class Delphi_Field : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.SeparatedList<com.eagle.programmar.Delphi.Symbols.Delphi_Variable_Definition, com.eagle.tokens.punctuation.PunctuationComma> variables;
				public SeparatedList<Delphi_Variable_Definition, PunctuationComma> variables;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
				public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Delphi_Type type;
				public Delphi_Type type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
				public PunctuationSemicolon semicolon;
			}
		}

		public class Delphi_PrivateEntries : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Delphi.Terminals.Delphi_Keyword PRIVATE = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("Private");
			public Delphi_Keyword PRIVATE = new Delphi_Keyword("Private");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<Delphi_Class_Entry> classEntries;
			public TokenList<Delphi_Class_Entry> classEntries;
		}

		public class Delphi_ProtectedEntries : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Delphi.Terminals.Delphi_Keyword PROTECTED = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("Protected");
			public Delphi_Keyword PROTECTED = new Delphi_Keyword("Protected");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<Delphi_Class_Entry> classEntries;
			public TokenList<Delphi_Class_Entry> classEntries;
		}

		public class Delphi_PublicEntries : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Delphi.Terminals.Delphi_Keyword PUBLIC = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("Public");
			public Delphi_Keyword PUBLIC = new Delphi_Keyword("Public");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<Delphi_Class_Entry> classEntries;
			public TokenList<Delphi_Class_Entry> classEntries;
		}
	}

}
