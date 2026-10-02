// Copyright Eagle Legacy Modernization LLC, 2010-date
// Added on the shane branch, Oct 1, 2026: a member whose name is a reserved word, with or
// without a call: promise.then(f).catch(g), module.default, obj.delete(k). Javascript_Subfield
// is tried first and takes every ordinary name; this one only matches what it refuses.

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_ParenthesizedExpression;
import com.eagle.programmar.Javascript.Symbols.Javascript_Field_Reference;
import com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Javascript_SubfieldKeyword extends PrecedenceOperator
{
	public @S(10) Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Javascript_PunctuationChoice dot = new Javascript_PunctuationChoice(".", "?.");
	public @S(30) Javascript_Field_Reference field;
	public @S(40) @OPT Javascript_ParenthesizedExpression arguments;
}
