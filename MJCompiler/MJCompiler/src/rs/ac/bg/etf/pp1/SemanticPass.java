package rs.ac.bg.etf.pp1;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticPass extends VisitorAdaptor {

	
	boolean errorDetected = false;
	int nVars;	
	Logger log = Logger.getLogger(getClass());
	private Obj currentProgram;
	private Struct currentType;
	private int constant = 0;
	private Struct constantType;
	private Struct boolType;
	private boolean isArray=false;
	private boolean isLocal=false;
	private ArrayList<String> labelsInMeth=new ArrayList<String>();
	private ArrayList<String> labelsGoto=new ArrayList<String>();
	
	public SemanticPass(Struct bool) {
		this.boolType = bool;
	}
	private Obj mainMethod;
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	@Override
	public void visit(ProgramName programName) {
		currentProgram = Tab.insert(Obj.Prog, programName.getI1(), Tab.noType);
		Tab.openScope();
		
	}
	
	@Override
	public void visit(Program program) {
		nVars=Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(currentProgram);
		Tab.closeScope();
	}
	
	@Override
	public void visit(ConDecl conDecl) {
		Obj  conObj = Tab.find(conDecl.getI1());
		if(!conObj.equals(Tab.noObj)) {
			report_error("Dvostruka definicija konstante " + conDecl.getI1(), conDecl);
		}
		else {
			if(constantType.assignableTo(currentType)) {
				conObj = Tab.insert(Obj.Con, conDecl.getI1(), currentType);
				conObj.setAdr(constant);
			}
			else
				report_error("Neadekvatna dodela konstante " + conDecl.getI1(), conDecl);
		}
	}
	
	@Override
	public void visit(Const_n const_n) {
		constant = const_n.getN1();
		constantType = Tab.intType;
	}
	
	@Override
	public void visit(Const_c const_c) {
		constant = const_c.getC1();
		constantType= Tab.charType;
	}
	
	@Override
	public void visit(Const_b const_b) {
		constant = const_b.getB1();
		constantType = boolType;
	}
	
	@Override
	public void visit(Type type) {
		Obj typeObj = Tab.find(type.getI1());
		if(typeObj.equals(Tab.noObj) || typeObj.getKind() != Obj.Type) {
			report_error("Neadekvatan tip " + type.getI1(), type);
			type.struct=currentType = Tab.noType;
		}
		else {
			type.struct=currentType = typeObj.getType();
		}
		
		
	}
	
    @Override
    public void visit(VarDecl varDecl)
    {
    	Obj  varObj = Tab.find(varDecl.getI1());
		if(!varObj.equals(Tab.noObj)) {
			report_error("Dvostruka definicija promenjive " + varDecl.getI1(), varDecl);
		}
		else
		{
			if(isArray)
				{
				Struct array = new Struct(3,currentType); //mozda nepotrebno svaki put novi struct nego jedan array struct za int,bool i char
				
				varObj=Tab.insert(Obj.Var, varDecl.getI1(),array);
				
				
				}
			else
				{
					
				varObj=Tab.insert(Obj.Var, varDecl.getI1(), currentType);
				}
		}
		if(isLocal)
			varObj.setLevel(1);
		else this.nVars++;
			
    }
    @Override
    public void visit(Brackets_yes brackets_yes)
    {
    	isArray=true;
    }
    @Override
    public void visit(Brackets_e brackets_e)
    {
    	isArray=false;
    }
    @Override
    public void visit(Main main)
    {
    	mainMethod=Tab.insert(Obj.Meth, "main", Tab.noType);
    	main.obj=mainMethod;
    	Tab.openScope();
    	isLocal=true;
    	
    }
    @Override
    public void visit(Statements statements)
    {
    	
    	boolean postoji;
    	
    	for(int i=0;i<this.labelsGoto.size();i++)
    	{
    		
    		postoji=false;
    		
    		for(int j=0;j<this.labelsInMeth.size();j++)
    		{
    			
    			
    			if(labelsGoto.get(i).equals(labelsInMeth.get(j)))
    			{
    				
    				postoji=true;
    				break;
    			}
    		}
    		
    		if(!postoji)
    			report_error("Labela "+labelsGoto.get(i)+" ne postoji u metodi",statements);
    	}
    		
    	
    	isLocal=false;
    	mainMethod.setLocals(Tab.currentScope.getLocals());
		Tab.closeScope();
    }
    
    @Override
    public void visit(Factor_number factor_number)
    {
    	factor_number.struct=Tab.intType;
    }
    
    @Override
    public void visit(Factor_bool factor_bool)
    {
    	factor_bool.struct=boolType;
    }
    
    @Override
    public void visit(Factor_char factor_char)
    {
    	factor_char.struct=Tab.charType;
    }
    @Override
    public void visit(Factor_designator factor_designator)
    {
    	factor_designator.struct=factor_designator.getDesignator().obj.getType();
    }
    @Override
    public void visit(Factor_new factor_new)
    {
    	Obj typeObj = Tab.find(factor_new.getType().getI1());
    	
			factor_new.struct =new Struct(Struct.Array,typeObj.getType()) ;
		    	    	    	
    		 if(factor_new.getExpr().struct!=Tab.intType)
    		    	report_error("Izraz u zagradi [] nije tipa int " , factor_new);
    	   	
    }
    @Override
    public void visit(Factor_expr factor_expr)
    {
    	factor_expr.struct=factor_expr.getExpr().struct;
    }
    @Override
    public void visit(Factor factor)
    {
    	if(factor.getExprMinus() instanceof ExprMinus_yes)
    	{
    		if(factor.getFactorLow().struct.equals(Tab.intType))
    			factor.struct=Tab.intType;
    		else 
    		{
    			report_error("Negacija ne int vrednosti",factor);
    			factor.struct=Tab.noType;
    		}
    	}
    	else
    	{
    		factor.struct=factor.getFactorLow().struct;
    	}
    }
    
    @Override
    public void visit(TermList_first termList_first)
    {
    	termList_first.struct=termList_first.getFactor().struct;
    }
    
    @Override
    public void visit(TermList_more termList_more)
    {    	
    	
    		if(termList_more.getTermList().struct!=Tab.intType||termList_more.getFactor().struct!=Tab.intType)	
        	{
        		report_error("Mulop ne int vrednosti",termList_more);
        		termList_more.struct=Tab.noType;
        	}
    		else
    			termList_more.struct=Tab.intType;    	   
    }
    
    @Override
    public void visit(Term term)
    {
    	term.struct=term.getTermList().struct;
    }
    
    @Override
   public void visit(ExprMore_first exprMore_first)
   {
    	exprMore_first.struct=exprMore_first.getTerm().struct;
   }
    @Override
    public void visit(ExprMore_yes exprMore_yes)
    {
    	    	    	
    		if(exprMore_yes.getExprMore().struct!=Tab.intType||exprMore_yes.getTerm().struct!=Tab.intType)	
        	{
        		report_error("Addop ne int vrednosti",exprMore_yes);
        		exprMore_yes.struct=Tab.noType;
        	}
    		else
    			exprMore_yes.struct=Tab.intType;
    	
    	
    }
    @Override
    public void visit(Expr expr)
    {
    	expr.struct=expr.getExprMore().struct;
    }
    
    @Override
    public void visit(DesignatorVar designatorVar)
    {
    	Obj  desObj = Tab.find(designatorVar.getI1());
		if(desObj.equals(Tab.noObj)) 
			{
				report_error("Promenjiva nije deklarisana",designatorVar);
				designatorVar.obj=Tab.noObj;
			}
		
		else
			{
				designatorVar.obj=desObj;
			}
    }
    
    @Override
   public void visit(DesignatorArrName designatorArrName )
   {
    	Obj  desObj = Tab.find(designatorArrName.getI1());
		if(desObj.equals(Tab.noObj)) 
		{
			report_error("Niz nije deklarisan",designatorArrName);
			designatorArrName.obj=Tab.noObj;
		}
		else
		{
			designatorArrName.obj=desObj;
		}
   }
		   @Override
		   public void visit(DesignatorElem designatorElem)
		   {
			     
			   Obj obj =designatorElem.getDesignatorArrName().obj;
			   
			   designatorElem.obj =new Obj(Obj.Elem,designatorElem.getDesignatorArrName().getI1(),obj.getType().getElemType());
			   			   			  								
					if(designatorElem.getExpr().struct!=Tab.intType)
					report_error("Izraz u zagradi [] nije tipa int",designatorElem);																							
					
		   }
		   
		   @Override
		   public void visit(DesignatorStatement_assignop_expr designatorStatement_assignop_expr)
		   {
			   if(designatorStatement_assignop_expr.getDesignator().obj.getKind()!=Obj.Var && designatorStatement_assignop_expr.getDesignator().obj.getKind()!=Obj.Elem  )
			   {	 
				   report_error("Pokusaj dodele objektu koji nije promenjiva ili element niza",designatorStatement_assignop_expr);
			 			return;   			   
			   }
			 
			    		if(designatorStatement_assignop_expr.getExpr().struct.getKind() != designatorStatement_assignop_expr.getDesignator().obj.getType().getKind())	
			    		{
			    						   		
			    			report_error("Dodela razlicitih tipova",designatorStatement_assignop_expr);	 
			        	
			    		
			    		}
			   			    		
		   }
		   
		  @Override
		  public void visit(Statement_print statement_print)
		  {
			  if(statement_print.getExpr().struct.equals(Tab.noType))
				  report_error("Pokusaj printa izraza nevalidnog tipa",statement_print);
		  }
    
		  @Override
		  public void visit(Statement_read statement_read)
		  {
			  if(statement_read.getDesignator().obj.getType().equals(Tab.noType))
				  report_error("Pokusaj citanja nevalidnog tipa",statement_read);
			  if(statement_read.getDesignator().obj.getKind()!=Obj.Var && statement_read.getDesignator().obj.getKind()!=Obj.Elem)
				  report_error("Pokusaj citanja objekta koji nije promenjiva ili element niza",statement_read);
			  
		  }
		  
		  public void visit(DesignatorStatement_minusminus designatorStatement_minusminus)
		  {
			  boolean arr=false;
			  if(designatorStatement_minusminus.getDesignator().obj.getKind()!=Obj.Var && designatorStatement_minusminus.getDesignator().obj.getKind()!=Obj.Elem )
				  report_error("Pokusaj operacije -- objekta koji nije promenjiva ili element niza",designatorStatement_minusminus);
			  else
			  {
				 
					  if(!designatorStatement_minusminus.getDesignator().obj.getType().equals(Tab.intType))
						  report_error("Pokusaj operacije -- ne int tipa",designatorStatement_minusminus);
				 
			  }
			  
		  }
		  
		  public void visit(DesignatorStatement_plusplus designatorStatement_plusplus)
		  {
			  
			  if(designatorStatement_plusplus.getDesignator().obj.getKind()!= Obj.Var && designatorStatement_plusplus.getDesignator().obj.getKind()!= Obj.Elem )
				  report_error("Pokusaj operacije ++ objekta koji nije promenjiva ili element niza",designatorStatement_plusplus);
			  else
			  {				  
					  if(!designatorStatement_plusplus.getDesignator().obj.getType().equals(Tab.intType))
						  report_error("Pokusaj operacije ++ ne int tipa",designatorStatement_plusplus);				 				  				  
			  }
			 
		  }
		  
		  
		  
		  @Override
		  public void visit(Statement_goto statement_goto)
		  {
			  labelsGoto.add(statement_goto.getLabel().getI1());
		  }
		  
		  @Override
		  public void visit(LabelColon labelColon)
		  {
			labelsInMeth.add(labelColon.getLabel().getI1());
		  }
		  
		  
		  
    public boolean passed(){
    	return !errorDetected;
    }
    
}
