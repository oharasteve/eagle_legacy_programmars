// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 15, 2011

package com.eagle.programmar.Delphi;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Delphi.Delphi_Parameter_List.Delphi_MoreParameters;
import com.eagle.programmar.Delphi.Delphi_Parameter_List.Delphi_Parameter;
import com.eagle.programmar.Delphi.Statements.Delphi_BeginEnd;
import com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
import com.eagle.programmar.Delphi.Symbols.Delphi_Procedure_Definition;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleTransformer;

public class Delphi_Procedure extends TokenSequence implements AbstractFunction, EagleRunnable
{
	public @S(10) Delphi_ProcedureForward forward;
	public @S(20) @OPT TokenList<Delphi_Header> headers;
	public @S(30) @OPT Delphi_BeginEnd body;
	public @S(40) @OPT TokenList<Delphi_Comment> comments;
	public @S(50) @OPT PunctuationSemicolon semicolon;

	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;

	public static class Delphi_ProcedureForward extends TokenSequence
	{
		public @S(10) @DOC("Procedures_and_Functions_(Delphi)#Procedure_Declarations") Delphi_KeywordChoice PROCEDURE = new Delphi_KeywordChoice(
				"Procedure", "Constructor", "Destructor");
		public @S(20) @OPT TokenList<Delphi_ProcedureClass> classes;
		public @S(30) Delphi_Procedure_Definition id;
		public @S(40) @OPT Delphi_Parameter_List args;
		public @S(50) PunctuationSemicolon semicolon;
		public @S(60) @OPT Delphi_Override override;

		public static class Delphi_ProcedureClass extends TokenSequence
		{
			public @S(10) Delphi_Identifier_Reference clsRef;
			public @S(20) PunctuationPeriod dot;
		}
		
		public static class Delphi_Override extends TokenSequence
		{
			public @S(10) Delphi_Keyword OVERRIDE = new Delphi_Keyword("Override");
			public @S(20) PunctuationSemicolon semicolon;
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Don't run it here. Wait until it is called.
		if (_callMetrics == null)
		{
			_callMetrics = new CallMetrics(interpreter._metrics, forward.id.getValue(), forward.id);
		}
		if (_argumentsMetrics == null)
		{
			_argumentsMetrics = new ArgumentsMetrics(interpreter._metrics, forward.id.getValue(), forward.id);
		}
	}

	public void transformProcedure(EagleTransformer transformer, EagleGenerator generator)
	{
		if (this.headers != null)
		{
			for (Delphi_Header header : this.headers._elements)
			{
				header.processHeader(transformer, generator);
			}
		}

		String procName = this.forward.id.getValue();
		generator.addMethod(null, procName, this);

		Delphi_Parameter param = this.forward.args.firstParam;
		if (param != null && param.isPresent())
		{
			AbstractType paramType1 = param.type.convertType(generator);
			String paramName1 = param.names.first().var.getValue();
			generator.addMethodParameter(paramType1, paramName1);
			
			for (Delphi_MoreParameters more : this.forward.args.moreParams._elements)
			{
				AbstractType paramType2 = more.param.type.convertType(generator);
				String paramName2 = more.param.names.first().var.getValue();
				generator.addMethodParameter(paramType2, paramName2);
			}
		}

		AbstractStatement newStmt = transformer.transformStatement1(generator, this.body);
		generator.addStatement(newStmt, this.body);
	}
}
