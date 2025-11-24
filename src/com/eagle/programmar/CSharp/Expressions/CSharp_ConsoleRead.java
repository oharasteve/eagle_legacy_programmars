// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_ConsoleRead extends PrimaryOperator
{
	public @S(10) @NEWLINE @OPT CSharp_Keyword SYSTEM = new CSharp_Keyword("System");
	public @S(20) @NOSPACE @OPT PunctuationPeriod dot1;
	public @S(30) @NOSPACE CSharp_Keyword CONSOLE = new CSharp_Keyword("Console");
	public @S(40) @NOSPACE PunctuationPeriod dot2;
	public @S(50) @NOSPACE CSharp_KeywordChoice READ = new CSharp_KeywordChoice("OpenStandardInput",
			"OpenStandardOutput", "ReadKey", "ReadLine");
	public @S(60) @NOSPACE PunctuationLeftParen leftParen;
	public @S(70) @NOSPACE @OPT SeparatedList<CSharp_Expression, PunctuationComma> exprs;
	public @S(80) @NOSPACE PunctuationRightParen rightParen;
}
