// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 22, 2022

package com.eagle.programmar.Powershell;

import com.eagle.programmar.Powershell.Terminals.Powershell_Comment;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.programmar.Powershell.Terminals.Powershell_Literal;
import com.eagle.programmar.Powershell.Terminals.Powershell_RealEndOfLine;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Powershell_Directive extends TokenSequence
{
	// [Diagnostics.CodeAnalysis.SuppressMessageAttribute("PSUseLiteralInitializerForHashtable", "")]
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) Powershell_Keyword DIAGNOSTICS = new Powershell_Keyword("Diagnostics");
	public @S(30) PunctuationPeriod dot1;
	public @S(40) Powershell_Keyword CODEANALYSIS = new Powershell_Keyword("CodeAnalysis");
	public @S(50) PunctuationPeriod dot2;
	public @S(60) Powershell_Keyword SUPPRESS = new Powershell_Keyword("SuppressMessageAttribute");
	public @S(70) PunctuationLeftParen leftParen;
	public @S(80) Powershell_Literal literal1;
	public @S(90) PunctuationComma comma;
	public @S(100) Powershell_Literal literal2;
	public @S(110) PunctuationRightParen rightParen;
	public @S(120) PunctuationRightBracket rightBracket;
	public @S(130) @OPT Powershell_Comment comment;
	public @S(140) Powershell_RealEndOfLine eoln;
}
