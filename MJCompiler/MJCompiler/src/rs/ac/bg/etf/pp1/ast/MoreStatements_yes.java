// generated with ast extension for cup
// version 0.8
// 26/0/2022 16:37:4


package rs.ac.bg.etf.pp1.ast;

public class MoreStatements_yes extends MoreStatements {

    private Statement Statement;
    private MoreStatements MoreStatements;

    public MoreStatements_yes (Statement Statement, MoreStatements MoreStatements) {
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.MoreStatements=MoreStatements;
        if(MoreStatements!=null) MoreStatements.setParent(this);
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public MoreStatements getMoreStatements() {
        return MoreStatements;
    }

    public void setMoreStatements(MoreStatements MoreStatements) {
        this.MoreStatements=MoreStatements;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Statement!=null) Statement.accept(visitor);
        if(MoreStatements!=null) MoreStatements.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(MoreStatements!=null) MoreStatements.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(MoreStatements!=null) MoreStatements.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MoreStatements_yes(\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MoreStatements!=null)
            buffer.append(MoreStatements.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MoreStatements_yes]");
        return buffer.toString();
    }
}
