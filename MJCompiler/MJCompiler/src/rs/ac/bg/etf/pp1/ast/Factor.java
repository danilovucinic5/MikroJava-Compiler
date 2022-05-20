// generated with ast extension for cup
// version 0.8
// 26/0/2022 16:37:4


package rs.ac.bg.etf.pp1.ast;

public class Factor implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private ExprMinus ExprMinus;
    private FactorLow FactorLow;

    public Factor (ExprMinus ExprMinus, FactorLow FactorLow) {
        this.ExprMinus=ExprMinus;
        if(ExprMinus!=null) ExprMinus.setParent(this);
        this.FactorLow=FactorLow;
        if(FactorLow!=null) FactorLow.setParent(this);
    }

    public ExprMinus getExprMinus() {
        return ExprMinus;
    }

    public void setExprMinus(ExprMinus ExprMinus) {
        this.ExprMinus=ExprMinus;
    }

    public FactorLow getFactorLow() {
        return FactorLow;
    }

    public void setFactorLow(FactorLow FactorLow) {
        this.FactorLow=FactorLow;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprMinus!=null) ExprMinus.accept(visitor);
        if(FactorLow!=null) FactorLow.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprMinus!=null) ExprMinus.traverseTopDown(visitor);
        if(FactorLow!=null) FactorLow.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprMinus!=null) ExprMinus.traverseBottomUp(visitor);
        if(FactorLow!=null) FactorLow.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Factor(\n");

        if(ExprMinus!=null)
            buffer.append(ExprMinus.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FactorLow!=null)
            buffer.append(FactorLow.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Factor]");
        return buffer.toString();
    }
}
