// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2026

package com.eagle.programmar.COBOL;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.COBOL.COBOL_DataDeclaration.COBOL_DataClause;
import com.eagle.programmar.COBOL.COBOL_WorkingStorage.COBOL_CopyOrDataDeclaration;
import com.eagle.programmar.COBOL.Picture.COBOL_PictureClause;
import com.eagle.programmar.COBOL.Picture.COBOL_Usage;
import com.eagle.programmar.COBOL.Symbols.COBOL_Data_Definition;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class COBOL_LinkageSection extends TokenSequence implements EagleRunnable
{
	public @S(10) COBOL_Keyword LINKAGE = new COBOL_Keyword("LINKAGE");
	public @S(20) COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
	public @S(30) PunctuationPeriod dot;
	public @S(40) @OPT COBOL_Comment comment;
	public @S(50) TokenList<COBOL_CopyOrDataDeclaration> dataDeclarations;
	
	// Fields used by processing SubProgram parameters
	public @SKIP ArrayList<String> paramNames = new ArrayList<String>();
	public @SKIP ArrayList<AbstractType> paramTypes = new ArrayList<AbstractType>();
	public @SKIP String retName = null;
	public @SKIP AbstractType retType = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (COBOL_CopyOrDataDeclaration decl : dataDeclarations._elements)
		{
			interpreter.tryToInterpret(decl);
		}
	}
	
	public void collectParameters(String funcName,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		for (COBOL_CopyOrDataDeclaration decl : dataDeclarations._elements)
		{
			AbstractType paramType = null;
			String paramName = null;
			AbstractToken which2 = decl.getWhich();
			if (which2 instanceof COBOL_DataDeclaration)
			{
				COBOL_DataDeclaration data = (COBOL_DataDeclaration) which2;
				AbstractToken which3 = data.fieldName.getWhich();
				if (which3 instanceof COBOL_Data_Definition)
				{
					COBOL_Data_Definition dataDef = (COBOL_Data_Definition) which3;
					paramName = COBOL_Variable.repairName(dataDef.getValue());
					boolean isComp = false;
					if (data.clauses != null)
					{
						for (COBOL_DataClause clause : data.clauses._elements)
						{
							AbstractToken which4 = clause.getWhich();
							if (which4 instanceof COBOL_PictureClause)
							{
								COBOL_PictureClause picClause = (COBOL_PictureClause) which4;
								String pic = picClause.picture.getValue().toUpperCase();
								if (pic.startsWith("9") || pic.startsWith("X") || pic.startsWith("Z"))
								{
									paramType = generator.transformType(TypeEnum.STRING, null, picClause);
									// Will get replaced by INTEGER if USAGE COMP is present
								}
							}
							if (which4 instanceof COBOL_Usage)
							{
								COBOL_Usage usage = (COBOL_Usage) which4;
								if (usage.type.getValue().toUpperCase().startsWith("COMP"))
								{
									isComp = true;
								}
							}
						}
					}
					if (isComp) // COMP, COMP-1, etc.
					{
						paramType = generator.transformType(TypeEnum.INTEGER, null, null);
					}
					
					boolean isModified = false;
/////////////// MASSIVE HACK FOR NOW ////////////////////
					if (funcName.indexOf("-func") > 0 && paramName.equals("rom"))
					{
						isModified = true;
					}
					if (isModified && retName == null)
					{
						retType = paramType;
						retName = paramName;
					}
					else
					{
						paramTypes.add(paramType);
						paramNames.add(paramName);
					}
				}
			}
		}
	}
}