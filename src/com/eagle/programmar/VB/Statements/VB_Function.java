// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 16, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.EagleGenerator.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.VB.VB_Parameters;
import com.eagle.programmar.VB.VB_Parameters.VB_Parameter;
import com.eagle.programmar.VB.VB_Statement;
import com.eagle.programmar.VB.VB_Syntax;
import com.eagle.programmar.VB.VB_Type;
import com.eagle.programmar.VB.Symbols.VB_Sub_Definition;
import com.eagle.programmar.VB.Terminals.VB_EndOfLine;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

public class VB_Function extends TokenSequence
		implements AbstractFunction, EagleRunnable, EagleScopeInterface, EagleTransformableFunction
{
	public @S(10) @OPT VB_KeywordChoice modifier = new VB_KeywordChoice("private", "public");
	public @S(20) VB_Keyword FUNCTION1 = new VB_Keyword("function");
	public @S(30) VB_Sub_Definition name;
	public @S(40) VB_Parameters params;
	public @S(50) @OPT VB_Keyword AS = new VB_Keyword("as");
	public @S(60) @OPT VB_Type type;
	public @S(70) VB_EndOfLine eoln;
	public @S(80) @OPT TokenList<VB_Statement> stmts;
	public @S(90) VB_Keyword END = new VB_Keyword("end");
	public @S(100) VB_Keyword FUNCTION2 = new VB_Keyword("function");
	
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
		// We searched for all the functions in a preliminary pass
		// And we only evaluate when it is called
	}
	
	private static AbstractType findType(EagleGenerator generator, String typeName)
	{
		TypeEnum type;
		switch (typeName)
		{
		case "boolean":
			type = TypeEnum.BOOLEAN;
			break;
		case "integer":
			type = TypeEnum.INTEGER;
			break;
		case "double":
			type = TypeEnum.DOUBLE;
			break;
		case "string":
			type = TypeEnum.STRING;
			break;
		default:
			type = TypeEnum.OTHER;
			break;
		}
		return generator.transformType(false, type, null, null);
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
		
		AbstractType newType = null;
		if (type != null && type.isPresent())
		{
			VB_KeywordChoice kw = (VB_KeywordChoice) type.getWhich();
			newType = findType(generator, kw.getValue());
		}
		
		generator.addMethod(newType, name.getValue(), this);
		
		if (params.params != null && params.params.isPresent())
		{
			for (int i = 0; i < params.params.getPrimaryCount(); i++)
			{
				VB_Parameter param = params.params.getPrimaryElement(i);
				AbstractType paramType = null;
				if (param.as != null && param.as.isPresent())
				{
					VB_KeywordChoice kw = (VB_KeywordChoice) param.as.type.getWhich();
					paramType = findType(generator, kw.getValue());
				}
				
				generator.addMethodParameter(paramType, param.var.getValue());
			}
		}
	}
}
