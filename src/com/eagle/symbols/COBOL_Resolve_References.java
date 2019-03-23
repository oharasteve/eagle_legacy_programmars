// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 13, 2010

package com.eagle.symbols;

import java.util.ArrayList;

import com.eagle.core.EagleSymbolException;
import com.eagle.programmar.COBOL.COBOL_DataDeclaration;
import com.eagle.programmar.COBOL.COBOL_DataDeclaration.COBOL_DataClause;
import com.eagle.programmar.COBOL.COBOL_DataDeclaration.COBOL_OccursClause;
import com.eagle.programmar.COBOL.COBOL_DataDivision.COBOL_Copy_or_FileDescriptor.COBOL_FileDescriptor;
import com.eagle.programmar.COBOL.COBOL_Program_Complete;
import com.eagle.programmar.COBOL.COBOL_ReportEntry.COBOL_ReportDescription;
import com.eagle.programmar.COBOL.Symbols.COBOL_Data_Definition;
import com.eagle.programmar.COBOL.Symbols.COBOL_File_Definition;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Symbols.COBOL_Index_Definition;
import com.eagle.programmar.COBOL.Symbols.COBOL_Paragraph_Definition;
import com.eagle.programmar.COBOL.Symbols.COBOL_ReportLine_Definition;
import com.eagle.programmar.COBOL.Symbols.COBOL_Report_Definition;
import com.eagle.programmar.COBOL.Symbols.COBOL_Section_Definition;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.EagleScope;

//
// Be careful in here ... a program can contain other nested programs ...
// Searching needs to be limited to the current program only
//

public class COBOL_Resolve_References extends Eagle_Resolve_References
{
	public void resolveRefs(EagleScope scope, COBOL_Program_Complete program)
	{
		resolveParagraphSectionReferences(scope, program);
		resolveFileReferences(scope, program);
		resolveDataReferences(scope, program);
		
		// Ick, might have embedded programs
		if (program.nestedPrograms != null)
		{
			for (COBOL_Program_Complete subProgram : program.nestedPrograms._elements)
			{
				resolveRefs(scope, subProgram);
			}
		}
	}
	
	private void resolveParagraphSectionReferences(EagleScope scope, COBOL_Program_Complete program)
	{
		ArrayList<AbstractToken> sectionDefinitions = new ArrayList<AbstractToken>();
		findAllInstances(sectionDefinitions, program.procedureDiv,
				COBOL_Section_Definition.class);

		ArrayList<AbstractToken> paragraphDefinitions = new ArrayList<AbstractToken>();
		findAllInstances(paragraphDefinitions, program.procedureDiv,
				COBOL_Paragraph_Definition.class);

		ArrayList<AbstractToken> paragraphSectionReferences = new ArrayList<AbstractToken>();
		findAllInstances(paragraphSectionReferences, program.procedureDiv,
				COBOL_Identifier_Reference.class);
		
		for (AbstractToken defToken : sectionDefinitions)
		{
			COBOL_Section_Definition def = (COBOL_Section_Definition) defToken;
			scope.addSymbol(def);
			if (_trace)
			{
				System.out.println("Section definition for " + def + " at " +
						(def._currentLine+1) + "/" + (def._currentChar+1));
			}
		}

		for (AbstractToken defToken : paragraphDefinitions)
		{
			COBOL_Paragraph_Definition def = (COBOL_Paragraph_Definition) defToken;
			scope.addSymbol(def);
			if (_trace)
			{
				System.out.println("Paragraph definition for " + def + " at " +
						(def._currentLine+1) + "/" + (def._currentChar+1));
			}
		}

		// Match 'em up!
		for (AbstractToken refToken : paragraphSectionReferences)
		{
			COBOL_Identifier_Reference ref = (COBOL_Identifier_Reference) refToken;
			int foundAny = 0;
			
			for (AbstractToken defToken : sectionDefinitions)
			{
				COBOL_Section_Definition section = (COBOL_Section_Definition) defToken;
				String sectionName = section.toString();
				if (ref.toString().equalsIgnoreCase(sectionName))
				{
					if (foundAny == 1) throw new EagleSymbolException("Duplicate section definition for " + sectionName);
					if (_trace) System.out.println("Section reference to " + sectionName + " at " +
							(ref._currentLine+1) + "/" + (ref._currentChar+1));
					ref.setDefinition(section);
					section.addReference(ref);
					foundAny++;
				}
			}

			for (AbstractToken defToken : paragraphDefinitions)
			{
				COBOL_Paragraph_Definition paragraph = (COBOL_Paragraph_Definition) defToken;
				String paragraphName = paragraph.toString();
				if (ref.toString().equalsIgnoreCase(paragraphName))
				{
					if (foundAny == 1) throw new EagleSymbolException("Duplicate paragraph definition for " + paragraphName);
					if (_trace) System.out.println("Paragraph reference to " + paragraphName + " at " +
							(ref._currentLine+1) + "/" + (ref._currentChar+1));
					ref.setDefinition(paragraph);
					paragraph.addReference(ref);
					foundAny++;
				}
			}
			
			if (foundAny == 0)
			{
				System.err.println("*** Unable to find a Section / Paragraph Definition for " + ref);
			}
		}
	}
	
