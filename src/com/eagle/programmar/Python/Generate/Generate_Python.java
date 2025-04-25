// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 17, 2017

package com.eagle.programmar.Python.Generate;

public class Generate_Python
//		extends Generate_Eagle<Python_Program, Python_ClassDeclaration,
//				Python_Statement, Python_Function, Python_Expression,
//				Python_Variable, Python_Type>
{
//	public Generate_Python_Statement _createStmt = new Generate_Python_Statement(null);
//	public boolean _needsSys = false;
//	public boolean _needsDatetime = false;
//	public boolean _usesPrint = false;
//
//	public Generate_Python(String targetFile, Date forceDate)
//	{
//		super(targetFile, forceDate);
//		_createProgram = new Generate_Python_Program();
//		_createClass = new Generate_Python_Class(this);
//		_createMethod = new Generate_Python_Method(this);
//		_createStatement = new Generate_Python_Statement(this);
//		_createExpression = new Generate_Python_Expression(this);
//
//		_mainPgm = new Python3_Program();
//	}
//
//	public Generate_Python(String targetFile)
//	{
//		this(targetFile, null);
//	}
//
//	@Override
//	public void createEmptyClass(String targetFile)
//	{
//		int dotPos = targetFile.lastIndexOf('.');
//		if (dotPos < 0) throw new RuntimeException("Missing dot in filename: " + targetFile);
//		int slashPos = targetFile.lastIndexOf('/'); // -1 is ok here
//		if (slashPos < 0) slashPos = targetFile.lastIndexOf('\\'); // here too
//		String clsName = targetFile.substring(slashPos + 1, dotPos);
//		_mainClass = _createClass.addInnerClass(PRIVACY.PUBLIC, null, clsName, CLASS_QUALIFIERS.NONE, null);
//	}
//
//	@Override
//	public void addLanguageSpecificMain(String mainName, String entryPoint)
//	{
//		_createProgram.addClass(_mainPgm, _mainClass);
//
//		Python_Statement main = new Python_Statement();
//		main.setSyntax(_mainPgm.getSyntax());
//		String[] lines = new String[] {
//				"if __name__ == '__main__':",
//				"  _mainProgram = " + mainName + "()",
//				"  _mainProgram." + entryPoint + "()"
//		};
//		parseLines(main, lines);
//		_createProgram.addProgramStatement(_mainPgm, main);
//
//		if (_needsSys) _createProgram.addImport(_mainPgm, "sys", false, null);
//		if (_needsDatetime) _createProgram.addImport(_mainPgm, "datetime", false, null);
//
//		if (_usesPrint)
//		{
//			// Note: all "future" imports MUST BE FIRST (in the list of imports), hence
//			// inserted LAST at position 0
//			Python_Statement fromStmt = _createStmt.createFromStatement("__future__", "print_function", null);
//			_mainPgm.entries.insert(0, fromStmt);
//		}
//	}
//
//	@Override
//	protected void addHeaderLines(String... lines)
//	{
//		for (String line : lines)
//		{
//			_mainPgm.entries.insert(0, _createStmt.createCommentStatement(line, null));
//		}
//	}
//
//	public static SeparatedList<Python_Expression, PunctuationComma> createArgumentList(Collection<AbstractExpression> args)
//	{
//		SeparatedList<Python_Expression, PunctuationComma> argList =
//				new SeparatedList<Python_Expression, PunctuationComma>();
//
//		if (args != null)
//		{
//			boolean first = true;
//			for (AbstractExpression arg0 : args)
//			{
//				if (! first)
//				{
//					argList.addSecondaryElement(new PunctuationComma());
//				}
//				first = false;
//				argList.addPrimaryElement((Python_Expression) arg0);
//			}
//		}
//		
//		return argList;
//	}
}
