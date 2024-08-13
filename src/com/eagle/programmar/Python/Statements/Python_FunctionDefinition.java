// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Python.Python_Decorators;
import com.eagle.programmar.Python.Python_Parameter_List;
import com.eagle.programmar.Python.Python_Statement.Python_StatementBlock;
import com.eagle.programmar.Python.Python_Syntax;
import com.eagle.programmar.Python.Python_Type;
import com.eagle.programmar.Python.Symbols.Python_Function_Definition;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.punctuation.PunctuationColon;

// Why does this implement AbstractMethod ?? Transformation needs / uses it, but why ??
public class Python_FunctionDefinition extends TokenSequence
			implements AbstractMethod, AbstractFunction, EagleRunnable
{
	public @S(10) @OPT Python_Decorators decorators;
	public @S(20) @OPT Python_Keyword ASYNC = new Python_Keyword("async");
	public @S(30) @NOSPACE @DOC("compound_stmts.html#function-definitions")
						Python_Keyword DEF = new Python_Keyword("def");
	public @S(40) Python_FunctionName fnName;
	public @S(50) Python_FunctionHeader header;
	
	public static class Python_FunctionHeader extends TokenSequence implements EagleScopeInterface
	{
		public @S(10) Python_Parameter_List params;
		public @S(20) @OPT Python_ReturnType returnType;
		public @S(30) @NOSPACE PunctuationColon colon;
		public @S(40) @OPT TokenList<Python_Comment> comment;
		public @S(50) Python_StatementBlock defBody;

		private @SKIP EagleScope _scope = new EagleScope(this, Python_Syntax.IS_CASE_SENSITIVE);

		@Override
		public EagleScope getScope()
		{
			return _scope;
		}
	}

	public static class Python_FunctionName extends TokenChooser
	{
		public @CHOICE Python_Function_Definition XXname;
		public @CHOICE Python_Keyword XXINIT = new Python_Keyword("__init__");
	}

	public static class Python_ReturnType extends TokenSequence
	{
		public @S(10) Python_Punctuation arrow = new Python_Punctuation("->");
		public @S(20) Python_Type type;
	}
	
	public @SKIP CallMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			Python_Function_Definition def = (Python_Function_Definition) fnName.getWhich();
			_metrics = new CallMetrics(interpreter._metrics, def.getValue(), this);
		}

		// Don't do anything here.
		// We searched for all the functions in a preliminary pass
		// And we only evaluate when it is called
	}
}
