// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2015

package com.eagle.programmar.BNF.OldTransform;

import java.util.ArrayList;

import com.eagle.core.AbstractLanguage;
import com.eagle.core.EagleSyntax;
import com.eagle.io.EaglePrinter;
import com.eagle.oldGenerate.Old_Generate_Eagle;
import com.eagle.oldGenerate.Old_Generate_Eagle_Class.CLASS_QUALIFIERS;
import com.eagle.oldGenerate.Old_Generate_Eagle_Expression.TYPES;
import com.eagle.oldGenerate.Old_Generate_Eagle_Method.METHOD_QUALIFIERS;
import com.eagle.oldGenerate.Old_Generate_Eagle_Statement.DATA_QUALIFIERS;
import com.eagle.oldGenerate.Old_Generate_Eagle_Statement.PRIVACY;
import com.eagle.programmar.BNF.BNF_Expression;
import com.eagle.programmar.BNF.BNF_Expression.BNF_ExpressionTerm;
import com.eagle.programmar.BNF.BNF_Program;
import com.eagle.programmar.BNF.BNF_Rule;
import com.eagle.programmar.BNF.Expressions.BNF_Group;
import com.eagle.programmar.BNF.Expressions.BNF_Optional;
import com.eagle.programmar.BNF.Expressions.BNF_Rulename;
import com.eagle.programmar.BNF.Terminals.BNF_Comment;
import com.eagle.programmar.BNF.Terminals.BNF_Identifier;
import com.eagle.programmar.BNF.Terminals.BNF_Keyword;
import com.eagle.programmar.BNF.Terminals.BNF_Literal;
import com.eagle.programmar.BNF.Terminals.BNF_Number;
import com.eagle.programmar.BNF.Terminals.BNF_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.Transform_Eagle;

