package rs.ac.bg.etf.pp1;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPc;
	
	public int getMainPc(){
		return mainPc;
	}
	
	@Override
	public void visit(Main main)
	{
		Code.put(Code.enter);
		Code.put(main.obj.getLevel());
		Code.put(main.obj.getLocalSymbols().size());
	}
	
	@Override
	public void visit(MethDecl methDecl)
	{
		Code.put(Code.exit);
		Code.put(Code.return_);
		
	}
	
	@Override
	public void visit(Factor_number factor_number)
	{
		Code.loadConst(factor_number.getN1());
	}
	
	@Override
	public void visit(Factor_char factor_char)
	{
		Code.loadConst(factor_char.getC1());
	}
	@Override
	public void visit(Factor_bool factor_bool)
	{
		if(factor_bool.getB1()==1)
			Code.put(Code.const_1);
		else
		{
			Code.put(15);
			
		}
	}
	@Override
	public void visit(Factor_designator factor_designator)
	{
		Code.load(factor_designator.getDesignator().obj);
	}
	@Override 
	public void visit (Factor_new factor_new)
	{
		Code.put(Code.newarray);
		if(factor_new.getType().struct.equals(Tab.charType))
			Code.put(0);
		else 
			Code.put(1);
	}
	
	@Override 
	public void visit(Factor factor)
	{
		if (factor.getExprMinus() instanceof ExprMinus_yes)
			Code.put(Code.neg);
	}
	
	@Override
	public void visit(Statement_read statement_read)
	{								
		 if(statement_read.getDesignator().obj.getType()==Tab.charType)
			Code.put(Code.bread);	
		 else
			 Code.put(Code.read);
		 
		Code.store(statement_read.getDesignator().obj);
	}
	
	@Override
	public void visit (Statement_print statement_print)
	{
		if(statement_print.getCommaNumConst() instanceof CommaNumConst_yes)
		{
			Code.loadConst(((CommaNumConst_yes)statement_print.getCommaNumConst()).getN1());
		}
		
		else
		{
			Code.loadConst(0);
		}
		
		if(statement_print.getExpr().struct.equals(Tab.charType))
		{
			Code.put(Code.bprint);
		}
		else
		{
			Code.put(Code.print);
		}
	}
	
	@Override
	public void visit(TermList_more termList_more)
	{
		if(termList_more.getMulop() instanceof Mulop_multiple)
			Code.put(Code.mul);
		
		else
			if(termList_more.getMulop() instanceof Mulop_divide)
			
				Code.put(Code.div);			
			else
				Code.put(Code.rem);
							
			
	}
	
	@Override
	public void visit (ExprMore_yes exprMore_yes)
	{
		if(exprMore_yes.getAddop() instanceof Addop_minus )
			Code.put(Code.sub);
			
		
		else
			Code.put(Code.add);
	}
	
	@Override
	public void visit(Statement_return statement_return)
	{
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(DesignatorStatement_assignop_expr designatorStatement_assignop_expr)
	{
		Code.store(designatorStatement_assignop_expr.getDesignator().obj);
	}
	
	@Override
	public void visit(DesignatorArrName designatorArrName)
	{
		Code.load(designatorArrName.obj);
		
	}
	@Override
	public void visit(DesignatorStatement_plusplus designatorStatement_plusplus)
	{
		
		if(designatorStatement_plusplus.getDesignator() instanceof DesignatorElem )
		{
			Code.put(Code.dup2);
			
		}
		Code.load(designatorStatement_plusplus.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(designatorStatement_plusplus.getDesignator().obj);
	}
	
	@Override
	public void visit(DesignatorStatement_minusminus designatorStatement_minusminus)
	{
		if(designatorStatement_minusminus.getDesignator() instanceof DesignatorElem )
		{
			Code.put(Code.dup2);
			
		}
		
		Code.load(designatorStatement_minusminus.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(designatorStatement_minusminus.getDesignator().obj);
	}

private Map<String,Integer> statementLabels=new HashMap<>();	
private Map<String,List<Integer>> gotoAdrs=new HashMap<>();	
	
@Override
public void visit(LabelColon labelColon)
{
	statementLabels.put(labelColon.getLabel().getI1(), Code.pc);	
	if(gotoAdrs.containsKey(labelColon.getLabel().getI1()))
		while(gotoAdrs.get(labelColon.getLabel().getI1()).size()>0)		
			Code.fixup(gotoAdrs.get(labelColon.getLabel().getI1()).remove(0));
		
}			

@Override
public void visit(Statement_goto statement_goto)
{
	if(statementLabels.containsKey(statement_goto.getLabel().getI1()))
	Code.putJump(statementLabels.get(statement_goto.getLabel().getI1()));
	else
	{
		Code.putJump(0);
		
		int wrongOffset=Code.pc-2;
		
		List<Integer> list;
		
		if(gotoAdrs.containsKey(statement_goto.getLabel().getI1()))

			list=gotoAdrs.get(statement_goto.getLabel().getI1());

		else
		{
			list=new ArrayList<Integer>();
			gotoAdrs.put(statement_goto.getLabel().getI1(), list);
		}
		
		list.add(wrongOffset);
	}

	
	/*
	 * 
	 * @Override
	public void visit(DesignatorStatement_hash designatorStatement_hash)
	{
				
		Code.load(designatorStatement_hash.getDesignator().obj);
		Code.loadConst(0);
		Code.put(Code.aload);
		Code.loadConst(-1);
		
		//petlja
		
		int pocetakPetlje=Code.pc;
		Code.loadConst(1);
		Code.put(Code.add);
		Code.put(Code.dup);	
		Code.load(designatorStatement_hash.getDesignator().obj);
		Code.put(Code.arraylength);
		Code.putFalseJump(Code.ne, 0);
		int zaFixup=Code.pc-2;
		
		Code.put(Code.dup2);
		Code.load(designatorStatement_hash.getDesignator().obj);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.put(Code.aload);
		Code.putFalseJump(Code.gt, 0);
		int zaFixup1=Code.pc-2;
		Code.putJump(pocetakPetlje);
		Code.fixup(zaFixup1);
		
		Code.put(Code.dup);
		Code.load(designatorStatement_hash.getDesignator().obj);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.put(Code.aload);
		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.put(Code.pop);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.putJump(pocetakPetlje);

		Code.fixup(zaFixup);
		
		
		Code.put(Code.pop);
		
	}
	
	
	
	
	
	
	@Override
	public void visit(DesignatorStatement_puta designatorStatement_puta)
	{
		Code.loadConst(-1);
		
		int startPetlje=Code.pc;
		
		Code.loadConst(1);
		Code.put(Code.add);
		Code.put(Code.dup);
		Code.load(designatorStatement_puta.getDesignator().obj);
		Code.put(Code.arraylength);
		Code.putFalseJump(Code.ne, 0);
		int fix=Code.pc-2;
		Code.put(Code.dup);
		Code.put(Code.dup);
		Code.load(designatorStatement_puta.getDesignator().obj);
		Code.put(Code.dup_x2);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.put(Code.aload);
		Code.loadConst(designatorStatement_puta.getN2());
		Code.put(Code.mul);
		Code.put(Code.astore);
		Code.putJump(startPetlje);
		Code.fixup(fix);
		
		
		
		
		
		
		
		
		
	}
	
	
	@Override
	public void visit(DesignatorStatement_assignop_expr designatorStatement_assignop_expr)
	{
		
		if (designatorStatement_assignop_expr.getDesignator().obj.getKind()==Obj.Elem)
		{
			Code.put(Code.dup_x2);
			Code.put(Code.pop);
			Code.put(Code.dup2);
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
			Code.put(Code.dup_x1);
			Code.put(Code.arraylength);
			Code.loadConst(2);
			Code.put(Code.div);
			Code.put(Code.add);
			Code.put(Code.dup2);
			Code.put(Code.aload);
			Code.loadConst(1);
			Code.putFalseJump(Code.ne, 0);
			int zafixup=Code.pc-2;
			Code.loadConst(1);
			Code.put(Code.astore);
			Code.put(Code.dup_x2);
			Code.put(Code.pop);
			Code.put(Code.dup_x2);
			Code.put(Code.pop);
			Code.put(Code.astore);
			Code.putJump(0);
			int zafixup1=Code.pc-2;
			Code.fixup(zafixup);
			Code.put(Code.trap);
			Code.fixup(zafixup1);
			}
		else
			
				Code.store(designatorStatement_assignop_expr.getDesignator().obj);
	}
	
	
	
	
	//adr n index_new 
	 * d2
	 * pop
	 * d2
	 * pop
	 * d2
	 * d1
	 * pop
	 * d1
	/adr n index_new adr index_new
	
	
	 * */
}
	
}
