// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Delphi_Readln_Statement extends TokenSequence
		implements AbstractStatement, EagleTransformableStatement
{
	public @S(10) @DOC("System.Readln") Delphi_Keyword READLN = new Delphi_Keyword("ReadLn");
	public @S(20) @OPT Delphi_Readln_What what;

	public static class Delphi_Readln_What extends TokenChooser
	{
		public @CHOICE Delphi_Readln_NoFile XXnoFile;
		public @CHOICE Delphi_Readln_FromFile XXfromFile;
	}

	public static class Delphi_Readln_NoFile extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Delphi_Identifier_Reference var;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static class Delphi_Readln_FromFile extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Delphi_Identifier_Reference file;
		public @S(30) PunctuationComma comma;
		public @S(40) Delphi_Identifier_Reference var;
		public @S(50) PunctuationRightParen rightParen;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		if (what.getWhich() instanceof Delphi_Readln_NoFile)
		{
			Delphi_Readln_NoFile noFile = (Delphi_Readln_NoFile) what.getWhich();
			String var1 = noFile.var.getValue();
			return generator.newReadInteger(var1, this);
		}
		else if (what.getWhich() instanceof Delphi_Readln_FromFile)
		{
			Delphi_Readln_FromFile fromFile = (Delphi_Readln_FromFile) what.getWhich();
			String id = fromFile.file.getValue();
			String var2 = fromFile.var.getValue();
			return generator.newFileReadStatement(id, var2, this);
		}
		else
			throw new RuntimeException("Unable to handle: " + what.getWhich());
	}
}
