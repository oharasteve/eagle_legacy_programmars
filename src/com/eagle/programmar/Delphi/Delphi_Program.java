// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableProgram;
import com.eagle.transform.EagleTransformer;

public class Delphi_Program extends AbstractLanguage
		implements EagleRunnable, EagleTransformableProgram, EagleScopeInterface
{
	public static final String DELPHI = "Delphi";

	public Delphi_Program()
	{
		super(DELPHI, new Delphi_Syntax());
	}

	private @SKIP EagleScope _scope = new EagleScope(this, Delphi_Syntax.IS_CASE_SENSITIVE);

	@Override
	public String booleanName(boolean flag)
	{
		if (flag) return "True";
		return "False";
	}

	@Override
	public String getDocRoot()
	{
		return "http://docwiki.embarcadero.com/RADStudio/en/";
	}

	public @S(10) Delphi_Full_or_Partial fullOrPartial;

	public static class Delphi_Full_or_Partial extends TokenChooser
	{
		public @FIRST Delphi_Full XXfull;
		
		public @CHOICE static class Delphi_Partial extends TokenSequence
		{
			public @S(10) TokenList<Delphi_Header> headers;
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(fullOrPartial);
	}

	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer,
			EagleGenerator generator)
	{
		if (! (fullOrPartial.getWhich() instanceof Delphi_Full))
		{
			throw new RuntimeException("Can only handle complete Delphi programs");
		}
		Delphi_Full full = (Delphi_Full) fullOrPartial.getWhich();
		full.transformFull(transformer, generator);
		return generator.getTransfomedProgram();
	}
}
