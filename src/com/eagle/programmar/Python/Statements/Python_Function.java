// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Python.Python_Decorators;
import com.eagle.programmar.Python.Python_Parameter_List;
import com.eagle.programmar.Python.Python_Params;
import com.eagle.programmar.Python.Python_Params.Python_MoreParams;
import com.eagle.programmar.Python.Python_Params.Python_Parameter;
import com.eagle.programmar.Python.Python_ComplexStatement;
import com.eagle.programmar.Python.Python_Syntax;
import com.eagle.programmar.Python.Python_Type;
import com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_MultilineStatement;
import com.eagle.programmar.Python.Symbols.Python_Function_Definition;
import com.eagle.programmar.Python.Symbols.Python_Variable_Definition;
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
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

// Why does this implement AbstractMethod ?? Transformation needs / uses it, but why ??
public class Python_Function extends TokenSequence
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
		public @S(50) @PYDENT Python_StatementBlock defBody;

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
	
	public static Python_Function newPythonFunction(String name)
	{
		Python_Function_Definition funcDef = new Python_Function_Definition();
		funcDef.setValue(name);
		Python_Function func = new Python_Function();
		func.fnName = new Python_FunctionName();
		func.fnName.setWhich(funcDef);
		
		func.header = new Python_FunctionHeader();
		func.header.colon = new PunctuationColon();

		func.header.params = new Python_Parameter_List();
		func.header.params.leftParen = new PunctuationLeftParen();
		func.header.params.params = new Python_Params();
		func.header.params.rightParen = new PunctuationRightParen();
		
		func.header.defBody = new Python_StatementBlock();
		Python_MultilineStatement multi = new Python_MultilineStatement();
		multi.statements = new TokenList<Python_ComplexStatement>();
		func.header.defBody.setWhich(multi);
		
		return func;
	}
	
	public void addFunctionParameter(AbstractType type, String name)
	{
		Python_Variable_Definition var = new Python_Variable_Definition();
		var.setValue(name);
		Python_Parameter newParam = new Python_Parameter();
		newParam.setWhich(var);
		
		if (header.params.params.param == null)
		{
			header.params.params.param = newParam;
		}
		else
		{
			if (header.params.params.moreParams == null)
			{
				header.params.params.moreParams = new TokenList<Python_MoreParams>();
			}
			Python_MoreParams more = new Python_MoreParams();
			more.param = newParam;
			header.params.params.moreParams.addToken(more);
		}
	}
}