	private void resolveFileReferences(EagleScope scope, COBOL_Program_Complete program)
	{
		ArrayList<AbstractToken> fileDescriptors = new ArrayList<AbstractToken>();
		findAllInstances(fileDescriptors, program.dataDiv, COBOL_FileDescriptor.class);

		ArrayList<AbstractToken> fileReferences = new ArrayList<AbstractToken>();
		findAllInstances(fileReferences, program.environmentDiv, COBOL_Identifier_Reference.class);
		findAllInstances(fileReferences, program.dataDiv, COBOL_Identifier_Reference.class);
		findAllInstances(fileReferences, program.procedureDiv, COBOL_Identifier_Reference.class);
		
		for (AbstractToken defToken : fileDescriptors)
		{
			COBOL_FileDescriptor def = (COBOL_FileDescriptor) defToken;
			scope.addSymbol(def.id);
			if (_trace)
			{
				System.out.println("File definition for " + def.id + " at " +
						(def._currentLine+1) + "/" + (def._currentChar+1));
			}
		}

		// Match 'em up!
		for (AbstractToken refToken : fileReferences)
		{
			COBOL_Identifier_Reference ref = (COBOL_Identifier_Reference) refToken;
			int foundAny = 0;
			for (AbstractToken defToken : fileDescriptors)
			{
				COBOL_FileDescriptor defParent = (COBOL_FileDescriptor) defToken;
				COBOL_File_Definition def = defParent.id;
				if (ref.toString().equalsIgnoreCase(def.toString()))
				{
					if (foundAny == 1) throw new EagleSymbolException("Duplicate file definition for " + def);
					if (_trace) System.out.println("File reference to " + def + " at " +
							(ref._currentLine+1) + "/" + (ref._currentChar+1));
					ref.setDefinition(def);
					def.addReference(ref);
					//def.parentDef = defParent;
					foundAny++;
				}
			}
			
			if (foundAny == 0)
			{
				System.err.println("*** Unable to find a File Definition for " + ref);
			}
		}
	}
	
