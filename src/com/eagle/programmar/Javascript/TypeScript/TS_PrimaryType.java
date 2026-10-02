// Copyright Eagle Legacy Modernization LLC, 2010-date
// Added on the shane branch, Oct 2, 2026: TypeScript is JavaScript plus types. The type
// syntax lives here; the JavaScript rules carry optional hooks to it, so one grammar reads both.

package com.eagle.programmar.Javascript.TypeScript;

import com.eagle.programmar.Javascript.Javascript_Variable;
import com.eagle.programmar.Javascript.Symbols.Javascript_Class_Reference;
import com.eagle.programmar.Javascript.Symbols.Javascript_Field_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.programmar.Javascript.Terminals.Javascript_Literal;
import com.eagle.programmar.Javascript.Terminals.Javascript_Number;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.programmar.Javascript.Terminals.Javascript_TemplateLiteral;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class TS_PrimaryType extends TokenChooser
{
	// The engine takes the first alternative that matches and does not come back, so the
	// keyword-led types come before the name-led reference, and the reference comes last.
	public @CHOICE TS_ObjectType XXobject;
	public @CHOICE Javascript_Literal XXliteral;
	public @CHOICE Javascript_TemplateLiteral XXtemplate;
	public @CHOICE Javascript_Number XXnumber;
	public @CHOICE Javascript_KeywordChoice XXprimitive = new Javascript_KeywordChoice(
			"number", "string", "boolean", "void", "any", "unknown", "never", "null", "undefined",
			"object", "bigint", "symbol", "this", "true", "false", "const");
	public @CHOICE TS_TypeOfType XXtypeof;
	public @CHOICE TS_KeyOfType XXkeyof;
	public @CHOICE TS_FunctionType XXfunction;
	public @CHOICE TS_ParenType XXparen;
	public @CHOICE TS_TupleType XXtuple;
	public @CHOICE TS_TypeReference XXreference;
}
