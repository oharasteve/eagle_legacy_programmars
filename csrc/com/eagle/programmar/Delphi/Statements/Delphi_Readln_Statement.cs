// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

namespace com.eagle.programmar.Delphi.Statements
{
	using Delphi_Identifier_Reference = com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
	using Delphi_Keyword = com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Delphi_Readln_Statement : TokenSequence, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("System.Readln") com.eagle.programmar.Delphi.Terminals.Delphi_Keyword READLN = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("ReadLn");
		public @DOC("System.Readln") Delphi_Keyword READLN = new Delphi_Keyword("ReadLn");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Delphi_Readln_What what;
		public @OPT Delphi_Readln_What what;

		public static class Delphi_Readln_What extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Readln_NoFile XXnoFile;
			public Delphi_Readln_NoFile XXnoFile;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Readln_FromFile XXfromFile;
			public Delphi_Readln_FromFile XXfromFile;
		}

		public static class Delphi_Readln_NoFile extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference var;
			public Delphi_Identifier_Reference var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public static class Delphi_Readln_FromFile extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference file;
			public Delphi_Identifier_Reference file;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationComma comma;
			public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference var;
			public Delphi_Identifier_Reference var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (what.getWhich() is Delphi_Readln_NoFile)
			{
				Delphi_Readln_NoFile noFile = (Delphi_Readln_NoFile) what.getWhich();
				string var1 = noFile.var.getValue();
				return generator.newReadInteger(var1, this);
			}
			else if (what.getWhich() is Delphi_Readln_FromFile)
			{
				Delphi_Readln_FromFile fromFile = (Delphi_Readln_FromFile) what.getWhich();
				string id = fromFile.file.getValue();
				string var2 = fromFile.var.getValue();
				return generator.newFileReadStatement(id, var2, this);
			}
			else
			{
				throw new Exception("Unable to handle: " + what.getWhich());
			}
		}
	}

}