	private void resolveDataReferences(EagleScope scope, COBOL_Program_Complete program)
	{
		ArrayList<AbstractToken> dataDeclarations = new ArrayList<AbstractToken>();
		findAllInstances(dataDeclarations, program.dataDiv, COBOL_DataDeclaration.class);

		ArrayList<AbstractToken> reportDataLines = new ArrayList<AbstractToken>();
		findAllInstances(reportDataLines, program.dataDiv, COBOL_ReportLine_Definition.class);
		
		ArrayList<AbstractToken> reportDescriptions = new ArrayList<AbstractToken>();
		findAllInstances(reportDescriptions, program.dataDiv, COBOL_ReportDescription.class);
		
		ArrayList<AbstractToken> dataReferences = new ArrayList<AbstractToken>();
		findAllInstances(dataReferences, program.environmentDiv, COBOL_Identifier_Reference.class);
		findAllInstances(dataReferences, program.dataDiv, COBOL_Identifier_Reference.class);
		findAllInstances(dataReferences, program.procedureDiv, COBOL_Identifier_Reference.class);

		for (AbstractToken defToken : dataDeclarations)
		{
			COBOL_DataDeclaration def = (COBOL_DataDeclaration) defToken;
			AbstractToken whichDef = def.fieldName.getWhich();
			if (whichDef instanceof COBOL_Data_Definition)
			{
				scope.addSymbol((COBOL_Data_Definition) whichDef);
				if (_trace)
				{
					System.out.println("Data definition for " + whichDef + " at " +
							(def._currentLine+1) + "/" + (def._currentChar+1));
				}
			}
		}
		for (AbstractToken defToken : reportDataLines)
		{
			COBOL_ReportLine_Definition def = (COBOL_ReportLine_Definition) defToken;
			scope.addSymbol(def);
			if (_trace)
			{
				System.out.println("Data definition for " + def + " at " +
						(def._currentLine+1) + "/" + (def._currentChar+1));
			}
		}
		
		// Match 'em up!
		for (AbstractToken refToken : dataReferences)
		{
			COBOL_Identifier_Reference ref = (COBOL_Identifier_Reference) refToken;
			int foundAny = 0;
			for (AbstractToken defToken : dataDeclarations)
			{
				COBOL_DataDeclaration defParent = (COBOL_DataDeclaration) defToken;
				AbstractToken whichDef = defParent.fieldName.getWhich();
				if (whichDef instanceof COBOL_Data_Definition)
				{
					COBOL_Data_Definition def = (COBOL_Data_Definition) whichDef;
					if (ref.toString().equalsIgnoreCase(def.toString()))
					{
						if (foundAny == 1) System.err.println("**** Duplicate data definition for " + def);
						if (_trace) System.out.println("Data reference to " + def + " at " +
								(ref._currentLine+1) + "/" + (ref._currentChar+1));
						ref.setDefinition(def);
						def.addReference(ref);
						//def.parentDef = defParent;
						foundAny++;
					}
				}

				// Maybe it is an INDEXED BY variable
				if (defParent.clauses != null)
				{
					for (COBOL_DataClause clause : defParent.clauses._elements)
					{
						AbstractToken whichClause = clause.getWhich();
						if (whichClause instanceof COBOL_OccursClause)
						{
							COBOL_OccursClause occurs = (COBOL_OccursClause) whichClause;
							if (occurs != null)
							{
								if (occurs.indexedBy != null)
								{
									COBOL_Index_Definition index = occurs.indexedBy.index;
									if (ref.toString().equalsIgnoreCase(index.toString()))
									{
										if (foundAny == 1) System.err.println("**** Duplicate data definition for " + ref);
										if (_trace) System.out.println("Data reference to " + ref + " at " +
												(ref._currentLine+1) + "/" + (ref._currentChar+1));
										ref.setDefinition(index);
										index.addReference(ref);
										//index.parentDef = defParent;
										foundAny++;
									}
								}
							}
						}
					}
				}
			}
			
			if (foundAny == 0)
			{
				// Maybe it is a report line
				for (AbstractToken defToken3 : reportDescriptions)
				{
					COBOL_ReportDescription defParent3 = (COBOL_ReportDescription) defToken3;
					COBOL_Report_Definition def3 = defParent3.reportName;
					if (ref.toString().equalsIgnoreCase(def3.toString()))
					{
						if (foundAny == 1) System.err.println("**** Duplicate data definition for " + ref);
						if (_trace) System.out.println("Data reference to " + ref + " at " +
								(ref._currentLine+1) + "/" + (ref._currentChar+1));
						ref.setDefinition(def3);
						def3.addReference(ref);
						//def3.parentDef = defParent3;
						foundAny++;
					}
				}
			}

			if (foundAny == 0)
			{
				// Maybe it is a report data line
				for (AbstractToken defToken2 : reportDataLines)
				{
					COBOL_ReportLine_Definition def2 = (COBOL_ReportLine_Definition) defToken2;
					if (ref.toString().equalsIgnoreCase(def2.toString()))
					{
						if (foundAny == 1) System.err.println("**** Duplicate data definition for " + ref);
						if (_trace) System.out.println("Data reference to " + ref + " at " +
								(ref._currentLine+1) + "/" + (ref._currentChar+1));
						ref.setDefinition(def2);
						def2.addReference(ref);
						foundAny++;
					}
				}
			}
			
			if (foundAny == 0)
			{
				System.err.println("*** Unable to find a Data Definition for " + ref);
			}
		}
	}
}
