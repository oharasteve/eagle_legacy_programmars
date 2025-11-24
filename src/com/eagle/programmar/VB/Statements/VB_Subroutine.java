// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 16, 2011

package com.eagle.programmar.VB.Statements;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.metrics.EagleMetrics;
import com.eagle.programmar.VB.VB_Element;
import com.eagle.programmar.VB.VB_Parameters;
import com.eagle.programmar.VB.VB_Parameters.VB_Parameter;
import com.eagle.programmar.VB.VB_Syntax;
import com.eagle.programmar.VB.VB_Type;
import com.eagle.programmar.VB.Symbols.VB_Sub_Definition;
import com.eagle.programmar.VB.Terminals.VB_EndOfLine;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

public class VB_Subroutine extends TokenSequence
		implements AbstractFunction, EagleRunnable, EagleScopeInterface, EagleTransformableFunction
{
	public @S(10) @OPT VB_KeywordChoice modifier = new VB_KeywordChoice("private", "public");
	public @S(20) @DOC("statements/sub-statement") VB_Keyword SUB1 = new VB_Keyword("sub");
	public @S(30) VB_Sub_Definition id;
	public @S(40) VB_Parameters params;
	public @S(50) VB_EndOfLine eoln;
	public @S(60) @OPT TokenList<VB_Element> stmts;
	public @S(70) VB_Keyword END = new VB_Keyword("end");
	public @S(80) VB_Keyword SUB2 = new VB_Keyword("sub");

	private @SKIP EagleScope _scope = new EagleScope(this, VB_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_callMetrics == null)
		{
			_callMetrics = new CallMetrics(interpreter._metrics, id.getValue(), id);
		}
		if (_argumentsMetrics == null)
		{
			_argumentsMetrics = new ArgumentsMetrics(interpreter._metrics, id.getValue(), id);
		}

		// Don't do anything here.
		// We searched for all the subs in a preliminary pass
		// And we only evaluate when it is called
	}

	@Override
	public void transformFunction(EagleTransformer transformer, EagleGenerator generator)
	{
//		PrivacyEnum privacy = PrivacyEnum.NONE;
//		if (modifier != null && modifier.isPresent())
//		{
//			switch (modifier.getValue())
//			{
//			case "private":
//				privacy = PrivacyEnum.PRIVATE;
//				break;
//			case "public":
//				privacy = PrivacyEnum.PUBLIC;
//				break;
//			default:
//				throw new RuntimeException("Unable to handle " + modifier.getValue());
//			}
//		}

		String subName = id.getValue();
		generator.addMethod(null, subName, this);
		generator.setMethodName(subName);
		if (VERBOSE)
		{
			System.out.println("** Found VB subroutine " + subName);
		}

		// Search metrics for arg types -- might not be any
		ArrayList<String> argTypes = transformer.findArgumentsMetric(id);

		if (params.params != null && params.params.isPresent())
		{
			for (int i = 0; i < params.params.getPrimaryCount(); i++)
			{
				VB_Parameter param = params.params.getPrimaryElement(i);
				AbstractType paramType = null;
				if (param.as != null && param.as.isPresent())
				{
					VB_KeywordChoice kw = (VB_KeywordChoice) param.as.type.getWhich();
					paramType = VB_Type.findType(generator, kw.getValue());
				}

				if (paramType == null && argTypes != null && i < argTypes.size())
				{
					String metricArgType = argTypes.get(i);
					TypeEnum metricArg = EagleMetrics.convertType(metricArgType);
					paramType = generator.transformType(metricArg, null, param);
				}

				generator.addMethodParameter(paramType, param.var.getValue());
			}
		}

		for (VB_Element stmt : stmts._elements)
		{
			AbstractToken which = stmt.baseStatement.getWhich();
			Collection<AbstractStatement> newStmts = transformer.transformStatement(generator, which);
			if (newStmts != null)
			{
				for (AbstractStatement newStmt : newStmts)
				{
					generator.addStatement(newStmt, stmt);
				}
			}
		}

		generator.doneMethod();
	}
}
