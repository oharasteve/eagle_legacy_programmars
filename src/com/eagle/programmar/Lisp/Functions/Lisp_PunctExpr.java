// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 4, 2026

package com.eagle.programmar.Lisp.Functions;

import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationQuestionMark;

public class Lisp_PunctExpr extends PrimaryOperator
{
	// Can't do this as a terminal node in Lisp_Expression
	// Because Punctuation*.java do not extend PrimaryOperator
	public @S(10) PunctuationQuestionMark question;
}
