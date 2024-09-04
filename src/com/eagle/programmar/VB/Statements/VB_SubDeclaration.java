// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 16, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.VB.VB_Parameters;
import com.eagle.programmar.VB.VB_Statement;
import com.eagle.programmar.VB.VB_Syntax;
import com.eagle.programmar.VB.Symbols.VB_Sub_Definition;
import com.eagle.programmar.VB.Terminals.VB_EndOfLine;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

public class VB_SubDeclaration extends TokenSequence
		implements AbstractFunction, EagleRunnable, EagleScopeInterface, EagleTransformableFunction
{
	public @S(10) @OPT VB_KeywordChoice modifier = new VB_KeywordChoice("private", "public");
	public @S(20) VB_Keyword SUB1 = new VB_Keyword("sub");
	public @S(30) VB_Sub_Definition name;
	public @S(40) VB_Parameters params;
	public @S(50) VB_EndOfLine eoln;
	public @S(60) @OPT TokenList<VB_Statement> stmts;
	public @S(70) VB_Keyword END = new VB_Keyword("end");
	public @S(80) VB_Keyword SUB2 = new VB_Keyword("sub");
	
	private @SKIP EagleScope _scope = new EagleScope(this, VB_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}
	
	public @SKIP CallMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new CallMetrics(interpreter._metrics, name.getValue(), this);
		}

		// Don't do anything here.
		// We searched for all the subs in a preliminary pass
		// And we only evaluate when it is called
	}
	
	@Override
	public AbstractFunction transformFunction(EagleTransformer transformer, EagleGenerator generator)
	{
		throw new RuntimeException("Need to implement");
	}
}
