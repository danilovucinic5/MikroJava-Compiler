// generated with ast extension for cup
// version 0.8
// 26/0/2022 16:37:4


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(MoreVarDeclList MoreVarDeclList);
    public void visit(Mulop Mulop);
    public void visit(Relop Relop);
    public void visit(TermList TermList);
    public void visit(Addop Addop);
    public void visit(Designator Designator);
    public void visit(Brackets Brackets);
    public void visit(ConVarDeclList ConVarDeclList);
    public void visit(FactorLow FactorLow);
    public void visit(CommaNumConst CommaNumConst);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(Const Const);
    public void visit(ConDeclMore ConDeclMore);
    public void visit(ExprMore ExprMore);
    public void visit(ExprMinus ExprMinus);
    public void visit(Statement Statement);
    public void visit(VarDeclMore VarDeclMore);
    public void visit(SingleStatement SingleStatement);
    public void visit(MoreStatements MoreStatements);
    public void visit(Mulop_percent Mulop_percent);
    public void visit(Mulop_divide Mulop_divide);
    public void visit(Mulop_multiple Mulop_multiple);
    public void visit(Addop_minus Addop_minus);
    public void visit(Addop_plus Addop_plus);
    public void visit(Relop_lessorequal Relop_lessorequal);
    public void visit(Relop_less Relop_less);
    public void visit(Relop_gratherorequal Relop_gratherorequal);
    public void visit(Relop_grather Relop_grather);
    public void visit(Relop_notequal Relop_notequal);
    public void visit(Relop_dblequal Relop_dblequal);
    public void visit(Assignop Assignop);
    public void visit(Label Label);
    public void visit(DesignatorArrName DesignatorArrName);
    public void visit(DesignatorElem DesignatorElem);
    public void visit(DesignatorVar DesignatorVar);
    public void visit(Factor_expr Factor_expr);
    public void visit(Factor_new Factor_new);
    public void visit(Factor_bool Factor_bool);
    public void visit(Factor_char Factor_char);
    public void visit(Factor_number Factor_number);
    public void visit(Factor_designator Factor_designator);
    public void visit(Factor Factor);
    public void visit(TermList_first TermList_first);
    public void visit(TermList_more TermList_more);
    public void visit(Term Term);
    public void visit(ExprMinus_e ExprMinus_e);
    public void visit(ExprMinus_yes ExprMinus_yes);
    public void visit(ExprMore_first ExprMore_first);
    public void visit(ExprMore_yes ExprMore_yes);
    public void visit(Expr Expr);
    public void visit(DesignatorStatement_minusminus DesignatorStatement_minusminus);
    public void visit(DesignatorStatement_plusplus DesignatorStatement_plusplus);
    public void visit(DesignatorStatement_assignop_expr DesignatorStatement_assignop_expr);
    public void visit(CommaNumConst_e CommaNumConst_e);
    public void visit(CommaNumConst_yes CommaNumConst_yes);
    public void visit(Statement_goto Statement_goto);
    public void visit(Statement_print Statement_print);
    public void visit(Statement_read Statement_read);
    public void visit(Statement_return Statement_return);
    public void visit(Statement_designator Statement_designator);
    public void visit(LabelColon LabelColon);
    public void visit(Statement_more Statement_more);
    public void visit(Statement_single Statement_single);
    public void visit(Statement_single_label Statement_single_label);
    public void visit(MoreStatements_e MoreStatements_e);
    public void visit(MoreStatements_yes MoreStatements_yes);
    public void visit(Statements Statements);
    public void visit(MoreVarDeclList_e MoreVarDeclList_e);
    public void visit(MoreVarDeclList_yes MoreVarDeclList_yes);
    public void visit(Main Main);
    public void visit(MethDecl MethDecl);
    public void visit(Type Type);
    public void visit(VarDecl VarDecl);
    public void visit(VarDeclMore_e VarDeclMore_e);
    public void visit(VarDeclMore_comma VarDeclMore_comma);
    public void visit(Brackets_e Brackets_e);
    public void visit(Brackets_yes Brackets_yes);
    public void visit(VarDeclList VarDeclList);
    public void visit(Const_b Const_b);
    public void visit(Const_c Const_c);
    public void visit(Const_n Const_n);
    public void visit(ConDeclMore_e ConDeclMore_e);
    public void visit(ConDeclMore_comma ConDeclMore_comma);
    public void visit(ConDecl ConDecl);
    public void visit(ConDeclList ConDeclList);
    public void visit(ConVarDeclList_e ConVarDeclList_e);
    public void visit(ConVarDeclList_var ConVarDeclList_var);
    public void visit(ConVarDeclList_con ConVarDeclList_con);
    public void visit(ProgramName ProgramName);
    public void visit(Program Program);

}
