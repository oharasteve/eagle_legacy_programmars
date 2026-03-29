// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 17, 2017

namespace com.eagle.programmar.Python.Generate_Unused
{
	public class Generate_Python_Class
	//		implements Generate_Eagle_Class<Python_ClassDeclaration,
	//				Python_Function, Python_Statement, Python_Type>
	{
	//	private Generate_Python _target;
	//	private Generate_Python_Statement _creator;
	//
	//	public Generate_Python_Class(Generate_Python target)
	//	{
	//		_target = target;
	//		_creator = new Generate_Python_Statement(_target);
	//	}
	//
	//	@Override
	//	public Python_ClassDeclaration createNewClass(PRIVACY privacy, String className)
	//	{
	//		Python_ClassDeclaration cls = new Python_ClassDeclaration();
	//		cls.name = new Python_Class_Definition();
	//		cls.name.setValue(className);
	//		cls.colon = new PunctuationColon();
	//		return cls;
	//	}
	//	
	//	@Override
	//	public void addClassComment(Python_ClassDeclaration parentClass, String comment, AbstractToken source)
	//	{
	//		Python_Statement stmt = _creator.createCommentStatement(comment, source);
	//		addStatement(parentClass, stmt);
	//	}
	//
	//	@Override
	//	public void addClassData(Python_ClassDeclaration parentClass, Python_Statement dataStmt)
	//	{
	//		// No need to pre-declare variables in python. Only needed if there is an
	//		// initial value
	//		addStatement(parentClass, dataStmt);
	//		return;
	//	}
	//
	//	@Override
	//	public void addMethod(Python_ClassDeclaration parentClass, Python_Function method)
	//	{
	//		Python_Simple_Statement simple = new Python_Simple_Statement();
	//		simple.setWhich(method);
	//		Python_SameLineStatement statementList = new Python_SameLineStatement();
	//		statementList.statements = new SeparatedList<Python_Simple_Statement, PunctuationSemicolon>();
	//		statementList.statements.addPrimaryElement(simple);
	//		Python_Statement stmt = new Python_Statement();
	//		stmt.statementOrComment = new Python_StatementOrComment();
	//		stmt.statementOrComment.setWhich(statementList);
	//		addStatement(parentClass, stmt);
	//	}
	//
	//	private static void addStatement(Python_ClassDeclaration parentClass, Python_Statement stmt)
	//	{
	//		if (parentClass.statements == null) parentClass.statements = new Python_StatementBlock();
	//		AbstractToken which = parentClass.statements.getWhich();
	//		Python_MultilineStatement multi;
	//		if (which == null)
	//		{
	//			multi = new Python_MultilineStatement();
	//			multi.statements = new TokenList<Python_Statement>();
	//			multi.eoln = new Python_EndOfLine();
	//			parentClass.statements.setWhich(multi);
	//		}
	//		else
	//		{
	//			multi = (Python_MultilineStatement) which;
	//		}
	//
	//		multi.statements.addToken(stmt);
	//	}
	//
	//	@Override
	//	public void addConstructor(Python_ClassDeclaration cls, String className, Collection<AbstractExpression> args,
	//			AbstractToken source)
	//	{
	//		throw new RuntimeException("need to implement");
	//	}
	//
	//	@Override
	//	public Python_ClassDeclaration addInnerClass(PRIVACY privacy, Python_ClassDeclaration parentClass, String className,
	//			CLASS_QUALIFIERS qual, AbstractToken source)
	//	{
	//		// Create the new inner class
	//		Python_ClassDeclaration newClass = new Python_ClassDeclaration();
	//		newClass.name = new Python_Class_Definition();
	//		newClass.name.setValue(className);
	//		newClass.colon = new PunctuationColon();
	//		newClass.setTransformationSource(source);
	//
	//		Python_Simple_Statement simple = new Python_Simple_Statement();
	//		simple.setWhich(newClass);
	//		Python_SameLineStatement statementList = new Python_SameLineStatement();
	//		statementList.statements = new SeparatedList<Python_Simple_Statement, PunctuationSemicolon>();
	//		statementList.statements.addPrimaryElement(simple);
	//		Python_Statement stmt = new Python_Statement();
	//		stmt.statementOrComment = new Python_StatementOrComment();
	//		stmt.statementOrComment.setWhich(statementList);
	//
	//		// If parentClass is null, that means it is the main class
	//		if (parentClass != null)
	//		{
	//			// Put all Python inner classes in the main program,
	//			// so we don't have to deal with 'self.' on all of them
	//			_target._createProgram.addClass(_target._mainPgm, newClass);
	//		}
	//
	//		return newClass;
	//	}
	//
	//	@Override
	//	public void setClassExtends(Python_ClassDeclaration cls, String extendsClass)
	//	{
	//		throw new RuntimeException("need to implement");
	//	}
	//
	//	@Override
	//	public Python_ClassDeclaration addInnerDataClass(Python_ClassDeclaration cls, String className, TYPES type,
	//			AbstractToken source)
	//	{
	//		throw new RuntimeException("need to implement");
	//	}
	//
	//	@Override
	//	public Python_Type createType(String typeName)
	//	{
	//		throw new RuntimeException("need to implement");
	//	}
	//
	//	@Override
	//	public void finalize(Python_ClassDeclaration cls)
	//	{
	//		if (cls.statements == null)
	//		{
	//			Python_Statement stmt = _creator.createPassStatement(null);
	//			cls.statements = new Python_StatementBlock();
	//			Python_MultilineStatement multi = new Python_MultilineStatement();
	//			multi.statements = new TokenList<Python_Statement>();
	//			multi.statements.addToken(stmt);
	//			cls.statements.setWhich(multi);
	//		}
	//	}
	}

}