public class Transform_BNF<Lang extends AbstractLanguage, Cls extends AbstractClass, Stmt extends AbstractStatement, Meth extends AbstractMethod, Expr extends AbstractExpression, Var extends AbstractVariable, Type extends AbstractType>
		extends Transform_Eagle
{
	public Old_Generate_Eagle<Lang, Cls, Stmt, Meth, Expr, Var, Type> _target;

	Transform_BNF_Name<Lang, Cls, Stmt, Meth, Expr, Var, Type> _transformName = new Transform_BNF_Name<Lang, Cls, Stmt, Meth, Expr, Var, Type>();
	Transform_BNF_Literal<Lang, Cls, Stmt, Meth, Expr, Var, Type> _transformLiteral = new Transform_BNF_Literal<Lang, Cls, Stmt, Meth, Expr, Var, Type>();
	private Transform_BNF_Group<Lang, Cls, Stmt, Meth, Expr, Var, Type> _transformGroup = new Transform_BNF_Group<Lang, Cls, Stmt, Meth, Expr, Var, Type>();
	private Transform_BNF_Optional<Lang, Cls, Stmt, Meth, Expr, Var, Type> _transformOptional = new Transform_BNF_Optional<Lang, Cls, Stmt, Meth, Expr, Var, Type>();
	Transform_BNF_Alternation<Lang, Cls, Stmt, Meth, Expr, Var, Type> _transformAlternation = new Transform_BNF_Alternation<Lang, Cls, Stmt, Meth, Expr, Var, Type>();

	private static String _bnf = BNF_Program.BNF + "_-"; // The '-' goes away, but forces the next char to be upper case
	private String _langName = "\"EBNF\""; // Should probably change this to Lisp or Pascal or whatever, based on source
											// grammar

	private EaglePrinter _prt = new EaglePrinter();

	public Transform_BNF(Old_Generate_Eagle<Lang, Cls, Stmt, Meth, Expr, Var, Type> target)
	{
		_target = target;
	}

	@Override
	public void transformFromXML(AbstractLanguage pgm, String sourceName, String targetName)
	{
		BNF_Program bnfProgram = (BNF_Program) pgm;
		String mainName = fixVarName(sourceName);
		convertBnf(bnfProgram, mainName, targetName);
	}

	private void convertBnf(BNF_Program bnfProgram, String mainName, String targetName)
	{
		_target.createEmptyClass(targetName);

		// set package
		_target._createProgram.setPackage(_target._mainPgm, "BNF.java", null);

		// add imports
		_target._createProgram.addImport(_target._mainPgm, AbstractLanguage.class.getCanonicalName(), false, null);
		_target._createProgram.addImport(_target._mainPgm, EagleSyntax.class.getCanonicalName(), false, null);
		_target._createProgram.addImport(_target._mainPgm, TokenChooser.class.getCanonicalName(), false, null);
		_target._createProgram.addImport(_target._mainPgm, TokenList.class.getCanonicalName(), false, null);
		_target._createProgram.addImport(_target._mainPgm, TokenSequence.class.getCanonicalName(), false, null);
		_target._createProgram.addImport(_target._mainPgm, BNF_Comment.class.getCanonicalName(), false, null);
		_target._createProgram.addImport(_target._mainPgm, BNF_Identifier.class.getCanonicalName(), false, null);
		_target._createProgram.addImport(_target._mainPgm, BNF_Keyword.class.getCanonicalName(), false, null);
		_target._createProgram.addImport(_target._mainPgm, BNF_Literal.class.getCanonicalName(), false, null);
		_target._createProgram.addImport(_target._mainPgm, BNF_Number.class.getCanonicalName(), false, null);
		_target._createProgram.addImport(_target._mainPgm, BNF_Punctuation.class.getCanonicalName(), false, null);

		// extends EagleLanguage
		_target._createClass.setClassExtends(_target._mainClass, AbstractLanguage.class.getSimpleName());

		// needs a constructor
		addConstructor(mainName);

		// needs an EagleSyntax instance
		addSyntaxClass(mainName);

		// start processing all the rules
		BNF_Rule firstRule = null;
		int ruleNumber = 0;
		for (BNF_Rule rule : bnfProgram.rules._elements)
		{
			String originalName = rule.definition.toString();

			try
			{
				// Insert comment for each rule
				String expand = _prt.writeToken(rule.expression);
				String originalLine = expand.replaceAll("\\r", "");
				ruleNumber++;
				String comment = ruleNumber + ": " + originalName + " ::= " + originalLine + ';';
				_target._createProgram.addProgramComment(_target._mainPgm, comment, null);
			}
			catch (Exception ex)
			{
				// Ignore inability to create a nice comment
			}

			String ruleName = fixClassName(originalName);
			writeExpression(_target._mainClass, ruleName, rule.expression);

			if (firstRule == null)
			{
				firstRule = rule;
			}
		}
		_target._createProgram.addProgramComment(_target._mainPgm, null, null);

		_target._createProgram.addProgramComment(_target._mainPgm, "Main program.", null);
		_target._createProgram.addProgramComment(_target._mainPgm, null, null);

		String firstRuleName = fixClassName(firstRule.definition.toString());
		Stmt data = _target._createStatement.createData(PRIVACY.PUBLIC, DATA_QUALIFIERS.SEQUENCE._value, 10, "program",
				firstRuleName, null, null, null);
		_target._createClass.addClassData(_target._mainClass, data);
	}

	private void addConstructor(String mainName)
	{
		ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();
		args.add(_target._createExpression.createLiteral(_langName));
		Type syntaxClass = _target._createClass.createType(mainName + "_Syntax");
		args.add(_target._createExpression.createNew(syntaxClass, null));
		_target._createClass.addConstructor(_target._mainClass, mainName, args, null);
	}

	private void addSyntaxClass(String mainName)
	{
		Cls syntaxClass = _target._createClass.addInnerClass(PRIVACY.PUBLIC, _target._mainClass,
				mainName + Old_Generate_Eagle.SYNTAX, CLASS_QUALIFIERS.NONE, null);
		_target._createClass.setClassExtends(syntaxClass, EagleSyntax.class.getSimpleName());

		// add this: @Override public String syntaxId() { return "EBNF"; }
		Meth method = _target._createMethod.createMethod(PRIVACY.PUBLIC, METHOD_QUALIFIERS.OVERRIDES, TYPES.STRING,
				null, "syntaxId", null, null);
		_target._createClass.addMethod(syntaxClass, method);
		Expr expr = _target._createExpression.createLiteral(_langName);
		Stmt ret = _target._createStatement.createReturnStatement(expr, null);
		_target._createMethod.addMethodStatement(method, ret, null);
	}

	// One call for each BNF rule
	protected void writeExpression(Cls cls, String className, BNF_Expression expr)
	{
		if (expr.choices == null)
		{
			Cls innerClass = _target._createClass.addInnerClass(PRIVACY.PUBLIC, _target._mainClass, className,
					CLASS_QUALIFIERS.NONE, expr);
			_target._createClass.setClassExtends(innerClass, TokenSequence.class.getSimpleName());

			int seq = 0;
			for (BNF_ExpressionTerm term : expr.terms._elements)
			{
				seq += 10;
				writeTerm(innerClass, true, seq, term);
			}
		}
		else
		{
			_transformAlternation.transformAlternation(this, cls, 10, className, expr);
		}
	}

	protected void writeTerm(Cls cls, boolean inTokenSequence, int seq, BNF_ExpressionTerm term)
	{
		AbstractToken which = term.getWhich();

		if (which instanceof BNF_Rulename)
		{
			_transformName.transformName(this, cls, seq, (BNF_Rulename) which, false, false, inTokenSequence);
		}
		else if (which instanceof BNF_Literal)
		{
			_transformLiteral.transformLiteral(this, cls, seq, (BNF_Literal) which, false, false, inTokenSequence);
		}
		else if (which instanceof BNF_Group)
		{
			_transformGroup.transformGroup(this, cls, seq, (BNF_Group) which, inTokenSequence);
		}
		else if (which instanceof BNF_Optional)
		{
			_transformOptional.transformOptional(this, cls, seq, (BNF_Optional) which, false, inTokenSequence);
		}
		else
			throw new RuntimeException("Unexpected bnf token: " + which);
	}

	protected static String fixClassName(String name)
	{
		return fixVarName(_bnf + name);
	}

	protected static String fixVarName(String name)
	{
		StringBuffer result = new StringBuffer();
		boolean foldNext = false;
		for (char ch : name.toCharArray())
		{
			if (ch == '-')
			{
				foldNext = true;
				// Discard the hyphen
			}
			else if (foldNext)
			{
				result.append(Character.toUpperCase(ch));
				foldNext = false;
			}
			else
			{
				result.append(ch);
			}
		}
		return result.toString();
	}

	protected String foldUp(String str)
	{
		StringBuffer sb = new StringBuffer();
		for (char ch : str.toCharArray())
		{
			sb.append(Character.toUpperCase(ch));
		}
		return sb.toString();
	}

	@Override
	public AbstractExpression transformExpression(AbstractExpression expr)
	{
		throw new RuntimeException("need to implement");
	}

	@Override
	public AbstractStatement transformStatement(AbstractStatement stmt)
	{
		throw new RuntimeException("need to implement");
	}
}
